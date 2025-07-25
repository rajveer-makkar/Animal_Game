public class Trail {
    private NatureFeature[] features;

    public Trail(char[] trailData) {
        features = new NatureFeature[trailData.length];

        for (int i = 0; i < trailData.length; i++) {
            if (trailData[i] == '_' || trailData[i] == 'S' || trailData[i] == 'F') {
                features[i] = null;
            } else {
                features[i] = new NatureFeature(trailData[i]);
            }
        }
    }

    public NatureFeature getFeatureAt(int pos){
        if (pos >= 0 && pos < features.length){
            return features[pos];
        }
        else {
            return null;
        }
    }

    public int length(){
        return features.length;
    }

    public void display(int pos1, int pos2){
        for (int i = 0; i < features.length; i++) {
            if (i == pos1 && i == pos2) System.out.print("HC ");
            else if (i == pos1) System.out.print("H ");
            else if (i == pos2) System.out.print("C ");
            else if (i == 0) System.out.print("S ");
            else if (i == features.length - 1) System.out.print("F ");
            else System.out.print("_ ");
        }
        System.out.println();
    }
}