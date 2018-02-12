package com.javafx.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class MainSnowmanPaint extends Application{

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 700;
    private static final int RGB_SYMBOLS_COUNT = 6;

    private static Scanner sc = new Scanner(System.in);
    private static Random r = new Random();

    public static Circle generateCircle(double maxRad, double minRad, double floorY){

            double radius = r.nextDouble() * (maxRad-minRad) + minRad;

            double x = WINDOW_WIDTH / 2;
            double y = floorY - radius;

            Circle c = new Circle(x, y, radius);

            c.setCenterX(x);
            c.setCenterY(y);

            c.setStrokeWidth(5);
            c.setStroke(generateRandomColor());
            c.setFill(Paint.valueOf("#00000000"));

            return  c;
    }

    public static Circle generateRightEye(Circle face){
        double r = face.getRadius();
        double halfR = r / 2;
        double x = face.getCenterX() + halfR;
        double y = face.getCenterY() - halfR;
        double eyeRadius = MainSnowmanPaint.r.nextDouble() * face.getRadius() / 4;

        Circle c = new Circle();
        c.setRadius(eyeRadius);
        c.setCenterX(x);
        c.setCenterY(y);

        c.setStrokeWidth(2);
        c.setStroke(generateRandomColor());
        c.setFill(Paint.valueOf("#00000000"));

        return c;
    }

    public static Circle generateLeftEye(Circle face){
        double r = face.getRadius();
        double halfR = r / 2;
        double x = face.getCenterX() - halfR;
        double y = face.getCenterY() - halfR;
        double eyeRadius = MainSnowmanPaint.r.nextDouble() * face.getRadius() / 4;

        Circle c = new Circle();
        c.setRadius(eyeRadius);
        c.setCenterX(x);
        c.setCenterY(y);

        c.setStrokeWidth(2);
        c.setStroke(generateRandomColor());
        c.setFill(Paint.valueOf("#00000000"));

        return c;
    }

    public static Circle generateMouth(Circle face){
        double r = face.getRadius();
        double halfR = r / 2;
        double x = face.getCenterX();
        double y = face.getCenterY() + halfR;
        double mouthRadius =  MainSnowmanPaint.r.nextDouble() * face.getRadius() / 4;

        Circle c = new Circle();
        c.setRadius(mouthRadius);
        c.setCenterX(x);
        c.setCenterY(y);

        c.setStrokeWidth(2);
        c.setStroke(generateRandomColor());
        c.setFill(Paint.valueOf("#00000000"));

        return c;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));

        System.out.println("quantity of circles ..");
        int quantity = sc.nextInt();

        System.out.println("set minimum radius ..");
        int minRad = sc.nextInt();

        System.out.println("set maximum radius ..");
        int maxRad = sc.nextInt();

        double floorY = WINDOW_HEIGHT;

        Circle firstCircle = generateCircle(maxRad, minRad, floorY);

        floorY = floorY - (int)firstCircle.getRadius() * 2 - firstCircle.getStrokeWidth();

        root.getChildren().addAll(firstCircle);

        Circle oldCircle = firstCircle;

        for (int i = 1; i < quantity; i++){
            oldCircle = generateCircle(maxRad, minRad, floorY);

            floorY -= oldCircle.getRadius() * 2 + oldCircle.getStrokeWidth();
            root.getChildren().addAll(oldCircle);
        }

        Circle rightEye = generateRightEye(oldCircle);
        Circle leftEye = generateLeftEye(oldCircle);
        Circle mouth = generateMouth(oldCircle);

        root.getChildren().addAll(rightEye, leftEye, mouth);

        primaryStage.show();
    }
    private static Paint generateRandomColor() {
        String rgb = "#";
        for(int i = 0; i < RGB_SYMBOLS_COUNT; i++) {
            rgb += (char)('0' + r.nextInt(9));
        }
        return Paint.valueOf(rgb);
    }

    public static void main(String[] args) {
        launch(args);
    }
}