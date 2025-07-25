import java.util.Random;
public class NatureFeature{
    private char featureType;
    private String featureName;
    private int spacePenalty;
    private int creekCount=0;
    private int bridgeCount=0;
    private int treeCount=0;
    private int landslideCount=0;


    public NatureFeature(char type){
        this.featureType=type;
        assignFeature(type);

    }
    public void assignFeature(char type) {
        switch (type) {
            case 'c':
                if ((Game.easyFlag && creekCount >= 1) || (Game.complexFlag && creekCount >= 3)) {
                    assignFeature(getRandomType());
                    return;
                }
                featureName = "Creek";
                spacePenalty = -2;
                creekCount += 1;
                break;

            case 'b':
                if ((Game.easyFlag && bridgeCount >= 1) || (Game.complexFlag && bridgeCount >= 3)) {
                    assignFeature(getRandomType());
                    return;
                }
                featureName = "Bridge";
                spacePenalty = +4;
                bridgeCount += 1;
                break;

            case 'f':
                if ((Game.easyFlag && treeCount >= 1) || (Game.complexFlag && treeCount >= 3)) {
                    assignFeature(getRandomType());
                    return;
                }
                featureName = "Fallen tree";
                spacePenalty = -3;
                treeCount += 1;
                break;

            case 'l':
                if ((Game.easyFlag && landslideCount >= 1) || (Game.complexFlag && landslideCount >= 3)) {
                    assignFeature(getRandomType());
                    return;
                }
                featureName = "Landslide";
                spacePenalty = -5;
                landslideCount += 1;
                break;

            default:
                featureName = "Unknown";
                spacePenalty = 0;
        }
    }



    public char getRandomType() {
        char[] types = {'c', 'b', 'f', 'l'};
        return types[new Random().nextInt(types.length)];
    }


    public int getPenalty() {
        return spacePenalty;
    }
    public String getName(){
        return featureName;
    }
    public char getType() {
        return featureType;
    }
}