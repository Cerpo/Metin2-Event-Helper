package metin2;

import java.io.IOException;
import static java.lang.System.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Metin2Controller {
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    public void exit_(MouseEvent e){
        exit(0);
    }
    
    @FXML
    public void mousePressed(MouseEvent e) {
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }
    
    @FXML
    public void mouseDragged(MouseEvent e) {
        Node  n;
        Stage s;
        
        n = (Node) e.getTarget();
        s = (Stage) n.getScene().getWindow();
        s.setX(e.getScreenX() - xOffset);
        s.setY(e.getScreenY() - yOffset);
    }
    
    @FXML
    public void startCatchTheKing(ActionEvent e) throws IOException, InterruptedException {
        Node   n;
        Stage  s;
        Parent r;
        Scene  c;
        
        n = (Node) e.getTarget();
        s = (Stage) n.getScene().getWindow();
        r = FXMLLoader.load(getClass().getResource("/resources/fxml/CatchTheKing.fxml"));
        c = new Scene(r);
        c.setFill(Color.TRANSPARENT);
        s.setScene(c);
        s.setX(s.getX() - 140);
        s.setY(s.getY() + 40);
        s.show();
    }
    
    @FXML
    public void startOkeyCard(ActionEvent e) throws IOException, InterruptedException {
        Node   n;
        Stage  s;
        Parent r;
        Scene  c;
        
        n = (Node) e.getTarget();
        s = (Stage) n.getScene().getWindow();
        r = FXMLLoader.load(getClass().getResource("/resources/fxml/OkeyCard.fxml"));
        c = new Scene(r);
        c.setFill(Color.TRANSPARENT);
        s.setScene(c);
        s.setX(s.getX() - 160);
        s.setY(s.getY() + 40);
        s.show();
    }
}
