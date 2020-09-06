package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting

import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.BestValueCounter
import pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting.BlackjackResult
import spock.lang.Specification

class BestValueCounterTest extends Specification {

    BestValueCounter bestValueCounter

    def setup(){
        bestValueCounter = new BestValueCounter()
    }

    def "should find result"(){
        expect:
        bestValueCounter.find(backjackResult) == result

        where:
        backjackResult                  || result
        new BlackjackResult(1, 0) || "1"
        new BlackjackResult(1, 10)  || "11"
        new BlackjackResult(21, 10) || "21"
        new BlackjackResult(10, 20) || "20"
    }

}
