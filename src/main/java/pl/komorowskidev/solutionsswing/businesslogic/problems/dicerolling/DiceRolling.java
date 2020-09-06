package pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;
import pl.komorowskidev.solutionsswing.businesslogic.problems.Problem;
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService;
import pl.komorowskidev.solutionsswing.businesslogic.util.LinesProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiceRolling implements Problem {

    private LinesProcessor linesProcessor;

    private RandomGeneratorToDiceConverter randomGeneratorToDiceConverter;

    public DiceRolling(SolutionService solutionService, LinesProcessor linesProcessor,
                       RandomGeneratorToDiceConverter randomGeneratorToDiceConverter) {
        solutionService.addProblem(this);
        this.linesProcessor = linesProcessor;
        this.randomGeneratorToDiceConverter = randomGeneratorToDiceConverter;
    }

    @Override
    public String getName() {
        return "Dice Rolling";
    }

    @Override
    public String getDescription() {
        return "Simulation of dice rolling by the values coming from a random numbers generator.";
    }

    @Override
    public String getExampleData() {
        return "0.59558786964\n" +
                "0.861037873663\n" +
                "0.385597702116\n" +
                "0.246237673331\n" +
                "0.808033385314\n" +
                "0.0544673665427";
    }

    @Override
    public String getSolution(String data) throws DataNotValidException {
        List<String> lines = linesProcessor.createLines(data);
        List<String> resultList = new ArrayList<>();
        for (String line : lines) {
            String result = randomGeneratorToDiceConverter.convert(line);
            resultList.add(result);
        }
        return String.join(" ", resultList);
    }

}
