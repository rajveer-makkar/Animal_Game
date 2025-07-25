import java.util.Random;

/**
* Dice mimics a real world die and is used to generate
* random numbers in a range.
*
* This class provides a static method to role a standard 4-sided die,
* but also supports creating a new dice with custom number of sides.
*
* @author Rushil
* @version 1.0
*/
public class Dice
{
    private final int numberOfSides;

    /**
    * Default Constructor that creates a 4-sided dice.
    */
    public Dice()
    {
        this.numberOfSides = 4;
    }

    /**
    * Creates a Dice object with specific number of sides.
    * 
    * @param numberOfSides The number of sides for this die.
    */
    public Dice(int numberOfSides)
    {
        this.numberOfSides = numberOfSides;
    }

    /**
    * Returns the number of sides of this die
    *
    * @return number of sides of the die
    */
    public int getNumberOfSides()
    {
        return this.numberOfSides;
    }
    /**
    * Static Method that rolls a standard 4 sided die and returns a number
    * between 1 and 4.
    * This method uses a new random object for every call.
    * 
    * @return A random number between 1 and 4.
    */
    public static int roll()
    {  
        return new Random().nextInt(4)+1;
    }

    /**
    * Method that rolls a n sided die object and returns a number
    * between 1 and n.
    * This method uses a new random object for every call.
    * 
    * @return A random number between 1 and n.
    */
    public int rollCustom()
    {
        // a random instance variable would have also worked
        return new Random().nextInt(numberOfSides) + 1;
    }    
    
    /**
    * Returns a string represtation of this die 
    *
    * @return A string with the number of sides
    */
    public String toString()
    {
        return "Dice with " + numberOfSides + " number of sides.";
    }

    // no mutators required because fields are final

}
