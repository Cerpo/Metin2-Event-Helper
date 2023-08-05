package metin2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Metin2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent r;
        Scene  c;
        
        r = FXMLLoader.load(getClass().getResource("/resources/fxml/Metin2.fxml"));
        c = new Scene(r);
        c.setFill(Color.TRANSPARENT);
        stage.setScene(c);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
