package pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid

import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException
import pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid.PathsInTheGridValidator
import spock.lang.Specification

class PathsInTheGridValidatorTest extends Specification {

    def validator = new PathsInTheGridValidator()

    def "should not throw exception when data correct"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@++X')
        data.add('++X+')
        data.add('X++$')

        when:
        validator.validate(data)

        then:
        noExceptionThrown()
    }

    def "should throw exception when data are less then 2 lines"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@123$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when data are less then 2 columns"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@')
        data.add('$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when lines has different size"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@++X')
        data.add('++++++X+')
        data.add('X++$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when first char is not @"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('123$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when last char is not dollar"(){
        given:
        List<String> data = new ArrayList<>()
        data.add("@123")

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when chars at first line different then X or +"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@+aX')
        data.add('++XX')
        data.add('X++$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when chars at last line different then X or +"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@++X')
        data.add('++XX')
        data.add('Xb+$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when chars at middle line different then X or +"(){
        given:
        List<String> data = new ArrayList<>()
        data.add('@++X')
        data.add('++cX')
        data.add('X++$')

        when:
        validator.validate(data)

        then:
        thrown(DataNotValidException)
    }

}
