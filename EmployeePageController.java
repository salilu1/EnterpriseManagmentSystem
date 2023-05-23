/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EmployeePageController implements Initializable {
        static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
      static final String DB_URL = "jdbc:mysql://localhost/utopia";
     static final String DB_USER = "root";
    static final String DB_PASS = "";

      @FXML
   public void logout(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
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
       
     Scenes n = new Scenes();
     n.changeScene("loginpage.fxml", event, "UTOPIA");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
