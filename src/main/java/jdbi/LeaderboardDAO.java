package jdbi;


import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;
import java.util.Optional;

/**
 * DAO interface to create SQL {@code Query}s.
 */
public interface LeaderboardDAO
{
    /**
     * Method to create a new table, to store the winners.
     */
    @SqlUpdate("CREATE TABLE leaderboard (name VARCHAR PRIMARY KEY, wins INTEGER)")
    void createTable();

    /**
     * Method to update the win count of a {@link jdbi.Player Player} in the database.
     * @param wins the actual number of wins
     * @param name the {@link jdbi.Player Player}'s name
     */
    @SqlUpdate("UPDATE leaderboard SET wins = :wins WHERE name IN(:name)")
    void updateWins(@Bind("wins") int wins,@Bind("name") String name);

    /**
     * Method to insert a new winner into the table.
     * @param name the {@link jdbi.Player Player}'s name.
     */
    @SqlUpdate("INSERT INTO leaderboard (name,wins) VALUES (:name,1)")
    void insertUser(@Bind("name") String name);

    /**
     * Returns the number of wins a {@link jdbi.Player Player} has.
     * @param name {@link jdbi.Player Player}'s name
     * @return number of wins the {@link jdbi.Player Player} has
     */
    @SqlQuery("SELECT wins FROM leaderboard WHERE name IN(:name)")
    int numberOfWins(@Bind("name") String name );

    /**
     * Returns an Opcional {@link jdbi.Player Player} object with the Players with the top 10 wins.
     * @return Opcional {@link jdbi.Player Player} object
     */
    @SqlQuery("SELECT * FROM leaderboard ORDER BY wins DESC ROW_COUNT =10")
    ArrayList<Optional<Player>> getFirstTen();

    /**
     * This method is used to decide whether a {@link jdbi.Player Player} is in the table already.
     * @param name winner's name
     * @return true if the player is in the table, false otherwise
     */
    @SqlQuery("SELECT EXISTS (SELECT * FROM leaderboard WHERE name IN(:name))")
    boolean playerExists(@Bind("name") String name);
}
