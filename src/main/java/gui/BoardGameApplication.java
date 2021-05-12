package gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//CHECKSTYLE:OFF
public class BoardGameApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
      Parent newGame = UILoader.fxmlLoader(getClass().getResource("/startui.fxml"));

        stage.setTitle("Start a new game");
        NewGameController.setController(stage);
        stage.setScene(new Scene(newGame));
        stage.setResizable(false);
        stage.show();
    }
}
