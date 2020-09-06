package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting

import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.BestValueCounter
import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.BlackjackCounter
import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.BlackjackResult
import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.CardDecoder
import spock.lang.Specification

class BlackjackCounterTest extends Specification {

    BlackjackCounter blackjackCounter

    CardDecoder cardDecoderMock

    BestValueCounter bestValueCounterMock

    def setup(){
        cardDecoderMock = Mock(CardDecoder)
        bestValueCounterMock = Mock(BestValueCounter)
        blackjackCounter = new BlackjackCounter(cardDecoderMock, bestValueCounterMock)
    }

    def "should call cardDecoder"(){
        given:
        def first = "1"
        def second = "2"
        def third = "3"
        String[] cards = [first, second, third]
        def actual1 = null
        def actual2 = null
        def actual3 = null
        BlackjackResult blackjackResult = new BlackjackResult(10, 20)

        when:
        blackjackCounter.count(cards)

        then:
        3 * cardDecoderMock.decodeValue({it.size() > 0}) >> {
            actual1 = it.first(); blackjackResult} >> {
            actual2 = it.first(); blackjackResult} >> {
            actual3 = it.first(); blackjackResult}
        actual1 == first
        actual2 == second
        actual3 == third
    }

    def "should call bestValueCounter"(){
        given:
        def first = "1"
        def second = "2"
        def third = "3"
        String[] cards = [first, second, third]
        cardDecoderMock.decodeValue(first) >> new BlackjackResult(1, 0)
        cardDecoderMock.decodeValue(second) >> new BlackjackResult(2, 0)
        cardDecoderMock.decodeValue(third) >> new BlackjackResult(3, 0)
        BlackjackResult expected = new BlackjackResult(6, 0)

        when:
        blackjackCounter.count(cards)

        then:
        bestValueCounterMock.find(expected) >> 6
    }

    def "should call return value from bestValueCounterMock when sum of cards < 22"(){
        given:
        def first = "1"
        def second = "2"
        def third = "3"
        String[] cards = [first, second, third]
        cardDecoderMock.decodeValue(first) >> new BlackjackResult(1, 0)
        cardDecoderMock.decodeValue(second) >> new BlackjackResult(10, 0)
        cardDecoderMock.decodeValue(third) >> new BlackjackResult(10, 20)
        BlackjackResult sum = new BlackjackResult(21, 20)
        bestValueCounterMock.find(sum) >> 6
        def expected = "6"

        when:
        def actual = blackjackCounter.count(cards)

        then:
        actual == expected
    }

    def "should return Bust when sum of cards > 21"(){
        given:
        def first = "1"
        def second = "2"
        def third = "3"
        String[] cards = [first, second, third]
        cardDecoderMock.decodeValue(first) >> new BlackjackResult(2, 0)
        cardDecoderMock.decodeValue(second) >> new BlackjackResult(10, 0)
        cardDecoderMock.decodeValue(third) >> new BlackjackResult(10, 20)
        BlackjackResult sum = new BlackjackResult(22, 20)
        bestValueCounterMock.find(sum) >> 6
        def expected = "Bust"

        when:
        def actual = blackjackCounter.count(cards)

        then:
        actual == expected
    }


}
