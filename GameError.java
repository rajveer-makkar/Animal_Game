/**
* Defines run-time errors that can be thrown during the execution of the game.
*
* @author Rushil
* @version 1.0
*/
public class GameError extends RuntimeException
{
    /**
    * Error code associated with this GameError.
    * Can't be changed once initialized.
    */
    private final String code;

    /**
    * Error code for an invalid player name.
    */
    public static final String INVALID_NAME_CODE = "INVALID_NAME";

    /**
    * Error code for an invalid trail position.
    */
    public static final String INVALID_POSITION_CODE = "INVALID_TRAIL_POSITION";

    /**
    * Pre-defined GameError instance for invalid player name
    */
    public static final GameError INVALID_NAME = new GameError(
        INVALID_NAME_CODE,
        "Invalid Name: Name must be between 1 and"
        + " 10 characters without any trailing spaces"
        );

    /**
    * Creates a GameError with specified code and message.
    *
    * @param code The error code
    * @param message The error message
    */
    public GameError(String code, String message)
    {   
        super(message);
        this.code = code;
    }

    /**
    * Gets the error code.
    *
    * @return The error code string
    */
    public String getCode()
    {
        return code;
    }

    /**
    * Creates a new GameError for accessing an invalid trail position.
    * 
    * @param position The invalid position that was attempted.
    * @param max The maximum allowed trail position (length of trail)
    * @return A new game error with appropriate message
    */
    public static GameError invalidTrailPosition(int position, int max)
    {
        return new GameError(
            INVALID_POSITION_CODE,
            "Invalid trail position: " + position + " (valid range: 0-" +
             (max-1) + ")");
    }

    /**
    * Returns a string representation of this error
    *
    * @return String with code and message
    */
    public String toString()
    {
        return code + ": " + getMessage();
    }

    // no mutator because the field is final
}
