package by.boltuho.sudoku;

public class SudokuSolutionValidator implements SudokuValidator {
    private final SudokuStateValidator stateValidator;

    public SudokuSolutionValidator(SudokuStateValidator stateValidator) {
        this.stateValidator = stateValidator;
    }

    @Override
    public void validate(int[][] grid) {
        stateValidator.validate(grid);
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                int number = row[j];
                if (number == SudokuSolver.EMPTY_CELL_VALUE) {
                    throw new IllegalStateException(String.format("Cell (%d, %d) is empty", i, j));
                }
            }
        }
    }
}
