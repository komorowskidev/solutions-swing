package pl.komorowskidev.solutionsswing.businesslogic.problems.dicerolling;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.exceptions.DataNotValidException;

@Component
public class RandomGeneratorToDiceConverter {

    public String convert(String value) throws DataNotValidException  {
        double randomValue;
        String result;
        try{
            randomValue = Double.parseDouble(value);
        } catch(NullPointerException | NumberFormatException e){
             throw new DataNotValidException("{} is not a number");
        }
        if(randomValue >= 0 && randomValue < 1 ){
            result = String.valueOf((int) Math.floor(randomValue * 6) + 1);
        } else {
            throw new DataNotValidException("invalid value: {}. Valid data range: >=0 and <1");
        }
        return result;
    }
}
