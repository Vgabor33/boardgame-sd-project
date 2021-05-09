import board.*;

public class Main
{
    public static void main(String[] args)
    {
        Board.getInstance();
        for (int i = 0; i < 11; i++)
        {
            System.out.printf("\n");
            for (int j = 0; j < 11; j++)
            {
                if(Board.getInstance().getFieldstate(i,j).equals(FieldState.EMPTY))
                    System.out.printf(Board.getInstance().getFieldstate(i,j)+ " ");
                else if (Board.getInstance().getFieldstate(i,j).equals(FieldState.RED))
                    System.out.printf(Board.getInstance().getFieldstate(i,j)+ "   ");
                else
                    System.out.printf(Board.getInstance().getFieldstate(i,j)+ "  ");
            }
        }
    }
}
