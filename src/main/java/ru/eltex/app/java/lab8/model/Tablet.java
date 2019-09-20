package ru.eltex.app.java.lab8.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.util.List;

@Entity
public class Tablet extends Device {

    @NotBlank
    private String videoProcessor;

    @NotNull
    private Dimension screenResolution;

    public Tablet() {
    }

    public Tablet(String videoProcessor, Dimension screenResolution,
                  String name, double price, String firm, String model, String os, List<ShoppingCart> carts) {
        super(name, price, firm, model, os, carts);
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