package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class mainMenuController {

	
	 @FXML
	  private VBox logo;
	 
	   @FXML
	    public void initialize() {
	        loadLogo();
	    }
	 
	 
	 public void start(ActionEvent e) {
		 
	 
	 }

	 private void loadLogo() {

	        try {

	            Image image = new Image(
	                getClass().getResourceAsStream("Start_Logo_Improved2.png")
	            );

	            ImageView imageView = new ImageView(image);

	            imageView.setFitWidth(150);
	            imageView.setPreserveRatio(true);

	            logo.getChildren().clear();
	            logo.getChildren().add(imageView);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	
	
}
