package pl.komorowskidev.solutionsswing.businesslogic.problems.p41median

import pl.komorowskidev.solutionsswing.businesslogic.problems.p41median.Separator
import spock.lang.Specification

class SeparatorTest extends Specification {

    Separator separator

    def setup(){
        separator = new Separator()
    }

    def "should separate numbers"(){
        given:
        def data = [11d, -5d, 111d, 0d, 20d, 0.1d]
        def smallerList = new ArrayList<Double>()
        def biggerList = new ArrayList<Double>()

        when:
        def actual = separator.separateNumbers(data, smallerList, biggerList)

        then:
        actual == 11d
        smallerList == [-5d, 0d, 0.1d]
        biggerList == [111d, 20d]
    }

}
