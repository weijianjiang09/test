package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class StudentMainController {
	@FXML
	private Label txtcno_0;
	@FXML
	private Label txtclassnum_0;
	@FXML
	private Label txtname_0;
	@FXML
	private Label txtsex_0;
	@FXML
	private Button btnupdate;
	@FXML
	private TextField txtsno_0;
	@FXML
	private TextField txtcourse_1;
	@FXML
	private Button btnresults_1;
	@FXML
	private Label txtresult_1;
	@FXML
	private TextField txtclass_num_2;
	@FXML
	private TextField txtcourse_2;
	@FXML
	private Button btnresults_2;
	@FXML
	private Label txtresult_2;
	@FXML
	private TextField txtcourse_3;
	@FXML
	private Button btnresult_3;
	@FXML
	private TableView txtcoursechart_3;
	@FXML
	private TableColumn chart_coursename;
	@FXML
	private TableColumn chart_coursepreposition;
	@FXML
	private TableColumn chart_credit;

	// Event Listener on Button[#btnupdate].onAction
	@FXML
	public void updateAction(ActionEvent event) throws Exception{
		// TODO Autogenerated
		String s= txtsno_0.getText();
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		ps = conn.prepareStatement("SELECT * FROM student");
		res = ps.executeQuery();
		while (res.next()) {
			if(res.getString(1).equals(s)) {
				txtcno_0.setText(res.getString(2));
				txtclassnum_0.setText(res.getString(3));
				txtname_0.setText(res.getString(4));
				txtsex_0.setText(res.getString(5));
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
	// Event Listener on Button[#btnresults_1].onAction
	@FXML
	public void btnresults_1Action(ActionEvent event) {
		// TODO Autogenerated
		String sno=txtsno_0.getText();
		String course = txtcourse_1.getText();
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		ps = conn.prepareStatement("SELECT * FROM studentreport");
		res = ps.executeQuery();
		while (res.next()) {
			if(res.getString(1).equals(sno)) {
				if(res.getString(2).equals(course)) {
					txtresult_1.setText(res.getString(3));
				}
                
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
		txtcourse_1.setText("");
	}
	// Event Listener on Button[#btnresults_2].onAction
	@FXML
	public void btnresults_2Action(ActionEvent event) {
		// TODO Autogenerated
		String classroomnum=txtclass_num_2.getText();
		String course=txtcourse_2.getText();
		DateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
		ps = conn.prepareStatement("SELECT * FROM course_room");
		res = ps.executeQuery();
		while (res.next()) {
			if(res.getString(1).equals(classroomnum)) {
				if(res.getString(2).equals(course)) {
		
					txtresult_2.setText(date.format(res.getTimestamp(3)));
				}
                
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
		txtclass_num_2.setText("");
		txtcourse_2.setText("");
	}
	// Event Listener on Button[#btnresult_3].onAction
	@FXML
	public void btnresults_3Action(ActionEvent event) throws Exception {
		// TODO Autogenerated
		final ObservableList<Person> data =
		        FXCollections.observableArrayList(
		            new Person("...","...", "...", "...")
		        );
	      TableColumn firsNameCol = new TableColumn("课程编码");
	      firsNameCol.setMinWidth(100);
	      firsNameCol.setCellValueFactory(
	              new PropertyValueFactory<>("Num"));
	      
	      TableColumn firstNameCol = new TableColumn("课程名");
	      firstNameCol.setMinWidth(100);
	      firstNameCol.setCellValueFactory(
	              new PropertyValueFactory<>("firstName"));

	      TableColumn lastNameCol = new TableColumn("先行课程号");
	      lastNameCol.setMinWidth(100);
	      lastNameCol.setCellValueFactory(
	              new PropertyValueFactory<>("lastName"));

	      TableColumn emailCol = new TableColumn("学分");
	      emailCol.setMinWidth(200);
	      emailCol.setCellValueFactory(
	              new PropertyValueFactory<>("email"));
	      txtcoursechart_3.getColumns().addAll(firsNameCol,firstNameCol, lastNameCol, emailCol);
	      String course_num=null;
	      String course_name=null;
	      String course_preposition=null;
	      String credit=null;
		PreparedStatement ps = null;
		ResultSet res = null;
	Connection conn = null;
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sss?useSSL=false&serverTimezone=UTC","root","111111");
	ps = conn.prepareStatement("SELECT * FROM course");
	res = ps.executeQuery();
	while(res.next()) {
		course_num=res.getString(1);
		course_name=res.getString(2);
		course_preposition=res.getString(3);
		credit=res.getString(4);
	
		 
		
		 data.add(new Person(course_num,course_name,course_preposition,credit));
			    
	}
	txtcoursechart_3.setItems(data);
	}
}
