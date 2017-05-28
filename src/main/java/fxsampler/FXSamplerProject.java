package fxsampler;

import fxsampler.model.WelcomePage;

public interface FXSamplerProject {
    public String getProjectName();

    public String getSampleBasePackage();

    public WelcomePage getWelcomePage();
}