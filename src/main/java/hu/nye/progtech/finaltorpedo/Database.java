package hu.nye.progtech.finaltorpedo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

/**
 * Database for leaderboard.
 */
public class Database {

    /**
     * Prints leaderboard from MySQL.
     */
    public static void leaderboardShow() {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/torpedo_leaderboard", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from leaderboard \n order by Wins DESC");

            System.out.println("Leaderboard:");
            while (resultSet.next()) {
                System.out.println("Name:" + resultSet.getString("PlayerName") + " Wins:" + resultSet.getString("Wins"));
            }
            resultSet.close();
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds the player a win if won and already exists.
     * If player does not exist adds player with 1 win.
     *
     * @param playerName User player name
     */
    public static void leaderboardAdd(String playerName) {
        try {

            int id = 0;
            int newPlayer = 1;

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/torpedo_leaderboard", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from leaderboard \n order by Wins DESC");

            while (resultSet.next()) {
                String existingName = resultSet.getString("PlayerName");
                if (Objects.equals(playerName, existingName)) {
                    statement.execute("update leaderboard \n set Wins = Wins + 1 \n where PlayerName = '" + playerName + "'");
                    newPlayer = 0;
                    System.out.println("Data successfully saved!");
                    break;
                }
                id++;
            }
            if (newPlayer == 1) {
                statement.execute("insert into leaderboard (id,PlayerName, Wins) \n values (" + id + ",'" + playerName + "',1)");
                System.out.println("Data successfully saved!");
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
