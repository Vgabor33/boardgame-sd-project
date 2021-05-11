package board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardStateTest
{

    private static Board setRedWinningState(int row)
    {
        for (int i = 0; i < 11; i++)
        {
                if(Board.getInstance().getFieldState(row,i).equals(FieldState.EMPTY))
                    Board.getInstance().setFieldState(row,i,FieldState.RED);
        }
        return Board.getInstance();
    }
    private static Board setBlueWinningState(int column)
    {
        for (int i = 0; i < 11; i++)
        {
            if(Board.getInstance().getFieldState(i,column).equals(FieldState.EMPTY))
                Board.getInstance().setFieldState(i,column,FieldState.BLUE);
        }
        return Board.getInstance();
    }

    @Test
    void isBlueWon()
    {
        for (int k = 1; k < 11; k=k+2)
        {
            assertTrue(BoardState.isBlueWon(setBlueWinningState(k)));
        }
        Board.resetBoard();
        assertFalse(BoardState.isBlueWon(Board.getInstance()));
    }

    @Test
    void isRedWon()
    {
        for (int k = 1; k < 11; k=k+2)
        {
                assertTrue(BoardState.isRedWon(setRedWinningState(k)));
        }
        Board.resetBoard();
        assertFalse(BoardState.isRedWon(Board.getInstance()));

    }
}