

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class EmployeeRegisterationPageController implements Initializable {
    
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/utopia";
    static final String DB_USER = "root";
    static final String DB_PASS = "";

    @FXML
    private TextField email;

    @FXML
    private TextField fname;

  
    @FXML
    private TextField username;

    @FXML
    private TextField lname;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField position;

    @FXML
    private TextField salary;

    @FXML
    private TextField sex;
     @FXML
    private Label info;
     Scenes emp = new Scenes();

    @FXML
   public void back(ActionEvent event) throws IOException {
       emp.changeScene("adminPage.fxml", event, "AdminPage");
    }

    @FXML
   public void logout(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
       
       Class.forName(DB_DRIVER); 
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        
       LocalDateTime currentTime = LocalDateTime.now();
       DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
       String current = currentTime.format(formater);
       Statement stmt = con.createStatement();
       ResultSet result = stmt.executeQuery("select MAX(info_id) from logInfo");
       if(result.next()){
           int x = result.getInt(1);
           PreparedStatement ps = con.prepareStatement("update logInfo set logout_time = ? WHERE info_id = ? ");
           ps.setString(1, current);
           ps.setInt(2, x);
           ps.executeUpdate();
       }
       emp.changeScene("loginpage.fxml", event, "AdminPage");
    }

    @FXML
   public void submit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER); 
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        
        String query1 = "insert into employee (name, lastname,email,phoneNumber,salary,position,gender) values(?,?,?,?,?,?,?)";
        String query2 = "insert into login(username,password,role,emp_id) values(?,?,?,?)";
        
        
        PreparedStatement prepared1 = con.prepareStatement(query1);
        PreparedStatement prepared2 = con.prepareStatement(query2);
        
        double salry=0;
      String name = fname.getText();
      String lastn = lname.getText();
      String emal = email.getText();
      String gender =  sex.getText();
     String pos = position.getText();
     String birr = salary.getText();
     int lastid=0;
     if(!birr.equals("")){
      salry = Integer.parseInt(birr); 
     }
     
     String num = phone.getText();
     int number = Integer.parseInt(num);
     String uname = username.getText();
     String pass = password.getText();
     
     if(name.equals("") || lastn.equals("") || emal.equals("") || gender.equals("") || pos.equals("") || pass.equals("") || uname.equals("") || birr.equals("") || num.equals("")){
         
         info.setText("*All fields are required!");
     }
     else{
        
            Statement st = con.createStatement();
            String query = "select max(emp_id) from employee";
            ResultSet result = st.executeQuery(query);
            if(result.next()){
                lastid = result.getInt(1);
                lastid+=1;
                
            }
     prepared1.setString(1,name);
     prepared1.setString(2,lastn);
     prepared1.setString(3,emal);
     prepared1.setInt(4,number);
     prepared1.setDouble(5,salry);
     prepared1.setString(6,pos);
     prepared1.setString(7,gender);
     int rowAffected1 = prepared1.executeUpdate();
     
     prepared2.setString(1,uname);
     prepared2.setString(2,pass);
     prepared2.setString(3,"Employee");
     prepared2.setInt(4,lastid);
     int rowAffected2 = prepared2.executeUpdate();
     
     if(rowAffected1 > 0 && rowAffected2 > 0){
         info.setText("Succesfully Added!");
         fname.clear();
         lname.clear();
         email.clear();
         sex.clear();
         position.clear();
         salary.clear();
         phone.clear();
         password.clear();
         username.clear();
     }
     else{
         info.setText("Database connection lost");
     }
     }
     
 
     
     
     
      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
