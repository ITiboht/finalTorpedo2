package hu.nye.progtech.finaltorpedo;

/**
 * All players data.
 */
public class Player {
    /**
     * Sets different ship sizes.
     */
    private static final int[] SHIP_LENGTH = {2, 3, 3, 4, 5};
    /**
     * Sets amount of ships.
     */
    private static final int NUM_OF_SHIPS = 5;

    /**
     * Ship variable.
     */
    private Ship[] ships;

    /**
     * User map.
     */
    private GameController playerMap;
    /**
     * Ai map.
     */
    private GameController enemyMap;

    /**
     * Places ships until num of ships is 5.
     */
    public Player() {
        //if (num_of_ships != 5)
        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            Ship tempShip = new Ship(SHIP_LENGTH[i]);
            ships[i] = tempShip;
        }

        playerMap = new GameController();
        enemyMap = new GameController();
    }

    /**
     * Checks the number of ships left.
     *
     * @return Returns how many ships are left
     */
    public int numOfShipsLeft() {
        int counter = NUM_OF_SHIPS;
        for (Ship s : ships) {
            if (s.isLocationSet() && s.isFacingSet()) {
                counter--;
            }
        }
        return counter;
    }

    /**
     * Gets Ship data.
     *
     * @return Returns Ship value.
     */
    public Ship[] getShips() {
        return ships;
    }

    /**
     * Gets Player map data.
     *
     * @return Returns player Map
     */
    public GameController getPlayerMap() {
        return playerMap;
    }

    /**
     * Gets Ai map data.
     *
     * @return returns Ai Map
     */
    public GameController getEnemyMap() {
        return enemyMap;
    }
}
