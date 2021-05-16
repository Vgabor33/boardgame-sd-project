package jdbi;


import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;


/**
 * This class is using jdbi to save the winners to a local database.
 */
public class LeaderboardController
{
    /**
     * The jdbi object, which we will use to manage database connection.
     */
    private Jdbi jdbi;

    /**
     * Constructor, to start a new connection and make the local database file and table to store the winners.
     */
    public LeaderboardController()
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
     * This method is used to insert a winner to the table, or increase the wins of an already existing winner.
     * @param winnerName the name of the winner.
     */
    public void insertWinner(String winnerName)
    {
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
}
