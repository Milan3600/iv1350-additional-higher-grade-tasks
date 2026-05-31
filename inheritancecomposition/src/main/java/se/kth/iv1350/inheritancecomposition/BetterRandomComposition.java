package se.kth.iv1350.inheritancecomposition;

import java.util.Random;

/**
 * Provides random number generation by composing a {@link java.util.Random} instance.
 * Adds a convenience method for generating integers within a specified inclusive range.
 */
public class BetterRandomComposition
{
    private Random random = new Random();

/**
 * Generates a random integer within the inclusive range [min, max].
 * @param min the lowest possible value
 * @param max the highest possible value
 * @return a random integer between min and max (inclusive)
 */
    public int nextIntInRange(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }
}