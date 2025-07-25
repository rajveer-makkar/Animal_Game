/**
* Trail is an array of nature features.
* It mimics the trail of nature features player is moving along.
*
* @author Rushil
* @version 1.0
*/
public class Trail
{
    private final NatureFeature[] natureTrail;

    /**
    * Default constructor - initializes an empty trail.
    */
    public Trail()
    {
        natureTrail = new NatureFeature[0];
    }

    /**
    * Creates a trail of NatureFeatures from an array of char codes,
    * where char codes represent nature feature code.
    *
    * @param trailChars Array of Characters representing nature feature codes.
    */
    public Trail(char[] trailChars)
    {
        natureTrail = new NatureFeature[trailChars.length];
        for (int i = 0; i < trailChars.length; i++)
        {
            char c = trailChars[i];
            natureTrail[i] = (c == '_') ? 
                    NatureFeature.NONE : 
                    new NatureFeature(c);
        }
    }

    /**
    * Gets the length of the trail.
    *
    * @return The length of the trail. 
    */
    public int getLength()
    {
        return natureTrail.length;
    }

    /**
    * Gets the trail array of nature features.
    * 
    * @return An array of nature features.
    */
    public NatureFeature[] getNatureTrail()
    {
        return natureTrail;
    }

    /**
    * Gets the Nature Feature at the specified position on the trail.
    * 
    * @param position The position on the trail [0,length of trail).
    * @return The nature feature at that position.
    * @throws GameError If the position is invalid.
    */
    public NatureFeature getFeatureAt(int position)
    {
        if (position >= 0 && position < natureTrail.length)
        {
            return natureTrail[position];
        }
        else
        {
            throw GameError.invalidTrailPosition(position, natureTrail.length);
        }
    }

    /**
    * Returns a string representation of the trail.
    *
    * @return String listing all features in the trail.
    */
    public String toString()
    {
        StringBuilder trailFeatures = new StringBuilder();
        for (NatureFeature feature : natureTrail)
        {
            trailFeatures.append(feature.toString());
        }
        return trailFeatures.toString();
    }

    // no mutators required since field is final
}
