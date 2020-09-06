package pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling


import pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling.DiceRolling
import pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling.RandomGeneratorToDiceConverter
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService
import pl.komorowskidev.solutionsswing.businesslogic.util.LinesProcessor
import spock.lang.Specification

class DiceRollingTest extends Specification {

    DiceRolling diceRolling

    SolutionService serviceMock

    LinesProcessor linesProcessorMock

    RandomGeneratorToDiceConverter randomGeneratorToDiceConverterMock

    def setup(){
        serviceMock = Mock(SolutionService)
        linesProcessorMock = Mock(LinesProcessor)
        randomGeneratorToDiceConverterMock = Mock(RandomGeneratorToDiceConverter)
        diceRolling = new DiceRolling(serviceMock, linesProcessorMock, randomGeneratorToDiceConverterMock)
    }

    def "should call LinesProcessor"(){
        given:
        def data = "test1\ntest2"
        def lines = ["test1", "test2"]

        when:
        diceRolling.getSolution(data)

        then:
        1 * linesProcessorMock.createLines(data) >> lines
    }

    def "should call RandomGeneratorToDiceConverter"(){
        given:
        def data = "test1\ntest2"
        def lines = ["0.59558786964", "0.861037873663"]
        linesProcessorMock.createLines(data) >> lines

        when:
        diceRolling.getSolution(data)

        then:
        1 * randomGeneratorToDiceConverterMock.convert("0.59558786964") >> "result1"
        1 * randomGeneratorToDiceConverterMock.convert("0.861037873663") >> "result2"
    }

    def "should return solution"(){
        given:
        def data = "test1\ntest2"
        def lines = ["0.59558786964", "0.861037873663"]
        linesProcessorMock.createLines(data) >> lines
        randomGeneratorToDiceConverterMock.convert("0.59558786964") >> "result1"
        randomGeneratorToDiceConverterMock.convert("0.861037873663") >> "result2"

        when:
        def actual = diceRolling.getSolution(data)

        then:
        actual == "result1 result2"
    }

}
