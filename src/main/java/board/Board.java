
package board;


import org.tinylog.Logger;

/**
 * A Singleton class for setting up, modifying and getting the {@code Board}'s state.
 */
public class Board
{

    /**
     * The only declared {@code Board} object.
     */
    private static Board gameBoard;

    /**
     * Enum matrix for storing the {@code Board}'s {@code FieldState}'s.
     */
    private FieldState[] [] board;

    /**
     * Sets the board (modifies the {@code Board} object).
     */
    private Board()
    {
        FieldState [] [] board = new FieldState[11][11];
        for(int i=0;i<11;i++)
        {
            for(int j=0; j<11; j++)
            {
                if(Board.isOdd(i) && !Board.isOdd(j))
                {
                    board[i][j]= FieldState.BLUE;
                }
                else if(!Board.isOdd(i) && Board.isOdd(j))
                {
                    board[i][j]=FieldState.RED;
                }
                else
                {
                    board[i][j]=FieldState.EMPTY;
                }
            }
        }
        this.board = board;
    }

    /**
     * Returns the only {@code Board} object, or if it was null, it will call the {@link board.Board#Board() constructor} on it.
     *
     * @return the only {@code Board} object
     */
    public static Board getInstance()
    {
        if (gameBoard == null)
        {
            Logger.info("Setting up board");
            gameBoard = new Board();
        }
        return gameBoard;
    }

    /**
     * It resets the board by calling the {@link board.Board#Board() Constructor}.
     */
    public static void resetBoard()
    {
        Logger.info("Reseting board");
        gameBoard = new Board();
    }

    /**
     * Returns a field's state.
     * @param i row number (indexing from 0)
     * @param j column number (indexing from 0)
     * @return the {@link board.FieldState fieldstate} of the field
     */
    public FieldState getFieldState(int i, int j)
    {
        return Board.getInstance().board[i][j];
    }

    /**
     * Sets a field in the {@code Board}. If the given {@link board.FieldState state} isn't null or already claimed by a player.
     * @param i row number (indexing from 0)
     * @param j column number (indexing from 0)
     * @param state the {@link board.FieldState state} we would like to set the state in the i-th row and j-th column
     * @throws NullPointerException if the given {@code Fieldstate} is {@code null}
     * @throws IllegalArgumentException if the given {@code Fieldstate} already claimed by a player
     */
    public void setFieldState(int i, int j, FieldState state) throws NullPointerException, IllegalArgumentException
    {
        if (state.equals(null))
        {
            Logger.error("FieldState is null");
            throw new NullPointerException("Field state cannot be null!");
        }
        else if(Board.getInstance().equals(i,j,FieldState.EMPTY))
        {
            Board.getInstance().board[i][j] = state;
        }
        else
        {
            Logger.error("FieldState is already set!");
            throw new IllegalArgumentException("Field state already set!");
        }
    }

    /**
     * Checks if the {@code Board}'s {@link board.FieldState Fieldstate} is the same as the given {@link board.FieldState Fieldstate}.
     * @param i row number (indexing from 0)
     * @param j column number (indexing from 0)
     * @param state one of the {@link board.FieldState states} we would like to compare
     * @return a boolean, based on the equality of the the {@link board.FieldState states}
     */
    public boolean equals(int i, int j, FieldState state)
    {
        if( state == null )
        {
            return false;
        }
        else
        {
            switch (state)
            {
                case EMPTY:
                    return Board.getInstance().board[i][j].equals(FieldState.EMPTY);
                case RED:
                    return Board.getInstance().board[i][j].equals(FieldState.RED);
                case BLUE:
                    return Board.getInstance().board[i][j].equals(FieldState.BLUE);
                default:
                    return false;
            }
        }
    }

    /**
     * Checks if the given number is odd.
     * @param i the number we would like to check
     * @return {@code true} if the given value is odd, {@code false} otherwise
     */
    private static boolean isOdd(int i)
    {
        if( (i%2)>0 )
        {
            return false;
        }
        return true;
    }

}
