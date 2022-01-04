package hu.nye.progtech.finaltorpedo;

import java.util.Scanner;

/**
 * Main gameplay class.
 */
public final class GameState {

    /**
     * Max map size.
     */
    private static final int MAP_BOUNDARY_LIMIT = 10;
    /**
     * Scanner for methods.
     */
    private static Scanner reader = new Scanner(System.in);

    /**
     * Sets up ships with player input.
     *
     * @param p Player value
     */
    public static void setup(final Player p) {
        p.getPlayerMap().printShips();
        System.out.println();
        int counter = 1;
        int normCounter = 0;
        while (p.numOfShipsLeft() > 0) {
            for (Ship s : p.getShips()) {
                System.out.println("\nShip #" + counter
                        + ": Length-" + s.getLength());
                int row = -1;
                int column = -1;
                int facing = -1;
                while (true) {
                    System.out.println("Type in row (A-J): ");
                    String userInputRow = reader.next();
                    userInputRow = userInputRow.toUpperCase();
                    row = convertLetterToInt(userInputRow);

                    System.out.println("Type in column (1-10): ");
                    column = reader.nextInt();
                    column = convertUserColToProCol(column);

                    System.out.println("Which way is it facing? "
                            + "(0-Horizontal, 1-Vertical): ");
                    facing = reader.nextInt();

                    if (column >= 0 && column <= 9
                            && row != -1 && facing != -1) {
                        if (!hasErrors(row, column,
                                facing, p, normCounter)) {
                            break;
                        }
                    }
                    System.out.println("Invalid location");
                }
                p.getShips()[normCounter].setLoc(row, column);
                p.getShips()[normCounter].setFacing(facing);
                p.getPlayerMap().addShip(p.getShips()[normCounter]);
                p.getPlayerMap().printShips();
                System.out.println();
                System.out.println("You have " + p.numOfShipsLeft()
                        + " remaining ships to place");

                normCounter++;
                counter++;
            }
        }
    }

    /**
     * Sets up ships for AI.
     *
     * @param p Player value
     */
    public static void setupAI(final Player p) {
        System.out.println();
        int counter = 1;
        int normCounter = 0;
        Randomizer rand = new Randomizer();
        while (p.numOfShipsLeft() > 0) {
            for (Ship s : p.getShips()) {
                int row = Randomizer.nextInt(0, 9);
                int column = Randomizer.nextInt(0, 9);
                int facing = Randomizer.nextInt(0, 1);
                while (hasErrorsComp(row, column,
                        facing, p, normCounter)) {
                    row = Randomizer.nextInt(0, 9);
                    column = Randomizer.nextInt(0, 9);
                    facing = Randomizer.nextInt(0, 1);
                }
                p.getShips()[normCounter].setLoc(row, column);
                p.getShips()[normCounter].setFacing(facing);
                p.getPlayerMap().addShip(p.getShips()[normCounter]);

                normCounter++;
                counter++;
            }

        }
    }

    /**
     * Asks the player for a specific position to shoot.
     *
     * @param p   Player value
     * @param opp AI value
     * @return Returns hit or miss
     */
    public static String askForGuess(final Player p, final Player opp) {
        System.out.println("Guesses: ");
        p.getEnemyMap().printStatus();

        int row = -1;
        int column = -1;

        String oldRow = "Z";
        int oldCol = -1;

        while (true) {
            System.out.println("Type in row (A-J): ");
            String userInputRow = reader.next();
            userInputRow = userInputRow.toUpperCase();
            oldRow = userInputRow;
            row = convertLetterToInt(userInputRow);

            System.out.println("Type in column (1-10): ");
            column = reader.nextInt();
            oldCol = column;
            column = convertUserColToProCol(column);

            if (column >= 0 && column <= 9 && row != -1) {
                break;
            }

            System.out.println("Invalid location!");
        }

        if (opp.getPlayerMap().hasShip(row, column)) {
            p.getEnemyMap().markHit(row, column);
            opp.getPlayerMap().markHit(row, column);
            return "User hit at " + oldRow + oldCol;
        } else {
            p.getEnemyMap().markMiss(row, column);
            opp.getPlayerMap().markMiss(row, column);
            return "User miss at " + oldRow + oldCol;
        }
    }

    /**
     * Randomly gives position data to AI until it can shoot.
     *
     * @param comp AI value
     * @param user Player value
     */
    public static void compMakeGuess(final Player comp, final Player user) {
        Randomizer rand = new Randomizer();
        int row = Randomizer.nextInt(0, 9);
        int column = Randomizer.nextInt(0, 9);

        while (comp.getEnemyMap().alreadyGuessed(row, column)) {
            row = Randomizer.nextInt(0, 9);
            column = Randomizer.nextInt(0, 9);
        }

        if (user.getPlayerMap().hasShip(row, column)) {
            comp.getEnemyMap().markHit(row, column);
            user.getPlayerMap().markHit(row, column);
            System.out.println("Ai hit at " + convertIntToLetter(row)
                    + convertCompColToRegular(column));
        } else {
            comp.getEnemyMap().markMiss(row, column);
            user.getPlayerMap().markMiss(row, column);
            System.out.println("Ai miss at " + convertIntToLetter(row)
                    + convertCompColToRegular(column));
        }
        System.out.println("\nYour board");
        reader.nextLine();
        user.getPlayerMap().printCombined();
        System.out.println("Continue...");
        reader.nextLine();
    }

    /**
     * Checks if the ship fits on the map
     * and does not collide with other ships.
     *
     * @param row    Map row pos
     * @param column Map column pos
     * @param facing Vertical/Horizontal value
     * @param p      Player value
     * @param count  Length of current ship
     * @return Returns ture or false based on if the ship can be placed
     */
    private static boolean hasErrors(final int row, final int column,
                                     final int facing,
                                     final Player p, final int count) {
        int length = p.getShips()[count].getLength();
        if (facing == 0) {
            int checker = length + column;
            System.out.println("Checker is " + checker);
            if (checker > MAP_BOUNDARY_LIMIT) {
                System.out.println("Ship does not fit");
                return true;
            }
        }
        if (facing == 1) {
            int checker = length + row;
            System.out.println("Checker is " + checker);
            if (checker > MAP_BOUNDARY_LIMIT) {
                System.out.println("Ship does not fit");
                return true;
            }
        }
        if (facing == 0) {
            for (int i = column; i < column + length; i++) {
                if (p.getPlayerMap().hasShip(row, i)) {
                    System.out.println("Ship already exists at this location");
                    return true;
                }
            }
        } else if (facing == 1) {
            for (int i = row; i < row + length; i++) {
                if (p.getPlayerMap().hasShip(i, column)) {
                    System.out.println("Ship already exists at this location");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * For the AI. Checks if the computer ship is on
     * the map and does not collide with other ships.
     *
     * @param row    Map row pos
     * @param column Map column pos
     * @param facing Vertical/Horizontal value
     * @param p      Player value
     * @param count  Length of current ship
     * @return Returns ture or false based on if the ship can be placed
     */
    private static boolean hasErrorsComp(final int row, final int column,
                                         final int facing,
                                         final Player p, final int count) {
        int length = p.getShips()[count].getLength();
        if (facing == 0) {
            int checker = length + column;
            if (checker > MAP_BOUNDARY_LIMIT) {
                return true;
            }
        }
        if (facing == 1) {
            int checker = length + row;
            if (checker > MAP_BOUNDARY_LIMIT) {
                return true;
            }
        }
        if (facing == 0) {
            for (int i = column; i < column + length; i++) {
                if (p.getPlayerMap().hasShip(row, i)) {
                    return true;
                }
            }
        } else if (facing == 1) {
            for (int i = row; i < row + length; i++) {
                if (p.getPlayerMap().hasShip(i, column)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * At user input convert row letters to int.
     *
     * @param val Character value
     * @return Returns row value in int
     */

    private static int convertLetterToInt(final String val) {
        int toReturn = -1;
        switch (val) {
            case "A":
                toReturn = 0;
                break;
            case "B":
                toReturn = 1;
                break;
            case "C":
                toReturn = 2;
                break;
            case "D":
                toReturn = 3;
                break;
            case "E":
                toReturn = 4;
                break;
            case "F":
                toReturn = 5;
                break;
            case "G":
                toReturn = 6;
                break;
            case "H":
                toReturn = 7;
                break;
            case "I":
                toReturn = 8;
                break;
            case "J":
                toReturn = 9;
                break;
            default:
                toReturn = -1;
                break;
        }

        return toReturn;
    }

    /**
     * Converts back to letter from int.
     *
     * @param val Int value
     * @return Returns Character based on int
     */
    private static String convertIntToLetter(final int val) {
        String toReturn = "Z";
        switch (val) {
            case 0:
                toReturn = "A";
                break;
            case 1:
                toReturn = "B";
                break;
            case 2:
                toReturn = "C";
                break;
            case 3:
                toReturn = "D";
                break;
            case 4:
                toReturn = "E";
                break;
            case 5:
                toReturn = "F";
                break;
            case 6:
                toReturn = "G";
                break;
            case 7:
                toReturn = "H";
                break;
            case 8:
                toReturn = "I";
                break;
            case 9:
                toReturn = "J";
                break;
            default:
                toReturn = "Z";
                break;
        }

        return toReturn;
    }

    /**
     * Converts user column input for array usage.
     *
     * @param val Player input value
     * @return Returns column value for array
     */
    private static int convertUserColToProCol(final int val) {
        int toReturn = -1;
        switch (val) {
            case 1:
                toReturn = 0;
                break;
            case 2:
                toReturn = 1;
                break;
            case 3:
                toReturn = 2;
                break;
            case 4:
                toReturn = 3;
                break;
            case 5:
                toReturn = 4;
                break;
            case 6:
                toReturn = 5;
                break;
            case 7:
                toReturn = 6;
                break;
            case 8:
                toReturn = 7;
                break;
            case 9:
                toReturn = 8;
                break;
            case 10:
                toReturn = 9;
                break;
            default:
                toReturn = -1;
                break;
        }

        return toReturn;
    }

    /**
     * Converts AI column input for array usage.
     *
     * @param val AI input value
     * @return Returns column value for array
     */
    private static int convertCompColToRegular(final int val) {
        int toReturn = -1;
        switch (val) {
            case 0:
                toReturn = 1;
                break;
            case 1:
                toReturn = 2;
                break;
            case 2:
                toReturn = 3;
                break;
            case 3:
                toReturn = 4;
                break;
            case 4:
                toReturn = 5;
                break;
            case 5:
                toReturn = 6;
                break;
            case 6:
                toReturn = 7;
                break;
            case 7:
                toReturn = 8;
                break;
            case 8:
                toReturn = 9;
                break;
            case 9:
                toReturn = 10;
                break;
            default:
                toReturn = -1;
                break;
        }

        return toReturn;
    }

}
