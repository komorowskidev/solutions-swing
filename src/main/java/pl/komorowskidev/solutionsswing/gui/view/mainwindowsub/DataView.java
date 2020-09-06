package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.view.Messages;
import pl.komorowskidev.solutionsswing.gui.MainContract;
import pl.komorowskidev.solutionsswing.gui.controller.Observer;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.dataviewsub.StartButtonView;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class DataView extends JPanel implements MainContract.DataView {

    private final JLabel dataLabel;

    private final JTextArea dataTextArea;

    private final StartButtonView startButtonView;

    public DataView(Messages messages, StartButtonView startButtonView){
        dataLabel = new JLabel(messages.get("main.dataLabel"));
        dataTextArea = new JTextArea();
        dataTextArea.setLineWrap(true);
        this.startButtonView = startButtonView;
    }

    @PostConstruct
    private void init(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(dataLabel, BorderLayout.NORTH);
        add(dataTextArea, BorderLayout.CENTER);
        add(startButtonView, BorderLayout.SOUTH);
    }

    @Override
    public void setExampleData(String example) {
        dataTextArea.setText(example);
    }

    @Override
    public String getData() {
        return dataTextArea.getText();
    }

}
