package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Controller {
	
	

    public void userGenerate(ActionEvent e) {

        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("UserEnterQuizPrompt.fxml")
            );

            Stage stage = (Stage) ((Node) e.getSource())
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            scene.getStylesheets().add(
                getClass().getResource("application.css").toExternalForm()
            );

            stage.setScene(scene);
            stage.show();

        } catch (Exception g) {
            g.printStackTrace();
        }
    }

    public void AiGenerate(ActionEvent e) {
        System.out.println("AI clicked");
    }
}