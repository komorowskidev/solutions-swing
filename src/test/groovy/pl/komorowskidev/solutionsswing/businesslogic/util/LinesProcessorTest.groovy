package pl.komorowskidev.solutionsswing.businesslogic.util

import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException
import spock.lang.Specification

class LinesProcessorTest extends Specification {

    def linesProcessor = new LinesProcessor()

    def "createLines should throw exception when null"(){
        when:
        linesProcessor.createLines(null)

        then:
        thrown DataNotValidException
    }

    def "createLines should throw exception when empty string"(){
        when:
        linesProcessor.createLines("")

        then:
        thrown DataNotValidException
    }

    def "createLines should throw esxception when new line only"(){
        when:
        linesProcessor.createLines("\n")

        then:
        thrown DataNotValidException
    }

    def "createLines should return one line when one line"(){
        given:
        String data = "one line"
        List<String> expected = new ArrayList<>()
        expected.add("one line")

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return one line when another lines are empty"(){
        given:
        String data = "one line\n\n\n"
        List<String> expected = new ArrayList<>()
        expected.add("one line")

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createLines should return two lines when two lines"(){
        given:
        String data = "one line\n second line\n"
        List<String> expected = new ArrayList<>()
        expected.add("one line")
        expected.add(" second line")

        when:
        List<String> actual = linesProcessor.createLines(data)

        then:
        expected == actual
    }

    def "createString should return empty String when lines is empty"(){
        given:
        List<String> lines = new ArrayList<>()

        when:
        String actual = linesProcessor.createString(lines)

        then:
        actual == ""
    }

    def "createString should return one lines when one element"(){
        given:
        String first = "one line"
        List<String> lines = new ArrayList<>()
        lines.add(first)
        String expected = first

        when:
        String actual = linesProcessor.createString(lines)

        then:
        expected == actual
    }

    def "createString should return three lines when three element"(){
        given:
        String first = "one line"
        String second = "two lines"
        String third = "three line"
        List<String> lines = new ArrayList<>()
        lines.add(first)
        lines.add(second)
        lines.add(third)
        String expected = first + "\n" + second + "\n" + third

        when:
        String actual = linesProcessor.createString(lines)

        then:
        expected == actual
    }

    def "removingSpaces test"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("   one line    second third   ")
        lines.add("twolines")
        lines.add("three line")
        List<String> expected = new ArrayList<>()
        expected.add("onelinesecondthird")
        expected.add("twolines")
        expected.add("threeline")

        when:
        List<String> actual = linesProcessor.removeSpaces(lines)

        then:
        expected == actual
    }

    def "creating charArray should add space when lines shorter then first line"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("onel")
        lines.add("two")
        lines.add("four")
        char[][] expected = [['o', 'n', 'e', 'l'], ['t', 'w', 'o', ' '], ['f', 'o', 'u', 'r']] as char[][]

        when:
        char[][] actual = linesProcessor.createCharArray(lines)

        then:
        actual == expected
    }

    def "creating charArray should cut line when longer then first line"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("onel")
        lines.add("threee")
        lines.add("four")
        char[][] expected = [['o', 'n', 'e', 'l'], ['t', 'h', 'r', 'e'], ['f', 'o', 'u', 'r']] as char[][]

        when:
        char[][] actual = linesProcessor.createCharArray(lines)

        then:
        actual == expected
    }

    def "createIntArray should throw exception when lines is null"(){
        when:
        linesProcessor.createIntArray(null, 4, " ")

        then:
        thrown DataNotValidException
    }

    def "createIntArray should throw exception when lines is empty"(){
        given:
        List<String> lines = new ArrayList<>()

        when:
        linesProcessor.createIntArray(lines, 4, " ")

        then:
        thrown DataNotValidException
    }

    def "createIntArray should throw exception when delimiter is null"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("1 4 11 15")
        lines.add("55 2 5 4")
        lines.add("123 55 44 15")

        when:
        linesProcessor.createIntArray(lines, 4, null)

        then:
        thrown DataNotValidException
    }

    def "createIntArray should throw exception when delimiter is empty"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("1 4 11 15")
        lines.add("55 2 5 4")
        lines.add("123 55 44 15")

        when:
        linesProcessor.createIntArray(lines, 4, "")

        then:
        thrown DataNotValidException
    }

    def "createIntArray should throw exception when columns does not fit to data"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("1 4 11 15")
        lines.add("55 2 5 4")
        lines.add("123 55 44 15")
        int columns = 2
        String delimiter = " "

        when:
        linesProcessor.createIntArray(lines, columns, delimiter)

        then:
        thrown DataNotValidException
    }

    def "createIntArray should return int array"(){
        given:
        List<String> lines = new ArrayList<>()
        lines.add("1 4 11 15")
        lines.add("55 2 5 4")
        lines.add("123 55 44 15")
        int columns = 4
        String delimiter = " "
        int[][] expected = [[1, 4, 11, 15], [55, 2, 5, 4], [123, 55, 44, 15]]

        when:
        int[][] actual = linesProcessor.createIntArray(lines, columns, delimiter)

        then:
        actual == expected
    }

    def "createListOfDouble should create list of double"(){
        given:
        def line = "12 4 6.9 55"
        List<Double> expected = new ArrayList<>()
        expected.add(12)
        expected.add(4)
        expected.add(6.9)
        expected.add(55)

        when:
        List<Double> actual = linesProcessor.createListOfDouble(line, " ")

        then:
        actual == expected
    }

    def "createListOfDouble should throw exception when element is not a number"(){
        given:
        def line = "12 c 6.9 55"

        when:
        linesProcessor.createListOfDouble(line, " ")

        then:
        thrown DataNotValidException
    }
}
