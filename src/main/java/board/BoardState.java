package board;

import org.tinylog.Logger;

/**
 * This class can check the {@link board.Board Board}'s state (which is the game's state).
 */
public class BoardState
{

    /**
     * Returns a boolean based on the {@link board.Board gameBoard}'s state.
     * @param gameBoard the actual {@link board.Board gameBoard}'s state
     * @return {@code true} if the Blue player has won, {@code false} otherwise
     */
    public static boolean isBlueWon (Board gameBoard)
    {
        int fieldCount = 0;
        for (int i=0; i<11; i++)
        {
            if(fieldCount == 11)
            {
                return true;
            }
            fieldCount = 0;
            for (int j=0; j<11; j++)
            {
                if( Board.getInstance().getFieldState(j,i).equals(FieldState.BLUE))
                {
                    fieldCount++;
                }
            }
        }
        return false;
    }

    /**
     * Returns a boolean based on the {@link board.Board gameBoard}'s state.
     * @param gameBoard the actuak {@link board.Board gameBoard}'s state
     * @return {@code true} if the Red player has won, {@code false} otherwise
     */
    public static boolean isRedWon (Board gameBoard)
    {
        int fieldCount = 0;
        for (int i=0; i<11; i++)
        {
            if(fieldCount == 11)
            {
                return true;
            }
            fieldCount = 0;
            for (int j=0; j<11; j++)
            {
                if( Board.getInstance().getFieldState(i,j).equals(FieldState.RED))
                {
                    fieldCount++;
                }
            }
        }
        return false;
    }

    /**
     * Returns a {@code boolean} which tells us if there is an {@code EMPTY} field.
     * @param gameBoard the actual {@link board.Board gameBoard}'s state
     * @return {@code true} if the {@link board.Board gameBoard} doesn't have any {@code EMPTY} field(s), {@code false} otherwise
     */
    public static boolean isFull (Board gameBoard)
    {
        for (int i=0; i<11; i++)
        {
            for (int j=0; j<11; j++)
            {
                if( Board.getInstance().getFieldState(i,j).equals(FieldState.EMPTY))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
