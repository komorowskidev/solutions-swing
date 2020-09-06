package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub.DescriptionView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub.NameView;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
public class ProblemView extends JPanel {

    private final NameView nameView;

    private final DescriptionView descriptionView;

    public ProblemView(NameView nameView, DescriptionView descriptionView){
        this.nameView = nameView;
        this.descriptionView = descriptionView;
    }

    @PostConstruct
    private void init(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(nameView, BorderLayout.NORTH);
        add(descriptionView, BorderLayout.CENTER);
    }

}
