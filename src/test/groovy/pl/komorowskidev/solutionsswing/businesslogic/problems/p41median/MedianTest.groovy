package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median

import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.Median
import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.MedianEvenNumberOfElements
import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.MedianOddNumberOfElements
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService
import pl.komorowskidev.solutionsswing.businesslogic.util.LinesProcessor
import spock.lang.Specification

class MedianTest extends Specification {

    SolutionService solutionServiceMock

    LinesProcessor linesProcessorMock

    MedianOddNumberOfElements medianOddNumberOfElementsMock

    MedianEvenNumberOfElements medianEvenNumberOfElementsMock

    Median median

    def setup(){
        solutionServiceMock = Mock(SolutionService)
        linesProcessorMock = Mock(LinesProcessor)
        medianOddNumberOfElementsMock = Mock(MedianOddNumberOfElements)
        medianEvenNumberOfElementsMock = Mock(MedianEvenNumberOfElements)
        medianOddNumberOfElementsMock.findMedian(_ as List<Double>) >> 10.0
        medianEvenNumberOfElementsMock.findMedian(_ as List<Double>) >> 20.0
        median = new Median(solutionServiceMock, linesProcessorMock, medianOddNumberOfElementsMock, medianEvenNumberOfElementsMock)
    }

    def "should call LinesProcessor.createLines"(){
        given:
        def delimiter = " "
        String data = "10 20"
        List<String> lines = new ArrayList<>()
        lines.add("30")
        List<Double> numberList = new ArrayList<>()
        numberList.add(1.0)
        numberList.add(2.0)
        linesProcessorMock.createListOfDouble(_ as String, delimiter) >> numberList

        when:
        median.getSolution(data)

        then:
        1 * linesProcessorMock.createLines(data) >> lines
    }

    def "should call LinesProcessor.createListOfDouble"(){
        given:
        def delimiter = " "
        String data = "10 20"
        List<String> lines = new ArrayList<>()
        lines.add("30 10")
        lines.add("40 4 6")
        lines.add("50 3332 55 553 2")
        linesProcessorMock.createLines(data) >> lines
        List<Double> numberList = new ArrayList<>()
        numberList.add(1.0)
        numberList.add(2.0)
        def expected1 = null
        def expected2 = null
        def expected3 = null

        when:
        median.getSolution(data)

        then:
        3 * linesProcessorMock.createListOfDouble({it.size() > 0}, delimiter) >> {
            expected1 = it.first(); numberList
        } >> {
            expected2 = it.first(); numberList
        } >> {
            expected3 = it.first(); numberList}
        expected1 == "30 10"
        expected2 == "40 4 6"
        expected3 == "50 3332 55 553 2"
    }

    def "should return number when list.size is 1"(){
        given:
        def delimiter = " "
        String data = "10 20"
        def lines = ["30", "40", "50"]
        linesProcessorMock.createLines(data) >> lines
        def numberList1 = [1d]
        def numberList2 = [2d]
        def numberList3 = [3d]
        linesProcessorMock.createListOfDouble(_ as String, delimiter) >>> [numberList1, numberList2, numberList3]
        def expectedList = [
                String.format("%.1f", numberList1.get(0)),
                String.format("%.1f", numberList2.get(0)),
                String.format("%.1f", numberList3.get(0))]

        when:
        def actual = median.getSolution(data)

        then:
        actual == String.join(" ", expectedList)
    }

    def "should call medianOdd when numbers.size is odd"(){
        given:
        def delimiter = " "
        String data = "10 20"
        def lines = ["30", "40", "50"]
        linesProcessorMock.createLines(data) >> lines
        def numberList1 = [1d, 30d, 20d]
        def numberList2 = [2d, 55d, 1d, 43d, 100d]
        def numberList3 = [3d, 23d, 3d, 24d, 332d, -10d, 0d]
        linesProcessorMock.createListOfDouble(_ as String, delimiter) >>> [numberList1, numberList2, numberList3]
        def actual1 = null
        def actual2 = null
        def actual3 = null

        when:
        median.getSolution(data)

        then:
        3 * medianOddNumberOfElementsMock.findMedian({it.size > 0}) >> {
            actual1 = it.first(); 0d
        } >> {
            actual2 = it.first(); 0d
        } >> {
            actual3 = it.first(); 0d
        }
        actual1 == numberList1
        actual2 == numberList2
        actual3 == numberList3
    }

    def "should call medianEven when numbers.size is even"(){
        given:
        def delimiter = " "
        String data = "10 20"
        def lines = ["30", "40", "50"]
        linesProcessorMock.createLines(data) >> lines
        def numberList1 = [30d, 20d]
        def numberList2 = [2d, 1d, 43d, 100d]
        def numberList3 = [3d, 23d, 24d, 332d, -10d, 0d]
        linesProcessorMock.createListOfDouble(_ as String, delimiter) >>> [numberList1, numberList2, numberList3]
        def actual1 = null
        def actual2 = null
        def actual3 = null

        when:
        median.getSolution(data)

        then:
        3 * medianEvenNumberOfElementsMock.findMedian({it.size > 0}) >> {
            actual1 = it.first(); 0d
        } >> {
            actual2 = it.first(); 0d
        } >> {
            actual3 = it.first(); 0d
        }
        actual1 == numberList1
        actual2 == numberList2
        actual3 == numberList3
    }

}
