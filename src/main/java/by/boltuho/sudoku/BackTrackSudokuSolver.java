package by.boltuho.sudoku;

import java.util.Arrays;

import javafx.util.Pair;

public class BackTrackSudokuSolver implements SudokuSolver {

    private final SudokuValidator stateValidator;

    public BackTrackSudokuSolver(SudokuStateValidator stateValidator) {
        this.stateValidator = stateValidator;
    }

    public int[][] solve(final int[][] grid) {
        int[][] result = new int[grid.length][grid.length];

        copyInitialStateFromOriginalGrid(grid, result);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; ) {
                if (grid[i][j] != EMPTY_CELL_VALUE) {
                    j++;
                    continue;
                }

                if (incrementCellValue(result, i, j) || bruteForceCellValue(result, i, j)) {
                    j++;
                } else {
                    result[i][j] = EMPTY_CELL_VALUE;

                    Pair<Integer, Integer> position;
                    int cellValue;
                    do {
                        position = findNewNumberPosition(grid, i, j);
                        i = position.getKey();
                        j = position.getValue();

                        cellValue = result[i][j];
                        if (cellValue == MAX_CELL_VALUE) {
                            result[i][j] = EMPTY_CELL_VALUE;
                        }
                    } while (cellValue == MAX_CELL_VALUE);
                }
            }
        }

        return result;
    }

    private boolean bruteForceCellValue(int[][] result, int i, int j) {
        while (result[i][j] < MAX_CELL_VALUE) {
            if (incrementCellValue(result, i, j)) {
                return true;
            }
        }
        return false;
    }

    private Pair<Integer, Integer> findNewNumberPosition(int[][] sourceGrid, int i, int j) {
        int startCol = j - 1;
        for (int k = i; k >= 0; k--) {
            for (int l = startCol; l >= 0; l--) {
                if (sourceGrid[k][l] == EMPTY_CELL_VALUE) {
                    return new Pair<>(k, l);
                }
            }
            startCol = sourceGrid[i].length - 1;
        }
        return new Pair<>(i, j);
    }

    private void copyInitialStateFromOriginalGrid(int[][] grid, int[][] result) {
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            result[i] = Arrays.copyOf(row, row.length);
        }
    }

    private boolean incrementCellValue(int[][] grid, int i, int j) {
        grid[i][j] = grid[i][j] == EMPTY_CELL_VALUE ? MIN_CELL_VALUE : grid[i][j] + 1;
        return isValidGridState(grid);
    }

    private boolean isValidGridState(int[][] grid) {
        try {
            stateValidator.validate(grid);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
