package application;

import java.io.IOException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	
		String password = String.valueOf(txtPassword.getText());
		if(password.equals("111111")) {
			
		String key;
		if((key=String.valueOf(txtUserId.getText().charAt(0))).equals("s")) {
			Parent root = FXMLLoader.load(getClass().getResource("StudentMain.fxml")); 
			Scene scene = new Scene(root,600, 500);
			Stage stage=new Stage();
			stage.initStyle(StageStyle.DECORATED); 
			stage.setScene(scene);
			stage.setTitle("学生教务系统");  
			stage.show();
			

	        
		}else if((key=String.valueOf(txtUserId.getText().charAt(0))).equals("t")){
		    Parent root = FXMLLoader.load(getClass().getResource("TeacherMain.fxml"));  
		    Scene scene = new Scene(root,600, 500);
		    Stage stage=new Stage();
			stage.initStyle(StageStyle.DECORATED); 
		    stage.setScene(scene);
		    stage.setTitle("教师教务系统");  
	        stage.show();
		}else {
			new Alert(Alert.AlertType.NONE, "输入错误", new ButtonType[]{ButtonType.CLOSE}).show();
		}
		Stage stager = (Stage) btnLogin.getScene().getWindow();
		stager.close();
		}else {
			new Alert(Alert.AlertType.NONE, "输入错误", new ButtonType[]{ButtonType.CLOSE}).show();
		}
      
          
     
        
     
	}
	

}
