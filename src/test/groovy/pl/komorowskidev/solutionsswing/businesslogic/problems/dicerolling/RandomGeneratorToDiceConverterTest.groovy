package pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling

import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException
import pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling.RandomGeneratorToDiceConverter
import spock.lang.Specification

class RandomGeneratorToDiceConverterTest extends Specification {

    RandomGeneratorToDiceConverter randomGeneratorToDiceConverter

    def setup(){
        randomGeneratorToDiceConverter = new RandomGeneratorToDiceConverter()
    }

    def "should throw exception when data is not a number"(){
        given:
        def data = "t"

        when:
        randomGeneratorToDiceConverter.convert(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when data >= 1"(){
        given:
        def data = "1"

        when:
        randomGeneratorToDiceConverter.convert(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when data < 0"(){
        given:
        def data = "-0.0001"

        when:
        randomGeneratorToDiceConverter.convert(data)

        then:
        thrown(DataNotValidException)
    }

    def "should return value 1 - 6"(data, value){
        expect:
        randomGeneratorToDiceConverter.convert(data) == value

        where:
        data                || value
        "0.59558786964"     || "4"
        "0.861037873663"    || "6"
        "0.385597702116"    || "3"
        "0.246237673331"    || "2"
        "0.808033385314"    || "5"
        "0.0544673665427"   || "1"
        "0"                 || "1"
    }
}
