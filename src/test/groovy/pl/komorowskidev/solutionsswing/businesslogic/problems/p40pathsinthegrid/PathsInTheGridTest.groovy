package pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid

import pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid.PathsInTheGrid
import pl.komorowskidev.solutionsswing.businesslogic.problems.p40pathsinthegrid.PathsInTheGridValidator
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService
import pl.komorowskidev.solutionsswing.businesslogic.util.LinesProcessor
import spock.lang.Specification

class PathsInTheGridTest extends Specification {

    SolutionService solutionServiceMock

    LinesProcessor linesProcessorMock

    PathsInTheGridValidator validatorMock

    PathsInTheGrid pathsInTheGrid

    def setup(){
        solutionServiceMock = Mock(SolutionService)
        linesProcessorMock = Mock(LinesProcessor)
        validatorMock = Mock(PathsInTheGridValidator)
        pathsInTheGrid = new PathsInTheGrid(solutionServiceMock, linesProcessorMock, validatorMock)
    }

    def "should call LinesProcessor.createLines"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        List<String> linesWithoutSpaces = new ArrayList<>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
        def elements = [['a'], ['b']] as char[][]
        linesProcessorMock.createCharArray(linesWithoutSpaces) >> elements

        when:
        pathsInTheGrid.getSolution(data)

        then:
        1 * linesProcessorMock.createLines(data) >> lines
    }

    def "should call LinesProcessor.removeSpaces"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        def linesWithoutSpaces = new ArrayList<String>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.createLines(data) >> lines
        def elements = [['a'], ['b']] as char[][]
        linesProcessorMock.createCharArray(_ as List<String>) >> elements

        when:
        pathsInTheGrid.getSolution(data)

        then:
        1 * linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
    }

    def "should call LinesProcessor.createCharArray"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        List<String> linesWithoutSpaces = new ArrayList<>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.createLines(data) >> lines
        linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
        def elements = [['a'], ['b']] as char[][]

        when:
        pathsInTheGrid.getSolution(data)

        then:
        1 * linesProcessorMock.createCharArray(linesWithoutSpaces) >> elements
    }

    def "should find result"(){
        given:
        String data = "test1"
        List<String> lines = new ArrayList<>()
        lines.add("test2")
        List<String> linesWithoutSpaces = new ArrayList<>()
        linesWithoutSpaces.add("test3")
        linesProcessorMock.createLines(data) >> lines
        linesProcessorMock.removeSpaces(lines) >> linesWithoutSpaces
        def elements = [
                ['@', '+', '+', '+', '+'],
                ['+', '+', '+', 'X', 'X'],
                ['+', 'X', '+', '+', '+'],
                ['+', '+', '+', 'X', '+'],
                ['+', 'X', '+', '+', 'X'],
                ['+', '+', '+', '+', '$']] as char[][]
        linesProcessorMock.createCharArray(linesWithoutSpaces) >> elements
        def expected = "Number of paths: 9"

        when:
        def actual = pathsInTheGrid.getSolution(data)

        then:
        actual == expected
    }

}
