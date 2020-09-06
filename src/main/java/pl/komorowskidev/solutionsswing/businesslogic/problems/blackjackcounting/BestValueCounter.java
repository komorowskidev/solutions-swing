package pl.komorowskidev.solutionsswing.businesslogic.problems.blackjackcounting;

import org.springframework.stereotype.Component;

@Component
public class BestValueCounter {

    public String find(BlackjackResult blackJackResult) {
        int value = blackJackResult.getMainValue();
        int canAdd = blackJackResult.getAdditionalValue();
        while (canAdd > 0) {
            if (value + 10 > 21) {
                canAdd = 0;
            } else {
                value += 10;
                canAdd -= 10;
            }
        }
        return String.valueOf(value);
    }

}
