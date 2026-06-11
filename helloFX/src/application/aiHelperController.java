package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class aiHelperController {

    @FXML
    private ToggleGroup MC;

    @FXML
    private ToggleGroup N;

    @FXML
    private ToggleGroup TF;

    @FXML
    private Label errors;

    public void selectHere(ActionEvent e) {
    	

        if (MC.getSelectedToggle() == null &&
            N.getSelectedToggle() == null &&
            TF.getSelectedToggle() == null) {

            errors.setText("Must select number of questions to create.");
        }
    }
}
