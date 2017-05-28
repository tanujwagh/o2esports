package fxsampler.util;

import fxsampler.FXSamplerProject;
import fxsampler.Sample;
import fxsampler.model.EmptySample;
import fxsampler.model.Project;
import fxsampler.model.WelcomePage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

public class SampleScanner {
    private static List<String> ILLEGAL_CLASS_NAMES = new ArrayList<String>();
    private static Map<String, FXSamplerProject> packageToProjectMap;
    private final Map<String, Project> projectsMap = new HashMap<String, Project>();

    public Map<String, Project> discoverSamples() {
        Class[] results = new Class[]{};
        try {
            results = this.loadFromPathScanning();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (Class sampleClass : results) {
            if (!Sample.class.isAssignableFrom(sampleClass) || sampleClass.isInterface() || Modifier.isAbstract(sampleClass.getModifiers()) || sampleClass == EmptySample.class) continue;
            Sample sample = null;
            try {
                sample = (Sample)sampleClass.newInstance();
            }
            catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            if (sample == null || !sample.isVisible()) continue;
            String packageName = sampleClass.getPackage().getName();
            for (String key : packageToProjectMap.keySet()) {
                Project project;
                if (!packageName.contains(key)) continue;
                String prettyProjectName = packageToProjectMap.get(key).getProjectName();
                if (!this.projectsMap.containsKey(prettyProjectName)) {
                    project = new Project(prettyProjectName, key);
                    project.setWelcomePage(packageToProjectMap.get(key).getWelcomePage());
                    this.projectsMap.put(prettyProjectName, project);
                } else {
                    project = this.projectsMap.get(prettyProjectName);
                }
                project.addSample(packageName, sample);
            }
        }
        return this.projectsMap;
    }

    private Class<?>[] loadFromPathScanning() throws ClassNotFoundException, IOException {
        ArrayList<File> dirs = new ArrayList<File>();
        ArrayList<File> jars = new ArrayList<File>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = "";
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            int sep;
            String fileName;
            URL url = resources.nextElement();
            if (url.toExternalForm().contains("/jre/")) continue;
            String protocol = url.getProtocol().toLowerCase();
            if ("file".equals(protocol)) {
                dirs.add(new File(url.getFile()));
                continue;
            }
            if (!"jar".equals(protocol) || (sep = (fileName = new URL(url.getFile()).getFile()).indexOf("!/")) <= 0) continue;
            jars.add(new File(fileName.substring(0, sep)));
        }
        Path workingDirectory = new File("").toPath();
        this.scanPath(workingDirectory, dirs, jars);
        LinkedHashSet classes = new LinkedHashSet();
        for (File directory : dirs) {
            classes.addAll(this.findClassesInDirectory(directory));
        }
        for (File jar : jars) {
            String fullPath = jar.getAbsolutePath();
            if (fullPath.endsWith("jfxrt.jar")) continue;
            classes.addAll(this.findClassesInJar(new File(fullPath)));
        }
        return (Class[])classes.toArray(new Class[classes.size()]);
    }

    private void scanPath(Path workingDirectory, final List<File> dirs, final List<File> jars) throws IOException {
        Files.walkFileTree(workingDirectory, new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                File file = path.toFile();
                String fullPath = file.getAbsolutePath();
                String name = file.toString();
                if (fullPath.endsWith("jfxrt.jar") || name.contains("jre")) {
                    return FileVisitResult.CONTINUE;
                }
                if (file.isDirectory()) {
                    dirs.add(file);
                } else if (name.toLowerCase().endsWith(".jar")) {
                    jars.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException ex) {
                System.err.println(ex + " Skipping...");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private List<Class<?>> findClassesInDirectory(File directory) throws IOException {
        ArrayList classes = new ArrayList();
        if (!directory.exists()) {
            System.out.println("Directory does not exist: " + directory.getAbsolutePath());
            return classes;
        }
        this.processPath(directory.toPath(), classes);
        return classes;
    }

    private List<Class<?>> findClassesInJar(File jarFile) throws IOException, ClassNotFoundException {
        ArrayList classes = new ArrayList();
        if (!jarFile.exists()) {
            System.out.println("Jar file does not exist here: " + jarFile.getAbsolutePath());
            return classes;
        }
        FileSystem jarFileSystem = FileSystems.newFileSystem(jarFile.toPath(), null);
        this.processPath(jarFileSystem.getPath("/", new String[0]), classes);
        return classes;
    }

    private void processPath(Path path, final List<Class<?>> classes) throws IOException {
        final String root = path.toString();
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Class clazz;
                String name = file.toString();
                if (name.endsWith(".class") && !ILLEGAL_CLASS_NAMES.contains(name) && (clazz = SampleScanner.this.processClassName(name = name.substring(root.length()))) != null) {
                    classes.add(clazz);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException ex) {
                System.err.println(ex + " Skipping...");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private Class<?> processClassName(String name) {
        String className = name.replace("\\", ".");
        if ((className = className.replace("/", ".")).contains("$")) {
            return null;
        }
        if (className.contains(".bin")) {
            className = className.substring(className.indexOf(".bin") + 4);
            className = className.replace(".bin", "");
        }
        if (className.startsWith(".")) {
            className = className.substring(1);
        }
        if (className.endsWith(".class")) {
            className = className.substring(0, className.length() - 6);
        }
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        }
        catch (Throwable var4_4) {
            // empty catch block
        }
        return clazz;
    }

    static {
        ILLEGAL_CLASS_NAMES.add("/com/javafx/main/Main.class");
        ILLEGAL_CLASS_NAMES.add("/com/javafx/main/NoJavaFXFallback.class");
        packageToProjectMap = new HashMap<String, FXSamplerProject>();
        System.out.println("Initialising FXSampler sample scanner...");
        System.out.println("\tDiscovering projects...");
        ServiceLoader<FXSamplerProject> loader = ServiceLoader.load(FXSamplerProject.class);
        for (FXSamplerProject project : loader) {
            String projectName = project.getProjectName();
            String basePackage = project.getSampleBasePackage();
            packageToProjectMap.put(basePackage, project);
            System.out.println("\t\tFound project '" + projectName + "', with sample base package '" + basePackage + "'");
        }
        if (packageToProjectMap.isEmpty()) {
            System.out.println("\tError: Did not find any projects!");
        }
    }

}