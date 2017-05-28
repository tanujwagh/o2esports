package fxsampler;

import fxsampler.model.EmptySample;
import fxsampler.model.Project;
import fxsampler.model.SampleTree;
import fxsampler.model.WelcomePage;
import fxsampler.util.SampleScanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.ServiceLoader;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXSampler
        extends Application {

    private Map<String, Project> projectsMap;
    private Stage stage;
    private GridPane grid;
    private Sample selectedSample;
    private TreeView<Sample> samplesTreeView;
    private TreeItem<Sample> root;
    private TabPane tabPane;
    private Tab welcomeTab;
    private Tab sampleTab;
    private Tab javaDocTab;
    private Tab sourceTab;
    private Tab cssTab;
    private WebView javaDocWebView;
    private WebView sourceWebView;
    private WebView cssWebView;

    public static void main(String[] args) {
        FXSampler.launch((String[]) args);
    }

    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        ServiceLoader<FXSamplerConfiguration> configurationServiceLoader = ServiceLoader.load(FXSamplerConfiguration.class);
        this.projectsMap = new SampleScanner().discoverSamples();
        this.buildSampleTree(null);
        this.grid = new GridPane();
        this.grid.setPadding(new Insets(5.0, 10.0, 10.0, 10.0));
        this.grid.setHgap(10.0);
        this.grid.setVgap(10.0);
        final TextField searchBox = new TextField();
        searchBox.setPromptText("Search");
        searchBox.getStyleClass().add("search-box");
        searchBox.textProperty().addListener(new InvalidationListener() {

            public void invalidated(Observable o) {
                FXSampler.this.buildSampleTree(searchBox.getText());
            }
        });
        GridPane.setMargin((Node) searchBox, (Insets) new Insets(5.0, 0.0, 0.0, 0.0));
        this.grid.add((Node) searchBox, 0, 0);
        this.samplesTreeView = new TreeView(this.root);
        this.samplesTreeView.setShowRoot(false);
        this.samplesTreeView.getStyleClass().add("samples-tree");
        this.samplesTreeView.setMinWidth(200.0);
        this.samplesTreeView.setMaxWidth(200.0);
        this.samplesTreeView.setCellFactory(new Callback<TreeView<Sample>, TreeCell<Sample>>() {
            @Override
            public TreeCell<Sample> call(TreeView<Sample> param) {
                return new TreeCell<Sample>() {
                    @Override
                    protected void updateItem(Sample item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setText("");
                        } else {
                            this.setText(item.getSampleName());
                        }
                    }
                };
            }
        });
        this.samplesTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Sample>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Sample>> observable, TreeItem<Sample> oldValue, TreeItem<Sample> newSample) {
                if (newSample == null) {
                    return;
                }
                if (newSample.getValue() instanceof EmptySample) {
                    Sample selectedSample = (Sample) newSample.getValue();
                    Project selectedProject = (Project) FXSampler.this.projectsMap.get(selectedSample.getSampleName());
                    if (selectedProject != null) {
                        FXSampler.this.changeToWelcomeTab(selectedProject.getWelcomePage());
                    }
                    return;
                }
                FXSampler.this.selectedSample = (Sample) newSample.getValue();
                FXSampler.this.changeSample();
            }
        });
        GridPane.setVgrow(this.samplesTreeView, (Priority) Priority.ALWAYS);
        this.grid.add(this.samplesTreeView, 0, 1);
        this.tabPane = new TabPane();
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.tabPane.getStyleClass().add("floating");
        this.tabPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                FXSampler.this.updateTab();
            }
        });
        GridPane.setHgrow((Node) this.tabPane, (Priority) Priority.ALWAYS);
        GridPane.setVgrow((Node) this.tabPane, (Priority) Priority.ALWAYS);
        this.grid.add((Node) this.tabPane, 1, 0, 1, 2);
        this.sampleTab = new Tab("Sample");
        this.javaDocTab = new Tab("JavaDoc");
        this.javaDocWebView = new WebView();
        this.javaDocTab.setContent((Node) this.javaDocWebView);
        this.sourceTab = new Tab("Source");
        this.sourceWebView = new WebView();
        this.sourceTab.setContent((Node) this.sourceWebView);
        this.cssTab = new Tab("Css");
        this.cssWebView = new WebView();
        this.cssTab.setContent((Node) this.cssWebView);
        ObservableList projects = this.samplesTreeView.getRoot().getChildren();
        if (!projects.isEmpty()) {
            TreeItem firstProject = (TreeItem) projects.get(0);
            this.samplesTreeView.getSelectionModel().select(firstProject);
        } else {
            this.changeToWelcomeTab(null);
        }
        Scene scene = new Scene((Parent) this.grid);
        scene.getStylesheets().add(this.getClass().getClassLoader().getResource("css/fxsampler.css").toExternalForm());
        for (FXSamplerConfiguration fxsamplerConfiguration : configurationServiceLoader) {
            String stylesheet = fxsamplerConfiguration.getSceneStylesheet();
            if (stylesheet == null) {
                continue;
            }
            scene.getStylesheets().add(stylesheet);
        }
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000.0);
        primaryStage.setMinHeight(600.0);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth() * 0.75);
        primaryStage.setHeight(screenBounds.getHeight() * 0.75);
        primaryStage.setTitle("FXSampler!");
        primaryStage.show();
        this.samplesTreeView.requestFocus();
    }

    protected void buildSampleTree(String searchText) {
        this.root = new TreeItem((Object) new EmptySample("FXSampler"));
        this.root.setExpanded(true);
        for (String projectName : this.projectsMap.keySet()) {
            Project project = this.projectsMap.get(projectName);
            if (project == null) {
                continue;
            }
            SampleTree.TreeNode n = project.getSampleTree().getRoot();
            this.root.getChildren().add(n.createTreeItem());
        }
        if (searchText != null) {
            this.pruneSampleTree(this.root, searchText);
            this.samplesTreeView.setRoot(null);
            this.samplesTreeView.setRoot(this.root);
        }
        this.sort(this.root, (o1, o2) -> ((Sample) o1.getValue()).getSampleName().compareTo(((Sample) o2.getValue()).getSampleName()));
    }

    private void sort(TreeItem<Sample> node, Comparator<TreeItem<Sample>> comparator) {
        node.getChildren().sort(comparator);
        for (TreeItem child : node.getChildren()) {
            this.sort(child, comparator);
        }
    }

    private boolean pruneSampleTree(TreeItem<Sample> treeItem, String searchText) {
        if (searchText == null) {
            return true;
        }
        if (treeItem.isLeaf()) {
            return ((Sample) treeItem.getValue()).getSampleName().toUpperCase().contains(searchText.toUpperCase());
        }
        ArrayList<TreeItem> toRemove = new ArrayList<TreeItem>();
        for (TreeItem child : treeItem.getChildren()) {
            boolean keep = this.pruneSampleTree(child, searchText);
            if (keep) {
                continue;
            }
            toRemove.add(child);
        }
        treeItem.getChildren().removeAll(toRemove);
        return !treeItem.getChildren().isEmpty();
    }

    protected void changeSample() {
        if (this.selectedSample == null) {
            return;
        }
        if (this.tabPane.getTabs().contains((Object) this.welcomeTab)) {
            this.tabPane.getTabs().setAll(new Tab[]{this.sampleTab, this.javaDocTab, this.sourceTab, this.cssTab});
        }
        this.updateTab();
    }

    private void updateTab() {
        Tab selectedTab = (Tab) this.tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == this.sampleTab) {
            this.sampleTab.setContent(this.buildSampleTabContent(this.selectedSample));
        } else if (selectedTab == this.javaDocTab) {
            this.javaDocWebView.getEngine().load(this.selectedSample.getJavaDocURL());
        } else if (selectedTab == this.sourceTab) {
            this.sourceWebView.getEngine().loadContent(this.formatSourceCode(this.selectedSample));
        } else if (selectedTab == this.cssTab) {
            this.cssWebView.getEngine().loadContent(this.formatCss(this.selectedSample));
        }
    }

    private String getResource(String resourceName, Class<?> baseClass) {
        Class clz = baseClass == null ? this.getClass() : baseClass;
        return this.getResource(clz.getClassLoader().getResourceAsStream(resourceName));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String getResource(InputStream is) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Throwable throwable = null;
            try {
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                String string = sb.toString();
                return string;
            } catch (Throwable line) {
                throwable = line;
                throw line;
            } finally {
                if (br != null) {
                    if (throwable != null) {
                        try {
                            br.close();
                        } catch (Throwable var7_9) {
                            throwable.addSuppressed(var7_9);
                        }
                    } else {
                        br.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getSourceCode(Sample sample) {
        String sourceURL = sample.getSampleSourceURL();
        try {
            URL url = new URL(sourceURL);
            InputStream is = url.openStream();
            return this.getResource(is);
        } catch (IOException url) {
            return this.getResource(sourceURL, sample.getClass());
        }
    }

    private String formatSourceCode(Sample sample) {
        String src;
        String sourceURL = sample.getSampleSourceURL();
        if (sourceURL == null) {
            src = "No sample source available";
        } else {
            src = "Sample Source not found";
            try {
                src = this.getSourceCode(sample);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        src = src.replace("<", "&lt;");
        String template = this.getResource("/fxsampler/util/SourceCodeTemplate.html", null);
        return template.replace("<source/>", src);
    }

    private String formatCss(Sample sample) {
        String src;
        String cssUrl = sample.getControlStylesheetURL();
        if (cssUrl == null) {
            src = "No CSS source available";
        } else {
            src = "Css not found";
            try {
                src = new String(Files.readAllBytes(Paths.get(this.getClass().getClassLoader().getResource(cssUrl).toURI())));
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        src = src.replace("<", "&lt;");
        String template = this.getResource("/fxsampler/util/CssTemplate.html", null);
        return template.replace("<source/>", src);
    }

    private Node buildSampleTabContent(Sample sample) {
        return SampleBase.buildSample(sample, this.stage);
    }

    private void changeToWelcomeTab(WelcomePage wPage) {
        if (null == wPage) {
            wPage = this.getDefaultWelcomePage();
        }
        this.welcomeTab = new Tab(wPage.getTitle());
        this.welcomeTab.setContent(wPage.getContent());
        this.tabPane.getTabs().setAll(new Tab[]{this.welcomeTab});
    }

    private WelcomePage getDefaultWelcomePage() {
        Label welcomeLabel1 = new Label("Welcome to FXSampler!");
        welcomeLabel1.setStyle("-fx-font-size: 2em; -fx-padding: 0 0 0 5;");
        Label welcomeLabel2 = new Label("Explore the available UI controls and other interesting projects by clicking on the options to the left.");
        welcomeLabel2.setStyle("-fx-font-size: 1.25em; -fx-padding: 0 0 0 5;");
        WelcomePage wPage = new WelcomePage("Welcome!", (Node) new VBox(5.0, new Node[]{welcomeLabel1, welcomeLabel2}));
        return wPage;
    }

    public final GridPane getGrid() {
        return this.grid;
    }

    public final TabPane getTabPane() {
        return this.tabPane;
    }

    public final Tab getWelcomeTab() {
        return this.welcomeTab;
    }

    public final Tab getSampleTab() {
        return this.sampleTab;
    }

    public final Tab getJavaDocTab() {
        return this.javaDocTab;
    }

    public final Tab getSourceTab() {
        return this.sourceTab;
    }

    public final Tab getCssTab() {
        return this.cssTab;
    }

}
