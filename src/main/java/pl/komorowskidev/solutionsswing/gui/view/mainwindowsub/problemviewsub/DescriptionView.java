package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.view.Messages;
import pl.komorowskidev.solutionsswing.gui.MainContract;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class DescriptionView extends JPanel implements MainContract.DescriptionView {

    private final JLabel descriptionLabel;

    private final JTextArea descriptionTextArea;

    public DescriptionView(Messages messages){
        descriptionLabel = new JLabel(messages.get("main.descriptionLabel"));
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setLineWrap(true);
    }

    @PostConstruct
    private void init(){
        setLayout(new BorderLayout());
        add(descriptionLabel, BorderLayout.NORTH);
        add(descriptionTextArea, BorderLayout.CENTER);
    }

    @Override
    public void setDescription(String description) {
        descriptionTextArea.setText(description);
    }
}
