package by.boltuho.sudoku;

public class SudokuSizeValidator implements SudokuValidator {
    private final int size;

    public SudokuSizeValidator(int size) {
        this.size = size;
    }

    public void validate(int[][] grid) {
        if (grid.length != size) {
            throw new IllegalArgumentException(String.format("Table has %d rows instead of %d", grid.length, size));
        }

        for (int i = 0; i < grid.length; i++) {
            final int[] row = grid[i];
            if (row.length != size) {
                throw new IllegalArgumentException(String.format("Row %d has %d size instead of %d", i, row.length, size));
            }
        }
    }
}
