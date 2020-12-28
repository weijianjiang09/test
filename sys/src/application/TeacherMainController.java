package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TeacherMainController {
	@FXML
	private Label txtcno_0;
	@FXML
	private Label txtteachername_0;
	@FXML
	private Button btnupdate;
	@FXML
	private TextField txtTno_0;
	@FXML
	private Button btnstudent_look;
	@FXML
	private TableView txtstudentchart;
	@FXML
	private TableColumn chart_sno_1;
	@FXML
	private TableColumn chart_cno_1;
	@FXML
	private TableColumn chart_classnum_1;
	@FXML
	private TableColumn chart_name_1;
	@FXML
	private TableColumn chart_sex_1;
	@FXML
	private TextField txtsno_add;
	@FXML
	private TextField txtcno_add;
	@FXML
	private TextField txtclassnum_add;
	@FXML
	private TextField txtname_add;
	@FXML
	private TextField txtsex_add;
	@FXML
	private Button btnstudent_add;
	@FXML
	private TextField txtsno_delete;
	@FXML
	private Button btnstudent_delete;
	@FXML
	private TextField txtsno_change;
	@FXML
	private TextField txtcno_change;
	@FXML
	private TextField txtclassnum_change;
	@FXML
	private TextField txtname_change;
	@FXML
	private TextField txtsex_change;
	@FXML
	private Button btnstudent_change;
	@FXML
	private Button btncourse_look;
	@FXML
	private TableView txtcoursechart;
	@FXML
	private TableColumn chart_cno_2;
	@FXML
	private TableColumn chart_coursename_2;
	@FXML
	private TableColumn chart_coursepreposition_2;
	@FXML
	private TextField txtcoursenum_add;
	@FXML
	private TextField txtcoursename_add;
	@FXML
	private TextArea txtcoursepreposition_add;
	@FXML
	private Button btncourse_add;
	@FXML
	private TextField txtcoursenum_delete;
	@FXML
	private Button btncourse_delete;
	@FXML
	private TextField txtcoursenum_change;
	@FXML
	private TextField txtcoursename_change;
	@FXML
	private TextArea txtcoursepreposition_change;
	@FXML
	private Button btncourse_change;

	// Event Listener on Button[#btnupdate].onAction
	@FXML
	public void updateAction(ActionEvent event) {
		// TODO Autogenerated
		String tno=txtTno_0.getText();
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		ps = conn.prepareStatement("SELECT * FROM teacher");
		res = ps.executeQuery();
		while (res.next()) {
			if(res.getString(1).equals(tno)) {
				txtcno_0.setText(res.getString(2));
				txtteachername_0.setText(res.getString(3));
				
			}
		}
		
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				res.close();
				ps.close();
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
		}
	}
	// Event Listener on Button[#btnstudent_look].onAction
	@FXML
	public void btnstudent_lookAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnstudent_add].onAction
	@FXML
	public void btnstudent_addAction(ActionEvent event) {
		// TODO Autogenerated
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		ps = conn.prepareStatement("INSERT INTO student(Sno,Cno,Class,Sname,Ssex) VALUES (?,?,?,?,?)");
		ps.setString(1, txtsno_add.getText());
		ps.setString(2,txtcno_add.getText());
		ps.setString(3, txtclassnum_add.getText());
		ps.setString(4, txtname_add.getText());
		ps.setString(5, txtsex_add.getText());
		ps.executeUpdate();
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				
				ps.close();
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
			txtsno_add.setText("");
			txtcno_add.setText("");
			txtclassnum_add.setText("");
			txtname_add.setText("");
			txtsex_add.setText("");
			
		}
	}
	// Event Listener on Button[#btnstudent_delete].onAction
	@FXML
	public void btnstudent_deleteAction(ActionEvent event) {
		// TODO Autogenerated
		
		String sno=txtsno_delete.getText();
		Statement stat = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		String sql = "DELETE FROM student WHERE Sno='"+sno+"'";
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				
				
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
			txtsno_delete.setText("");
		}
	}
	// Event Listener on Button[#btnstudent_change].onAction
	@FXML
	public void btnstudent_changeAction(ActionEvent event) {
		// TODO Autogenerated
		Connection conn = null;
		ResultSet res = null;
		Statement stat = null;
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		String sql = "SELECT Sno,Cno,Class,Sname,Ssex FROM student";
		stat = conn.createStatement();
		res=stat.executeQuery(sql);
		while(res.next()) {
			if (res.getString(1).equals(txtsno_change.getText())) {
				String sql2="UPDATE student SET Cno='"+txtcno_change.getText()+"'  WHERE Sno='"+txtsno_change.getText()+"'";
				String sql3="UPDATE student SET Class='"+txtclassnum_change.getText()+"'  WHERE Sno='"+txtsno_change.getText()+"'";
				String sql4="UPDATE student SET Sname='"+txtname_change.getText()+"'  WHERE Sno='"+txtsno_change.getText()+"'";
				String sql5="UPDATE student SET Ssex='"+txtsex_change.getText()+"'  WHERE Sno='"+txtsno_change.getText()+"'";
				stat.executeUpdate(sql2);
				stat.executeUpdate(sql3);
				stat.executeUpdate(sql4);
				stat.executeUpdate(sql5);
			}
		}
		
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				
				stat.close();
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
			txtsno_change.setText("");
			txtcno_change.setText("");
			txtclassnum_change.setText("");
			txtname_change.setText("");
			txtsex_change.setText("");
		}
	}
	// Event Listener on Button[#btncourse_look].onAction
	@FXML
	public void btncourse_lookAction(ActionEvent event) {
		// TODO Autogenerated

	}
	// Event Listener on Button[#btncourse_add].onAction
	@FXML
	public void btncourse_addAction(ActionEvent event) {
		// TODO Autogenerated
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		ps = conn.prepareStatement("INSERT INTO department(department_num,department_name,department_intro) VALUES (?,?,?)");
		ps.setString(1, txtcoursenum_add.getText());
		ps.setString(2,txtcoursename_add.getText());
		ps.setString(3, txtcoursepreposition_add.getText());

		ps.executeUpdate();
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				
				ps.close();
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
			txtcoursenum_add.setText("");
			txtcoursename_add.setText("");
			txtcoursepreposition_add.setText("");
		
		}
		
	}
	// Event Listener on Button[#btncourse_delete].onAction
	@FXML
	public void btncourse_deleteAction(ActionEvent event) {
		// TODO Autogenerated
		String coursenum=txtcoursenum_delete.getText();
		Statement stat = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		String sql = "DELETE FROM department WHERE Sno='"+coursenum+"'";
		stat = conn.createStatement();
		stat.executeUpdate(sql);
		
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				
				
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
			txtcoursenum_delete.setText("");
		}
	}
	// Event Listener on Button[#btncourse_change].onAction
	@FXML
	public void btncourse_changeAction(ActionEvent event) {
		// TODO Autogenerated
		Connection conn = null;
		ResultSet res = null;
		Statement stat = null;
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		String sql = "SELECT  department_num,department_name,department_intro FROM department";
		stat = conn.createStatement();
		res=stat.executeQuery(sql);
		while(res.next()) {
			if (res.getString(1).equals(txtcoursenum_change.getText())) {
				
				String sql3="UPDATE student SET department_name='"+txtcoursename_change.getText()+"'  WHERE department_num='"+txtcoursenum_change.getText()+"'";
				String sql4="UPDATE student SET department_intro='"+txtcoursepreposition_change.getText()+"'  WHERE department_num='"+txtcoursenum_change.getText()+"'";
				

				stat.executeUpdate(sql3);
				stat.executeUpdate(sql4);
	
			}
		}
		
		}catch (Exception q){
			q.printStackTrace();
			System.out.println("go die");
		}finally{
			try{
				
				stat.close();
				conn.close();
				System.out.println("close ok");
			}catch (SQLException o){
				o.printStackTrace();
				System.out.println("go die 2");
			}
			txtcoursenum_change.setText("");
			txtcoursename_change.setText("");
			txtcoursepreposition_change.setText("");

		}
	}
}
