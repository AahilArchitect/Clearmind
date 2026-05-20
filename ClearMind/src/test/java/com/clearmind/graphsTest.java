package com.clearmind;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class graphsTest extends Application {
    @Override
    public void start(Stage stage) {

        // Add your app logo here
        stage.getIcons().add(
            new Image(getClass().getResourceAsStream("/images/App_Logo.jpg"))
        );

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(x, y);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Focus");
        series.getData().add(new XYChart.Data<>("Mon", 3));
        series.getData().add(new XYChart.Data<>("Tue", 5));
        series.getData().add(new XYChart.Data<>("Wed", 2));

        chart.getData().add(series);

        stage.setScene(new Scene(chart, 600, 400));
        stage.setTitle("ClearMind – Focus Chart");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
