package debug;


public class Debug {
    public static String getArrayPrint(int[] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int aMatrix : matrix) {
            sb.append(aMatrix).append("    ");
        }
        return sb.toString();
    }

    public static String getArrayPrint(boolean[] matrix) {
        StringBuilder sb = new StringBuilder();
        for (boolean aMatrix : matrix) {
            sb.append(aMatrix? 1 : 0).append("    ");
        }
        return sb.toString();
    }
}
