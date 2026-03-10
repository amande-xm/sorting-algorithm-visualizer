package log121.amande;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {

        //creation of UI

        GridPane window = new GridPane();
        window.setPadding(new Insets(20));
        window.setHgap(10);
        window.setVgap(15);

        Label title = new Label("Paramètres pour la visualisation d'un tri");
        title.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        window.add(title, 0, 0, 2, 1);

        Label sortMode = new Label("Algorithme de tri :");
        ComboBox<String> comboSort = new ComboBox<>();
        comboSort.getItems().addAll("Merge sort", "Quick sort");
        comboSort.getSelectionModel().selectFirst();
        window.add(sortMode, 0, 1);
        window.add(comboSort, 1, 1);

        Label listLabel = new Label("Collection d'entiers à trier :");
        TextField listText = new TextField();
        window.add(listLabel, 0, 2);
        window.add(listText, 1, 2);

        Label sortSpeed = new Label("Vitesse de la simulation :");
        ComboBox<String> comboSpeed = new ComboBox<>();
        comboSpeed.getItems().addAll("Rapide", "Moyenne", "Lente");
        comboSpeed.getSelectionModel().selectFirst();
        window.add(sortSpeed, 0, 3);
        window.add(comboSpeed, 1, 3);

        Button cancel = new Button("Annuler");
        Button start = new Button("Appliquer");
        window.add(cancel, 2, 4);
        window.add(start, 3, 4);


        // action added to button when clicked to open the visualization panel
        // user's input is checked before opening the panel

        start.setOnAction(event -> {

            String userInput = listText.getText().trim();
            int[] arr;

            if (userInput.isEmpty()) {
                arr = new int[] { 50, 20, 80, 10, 90, 30, 70, 40, 60 };
            } else {
                try {
                    String[] parts = userInput.split("[,\\s]+");
                    arr = new int[parts.length];

                    for (int i = 0; i < parts.length; i++) {
                        arr[i] = Integer.parseInt(parts[i]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : Veuillez n'entrer que des nombres entiers.");
                    arr = new int[] { 1, 2, 3, 4 };
                }
            }

            // instanciation of new stage for visualization
            VisualizationStage secondStage = new VisualizationStage(arr, comboSort.getValue(), comboSpeed.getValue());
            secondStage.show();
        });

        cancel.setOnAction(event -> {
            listText.clear();
        });

        Scene scene = new Scene(window, 500, 250);
        primaryStage.setTitle("Paramètres de tri");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}