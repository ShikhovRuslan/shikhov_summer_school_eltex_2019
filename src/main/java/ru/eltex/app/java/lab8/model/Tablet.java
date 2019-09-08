package ru.eltex.app.java.lab8.model;

import javax.validation.constraints.NotNull;
import java.awt.*;

public class Tablet extends Device {

    @NotNull
    private String videoProcessor;

    @NotNull
    private Dimension screenResolution;

    public Tablet() {

    }

    public Tablet(String videoProcessor, Dimension screenResolution) {
        super();
        this.videoProcessor = videoProcessor;
        this.screenResolution = screenResolution;
    }

    public String getVideoProcessor() {
        return videoProcessor;
    }

    public void setVideoProcessor(String videoProcessor) {
        this.videoProcessor = videoProcessor;
    }

    public Dimension getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(Dimension screenResolution) {
        this.screenResolution = screenResolution;
    }

}