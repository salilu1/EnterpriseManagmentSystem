
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

public class SignupPageController implements Initializable {

     static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/utopia";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
     Scenes bb = new Scenes();
 
    @FXML
    private TextField email;
     @FXML
    private TextField password;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField pnum;

    @FXML
    private TextField sex;

    @FXML
    private TextField uname;
    
     @FXML
    private Label error2;
     

    @FXML
   public void back(ActionEvent event) throws IOException {
       bb.changeScene("LoginPage.fxml",event,"UTOPIA");

    }

    @FXML
  public  void submit(ActionEvent event) throws ClassNotFoundException, SQLException,  IOException {
        Class.forName(DB_DRIVER); 
      Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
       
       
        //int lastid;
        String name = fname.getText();
        String lastn = lname.getText();
        String gender = sex.getText();
        String username = uname.getText();
        String phone = pnum.getText();
        String pass = password.getText();
        int lastid=1;
        
        int pnum = Integer.parseInt(phone);
        String eml = email.getText();
        if("".equals(name) || "".equals(lastn) || "".equals(gender) || "".equals(username) || "".equals(eml) || "".equals(pnum) || "".equals(pass)){
            error2.setText("* All fields are required");
        }
        else{
            Statement st = con.createStatement();
            String query = "select max(cust_id) from customer";
            ResultSet result = st.executeQuery(query);
            if(result.next()){
                lastid = result.getInt(1);
                lastid+=1;
                
            }
           
            
             PreparedStatement prepared1 = con.prepareStatement("insert into customer(name,lastname,phonenumber,email,gender) values(?,?,?,?,?)");
             PreparedStatement prepared2 = con.prepareStatement("insert into login(username,password,role,cust_id) values(?,?,?,?)");
             prepared1.setString(1, name);
             prepared1.setString(2, lastn);
             prepared1.setInt(3, pnum);
             prepared1.setString(4, eml);
             prepared1.setString(5, gender);
             prepared1.executeUpdate();
             prepared2.setString(1,username);
             prepared2.setString(2, pass);
             prepared2.setString(3, "user");
             prepared2.setInt(4,lastid);
               
           // prepared2.executeUpdate();
            int rowAffected = prepared2.executeUpdate();
             
             if(rowAffected>0){
                 
                 bb.changeScene("LoginPage.fxml", event, "UTOPIA");
                 
             }
             else{
             error2.setText("Some mistake happen, please try again");
        }
                 
             
             
             
             
             con.close();
             //result.close();
             prepared1.close();
             prepared2.close();
        }
        
        
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

