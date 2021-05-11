package board;

import org.tinylog.Logger;

/**
 * This class can check the {@link board.Board Board}'s state (which is the game's state).
 */
public class BoardState
{

    /**
     * Returns a boolean based on the {@link board.Board gameBoard}'s state.
     * @param gameBoard the {@link board.Board gameBoard}'s state
     * @return {@code true} if the Blue player has won, {@code false} otherwise
     */
    public static boolean isBlueWon (Board gameBoard)
    {
        int fieldCount = 0;
        for (int i=0; i<11; i++)
        {
            if(fieldCount == 11)
            {
                Logger.info("Blue has won!");
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
     * @param gameBoard the {@link board.Board gameBoard}'s state
     * @return {@code true} if the Red player has won, {@code false} otherwise
     */
    public static boolean isRedWon (Board gameBoard)
    {
        int fieldCount = 0;
        for (int i=0; i<11; i++)
        {
            if(fieldCount == 11)
            {
                Logger.info("Red has won!");
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
}
