package se.kth.iv1350.inheritancecomposition;

import java.util.Random;

/**
 * Extends {@link java.util.Random} using inheritance and adds a convenience method
 * for generating random integers within a specified inclusive range.
 */
public class BetterRandomInheritance extends Random
{
    /**
    * Generates a random integer within the inclusive range [min, max].
    * @param min the lowest possible value
    * @param max the highest possible value
    * @return a random integer between min and max (inclusive)
    */
    public int nextIntInRange(int min, int max)
    {
        return nextInt(max - min + 1) + min;
    }
}