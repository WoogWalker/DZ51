package com.javafx.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Scanner;

public class MainStarPaint extends Application {

    Scanner sc = new Scanner(System.in);

    public Line[] drawStar(){
        System.out.println("set X ..");
        int x = sc.nextInt();

        System.out.println("set Y ..");
        int y = sc.nextInt();

        System.out.println("set radius ..");
        int r = sc.nextInt();

        int halfR = r / 2;

        float step = 72;

        Light.Point c = new Light.Point(x, y, 0, Color.BLACK);

        Line[] lines = new Line[10];

        for (int i = 0; i < 10; i+=2){
            lines[i] = new Line (getPosition(c, r, step * i).getX(), getPosition(c, r, step * i).getY(),
                    getPosition(c, halfR, step*(i + 0.5f)).getX(), getPosition(c, halfR, step*(i + 0.5f)).getY());

            lines[i+1] = new Line (lines[i].getEndX(), lines[i].getEndY(),
                    getPosition(c, r, step*(i+1)).getX(), getPosition(c, r, step*(i+1)).getY());
        }

        return  lines;
    }

    Light.Point getPosition(Light.Point center, float radius, float angle) {
        Light.Point p = new Light.Point(
                center.getX() + radius * Math.cos(Math.toRadians(angle)),
                center.getY() + radius * Math.sin(Math.toRadians(angle)),
                0,
                Color.BLACK);

        return p;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        Line [] lines = drawStar();

        root.getChildren().addAll(lines);
    }

    public static void main(String[] args) {
        launch(args);
    }
}