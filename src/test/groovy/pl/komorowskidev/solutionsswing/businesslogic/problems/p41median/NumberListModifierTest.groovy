package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median

import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.NumberListModifier
import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.RemovedMapKeys
import spock.lang.Specification

class NumberListModifierTest extends Specification {

    NumberListModifier numberListModifier

    def setup(){
        numberListModifier = new NumberListModifier()
    }

    def "should create new list of numbers and update removedMap when smaller list is shorter"(){
        given:
        def separator = 11d
        def smallerList = [-5d, 0d, 0.1d]
        def biggerList = [111d, 20d, 30d, 21.55d]
        Map<RemovedMapKeys, Integer> removedMap = new HashMap<>()
        removedMap.put(RemovedMapKeys.SMALLER, 1)
        removedMap.put(RemovedMapKeys.BIGGER, 2)

        when:
        def actual = numberListModifier.createNewListOfNumbers(separator, smallerList, biggerList, removedMap)

        then:
        actual == [111d, 20d, 30d, 21.55d, 11d]
        removedMap.get(RemovedMapKeys.BIGGER) == 2
        removedMap.get(RemovedMapKeys.SMALLER) == 4
    }

    def "should create new list of numbers and update removedMap when smaller list is not shorter"(){
        given:
        def separator = 11d
        def smallerList = [-5d, 0d, 0.1d]
        def biggerList = [111d, 20d]
        Map<RemovedMapKeys, Integer> removedMap = new HashMap<>()
        removedMap.put(RemovedMapKeys.SMALLER, 1)
        removedMap.put(RemovedMapKeys.BIGGER, 2)

        when:
        def actual = numberListModifier.createNewListOfNumbers(separator, smallerList, biggerList, removedMap)

        then:
        actual == [-5d, 0d, 0.1d, 11d]
        removedMap.get(RemovedMapKeys.BIGGER) == 4
        removedMap.get(RemovedMapKeys.SMALLER) == 1
    }

}
