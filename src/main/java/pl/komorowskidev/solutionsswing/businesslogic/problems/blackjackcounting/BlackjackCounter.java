package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;

@Component
public class BlackjackCounter {

    private CardDecoder cardDecoder;

    private BestValueCounter bestValueCounter;

    public BlackjackCounter(CardDecoder cardDecoder, BestValueCounter bestValueCounter) {
        this.cardDecoder = cardDecoder;
        this.bestValueCounter = bestValueCounter;
    }

    public String count(String[] cards) throws DataNotValidException {
        BlackjackResult blackJackResult = new BlackjackResult(0 ,0);
        for(String card : cards){
            BlackjackResult cardValue = cardDecoder.decodeValue(card);
            updateResult(blackJackResult, cardValue);
        }
        return blackJackResult.getMainValue() > 21 ? "Bust" : bestValueCounter.find(blackJackResult);
    }

    private void updateResult(BlackjackResult result, BlackjackResult newValues) {
        int mainResult = result.getMainValue();
        int canAdd = result.getAdditionalValue();
        result.setMainValue(mainResult + newValues.getMainValue());
        result.setAdditionalValue(canAdd + newValues.getAdditionalValue());
    }

}
