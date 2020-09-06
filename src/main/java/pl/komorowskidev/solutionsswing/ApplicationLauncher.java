package pl.komorowskidev.solutionsswing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pl.komorowskidev.solutionsswing.gui.view.MainWindow;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@Slf4j
public class ApplicationLauncher {

    public static void main(String[] args) {
        log.info("Start");
        setSystemUIManager();
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ApplicationLauncher.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
        EventQueue.invokeLater(() -> {
            MainWindow mainWindow = ctx.getBean(MainWindow.class);
            mainWindow.setVisible(true);
        });
    }

    private static void setSystemUIManager() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            log.error(e.getMessage());
        }
    }
}
