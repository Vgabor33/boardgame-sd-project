package gui;

import board.Board;
import board.BoardState;
import board.FieldState;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

    private Color setColor(Color color, int i, int j)
    {
        if (color == Color.TRANSPARENT && PlayerState.whoNext().equals(FieldState.RED))
        {
            Board.getInstance().setFieldState(i,j,FieldState.RED);
            return Color.RED;
        }
        else
        {
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
            rectangle.setFill(setColor((Color) rectangle.getFill(), i, j));
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
    }
}
