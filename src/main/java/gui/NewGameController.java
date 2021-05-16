package gui;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.tinylog.Logger;


import java.io.IOException;

import static gui.EndGameController.setBlueName;
import static gui.EndGameController.setRedName;

//CHECKSTYLE:OFF
public class NewGameController
{
    private static Stage primaryStage;

    public static void setController(Stage stage)
    {
        primaryStage = stage;
    }


    @FXML
    private TextFlow redAskerFlow;

    @FXML
    private TextFlow blueAskerFlow;

    @FXML
    private TextField redNameField;

    @FXML
    private TextField blueNameField;

    @FXML
    private CheckBox starterPlayerCheckBox;

    @FXML
    private Label descLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private void initialize()
    {
        descLabel.setText("Welcome! \n The game is played on a 11x11 board. The RED player's goal is to make a horizontal RED line of fields, while the BLUE player's goal is to make a vertical one.");
        Text rednameAsker = new Text("'s name");
        Text bluenameAsker = new Text("'s name");
        Text redAsker = new Text("RED Player");
        redAsker.setFill(Color.RED);
        Text blueAsker = new Text("BLUE Player");
        blueAsker.setFill(Color.BLUE);
        redAskerFlow.getChildren().addAll(redAsker,rednameAsker);
        blueAskerFlow.getChildren().addAll(blueAsker,bluenameAsker);
        starterPlayerCheckBox.setSelected(true);

        newGameButton.setOnMouseClicked(this::handleClick);
    }

    private void handleClick(MouseEvent mouseEvent)
    {
        if(isAllSet())
        {
            switchScene();
        }
        else
        {
            Logger.info("Not all player's name set. Please set both player's name!");
            notSetWarning("Player names are not set!", "Could not start the game!","Set both player's name to start!");
        }
    }

    private static void notSetWarning(String infoMessage, String title, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    private void switchScene()
    {
        try
        {
            Logger.info("Switching to game screen!");
            Parent newGame = UILoader.fxmlLoader(getClass().getResource("/gameboardui.fxml"));

            primaryStage.setTitle("Game");
            BoardGameController.setController(primaryStage);
            setBlueName(blueNameField.getText());
            setRedName(redNameField.getText());
            setStartingPlayer();
            primaryStage.setScene(new Scene(newGame));
        } catch (IOException e)
        {
            throw new RuntimeException("Could not find boardgameui.fxml!", e);
        }
    }
    private boolean isAllSet()
    {
        if(!isRedNameSet())
        {
            return false;
        }
        if(!isBlueNameSet())
        {
            return false;
        }
        return true;
    }

    private boolean isRedNameSet()
    {
        if(redNameField.getText().trim().isEmpty())
        {
            Logger.info("Red player's name isn't set!");
            return false;
        }
        return true;
    }
    private boolean isBlueNameSet()
    {
        if(blueNameField.getText().trim().isEmpty())
        {
            Logger.info("Blue player's name isn't set!");
            return false;
        }
        return true;
    }
    private void setStartingPlayer()
    {
        if(starterPlayerCheckBox.isSelected())
        {
            Logger.info("Red starts!");
            player.PlayerState.setStartingPlayer(true);
        }
        else
        {
            Logger.info("Blue starts!");
            player.PlayerState.setStartingPlayer(false);
        }
    }
}