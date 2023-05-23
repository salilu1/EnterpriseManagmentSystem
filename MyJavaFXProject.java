

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.sql.*;



public class MyJavaFXProject extends Application {
    
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/utopia";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
       FXMLLoader fxml = new FXMLLoader();
       
       Parent root = fxml.load(getClass().getResource("loginPage.fxml"));
       Scene scene = new Scene(root);
       primaryStage.setTitle("UTOPIA");
       primaryStage.setScene(scene);
       primaryStage.setResizable(false);
       primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Class.forName(DB_DRIVER); 
      Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      Statement stmt = con.createStatement();
      String query = "select * from login where role = 'Admin'";
      ResultSet result = stmt.executeQuery(query);
      if(!result.next()){
         query = "insert into employee (name, lastname,email,phoneNumber,salary,position,gender) values('SaleAmlak','Takele','sale21@gmail.com',0993663737,20000,'Project Manager','M')";
         stmt.executeUpdate(query);
         
         query = "insert into login(username,password,role,emp_id) values('sale123','ethio123','Admin',1)";
         stmt.executeUpdate(query);
      }
        launch(args);
    }
    
}
