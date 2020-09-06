package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;
import pl.komorowskidev.solutionsswing.businesslogic.problems.Problem;
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService;
import pl.komorowskidev.solutionsswing.businesslogic.util.LinesProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlackjackCounting implements Problem {

    private LinesProcessor linesProcessor;

    private BlackjackCounter blackjackCounter;

    public BlackjackCounting(SolutionService solutionService, LinesProcessor linesProcessor, BlackjackCounter blackjackCounter) {
        solutionService.addProblem(this);
        this.linesProcessor = linesProcessor;
        this.blackjackCounter = blackjackCounter;
    }

    @Override
    public String getName() {
        return "Blackjack Counting";
    }

    @Override
    public String getDescription() {
        return "Solution should contain the number of points in each test-case, not exceeding 21 - or the word Bust " +
                "if the total is greater than 21.\nThe deck contains all cards from 2 to 10 inclusive, which are " +
                "counted according to their value, also Kings, Queens and Jacks which cost 10 points each and also " +
                "Aces, which could be counted as 1 or 11 points, whatever is better.";
    }

    @Override
    public String getExampleData() {
        return "A T\n" +
                "2 K 4\n" +
                "3 A Q 8\n" +
                "A 3 3 3 A";
    }

    @Override
    public String getSolution(String data) throws DataNotValidException {
        List<String> lines = linesProcessor.createLines(data);
        String delimiter = " ";
        List<String> resultList = new ArrayList<>();
        for (String line : lines) {
            String result = blackjackCounter.count(line.split(delimiter));
            resultList.add(result);
        }
        return String.join(" ", resultList);
    }

}
