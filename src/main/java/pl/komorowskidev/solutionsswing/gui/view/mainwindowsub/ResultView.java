package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.view.Messages;
import pl.komorowskidev.solutionsswing.gui.MainContract;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class ResultView extends JPanel implements MainContract.ResultView {

    private final JLabel resultLabel;

    private final JTextArea resultTextArea;

    public ResultView(Messages messages){
        resultLabel = new JLabel(messages.get("main.resultLabel"));
        resultTextArea = new JTextArea();
    }

    @PostConstruct
    private void init(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(resultLabel, BorderLayout.NORTH);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        add(resultTextArea, BorderLayout.CENTER);
    }

    @Override
    public void showResult(String result) {
        resultTextArea.setText(result);
    }
}
