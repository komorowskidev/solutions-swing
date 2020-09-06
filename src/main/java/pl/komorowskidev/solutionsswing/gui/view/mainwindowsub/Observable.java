package pl.komorowskidev.solutionsswing.gui.view.mainwindowsub;

import pl.komorowskidev.solutionsswing.gui.controller.Observer;

public interface Observable {

    void setObserver(Observer observer);
    void notifyObserver();
}
