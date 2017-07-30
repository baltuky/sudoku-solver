package by.boltuho.sudoku;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BackTrackSudokuSolverTest {
    private final int[][] board;
    private SudokuSolver solver;
    private SudokuStateValidator stateValidator;
    private SudokuValidator solutionValidator;

    public BackTrackSudokuSolverTest(int[][] board) {
        this.board = board;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> boards() {
        SudokuGenerator sudokuGenerator = new SudokuGenerator(9);
        List<Object[]> boards = new ArrayList<>();

        for (int i = 0; i < 2_00; i++) {
            boards.add(new Object[]{sudokuGenerator.nextBoard(35)});
        }

        return boards;
    }

    @Before
    public void setUp() {
        stateValidator = new SudokuStateValidator(3);
        solver = new BackTrackSudokuSolver(stateValidator);
        solutionValidator = new SudokuSolutionValidator(stateValidator);
    }

    @Test
    public void solve() {
        solutionValidator.validate(solver.solve(board));
    }
}