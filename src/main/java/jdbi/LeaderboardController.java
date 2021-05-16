package jdbi;


import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;

import java.util.ArrayList;


/**
 * This class is using jdbi to save the winners to a local database.
 */
public class LeaderboardController
{
    /**
     * The static jdbi object, which we will use to manage database connection.
     */
    private static Jdbi jdbi;

    /**
     * Constructor, to start a new connection and make the local database file and table to store the winners.
     */
    private LeaderboardController()
    {
        this.jdbi=Jdbi.create("jdbc:h2:file:~/.boardgame-2_1/leaderboard","sa","");
        this.jdbi.installPlugin(new SqlObjectPlugin());
        this.jdbi.installPlugin(new H2DatabasePlugin());
        try
        {
            jdbi.withExtension(LeaderboardDAO.class, dao -> {dao.createTable(); return true;});
        }
        catch (Exception e)
        {
            Logger.info("DB already exists");
        }
    }

    /**
     * Returns the static jdbi object.
     * @return this classes jdbi object
     */
    public static Jdbi getInstance()
    {
        if(jdbi.equals(null))
        {
            new LeaderboardController();
        }
        return jdbi;
    }

    /**
     * This method is used to insert a winner to the table, or increase the wins of an already existing winner.
     * @param winnerName the name of the winner.
     */
    public static void insertWinner(String winnerName)
    {
        new LeaderboardController();
        boolean playerAlreadyExists = jdbi.withExtension(LeaderboardDAO.class, dao -> dao.playerExists(winnerName));

        if(playerAlreadyExists)
        {
            int wins = jdbi.withExtension(LeaderboardDAO.class, dao -> dao.numberOfWins(winnerName));
            jdbi.withExtension(LeaderboardDAO.class, dao ->
            {
                dao.updateWins(wins+1,winnerName);
                return true;
            });
        }
        else
        {
            jdbi.withExtension(LeaderboardDAO.class, dao -> {dao.insertUser(winnerName);return true;});
        }
    }

    /**
     * Returns an {@code ArrayList} object with ten or less {@link Player Player}s (based on the number of {@code Player}s in our database).
     * @return an {@code ArrayList} object with ten or less {@link Player Player}s
     */
    public static ArrayList<Player> getFirstTen()
    {
        int[] winners = jdbi.withExtension(LeaderboardDAO.class, dao -> dao.getFirstTenWinCount());
        String[] names = jdbi.withExtension(LeaderboardDAO.class, dao -> dao.getFirstTenName());
        LeaderboardController db = new LeaderboardController();
        ArrayList<Player> result = new ArrayList<Player>();
        int goToLength;
        if(names.length>10)
        {
            goToLength =10;
        }
        else
        {
            goToLength = names.length;
        }
        for (int i = 0; i < goToLength; i++)
        {
            result.add(new Player(names[i],winners[i]));
        }
        return result;
    }
}
