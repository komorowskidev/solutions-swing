package pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;
import pl.komorowskidev.solutionsswing.businesslogic.problems.Problem;
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService;
import pl.komorowskidev.solutionsswing.businesslogic.util.LinesProcessor;

import java.util.List;

@Component
public class PathsInTheGrid implements Problem {

    private final char START = '@';

    private final char END = '$';

    private final char SAFE_PATH = '+';

    private final char PIT = 'X';

    private LinesProcessor linesProcessor;

    private PathsInTheGridValidator validator;

    public PathsInTheGrid(SolutionService solutionsModel, LinesProcessor linesProcessor,
                          PathsInTheGridValidator validator) {
        solutionsModel.addProblem(this);
        this.linesProcessor = linesProcessor;
        this.validator = validator;
    }

    @Override
    public String getName() {
        return "Paths in the Grid";
    }

    @Override
    public String getDescription() {
        return "Travel from upper left corner (marked with " + START + ") to lower right corner (marked with " + END + "). You only can move by safe squares, and from each square you only can move in two directions - either right or down. \n" + PIT + " is a pit, " + SAFE_PATH + " is safe path. \nHow many different paths there exist from one corner to another under given rules?";
    }

    @Override
    public String getExampleData() {
        return "@ + + + +\n" +
                "+ + + X X\n" +
                "+ X + + +\n" +
                "+ + + X +\n" +
                "+ X + + X\n" +
                "+ + + + $";
    }

    @Override
    public String getSolution(String data) throws DataNotValidException {
        List<String> lines = linesProcessor.createLines(data);
        lines = linesProcessor.removeSpaces(lines);
        validator.validate(lines);
        char[][] elements = linesProcessor.createCharArray(lines);
        int result = 0;
        int rows = elements.length;
        int columns = elements[0].length;
        return "Number of paths: " + move(elements, columns, rows, 0, 0, result);
    }

    private int move(char[][] elements, int columns, int rows, int columnIndex, int rowIndex, int result) {
        if (onTheGrid(columns, rows, columnIndex, rowIndex)) {
            char c = elements[rowIndex][columnIndex];
            switch (c) {
                case START:
                case SAFE_PATH:
                    result = move(elements, columns, rows, columnIndex + 1, rowIndex, result);
                    result = move(elements, columns, rows, columnIndex, rowIndex + 1, result);
                    break;
                case END:
                    result++;
                    break;
            }
        }
        return result;
    }

    private boolean onTheGrid(int columns, int rows, int columnIndex, int rowIndex) {
        return columnIndex < columns && rowIndex < rows;
    }

}
