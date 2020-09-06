package pl.komorowskidev.solutionsswing.gui.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.komorowskidev.solutionsswing.gui.MainContract;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.DataView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.ProblemView;
import pl.komorowskidev.solutionsswing.gui.view.mainwindowsub.ResultView;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
@Slf4j
public class MainWindow extends JFrame implements MainContract.MainWindow {

    private final Messages messages;

    private final ProblemView problemView;

    private final DataView dataView;

    private final ResultView resultView;


    public MainWindow(
            Messages messages,
            ProblemView problemView,
            DataView dataView,
            ResultView resultView){
        this.messages = messages;
        this.problemView = problemView;
        this.dataView = dataView;
        this.resultView = resultView;
    }

    @PostConstruct
    private void init(){
        setSize(800, 600);
        setLayout(new GridLayout(3, 1));
        add(problemView);
        add(dataView);
        add(resultView);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void setApplicationVersion(String applicationVersion) {
        setTitle(messages.get("main.applicationName") + " v." + applicationVersion);
    }
}
