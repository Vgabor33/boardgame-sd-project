package player;

import board.FieldState;

/**
 * This class is to determine which player is next.
 */
public class PlayerState
{
    /**
     * This variable determines if {@code RED} is the next player to move.
     */
    private static boolean redNext=true;

    /**
     * This method returns the {@link FieldState color} of the next player to move.
     * @return {@link FieldState Fieldstate} representing the next to move players color
     */
    public static FieldState whoNext()
    {
        if(redNext)
        {
            redNext = false;
            return FieldState.RED;
        }
        redNext = true;
        return FieldState.BLUE;
    }
}
