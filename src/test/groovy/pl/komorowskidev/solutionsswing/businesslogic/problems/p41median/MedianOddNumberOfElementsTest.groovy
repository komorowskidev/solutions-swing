package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median

import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.MedianOddNumberOfElements
import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.NumberListModifier
import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.Separator
import spock.lang.Specification

class MedianOddNumberOfElementsTest extends Specification {

    MedianOddNumberOfElements medianOddNumberOfElements

    def separator = new Separator()
    def numberListModifier = new NumberListModifier()

    def setup(){
        medianOddNumberOfElements = new MedianOddNumberOfElements(separator, numberListModifier)
    }

    def "should find result"(){
        expect:
        medianOddNumberOfElements.findMedian(data) == result

        where:
        data                        || result
        [7d, 3d, 5d]                || 5d
        [11d, -5d, 111d, 0d, 13d]   || 11d
        [3d, 1.3d, 1000.44d]        || 3d
    }

}
