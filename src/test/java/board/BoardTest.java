package board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest
{
    @Test
    @DisplayName("Board.getInstance() test")
    void getInstance()
    {
        Board board = Board.getInstance();
        assertNotEquals(board,null);
        assertFalse(isAllEmpty());
        assertTrue(startingState());
    }

    private static boolean startingState()
    {
        for (int i=0; i<11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                if( i%2== 0 && !(j%2 == 0) && Board.getInstance().getFieldState(i,j).equals(FieldState.BLUE))
                {
                    continue;
                }
                else if(!(i%2 == 0) && (j%2 == 0) && Board.getInstance().getFieldState(i,j).equals(FieldState.RED))
                {
                    continue;
                }
                else if ((Board.getInstance().getFieldState(i,j).equals(FieldState.EMPTY)))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isAllEmpty()
    {
        for (int i=0; i<11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                if(!Board.getInstance().equals(i,j,FieldState.EMPTY))
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    @DisplayName("Board.resetBoard() test")
    void resetBoard()
    {
        Board.resetBoard();
        assertFalse(isAllEmpty());
        Board.resetBoard();
        assertTrue(startingState());
    }

    @Test
    @DisplayName("Board.getFieldState() test")
    void getFieldState()
    {
        assertNotEquals(null,Board.getInstance().getFieldState(0,0));
        for (int i=0; i<11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                assertNotEquals(Board.getInstance().getFieldState(i, j), null);
            }
        }
    }

    @Test
    @DisplayName("Board.setFieldState() test")
    void setFieldState()
    {
        assertThrows(NullPointerException.class, () -> Board.getInstance().setFieldState(0,0,null));
        for (int i=0; i<11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                int i1 = i;
                int j1 = j;
                assertThrows(NullPointerException.class , () -> {Board.getInstance().setFieldState(i1, j1, null);});
                if(!Board.getInstance().equals(i,j,FieldState.EMPTY))
                {
                    assertThrows(IllegalArgumentException.class,() -> {Board.getInstance().setFieldState(i1, j1, FieldState.RED);});
                    assertThrows(IllegalArgumentException.class,() -> {Board.getInstance().setFieldState(i1, j1, FieldState.BLUE);});
                }
            }
        }
    }

    @Test
    void testEquals()
    {
        for (int i=0; i<11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                assertFalse(Board.getInstance().equals(i, j, null));
                assertTrue(Board.getInstance().equals(i,j,Board.getInstance().getFieldState(i,j)));
            }
        }
    }
}