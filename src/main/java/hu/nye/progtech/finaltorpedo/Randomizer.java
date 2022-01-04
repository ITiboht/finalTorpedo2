package hu.nye.progtech.finaltorpedo;

import java.util.Random;

/**
 * Randomizes positions for AI.
 */
public final class Randomizer {

    /**
     * Empty random variable.
     */
    private static Random theInstance = null;

    /**
     * Generates a new random position.
     *
     * @return Returns new random position
     */
    public static Random getInstance() {
        if (theInstance == null) {
            theInstance = new Random();
        }
        return theInstance;
    }

    /**
     * Returns n value.
     *
     * @param n n value
     * @return Returns n value
     */
    public static int nextInt(final int n) {
        return Randomizer.getInstance().nextInt(n);
    }

    /**
     * Returns random value between min and max.
     *
     * @param min Min value
     * @param max Max value
     * @return Returns random int.
     */
    public static int nextInt(final int min, final int max) {
        return min + Randomizer.nextInt(max - min + 1);
    }

}
