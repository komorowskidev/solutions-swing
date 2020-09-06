package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.view.Messages;
import pl.komorowskidev.solutionsswing.gui.MainContract;
import pl.komorowskidev.solutionsswing.gui.controller.Observer;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.Observable;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.Set;

@Component
public class NameView extends JPanel implements MainContract.ProblemView, Observable {

    private final JLabel problemNameLabel;

    private final JComboBox<String> problemNameComboBox;

    private Observer observer;

    public NameView(Messages messages){
        problemNameLabel = new JLabel(messages.get("main.problemNameLabel"));
        problemNameComboBox = new JComboBox<>();
    }

    @PostConstruct
    private void init(){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        problemNameComboBox.setMaximumSize(new Dimension(300, 20));
        add(problemNameLabel);
        add(Box.createRigidArea(new Dimension(5, 5)));
        add(problemNameComboBox);
    }

    @Override
    public void setProblemsNames(Set<String> problemsNameSet) {
        String[] labels = problemsNameSet.toArray(new String[0]);
        problemNameComboBox.setModel(new DefaultComboBoxModel<String>(labels));
        problemNameComboBox.addActionListener(e -> notifyObserver());
        notifyObserver();
    }

    @Override
    public String getCurrentProblemName() {
        return problemNameComboBox
                .getModel()
                .getElementAt(problemNameComboBox.getSelectedIndex());
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
