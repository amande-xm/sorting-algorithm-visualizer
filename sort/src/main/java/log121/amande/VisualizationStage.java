package log121.amande;

import javafx.util.Duration;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VisualizationStage extends Stage {
    private int[] integers;
    private String algorithm;
    private String speed;

    private HBox animationPane;

    // constructor with parameters set according to user's input
    public VisualizationStage(int[] integers, String algorithm, String speed) {
        this.integers = integers;
        this.algorithm = algorithm;
        this.speed = speed;
        initUI();
    }

    // method to create the UI
    private void initUI() {
        this.setTitle("Fenêtre de l'algorithme");
        Label lblInfo = new Label("Tri : " + algorithm + " | Vitesse : " + speed);
        Button btnOk = new Button("OK (Lancer)");

        // Animation panel
        animationPane = new HBox(5);
        animationPane.setAlignment(Pos.BOTTOM_CENTER);
        animationPane.setPrefHeight(300);

        // initial state of the integers list is drawn before sorting
        drawArray(integers);

        // button to start visalization
        btnOk.setOnAction(event -> {
            btnOk.setDisable(true);
            startVisualization();
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblInfo, animationPane, btnOk);
        Scene scene = new Scene(layout, 800, 400);
        this.setScene(scene);
    }

    // method to draw a rectangle inside the visualization panel
    private void drawArray(int[] array) {
        animationPane.getChildren().clear();

        // max value of the list is defined
        int max = 1;
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }

        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            double ratio = (double) value / max;

            // height of the rectangle is proportional to its corresponding value
            Rectangle bar = new Rectangle(30, (double) value / max * 250);

            Color lightColor = Color.TEAL;
            Color darkColor = Color.TURQUOISE;

            bar.setFill(lightColor.interpolate(darkColor, ratio));
            bar.setStroke(darkColor.interpolate(lightColor, ratio));
            bar.setStrokeWidth(2);

            Label labelNumber = new Label(String.valueOf(value));

            VBox barContainer = new VBox(5);
            barContainer.setAlignment(Pos.BOTTOM_CENTER);
            barContainer.getChildren().addAll(bar, labelNumber);

            animationPane.getChildren().add(barContainer);
        }
    }

    // method to dynamically draw the rectangles inside the panel to visualize the
    // different steps of the selected sort algorithm

    private void startVisualization() {

        // instance of parent class is declared
        SortAlgorithm sort = null;
        switch (algorithm) {

            // child class is instanciated according to user's input
            case "Merge sort":
                sort = new MergeSort(integers);
                break;
            case "Quick sort":
                sort = new QuickSort(integers);
                break;
        }
        if (sort != null) {
            sort.sort();
            List<int[]> history = sort.getHistory();

            // speed is defined accoring to user's input

            int delayMs = 250;
            if (speed.equals("Rapide"))
                delayMs = 50;
            if (speed.equals("Lente"))
                delayMs = 700;

            Timeline timeline = new Timeline();

            // each frame representing the steps of the sort is drawn

            for (int i = 0; i < history.size(); i++) {
                int[] snapshot = history.get(i);
                KeyFrame frame = new KeyFrame(Duration.millis(delayMs * i), e -> drawArray(snapshot));
                timeline.getKeyFrames().add(frame);
            }

            timeline.play();
        }
    }

}
