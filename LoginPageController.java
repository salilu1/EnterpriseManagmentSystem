

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

public class LoginPageController implements Initializable {
    
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/utopia";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
     Scenes s = new Scenes();
     
    
    
    

    @FXML
    private TextField password;
    @FXML
    private Label error;

    @FXML
    private TextField username;

    @FXML
   public void signIn(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
      Class.forName(DB_DRIVER); 
      Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      
        LocalDateTime currentTime = LocalDateTime.now();
     DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
     String current = currentTime.format(formater);
      PreparedStatement stmt = con.prepareStatement("insert into logInfo(login_time,log_id) values(?,?)");
       stmt.setString(1,current);
           String uname = username.getText();
          String pass = password.getText();
          if("".equals(uname) || "".equals(pass)){
          
             error.setText("Please, Enter your username and password");
           }
          else{
              
          PreparedStatement prepared = con.prepareStatement("select * from login where username=? AND password =?");

            prepared.setString(1,uname);
          prepared.setString(2,pass);
          ResultSet result = prepared.executeQuery();
         if(!result.next()){
             error.setText("Invalid password or username");
              }
         else{
              int who = result.getInt(1);
                
                
                 stmt.setInt(2,who);
                 stmt.executeUpdate();
             if(result.getString(4).equals("Admin")){
                 
               s.changeScene("adminPage.fxml", event, "Admin Page"); 
             }
             else if(result.getString(4).equals("user")){
                 
                  s.changeScene("userPage.fxml", event, "UTOPIA");
             }
             else{
                  s.changeScene("employeePage.fxml", event, "Admin Page");
             }
            
             
         }
          } 
    
      
      

    }
     @FXML
  public void signUp(ActionEvent event) throws IOException{
      s.changeScene("signupPage.fxml",event,"UTOPIA");
       
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
