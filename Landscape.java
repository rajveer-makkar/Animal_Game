import java.io.PrintStream;

public class Landscape {
    public static final char CREEK = 'c';
    public static final char BRIDGE = 'b';
    public static final char FALLEN_TREE = 'f';
    public static final char LANDSLIDE = 'l';

    public static void main(String[] var0) {
        PrintStream var10000 = System.out;
        String var10001 = new String(getEasyTrail());
        var10000.println("easy:    " + var10001);
        var10000 = System.out;
        var10001 = new String(getComplexTrail());
        var10000.println("complex: " + var10001);
    }

    private static int generateRandomNumber(int var0, int var1) {
        int var2 = var1 - var0 + 1;
        return (int)(Math.random() * (double)var2 + (double)var0);
    }

    public static int getNatureFeatureAction(char var0) {
        switch(var0) {
            case 'b':
                return 4;
            case 'c':
                return -2;
            case 'f':
                return -3;
            case 'l':
                return -5;
            default:
                throw new IllegalArgumentException("Nature Feature does not exist: '" + var0 + "'");
        }
    }

    private static void setNatureFeatureInTrail(char[] var0, char var1, boolean var2) {
        boolean var3 = false;
        boolean var4 = false;

        while(true) {
            byte var5;
            int var6;
            int var8;
            int var9;
            do {
                if (var4) {
                    return;
                }

                var5 = 1;
                var6 = var0.length - 2;
                var9 = generateRandomNumber(var5, var6);
                if (!var2) {
                    break;
                }

                int var7 = getNatureFeatureAction(var1);
                var8 = var9 + var7;
            } while(var8 < var5 || var8 > var6);

            if (var0[var9] == '_') {
                var4 = true;
                var0[var9] = var1;
            }
        }
    }

    private static void setNatureFeatureInTrail(char[] var0, char var1, boolean var2, int var3) {
        if (var3 >= 1) {
            int var4 = var3 == 1 ? 1 : generateRandomNumber(1, var3);

            for(int var5 = 0; var5 < var4; ++var5) {
                setNatureFeatureInTrail(var0, var1, var2);
            }

        }
    }

    private static char[] initialiseTrial(int var0) {
        char[] var1 = new char[var0];

        for(int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = '_';
        }

        return var1;
    }

    public static char[] getEasyTrail() {
        int var0 = generateRandomNumber(12, 20);
        char[] var1 = initialiseTrial(var0);
        setNatureFeatureInTrail(var1, 'c', true);
        setNatureFeatureInTrail(var1, 'b', true);
        setNatureFeatureInTrail(var1, 'f', true);
        setNatureFeatureInTrail(var1, 'l', true);
        return var1;
    }

    public static char[] getComplexTrail() {
        int var0 = generateRandomNumber(15, 30);
        char[] var1 = initialiseTrial(var0);
        byte var2 = 3;
        setNatureFeatureInTrail(var1, 'c', false, var2);
        setNatureFeatureInTrail(var1, 'b', false, var2);
        setNatureFeatureInTrail(var1, 'f', false, var2);
        setNatureFeatureInTrail(var1, 'l', false, var2);
        return var1;
    }
}