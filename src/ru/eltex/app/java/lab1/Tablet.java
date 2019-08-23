package ru.eltex.app.java.lab1;

import java.awt.*;
import java.util.Scanner;

public class Tablet extends Device {

    private String videoProcessor;                        // видеопроцессор
    private Dimension screenResolution = new Dimension(); // разрешение экрана

    public Tablet() {

    }

    Tablet(String message) {
        super();
        System.out.println(message);
    }

    public void setVideoProcessor(String videoProcessor) {
        this.videoProcessor = videoProcessor;
    }

    public void setScreenResolution(Dimension screenResolution) {
        this.screenResolution = screenResolution;
    }

    @Override
    public void create() {
        super.create();
        videoProcessor = getRandom(new String[]{"vp1", "vp2", "vp3"});
        screenResolution.setSize(getRandom(new Integer[]{450, 550, 650}), getRandom(new Integer[]{120, 220, 320}));
    }

    @Override
    public void read() {
        super.read();
        System.out.print("\nвидеопроцессор:\t\t " + videoProcessor + "\nразрешение экрана:\t ");
        System.out.println(screenResolution.width + "x" + screenResolution.height + "\n");
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        super.update();
        System.out.print("видеопроцессор: ");
        videoProcessor = sc.nextLine();
        System.out.print("разрешение экрана (в формате <ширина>x<высота>): ");
        String str = sc.nextLine();
        screenResolution.width = Integer.parseInt(((str).split("x"))[0]);
        screenResolution.height = Integer.parseInt(((str).split("x"))[1]);
    }

    @Override
    public void delete() {
        super.delete();
        videoProcessor = "";
        screenResolution.width = 0;
        screenResolution.height = 0;
    }

}