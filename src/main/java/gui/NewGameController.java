package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.io.IOException;

//CHECKSTYLE:OFF
public class NewGameController
{
    private static Stage primaryStage;

    public static void setController(Stage stage)
    {
        primaryStage = stage;
    }


    @FXML
    private Label descLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private void initialize()
    {
        descLabel.setText("The game is played on a 11x11 board. The RED player's goal is to make a horizontal RED line of fields, while the BLUE player's goal is to make a vertical one.");
        newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                try
                {
                    Parent newGame = UILoader.fxmlLoader(getClass().getResource("/gameboardui.fxml"));

                    primaryStage.setTitle("Game");
                    BoardGameController.setController(primaryStage);
                    primaryStage.setScene(new Scene(newGame));
                } catch (IOException e)
                {
                    throw new RuntimeException("Could not find boardgameui.fxml!", e);
                }
            }
        });
    }

}
