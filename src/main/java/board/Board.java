package board;

public class Board
{

    private static Board gameBoard;

    private FieldState[] [] board;

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

    public static Board getInstance()
    {
        if (gameBoard == null){
            gameBoard = new Board();
        }
        return gameBoard;
    }

    public static void resetBoard()
    {
        gameBoard = new Board();
    }

    public FieldState getFieldState(int i, int j)
    {
        return Board.getInstance().board[i][j];
    }

    public void setFieldState(int i, int j, FieldState state) throws NullPointerException, IllegalArgumentException
    {
        if (state.equals(null))
        {
            throw new NullPointerException("Field state cannot be null!");
        }
        else if(Board.getInstance().equals(i,j,FieldState.EMPTY))
        {
            Board.getInstance().board[i][j] = state;
        }
        else
        {
            throw new IllegalArgumentException("Field state already set!");
        }
    }

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

    private static boolean isOdd(int i)
    {
        if( (i%2)>0 )
        {
            return false;
        }
        return true;
    }

}
