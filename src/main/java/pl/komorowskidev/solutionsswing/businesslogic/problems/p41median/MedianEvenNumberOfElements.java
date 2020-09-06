package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MedianEvenNumberOfElements {

    private Separator separator;

    private NumberListModifier numberListModifier;

    public MedianEvenNumberOfElements(Separator separator, NumberListModifier numberListModifier) {
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
            if (removedMap.get(RemovedMapKeys.SMALLER) + smallerList.size() + 1 == removedMap.get(RemovedMapKeys.BIGGER) + biggerList.size()) {
                find = true;
                Double result2 = Collections.min(biggerList);
                result = (result + result2) / 2;
            } else if (removedMap.get(RemovedMapKeys.SMALLER) + smallerList.size() == removedMap.get(RemovedMapKeys.BIGGER) + biggerList.size() + 1) {
                find = true;
                Double result2 = Collections.max(smallerList);
                result = (result + result2) / 2;
            } else {
                numbers = numberListModifier.createNewListOfNumbers(result, smallerList, biggerList, removedMap);
            }
        }
        return result;
    }
}
