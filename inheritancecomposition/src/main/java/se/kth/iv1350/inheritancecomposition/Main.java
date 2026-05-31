package se.kth.iv1350.inheritancecomposition;

/**
 * Starts the program.
 */
public class Main
{
    /**
     * Runs the program.
     * @param args No parameters are entered.
     */
    public static void main(String[] args)
    {
        BetterRandomInheritance inheritanceRandom =
                new BetterRandomInheritance();

        BetterRandomComposition compositionRandom =
                new BetterRandomComposition();

        System.out.println("Using inheritance:");
        System.out.println(
                inheritanceRandom.nextIntInRange(1, 10));

        System.out.println();

        System.out.println("Using composition:");
        System.out.println(
                compositionRandom.nextIntInRange(1, 10));
    }
}