package debug;


public class Debug {
    public static String getArrayPrint(int[] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int aMatrix : matrix) {
            sb.append(aMatrix).append("    ");
        }
        return sb.toString();
    }
}
