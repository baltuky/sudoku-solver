package by.boltuho.sudoku;

public class Application {
    public static void main(String[] args) {
        final int[][] grid = {
                {0, 0, 6, 0, 0, 8, 5, 0, 0},
                {0, 0, 0, 0, 7, 0, 6, 1, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 9, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 8, 0, 0},
                {4, 0, 0, 5, 3, 0, 0, 0, 0},
                {1, 0, 7, 0, 5, 3, 0, 0, 0},
                {0, 5, 0, 0, 6, 4, 0, 0, 0},
                {3, 0, 0, 1, 0, 0, 0, 6, 0},
        };


        SudokuValidator sizeValidator = new SudokuSizeValidator(9);
        sizeValidator.validate(grid);

        SudokuStateValidator stateValidator = new SudokuStateValidator(3);
        stateValidator.validate(grid);

        SudokuValidator solutionValidator = new SudokuSolutionValidator(stateValidator);


        final SudokuSolver sudokuSolver = new BackTrackSudokuSolver(stateValidator);
        int[][] solution = sudokuSolver.solve(grid);

        solutionValidator.validate(solution);

        PrintUtil.printGrid(solution);

    }
}
