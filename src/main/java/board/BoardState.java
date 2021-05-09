package board;

import org.tinylog.Logger;

public class BoardState
{
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
                if( Board.getInstance().getFieldState(i,j).equals(FieldState.BLUE))
                {
                    fieldCount++;
                }
            }
        }
        return false;
    }

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
                if( Board.getInstance().getFieldState(j,i).equals(FieldState.RED))
                {
                    fieldCount++;
                }
            }
        }
        return false;
    }
}
