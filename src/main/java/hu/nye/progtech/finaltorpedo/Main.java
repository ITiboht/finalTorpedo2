package hu.nye.progtech.finaltorpedo;

import static hu.nye.progtech.finaltorpedo.Database.leaderboardAdd;
import static hu.nye.progtech.finaltorpedo.Database.leaderboardShow;
import static hu.nye.progtech.finaltorpedo.GameState.askForGuess;
import static hu.nye.progtech.finaltorpedo.GameState.compMakeGuess;
import static hu.nye.progtech.finaltorpedo.GameState.setup;
import static hu.nye.progtech.finaltorpedo.GameState.setupAI;

import java.util.Scanner;

/**
 * Main class.
 */
public class Main {

    /**
     * Main method.
     *
     * @param args args
     */
    public static final void main(final String[] args) {

        System.out.println("Welcome to BattleShip!");
        System.out.println("Main Menu");
        int mainMenu = 1;
        while (mainMenu == 1) {
            System.out.println("[1] New Game");
            System.out.println("[2] Leaderboard");
            System.out.println("[3] Exit");
            Scanner in = new Scanner(System.in);
            String menu = in.nextLine();

            switch (menu) {
                case "1": {
                    System.out.println("Enter your name: ");
                    String playerName = in.nextLine();
                    Player userPlayer = new Player();
                    setup(userPlayer);
                    Player ai = new Player();
                    setupAI(ai);
                    System.out.println("Enable debug mode? "
                            + "(1-yes, anything else-no)");
                    String debug = in.nextLine();
                    int debugMode = Integer.valueOf(debug);
                    String result = "";
                    mainMenu = 0;
                    int gameStart = 1;
                    while (gameStart == 1) {
                        if (debugMode == 1) {
                            System.out.println("Enemy map (debug):\n");
                            ai.getPlayerMap().printShips();
                        }
                        System.out.println(debug);
                        System.out.println(result);
                        System.out.println(("Player's turn: "));
                        result = askForGuess(userPlayer, ai);
                        if (userPlayer.getPlayerMap().hasLost()) {
                            System.out.println("Computer hit!"
                                    + "   The player lost");
                            gameStart = 0;
                            break;
                        } else if (ai.getPlayerMap().hasLost()) {
                            System.out.println("Hit!"
                                    + "   The computer lost");
                            leaderboardAdd(playerName);
                            gameStart = 0;
                            break;
                        }
                        System.out.println("Computer's turn");
                        compMakeGuess(ai, userPlayer);
                    }
                    break;
                }
                case "2": {
                    leaderboardShow();
                    break;
                }
                case "3": {
                    mainMenu = 0;
                    break;
                }
                case "DEBUG": {
                    System.out.println("Enter your name: ");
                    String playerName = in.nextLine();
                    leaderboardAdd(playerName);
                    break;
                }
                default: {
                    System.out.println("No Options were selected.");
                    break;
                }
            }
        }

    }

}
