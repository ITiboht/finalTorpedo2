package hu.nye.progtech.finaltorpedo;

/**
 * Main map print class.
 */
public class GameController extends Map {

    /**
     * Private variable for points.
     */
    private int points;

    /**
     * Character for miss.
     */
    private char miss = 'X';

    /**
     * Character for hit.
     */
    private char hit = '+';

    /**
     * If the location got from user input or computer input
     * then changes the position's character to hit and adds
     * one point to the player.
     *
     * @param row       The map row position
     * @param column    the map column position
     */
    public void markHit(final int row, final int column) {
        getMap()[row][column].markHit();
        points++;
        setPoints(points);
    }

    /**
     * Prints out the map every turn.
     *
     * @param type  Different map types (Enemy, Own, Ships)
     */
    private void generalPrintMethod(final int type) {
        System.out.println();
        System.out.print("  ");
        for (int i = 1; i <= getColumns(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int endLetterForLoop = (getRows()) + 65;
        for (int i = 65; i < endLetterForLoop; i++) {
            char theChar = (char) i;
            System.out.print(theChar + " ");

            for (int j = 0; j < getColumns(); j++) {
                if (type == 0) {
                    if (getMap()[switchCounterToIntegerForArray(i)]
                            [j].isNotGuessed()) {
                        System.out.print("~ ");
                    } else if (getMap()[switchCounterToIntegerForArray(i)]
                            [j].checkMiss()) {
                        System.out.print(miss + " ");
                    } else if (getMap()[switchCounterToIntegerForArray(i)]
                            [j].checkHit()) {
                        System.out.print(hit + " ");
                    }
                } else if (type == 1) {
                    if (getMap()[switchCounterToIntegerForArray(i)]
                            [j].hasShip()) {
                        if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 2) {
                            System.out.print("D ");
                        } else if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 3) {
                            System.out.print("C ");
                        } else if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 4) {
                            System.out.print("B ");
                        } else if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 5) {
                            System.out.print("A ");
                        }
                    } else if (!(getMap()[switchCounterToIntegerForArray(i)]
                            [j].hasShip())) {
                        System.out.print("~ ");
                    }
                } else {
                    if (getMap()[switchCounterToIntegerForArray(i)]
                            [j].checkHit()) {
                        System.out.print(hit + " ");
                    } else if (getMap()[switchCounterToIntegerForArray(i)]
                            [j].hasShip()) {
                        if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 2) {
                            System.out.print("D ");
                        } else if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 3) {
                            System.out.print("C ");
                        } else if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 4) {
                            System.out.print("B ");
                        } else if (getMap()[switchCounterToIntegerForArray(i)]
                                [j].getLengthOfShip() == 5) {
                            System.out.print("A ");
                        }
                    } else if (!(getMap()[switchCounterToIntegerForArray(i)]
                            [j].hasShip())) {
                        System.out.print("~ ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * If location doesn't have ship mark position as miss.
     *
     * @param row       Map row position
     * @param column    Map column position
     */
    public void markMiss(final int row, final int column) {
        getMap()[row][column].markMiss();
    }

    /**
     * Checks if location has already been guessed.
     *
     * @param row       Map row position
     * @param column    Map column position
     * @return          Returns true or false
     */
    public boolean alreadyGuessed(final int row, final int column) {
        return !getMap()[row][column].isNotGuessed();
    }

    /**
     * Checks if location has ship.
     *
     * @param row       Map row position
     * @param column    Map column position
     * @return          Returns true or false
     */
    public boolean hasShip(final int row, final int column) {
        return getMap()[row][column].hasShip();
    }

    /**
     * Prints current map status.
     */
    public void printStatus() {
        generalPrintMethod(0);
    }

    /**
     * Prints ships.
     */
    public void printShips() {
        generalPrintMethod(1);
    }

    /**
     * Prints map and ships combined.
     */
    public void printCombined() {
        generalPrintMethod(2);
    }

    /**
     * If player/computer reaches 17 points (num of ship tiles)
     * foe is declared loser.
     *
     * @return  Returns true or false
     */
    public boolean hasLost() {
        return getPoints() >= 17;
    }

    /**
     * Places ship tiles in desired location.
     *
     * @param s Ship
     */
    public void addShip(final Ship s) {
        int row = s.getRow();
        int column = s.getColumn();
        int length = s.getLength();
        int facing = s.getFacing();

        if (facing == 0) {
            for (int i = column; i < column + length; i++) {
                getMap()[row][i].setShip(true);
                getMap()[row][i].setLengthOfShip(length);
                getMap()[row][i].setFacingOfShip(facing);
            }
        } else if (facing == 1) {
            for (int i = row; i < row + length; i++) {
                getMap()[i][column].setShip(true);
                getMap()[i][column].setLengthOfShip(length);
                getMap()[i][column].setFacingOfShip(facing);
            }
        }
    }

    /**
     * Switches to Integer value based on input.
     *
     * @param val   Integer value
     * @return      Returns integer
     */
    public int switchCounterToIntegerForArray(final int val) {
        int toReturn = -1;
        switch (val) {
            case 65:
                toReturn = 0;
                break;
            case 66:
                toReturn = 1;
                break;
            case 67:
                toReturn = 2;
                break;
            case 68:
                toReturn = 3;
                break;
            case 69:
                toReturn = 4;
                break;
            case 70:
                toReturn = 5;
                break;
            case 71:
                toReturn = 6;
                break;
            case 72:
                toReturn = 7;
                break;
            case 73:
                toReturn = 8;
                break;
            case 74:
                toReturn = 9;
                break;
            case 75:
                toReturn = 10;
                break;
            case 76:
                toReturn = 11;
                break;
            case 77:
                toReturn = 12;
                break;
            case 78:
                toReturn = 13;
                break;
            case 79:
                toReturn = 14;
                break;
            case 80:
                toReturn = 15;
                break;
            case 81:
                toReturn = 16;
                break;
            case 82:
                toReturn = 17;
                break;
            case 83:
                toReturn = 18;
                break;
            case 84:
                toReturn = 19;
                break;
            case 85:
                toReturn = 20;
                break;
            case 86:
                toReturn = 21;
                break;
            case 87:
                toReturn = 22;
                break;
            case 88:
                toReturn = 23;
                break;
            case 89:
                toReturn = 24;
                break;
            case 90:
                toReturn = 25;
                break;
            default:
                toReturn = -1;
                break;
        }

        if (toReturn == -1) {
            throw new IllegalArgumentException("ERROR OCCURRED IN SWITCH");
        } else {
            return toReturn;
        }
    }

}
