/**
* This defines the nature features that players can encounter on the trail.
* Each feature has a type, name and space penalty.
*
* Examples: Creek, Bridge, Fallen Tree, Ladder.
* 
* @author Rushil
* @version 1.0
*/
public class NatureFeature
{
    private final char featureType;
    private final String featureName;
    private final int spacePenalty; 
    // number of spaces player needs to move upon landing on this nature feature

    /**
    * Represents the absense of a nature feature
    */
    public static final NatureFeature NONE = new NatureFeature('_');

    /**
    * Default constructor- creates a feature with no penalty.
    */
    public NatureFeature()
    {
        this.featureType = '_';
        this.featureName = "None";
        this.spacePenalty = 0;
    }
    
    /**
    * Creates a nature features for the game, based on the specified
    * feature type.
    * Handles Creek, Bridge, Fallen Tree, Ladder, None.
    *
    * @param featureType The character feature code for this feature.
    */
    public NatureFeature(char featureType)
    {
        this.featureType = Character.toLowerCase(featureType);
        switch(featureType)
        {
            case 'c':
                featureName = "Creek";
                spacePenalty = -2;
                break;
            case 'b':
                featureName = "Bridge";
                spacePenalty = 4;
                break;
            case 'f':
                featureName = "Fallen Tree";
                spacePenalty = -3;
                break;
            case 'l':
                featureName = "Landslide";
                spacePenalty = -5;
                break;
            default:
                featureName = "None";
                spacePenalty = 0;
        }
    }

    /**
    * Gets the name of this feature. 
    *
    * @return The feature name string.
    */
    public String getFeatureName()
    {
        return featureName;
    }

    /**
    * Gets the type of this feature. 
    *
    * @return The feature type character.
    */
    public char getFeatureType()
    {
        return featureType;
    }

    /**
    * Gets the space penalty of this feature. 
    *
    * @return The space penalty.
    */
    public int getSpacePenalty()
    {
        return spacePenalty;
    }

    /**
    * Returns a string representation of this Nature Feature. 
    *
    * @return String describing this feature.
    */
    public String toString()
    {
        return "\nFeature Type: " + featureType + " Feature Name: " + featureName +
        " Space Penalty: " + spacePenalty + "\n";
    }

    // mutator methods not needed since fields are final
}
