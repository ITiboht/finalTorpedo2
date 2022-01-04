package hu.nye.progtech.finaltorpedo;

/**
 * Main map logic.
 */
public class Map<N> {

    /**
     * Row map size.
     */
    private static final int ROW_SIZE = 10;

    /**
     * Column map size.
     */
    private static final int COLUMN_SIZE = 10;
    /**
     * Location variable for correct map size.
     */
    private Location[][] map;

    /**
     * Points counter.
     */
    private int points;

    /**
     * Amount of rows.
     */
    private int rows = ROW_SIZE;

    /**
     * Amount of columns.
     */
    private int columns = COLUMN_SIZE;

    /**
     * Creates the map with the correct sizes.
     */
    public Map() {

        map = new Location[rows][columns];

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                Location tempLoc = new Location();
                map[row][column] = tempLoc;
            }
        }
    }

    /**
     * Getter for Map.
     *
     * @return Returns Map data.
     */
    public Location[][] getMap() {
        return map;
    }

    /**
     * Getter for points.
     *
     * @return Gets points amount.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter for Points.
     *
     * @param ppoints Sets points amount.
     */
    public void setPoints(final int ppoints) {
        this.points = ppoints;
    }

    /**
     * Getter for Rows.
     *
     * @return Returns Row size.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Getter for Columns.
     *
     * @return Returns Column size.
     */
    public int getColumns() {
        return columns;
    }

}
