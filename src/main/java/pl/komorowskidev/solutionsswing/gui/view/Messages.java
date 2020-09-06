package pl.komorowskidev.solutionsswing.gui.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
@Slf4j
public class Messages {

    private ResourceBundle bundle;

    public Messages() {
    }

    @PostConstruct
    private void init() {
        bundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag("en-GB"));
    }

    public String get(String code) {
        String result = code;
        try {
            result = bundle.getString(code);
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
