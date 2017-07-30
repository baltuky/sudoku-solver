package by.boltuho.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuStateValidator implements SudokuValidator {
    private final int boxSize;

    public SudokuStateValidator(int boxSize) {
        this.boxSize = boxSize;
    }

    public void validate(int[][] grid) {
        //todo Implement business logic

        for (int i = 0; i < 9; i++) {
            if (!isValidSquare(grid, i)) {
                throw new IllegalStateException(String.format("There are duplicates in square #%d", i + 1));
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (!isValidRow(grid, i)) {
                throw new IllegalStateException(String.format("There are duplicates in row #%d", i + 1));
            }
            if (!isValidColumn(grid, i)) {
                throw new IllegalStateException(String.format("There are duplicates in column #%d", i + 1));
            }
        }
    }

    private boolean isValidRow(int[][] grid, int i) {
        Set<Integer> numbers = new HashSet<>();
        int counter = 0;
        for (int j = 0; j < grid[i].length; j++) {
            int number = grid[i][j];
            if (!isEmptyCellValue(number)) {
                numbers.add(number);
                counter++;
            }
        }
        return numbers.size() == counter;
    }

    private boolean isValidColumn(int[][] grid, int j) {
        Set<Integer> numbers = new HashSet<>();
        int counter = 0;

        for (int i = 0; i < grid.length; i++) {
            final int number = grid[i][j];
            if (!isEmptyCellValue(number)) {
                numbers.add(number);
                counter++;
            }
        }

        return numbers.size() == counter;
    }

    private boolean isEmptyCellValue(int value) {
        return value == 0;
    }

    private boolean isValidSquare(int[][] grid, int square) {
        Set<Integer> numbers = new HashSet<>();
        int counter = 0;

        final int rowStart = square / boxSize * boxSize;
        final int colStart = square % boxSize * boxSize;

        for (int i = rowStart; i < rowStart + boxSize; i++) {
            for (int j = colStart; j < colStart + boxSize; j++) {
                final Integer number = grid[i][j];
                if (!isEmptyCellValue(number)) {
                    numbers.add(number);
                    counter++;
                }
            }
        }

        return numbers.size() == counter;
    }
}
