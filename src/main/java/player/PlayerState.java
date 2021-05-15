package player;

import board.FieldState;


/**
 * This class is to determine which player is next.
 */
public class PlayerState
{
    /**
     * This variable determines which {@link Player player} is the next player to move.
     */
    private static Player nextPlayer;

    /**
     * This method is to set the starting {@link Player player}.
     * @param redStart a parameter that let's the method know if the red player starts the game
     */
    public static void setStartingPlayer(boolean redStart)
    {
        if(redStart)
        {
            nextPlayer = Player.P1;
        }
        else
        {
            nextPlayer = Player.P2;
        }
    }


    /**
     * This method returns the {@link FieldState color} of the next player to move.
     *
     * @return {@link FieldState Fieldstate} representing the next to move players color
     */
    public static FieldState whosNext()
    {
        if(nextPlayer.equals(Player.P1))
        {
            return FieldState.RED;
        }
        return FieldState.BLUE;
    }

    /**
     * Gives the next move to the other player.
     */
    public static void setNext()
    {
        if(nextPlayer.equals(Player.P1))
        {
            nextPlayer = Player.P2;
        }
        else
        {
            nextPlayer = Player.P1;
        }
    }
}
