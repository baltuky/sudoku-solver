package by.boltuho.sudoku;

public interface SudokuSolver {
    int MAX_CELL_VALUE = 9;
    int EMPTY_CELL_VALUE = 0;
    int MIN_CELL_VALUE = 1;

    int[][] solve(final int[][] grid);
}
