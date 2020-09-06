package pl.komorowskidev.solutionsswing.gui.controller.observers;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.controller.MainController;
import pl.komorowskidev.solutionsswing.gui.controller.Observer;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub.NameView;

@Component
public class ProblemsNameComboBoxObserver implements Observer {

    private final MainController mainController;

    private final NameView nameView;

    public ProblemsNameComboBoxObserver(MainController mainController, NameView nameView){
        this.mainController = mainController;
        this.nameView = nameView;
        nameView.setObserver(this);
    }

    @Override
    public void update() {
        mainController.problemsNameComboBoxChangedTo(nameView.getCurrentProblemName());
    }
}
