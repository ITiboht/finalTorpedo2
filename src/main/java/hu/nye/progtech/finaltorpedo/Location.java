package hu.nye.progtech.finaltorpedo;

/**
 * Mainly checks different location data.
 */
public class Location {

    /**
     * Status value if pos is not guessed.
     */
    public static final int NOT_GUESSED = 0;
    /**
     * Status value if pos is hit.
     */
    public static final int HIT = 1;
    /**
     * Status value if pos is miss.
     */
    public static final int MISS = 2;

    /**
     * Status value if pos has ship.
     */
    private boolean hasShip;
    /**
     * Status value of pos.
     */
    private int status;
    /**
     * Int value of ship length.
     */
    private int lengthOfShip;

    /**
     * Int value of ship facing.
     */
    private int facingOfShip;

    /**
     * Default map position information.
     * Changes based on user and AI input.
     */
    public Location() {
        status = 0;
        hasShip = false;
        lengthOfShip = -1;
        facingOfShip = -1;
    }

    /**
     * Checks if chosen position has hit.
     *
     * @return Returns true or false
     */
    public boolean checkHit() {
        return status == HIT;
    }

    /**
     * Checks if chosen position has missed.
     *
     * @return Returns true or false
     */
    public boolean checkMiss() {
        return status == MISS;
    }

    /**
     * Checks if chose position has been guessed.
     *
     * @return Returns true or false
     */
    public boolean isNotGuessed() {
        return status == NOT_GUESSED;
    }

    /**
     * Sets chosen position to hit.
     */
    public void markHit() {
        setStatus(HIT);
    }

    /**
     * Sets chosen position to miss.
     */
    public void markMiss() {
        setStatus(MISS);
    }

    /**
     * Sets map values to hasShip at placed ship.
     *
     * @param val Sets position to true or false
     *            depending on if ship are placed
     */
    public void setShip(final boolean val) {
        this.hasShip = val;
    }

    /**
     * Checks if position has ship.
     *
     * @return Returns true or false.
     */
    public boolean hasShip() {
        return hasShip;
    }

    /**
     * Changes map position to status.
     *
     * @param pstatus Sets status value
     */
    public void setStatus(final int pstatus) {
        this.status = pstatus;
    }

    /**
     * Checks current ship length.
     *
     * @return Returns ship length
     */
    public int getLengthOfShip() {
        return lengthOfShip;
    }

    /**
     * Sets current ship length.
     *
     * @param plengthOfShip Sets ship length value
     */
    public void setLengthOfShip(final int plengthOfShip) {
        this.lengthOfShip = plengthOfShip;
    }

    /**
     * Sets ship facing.
     *
     * @param pfacingOfShip Returns facing value
     */
    public void setFacingOfShip(final int pfacingOfShip) {
        this.facingOfShip = pfacingOfShip;
    }

}
