package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting

import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException
import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.BlackjackResult
import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.CardDecoder
import spock.lang.Specification

class CardDecoderTest extends Specification {

    CardDecoder cardDecoder

    def setup(){
        cardDecoder = new CardDecoder()
    }

    def "should return value of the card"(){
        expect:
        cardDecoder.decodeValue(card) == value

        where:
        card    || value
        "2"     || new BlackjackResult(2, 0)
        "3"     || new BlackjackResult(3, 0)
        "4"     || new BlackjackResult(4, 0)
        "5"     || new BlackjackResult(5, 0)
        "6"     || new BlackjackResult(6, 0)
        "7"     || new BlackjackResult(7, 0)
        "8"     || new BlackjackResult(8, 0)
        "9"     || new BlackjackResult(9, 0)
        "T"     || new BlackjackResult(10, 0)
        "J"     || new BlackjackResult(10, 0)
        "Q"     || new BlackjackResult(10, 0)
        "K"     || new BlackjackResult(10, 0)
        "A"     || new BlackjackResult(1, 10)
    }

    def "should throw exception when get uknown cards"(){
        when:
        cardDecoder.decodeValue("1")

        then:
        thrown(DataNotValidException)
    }

}
