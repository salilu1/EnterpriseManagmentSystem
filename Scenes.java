
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Scenes {
    public  void  changeScene(String page, ActionEvent event, String title) throws IOException{
        
         Parent info;
        info = FXMLLoader.load(getClass().getResource(page));
          Scene scene = new Scene(info);
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          window.setScene(scene);
          window.setTitle(title);
          window.setResizable(false);
          window.show();
    }
    
}
