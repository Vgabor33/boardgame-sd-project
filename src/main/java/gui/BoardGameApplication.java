package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

//CHECKSTYLE:OFF
public class BoardGameApplication extends Application
{
    @Override
    public void start(Stage stage)
    {
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome!");
        Label desc = new Label("The game is played on a 11x11 board. \n The RED player's goal is to make a horizontal RED line of fields, \n while the BLUE player's goal is to make a vertical one.");
        Button button1 = new Button("Start game!");
        layout1.getChildren().addAll(welcomeLabel, button1,desc);
        Scene sceneStart = new Scene(layout1, 500, 250);
        stage.setTitle("Starting new game");
        stage.setScene(sceneStart);
        stage.setResizable(false);
        stage.show();
    }
}
