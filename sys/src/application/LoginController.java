package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		String id =txtUserId.getText();
		if(id.equals("")||password.equals("")) {
			new Alert(Alert.AlertType.NONE, "输入为空", new ButtonType[]{ButtonType.CLOSE}).show();
			return;
		}
		
		Connection conn = null;
		ResultSet res = null;
		Statement stat = null;
		
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
			String sql = "SELECT  id,password FROM user";
			stat = conn.createStatement();
			res=stat.executeQuery(sql);
			while(res.next()) {
		if(id.equals(res.getString(1))&&password.equals(res.getString(2))) {
			
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
		}
		Stage stager = (Stage) btnLogin.getScene().getWindow();
		stager.close();
		stat.close();
		conn.close();
		return;
		}
      
      }
			
			new Alert(Alert.AlertType.NONE, "账号错误", new ButtonType[]{ButtonType.CLOSE}).show();
		
			
	}
	

}
