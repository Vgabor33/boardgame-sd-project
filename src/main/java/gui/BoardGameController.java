package gui;

import board.Board;
import board.BoardState;
import board.FieldState;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import player.PlayerState;

import java.io.IOException;


//CHECKSTYLE:OFF
public class BoardGameController
{

    private static Stage primaryStage;

    public static void setController(Stage stage)
    {
        primaryStage = stage;
    }


    @FXML
    private GridPane gboard;

    @FXML
    private void initialize()
    {
        for (int i = 0; i < gboard.getRowCount(); i++)
        {
            for (int j = 0; j < gboard.getColumnCount(); j++)
            {
                var square = setRectangle(i, j);
                gboard.add(square, j, i);
            }
        }
    }


    @FXML
    private StackPane setRectangle(int i, int j)
    {
        var square = new StackPane();
        square.getStyleClass().add("square");

        colorRectangles(square,i,j);

        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event)
    {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);

        var rectangle = (Rectangle) square.getChildren().get(0);
        colorEmptyRectangle(rectangle,row,col);
        isThereAWinner();
    }

    private Color setColor(int i, int j)
    {
        if (PlayerState.whosNext().equals(FieldState.RED))
        {
            PlayerState.setNext();
            Board.getInstance().setFieldState(i,j,FieldState.RED);
            return Color.RED;
        }
        else
        {
            PlayerState.setNext();
            Board.getInstance().setFieldState(i, j, FieldState.BLUE);
            return Color.BLUE;
        }
    }

    private void colorRectangles(StackPane parent, int i, int j)
    {
        Rectangle square = new Rectangle();
        if (Board.getInstance().getFieldState(i, j).equals(FieldState.BLUE))
        {
            parent.getChildren().add(new Rectangle(100, 100, Color.BLUE));
        }
        else if (Board.getInstance().getFieldState(i, j).equals(FieldState.RED))
        {
            parent.getChildren().add(new Rectangle(100, 100, Color.RED));
        }
        else
        {
            parent.getChildren().add(new Rectangle(100, 100, Color.TRANSPARENT));
        }
    }

    private void colorEmptyRectangle(Rectangle rectangle, int i, int j)
    {
        if (Board.getInstance().getFieldState(i, j).equals(FieldState.EMPTY))
        {
            rectangle.setFill(setColor(i, j));
        }
    }

    private void isThereAWinner()
    {
        if (BoardState.isRedWon(Board.getInstance()) || BoardState.isBlueWon(Board.getInstance()))
        {
            try
            {
                Parent endGame = UILoader.fxmlLoader(getClass().getResource("/endui.fxml"));
                primaryStage.setTitle("Game concluded");
                EndGameController.setController(primaryStage);
                primaryStage.setScene(new Scene(endGame));
            }
            catch (IOException e)
            {
                throw new RuntimeException("Could not find endui.fxml!", e);
            }
        }
        if(BoardState.isFull(Board.getInstance()))
        {
            noMoveWarning("No more moves!","Tie");
        }
    }
    private void noMoveWarning(String infoMessage, String title)
    {
        final Stage dialog = new Stage();
        dialog.setTitle(title);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().add(new Text(infoMessage));
        Button newGameButton = new Button("New Game");
        newGameButton.setOnMouseClicked(this::handleNewGame);
        Button quitButton = new Button("Quit Game");
        quitButton.setOnMouseClicked(this::handleQuit);
        dialogVbox.getChildren().addAll(newGameButton,quitButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    private void handleNewGame(MouseEvent mouseEvent)
    {

    }
    private void handleQuit(MouseEvent mouseEvent)
    {
       primaryStage.close();
    }
}