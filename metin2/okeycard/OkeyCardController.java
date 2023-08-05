package metin2.okeycard;

import java.io.IOException;
import static java.lang.System.exit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OkeyCardController {
    @FXML
    private GridPane visualTable;
    
    private static final Card cards;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    static {
        cards = new Card();
    }
    
    @FXML
    public void backToMainMenu(ActionEvent e) throws IOException {
        Node   n;
        Stage  s;
        Parent r;
        Scene  c;
        
        resetGame_(false);
        n = (Node) e.getTarget();
        s = (Stage) n.getScene().getWindow();
        r = FXMLLoader.load(getClass().getResource("/resources/fxml/MainMenu.fxml"));
        c = new Scene(r);
        c.setFill(Color.TRANSPARENT);
        s.setScene(c);
        s.setX(s.getX() + 160);
        s.setY(s.getY() - 40);
        s.show();
    }
    
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
    public void resetGame(ActionEvent e) {
        resetGame_(true);
    }
    
    private void resetGame_(boolean fullReset) {
        cards.refillCards();
        if (fullReset) {
            refreshTable();
        }
    }
    
    @FXML
    public void changeCardStatus(MouseEvent e) {
        Node    n;
        boolean b;
        Integer rowIndex;
        Integer colIndex;
        
        n        = (Node) e.getTarget();
        rowIndex = GridPane.getRowIndex(n);
        colIndex = GridPane.getColumnIndex(n);
        
        switch (rowIndex) {
            case 1:
                b = cards.cardAvailable("yellow", colIndex);
                setCardImage(getNode(rowIndex, colIndex), b ? "gray" : "yellow", colIndex);
                cards.setCardAvailability("yellow", colIndex, !b);
                break;
            case 2:
                b = cards.cardAvailable("blue", colIndex);
                setCardImage(getNode(rowIndex, colIndex), b ? "gray" : "blue", colIndex);
                cards.setCardAvailability("blue", colIndex, !b);
                break;
            case 3:
                b = cards.cardAvailable("red", colIndex);
                setCardImage(getNode(rowIndex, colIndex), b ? "gray" : "red", colIndex);
                cards.setCardAvailability("red", colIndex, !b);
        }
    }
    
    private void refreshTable() {
        for (int i = 1; i <= 8; i++) {
            setCardImage(getNode(1, i), "yellow", i);
            setCardImage(getNode(2, i), "blue", i);
            setCardImage(getNode(3, i), "red", i);
        }
    }
    
    private void setCardImage(Node node, String color, int number) {
        Image     i;
        ImageView v;
        
        i = getImage(color, number);
        v = (ImageView) node;
        v.setImage(i);
    }
    
    private Node getNode(int rowIndex, int colIndex) {
        ObservableList<Node> n;
        
        n = visualTable.getChildren();
        
        for (Node node : n) {
            if ((GridPane.getRowIndex(node) == rowIndex) && (GridPane.getColumnIndex(node) == colIndex)) {
                return node;
            }
        }
        
        return null;
    }
    
    private Image getImage(String color, int number) {
        Image  i;
        String n;
        
        n = "img_okeycard_" + color + "_" + number + ".png";
        i = new Image("/resources/img/okeycard/" + n, false);
        
        return i;
    }
}
