package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Separator {

    public Double separateNumbers(List<Double> numbers, List<Double> smallerList, List<Double> biggerList) {
        Double separator = numbers.get(0);
        smallerList.clear();
        biggerList.clear();
        for (int i = 1; i < numbers.size(); i++) {
            Double currentElement = numbers.get(i);
            if (currentElement < separator) {
                smallerList.add(currentElement);
            } else {
                biggerList.add(currentElement);
            }
        }
        return separator;
    }

}
