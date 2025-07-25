/**
* Defines a player in the game.
* Can be a real player or computer.
*
* @author Rushil
* @version 1.0
*/
public class Player
{
    private String name;
    private int position;
    private int score;

    /**
    * Default constructor that creates a player
    * with a default name, at 0 position, with 0 score.
    */
    public Player()
    {
        this.name = "Player 1";
        this.position = 0;
        this.score = 0;
    }

    /**
    * Creates a new Player with the help of the name.
    * For a new player, position and score will be 0.
    *
    * @param name The name of the player.
    */
    public Player(String name)
    {   
        // Calls the mutator that handles name validations too.
        setName(name);
        this.position = 0;
        this.score = 0;
    }

    /**
    * Returns the name of the player.
    * @return The player's name.
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * Returns the position of the player.
    * @return The player's position.
    */
    public int getPosition()
    {
        return position;
    }

    /**
    * Returns the score of the player.
    * @return The player's score.
    */
    public int getScore()
    {
        return score;
    }

    /**
    * Sets name of the player.
    * @param name Accepts the name of the player as String.
    * Name's length can range from 1-10 characters and cannot be empty.
    * Name cannot begin or end with spaces.
    *
    */
    public void setName(String name)
    {    
        if(name == null || name.length()<1 || name.length()>10 ||
        name.startsWith(" ")||name.endsWith(" "))
        {
            throw GameError.INVALID_NAME;
        }
        this.name = name;
    }

    /**
    * Sets the position of the player.
    * @param position Accepts the position of the player as an integer.
    * Position cannot be less than 0 in the game.
    *
    */
    public void setPosition(int position)
    {
        if(position<0)
        {
            this.position = 0;
        }
        else
        {
            this.position = position;
        }
    }

    /**
    * Sets the score of the player.
    * @param score Accepts the score of the player as an integer.
    * Score cannot be less than 0 in the game.
    *
    */
    public void setScore(int score)
    {
        if(score < 0)
        {
            this.score = 0;
        }
        else
        {
            this.score = score;
        }
    }

    /**
    * Returns a String representation of the Player.
    *
    * @return A string containing key information of this object.
    */
    public String toString()
    {
        return "Name: "+this.name+" Position: "+ this.position+ 
        " Score: "+ this.score;
    }

    /**
    * Increases/Decreases the score of the player by some integer points.
    * Score cannot fall below 0.
    *
    * @param points Points to be incremented or decremented.
    */
    public void updateScore(int points)
    {
        this.score += points;
        // if user goes below 0 points, reset them to 0
        if(this.score < 0)
        {
            this.score = 0;
        }
    }
    
}
