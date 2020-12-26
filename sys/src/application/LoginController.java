package application;

import java.io.IOException;

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
	
	
	public void btnLoginAction() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SystemMain.fxml"));  
		  
        Scene scene = new Scene(root,500, 400);  
        Stage stage=new Stage();
		stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
      stage.setTitle("教学管理系统登录");  
        stage.show();
	}

}
