package debug;

public class Debug {
    public static String getArrayPrint(int[] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i] + "    ");
        }
        return sb.toString();
    }
}
