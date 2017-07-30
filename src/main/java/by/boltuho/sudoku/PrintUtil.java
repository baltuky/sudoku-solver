package by.boltuho.sudoku;

public class PrintUtil {
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                int number = row[j];
                System.out.print(number + " ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("  ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }

    public static void printHorizontalLine() {
        System.out.println(new String(new char[80]).replace("\0", "-"));
    }

}
