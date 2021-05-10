package main; /**
 * Using {@link board board} package to create a representation to the console.
 */

import board.*;

//CHECKSTYLE:OFF
public class Main
{

    public static void main(String[] args)
    {
        Board.getInstance();
        for (int i = 0; i < 11; i++)
        {
            System.out.printf("\n");
            for (int j = 0; j < 11; j++)
            {
                if(Board.getInstance().getFieldState(i,j).equals(FieldState.EMPTY))
                    System.out.printf(Board.getInstance().getFieldState(i,j)+ " ");
                else if (Board.getInstance().getFieldState(i,j).equals(FieldState.RED))
                    System.out.printf(Board.getInstance().getFieldState(i,j)+ "   ");
                else
                    System.out.printf(Board.getInstance().getFieldState(i,j)+ "  ");
            }
        }
    }
}
