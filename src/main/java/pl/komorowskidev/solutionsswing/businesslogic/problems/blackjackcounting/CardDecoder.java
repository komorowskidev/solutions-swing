package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;

@Component
public class CardDecoder {

    public BlackjackResult decodeValue(String card) throws DataNotValidException {
        int mainResult;
        int canAdd = 0;
        if(card.equals("A")){
            mainResult = 1;
            canAdd = 10;
        } else if(card.matches("[TJQK]")){
            mainResult = 10;
        } else {
            mainResult = getIntegerValue(card);
        }
        return new BlackjackResult(mainResult, canAdd);
    }

    private int getIntegerValue(String card) throws DataNotValidException {
        int result;
        try{
            int value = Integer.parseInt(card);
            if(value > 1 && value < 10){
                result = value;
            } else {
                throw new DataNotValidException("Card: " + card + ". Value is out of range (should be from 2 to 9)");
            }
        } catch (NumberFormatException e){
            throw new DataNotValidException("Cannot count. Uknown card: " + card);
        }
        return result;
    }
}
