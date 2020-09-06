package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MedianOddNumberOfElements {

    private Separator separator;

    private NumberListModifier numberListModifier;

    public MedianOddNumberOfElements(Separator separator, NumberListModifier numberListModifier) {
        this.separator = separator;
        this.numberListModifier = numberListModifier;
    }

    public Double findMedian(List<Double> numbers) {
        boolean find = false;
        Double result = null;
        Map<RemovedMapKeys, Integer> removedMap = new HashMap<>();
        removedMap.put(RemovedMapKeys.SMALLER, 0);
        removedMap.put(RemovedMapKeys.BIGGER, 0);
        List<Double> smallerList = new ArrayList<>();
        List<Double> biggerList = new ArrayList<>();
        while (!find) {
            result = separator.separateNumbers(numbers, smallerList, biggerList);
            if (removedMap.get(RemovedMapKeys.SMALLER) + smallerList.size() == removedMap.get(RemovedMapKeys.BIGGER) + biggerList.size()) {
                find = true;
            } else {
                numbers = numberListModifier.createNewListOfNumbers(result, smallerList, biggerList, removedMap);
            }
        }
        return result;
    }

}
