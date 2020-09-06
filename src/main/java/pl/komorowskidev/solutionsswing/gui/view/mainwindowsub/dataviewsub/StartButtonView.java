package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.dataviewsub;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.MainContract;
import pl.komorowskidev.solutionsswing.gui.controller.Observer;
import pl.komorowskidev.solutionsswing.gui.view.Messages;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.Observable;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class StartButtonView extends JPanel implements MainContract.StartButtonView, Observable {

    private final JButton startButton;

    private Observer observer;

    public StartButtonView(Messages messages){
        startButton = new JButton(messages.get("main.startSolvingButton"));
    }

    @PostConstruct
    private void init(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(startButton);
        startButton.addActionListener(e -> notifyObserver());
    }

    @Override
    public void setStartButtonEnabled(boolean enabled) {
        startButton.setEnabled(enabled);
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        observer.update();
    }
}
