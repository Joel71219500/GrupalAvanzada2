package com.avanzada.grupal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.avanzada.grupal.config.SpringConfig;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    private AnnotationConfigApplicationContext context;
    
    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/author.fxml"));
        loader.setControllerFactory(context::getBean);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cliente de Autores");
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        if (context != null) {
            context.close();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
