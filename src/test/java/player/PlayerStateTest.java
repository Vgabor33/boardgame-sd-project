package player;

import board.FieldState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStateTest
{

    @Test
    void setStartingPlayer()
    {
        PlayerState.setStartingPlayer(true);
        assertTrue(PlayerState.whosNext().equals(FieldState.RED));
        PlayerState.setStartingPlayer(false);
        assertFalse(PlayerState.whosNext().equals(FieldState.RED));
    }

    @Test
    void whosNext()
    {
        PlayerState.setStartingPlayer(true);
        assertTrue(PlayerState.whosNext().equals(FieldState.RED));
        PlayerState.setStartingPlayer(false);
        assertFalse(PlayerState.whosNext().equals(FieldState.RED));
    }

    @Test
    void setNext()
    {
        PlayerState.setStartingPlayer(true);
        PlayerState.setNext();
        assertTrue(PlayerState.whosNext().equals(FieldState.BLUE));
        PlayerState.setStartingPlayer(false);
        PlayerState.setNext();
        assertTrue(PlayerState.whosNext().equals(FieldState.RED));
    }
}