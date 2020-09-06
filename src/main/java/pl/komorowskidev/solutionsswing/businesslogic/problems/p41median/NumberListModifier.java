package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NumberListModifier {

    public List<Double> createNewListOfNumbers(Double separator, List<Double> smallerList, List<Double> biggerList,
                                               Map<RemovedMapKeys,Integer> removedMap) {
        List<Double> numbers;
        if (smallerList.size() < biggerList.size()) {
            int removed = removedMap.get(RemovedMapKeys.SMALLER);
            removedMap.put(RemovedMapKeys.SMALLER, removed + smallerList.size());
            numbers = new ArrayList<>(biggerList);
        } else {
            int removed = removedMap.get(RemovedMapKeys.BIGGER);
            removedMap.put(RemovedMapKeys.BIGGER, removed + biggerList.size());
            numbers = new ArrayList<>(smallerList);
        }
        numbers.add(separator);
        return numbers;
    }
}
