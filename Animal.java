/**
* Represents an Animal in the Game.
* Each animal has a name and some points associated with it.
* Those points are a part of Game logic and player will
* lose or gain get points depending upon what animal they sight.
*
* @author Rushil
* @version 1.0
*/
public class Animal
{
    private final String name;
    private final int points;

    /**
    * Default constructor that creates an animal with an empty name
    * and 0 points.
    */
    public Animal()
    {
        this.name = "";
        this.points = 0;
    }

    /**
    * Creates an Animal with a specified name and point value.
    * 
    * @param name The name of the animal.
    * @param points The points associated with the animal.
    */
    public Animal(String name, int points)
    {
        this.name = name;
        this.points = points;
    }

    /**
    * Returns the name of the animal.
    * @return The animal's name.
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * Returns the points associated with the animal.
    * @return The animal's points.
    */
    public int getPoints()
    {
        return this.points;
    }

    /**
    * Returns a String representation of the Animal.
    *
    * @return A string containing key information of this object.
    */
    public String toString()
    {
        return "Animal Name: " + name + " Points: " + points;
    }

    // no mutators because fields are final
}
