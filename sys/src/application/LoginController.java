package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.PasswordField;

public class LoginController {
	@FXML
	private Button btnLogin;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtUserId;
	
	
	public void btnLoginAction() throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("StudentMain.fxml"));  
		  
        Scene scene = new Scene(root,700, 400);  
        Stage stage=new Stage();
		stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
        stage.setTitle("ѧ������ϵͳ");  
        stage.show();
      
	}
	

}
