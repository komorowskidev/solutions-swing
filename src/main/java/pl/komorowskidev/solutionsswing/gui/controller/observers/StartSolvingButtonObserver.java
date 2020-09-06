package pl.komorowskidev.solutionsswing.gui.controller.observers;

import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.controller.MainController;
import pl.komorowskidev.solutionsswing.gui.controller.Observer;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.dataviewsub.StartButtonView;

@Component
public class StartSolvingButtonObserver implements Observer {

    private final MainController mainController;

    public StartSolvingButtonObserver(MainController mainController, StartButtonView startButtonView){
        this.mainController = mainController;
        startButtonView.setObserver(this);
    }

    @Override
    public void update() {
        mainController.startSolving();
    }
}
