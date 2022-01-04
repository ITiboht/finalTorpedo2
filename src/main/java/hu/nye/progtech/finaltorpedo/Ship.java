package hu.nye.progtech.finaltorpedo;

/**
 * Ship data.
 */
public class Ship {
    /**
     * Ship facing UNSET int value.
     */
    public static final int UNSET = -1;
    /**
     * Ship facing HORIZONTAL int value.
     */
    public static final int HORIZONTAL = 0;
    /**
     * Ship facing VERTICAL int value.
     */
    public static final int VERTICAL = 1;
    /**
     * Ship row pos.
     */
    private int srow;
    /**
     * Ship column pos.
     */
    private int scolumn;
    /**
     * Ship length.
     */
    private int slength;
    /**
     * Ship facing.
     */
    private int sfacing;

    /**
     * Preset Ship data.
     *
     * @param length Ship length
     */
    public Ship(final int length) {
        this.srow = -1;
        this.scolumn = -1;
        this.slength = length;
        this.sfacing = UNSET;
    }

    /**
     * Gets row value.
     *
     * @return Returns int value
     */
    public int getRow() {
        return srow;
    }

    /**
     * Gets column value.
     *
     * @return Returns int value
     */
    public int getColumn() {
        return scolumn;
    }

    /**
     * Gets ship length.
     *
     * @return Returns int value
     */
    public int getLength() {
        return slength;
    }

    /**
     * Gets facing value.
     *
     * @return Returns facing value
     */
    public int getFacing() {
        return sfacing;
    }

    /**
     * Sets facing value.
     *
     * @param facing Returns facing value.
     */
    public void setFacing(final int facing) {
        if (facing != UNSET && facing != HORIZONTAL && facing != VERTICAL) {
            throw new IllegalArgumentException("Invalid facing.");
        }
        this.sfacing = facing;
    }

    /**
     * Checks if location is set.
     *
     * @return Returns true/false
     */
    public boolean isLocationSet() {
        return srow != -1 && scolumn != -1;
    }

    /**
     * Checks if facing is set.
     *
     * @return Returns ture/false
     */
    public boolean isFacingSet() {
        return sfacing != UNSET;
    }

    /**
     * Sets ship location.
     *
     * @param row    Row value
     * @param column Column value
     */
    public void setLoc(final int row, final int column) {
        this.srow = row;
        this.scolumn = column;
    }

}
