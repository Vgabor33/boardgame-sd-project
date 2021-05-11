package main; /**
 * Using {@link board board} package to create a representation to the console.
 */

import board.*;
import gui.*;
import javafx.application.Application;

//CHECKSTYLE:OFF
public class Main
{

    public static void main(String[] args)
    {
        Board.getInstance();
        Application.launch(BoardGameApplication.class, args);
    }
}
