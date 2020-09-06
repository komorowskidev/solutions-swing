package pl.komorowskidev.solutionsswing.gui;

import java.util.Set;

public interface MainContract {

    interface MainWindow {
        void setApplicationVersion(String title);
    }

    interface ProblemView {
        void setProblemsNames(Set<String> problemsNameSet);
        String getCurrentProblemName();
    }

    interface DescriptionView {
        void setDescription(String description);
    }

    interface DataView {
        void setExampleData(String example);
        String getData();
    }

    interface StartButtonView {
        void setStartButtonEnabled(boolean enabled);
    }

    interface ResultView {
        void showResult(String result);
    }

}
