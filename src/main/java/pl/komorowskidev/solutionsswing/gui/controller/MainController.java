package pl.komorowskidev.solutionsswing.gui.controller;

import org.springframework.boot.info.BuildProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.businesslogic.problems.Problem;
import pl.komorowskidev.solutionsswing.gui.view.MainWindow;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.DataView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.ResultView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.dataviewsub.StartButtonView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub.DescriptionView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.problemviewsub.NameView;
import pl.komorowskidev.solutionsswing.businesslogic.SolutionService;

@Component
public class MainController {

    private final MainWindow mainWindow;

    private final NameView nameView;

    private final DescriptionView descriptionView;

    private final DataView dataView;

    private final StartButtonView startButtonView;

    private final ResultView resultView;

    private final String applicationVersion;

    private final SolutionService solutionService;

    public MainController(MainWindow mainWindow,
                          NameView nameView,
                          DescriptionView descriptionView,
                          DataView dataView,
                          StartButtonView startButtonView,
                          ResultView resultView,
                          BuildProperties buildProperties,
                          SolutionService solutionService){
        this.mainWindow = mainWindow;
        this.nameView = nameView;
        this.descriptionView = descriptionView;
        this.dataView = dataView;
        this.startButtonView = startButtonView;
        this.resultView = resultView;
        applicationVersion = buildProperties.getVersion();
        this.solutionService = solutionService;
    }

    @EventListener
    private void init(ContextRefreshedEvent event){
        mainWindow.setApplicationVersion(applicationVersion);
        nameView.setProblemsNames(solutionService.getProblemNameSet());
    }

    public void problemsNameComboBoxChangedTo(String problemName){
        Problem problem = solutionService.getProblem(problemName);
        descriptionView.setDescription(problem.getDescription());
        dataView.setExampleData(problem.getExampleData());
        resultView.showResult("");
    }

    public void startSolving(){
        startButtonView.setStartButtonEnabled(false);
        String solution = solutionService.getSolution(
                nameView.getCurrentProblemName(),
                dataView.getData());
        resultView.showResult(solution);
        startButtonView.setStartButtonEnabled(true);
    }
}
