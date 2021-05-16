package jdbi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardControllerTest
{
    private static void deleteTest()
    {
        LeaderboardController.getInstance().withExtension(LeaderboardDAO.class, dao -> {dao.deleteUser("test");return true;});
    }
    @Test
    void getInstance()
    {
        assertFalse(LeaderboardController.getInstance().toString()==null);
    }

    @Test
    void insertWinner()
    {
        LeaderboardController.getInstance().withExtension(LeaderboardDAO.class, dao -> {dao.insertUser("test");return true;});
        assertTrue(LeaderboardController.getInstance().withExtension(LeaderboardDAO.class, dao -> {return dao.playerExists("test");}).booleanValue());
        deleteTest();
    }

    @Test
    void getFirstTen()
    {
        LeaderboardController.getInstance().withExtension(LeaderboardDAO.class, dao -> {dao.insertUser("test");return true;});
        assertTrue(LeaderboardController.getInstance().withExtension(LeaderboardDAO.class, dao -> dao.getFirstTenWinCount()).length !=0);
        deleteTest();
    }
}