package board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardStateTest
{

    private static Board setWinningState(int row_or_column, FieldState state)
    {
        for (int i = 0; i < 11; i++)
        {
            if(state.equals(FieldState.RED))
            {
                if(Board.getInstance().getFieldState(i,row_or_column).equals(FieldState.EMPTY))
                    Board.getInstance().setFieldState(i, row_or_column, state);
            }
            else if (state.equals(FieldState.BLUE))
            {
                if(Board.getInstance().getFieldState(row_or_column,i).equals(FieldState.EMPTY))
                    Board.getInstance().setFieldState( row_or_column,i, state);
            }
        }
        return Board.getInstance();
    }

    @Test
    void isBlueWon()
    {
        for (int k = 0; k < 11; k++)
        {
            assertTrue(BoardState.isBlueWon(setWinningState(k, FieldState.BLUE)));
        }
        Board.resetBoard();
        assertFalse(BoardState.isBlueWon(Board.getInstance()));
    }

    @Test
    void isRedWon()
    {
        for (int k = 0; k < 11; k++)
        {
            assertTrue(BoardState.isRedWon(setWinningState(k, FieldState.RED)));
        }
        Board.resetBoard();
        assertFalse(BoardState.isRedWon(Board.getInstance()));

    }
}