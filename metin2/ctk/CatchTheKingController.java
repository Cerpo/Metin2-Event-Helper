package metin2.ctk;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CatchTheKingController {
    @FXML
    private GridPane visualTable;
    @FXML
    private GridPane statusTable;
    @FXML
    private ToggleGroup fiveNeighbour;
    
    private static final ChangeCard changeCard;
    private static final Card       cards     ;
    private static Cell[][]         table     ;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    static {
        changeCard  = new ChangeCard();
        cards       = new Card      ();
        initTable();
    }
    
    @FXML
    public void initialize() {
        if (statusTable != null) {
            refreshStatusTable();
        }
    }
    
    private static void initTable() {
        table = new Cell[5][5];
        
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = new Cell();
            }
        }
    }
    
    private void refreshStatusTable() {
        ObservableList<Node> n;
        
        n = statusTable.getChildren();
        
        for (Node node : n) {
            if (GridPane.getColumnIndex(node) == 2) {
                refreshStatusOfCard(node, GridPane.getRowIndex(node));
            }
        }
    }
    
    private void refreshStatusOfCard(Node node, int index) {
        Label  l;
        String s;
        
        l = (Label) node;
        
        switch(index) {
            case(1):
                s = String.valueOf(cards.getAvailableCardsOf1());
                break;
            case(2):
                s = String.valueOf(cards.getAvailableCardsOf2());
                break;
            case(3):
                s = String.valueOf(cards.getAvailableCardsOf3());
                break;
            case(4):
                s = String.valueOf(cards.getAvailableCardsOf4());
                break;
            case(5):
                s = String.valueOf(cards.getAvailableCardsOf5());
                break;
            case(6):
                s = String.valueOf(cards.getAvailableCardsOfk());
                break;
            default:
                s = "-";
        }
        
        if (s.equals("-")) {
            l.setText(s);
        } else {
            l.setText("X" + s);
        }
    }
    
    @FXML
    public void backToMainMenu(ActionEvent e) throws IOException {
        Node   n;
        Stage  s;
        Parent r;
        Scene  c;
        
        resetGame_();
        n = (Node) e.getTarget();
        s = (Stage) n.getScene().getWindow();
        r = FXMLLoader.load(getClass().getResource("/resources/fxml/MainMenu.fxml"));
        c = new Scene(r);
        c.setFill(Color.TRANSPARENT);
        s.setScene(c);
        s.setX(s.getX() + 140);
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
    public void backToTable(MouseEvent e) {
        Node  n;
        Stage s;
        
        changeCard.reset();
        n = (Node) e.getTarget();
        s = (Stage) n.getScene().getWindow();
        s.close();
    }
    
    @FXML
    public void changeCard(MouseEvent e) {
        RadioButton fiveRb;
        String      fiveStr;
        Node        n;
        Stage       s;
        
        if (changeCard.getCardName() != null) {
            fiveRb = (RadioButton) fiveNeighbour.getSelectedToggle();
            fiveStr = fiveRb.getText();
            if (fiveStr.equals("Yes")) {
                changeCard.setFiveNeighbour(true);
            } else if (fiveStr.equals("No")) {
                changeCard.setFiveNeighbour(false);
            }
            n = (Node) e.getTarget();
            s = (Stage) n.getScene().getWindow();
            s.close();
        }
    }
    
    @FXML
    public void openSelectorWindow(MouseEvent e) throws InterruptedException, IOException {
        Node    n       ;
        Stage   o       ;
        Stage   s       ;
        Scene   c       ;
        Parent  r       ;
        Integer rowIndex;
        Integer colIndex;
        
        r = FXMLLoader.load(getClass().getResource("/resources/fxml/CatchTheKingPopupWindow.fxml"));
        n = (Node) e.getTarget();
        o = (Stage) n.getScene().getWindow();
        s = new Stage (                          );
        c = new Scene (r                         );
        c.setFill     (Color.TRANSPARENT         );
        s.setScene    (c                         );
        s.initStyle   (StageStyle.TRANSPARENT    );
        s.initModality(Modality.APPLICATION_MODAL);
        s.setX        (o.getX() + 70             );
        s.setY        (o.getY() + 110            );
        s.showAndWait (                          );
        
        rowIndex = GridPane.getRowIndex   (n);
        colIndex = GridPane.getColumnIndex(n);
        
        if (changeCard.getCardName() != null && changeCard.isFiveNeighbour() != null) {
            if (changeCard.getCardName().equals(table[rowIndex - 1][colIndex - 1].getValue()) || changeCard.getCardName().equals("d")) {
                setValueOfTable(n);
            } else {
                if (decreaseStatusOfCard(changeCard.getCardName())) {
                    setValueOfTable(n);
                }
            }
        }
        refreshTable();
    }
    
    @FXML
    public void resetGame(ActionEvent e) {
        resetGame_();
    }
    
    @FXML
    public void selectCard(MouseEvent e) {
        Node    n;
        Integer c;
        
        n = (Node) e.getTarget();
        c = GridPane.getColumnIndex(n);
        changeCard.setCardName(convertColIndexToCard(c));
    }
    
    private void resetGame_() {
        changeCard.reset  ();
        cards     .reset  ();
        initTable         ();
        resetTable        ();
        refreshStatusTable();
    }
    
    private void setValueOfTable(Node node) {
        Integer rowIndex;
        Integer colIndex;
        
        rowIndex = GridPane.getRowIndex   (node) - 1;
        colIndex = GridPane.getColumnIndex(node) - 1;
        
        Cell.removeCardFromContainers(rowIndex.toString() + colIndex.toString());
        
        if (changeCard.getCardName().equals("d")) {
            increaseStatusOfCard(table[rowIndex][colIndex].getValue());
            Cell.removeFromHasFNList(rowIndex.toString() + colIndex.toString());
            Cell.addToGuaranteedNFN(rowIndex.toString() + colIndex.toString(), getSelfList(rowIndex.toString() + colIndex.toString()));
        } else {
            if (changeCard.isFiveNeighbour()) {
                Cell.addToHasFN(rowIndex.toString() + colIndex.toString(), getListOfNeighbours(rowIndex, colIndex));
            } else {
                Cell.addToGuaranteedNFN(rowIndex.toString() + colIndex.toString(), getListOfNeighbours(rowIndex, colIndex));
            }
        }
        table[rowIndex][colIndex].setValue(changeCard.getCardName());
        changeCard.reset();
    }
    
    private List<String> getSelfList(String card) {
        List<String> l;
        
        l = new ArrayList();
        l.add(card);
        
        return l;
    }
    
    private List<String> getListOfNeighbours(int rowIndex, int colIndex) {
        List<String> l;
        
        l = new ArrayList();
        
        for (int i = rowIndex - 1; i <= rowIndex + 1; i ++) {
            for (int j = colIndex - 1; j <= colIndex + 1; j++) {
                if (inRange(i, 0, 4) && inRange(j, 0, 4) && !(rowIndex == i && colIndex == j)) {
                    l.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        
        return l;
    }
    
    private boolean inRange(int x, int min, int max) {
        return (min <= x) && (max >= x);
    }
    
    private void refreshTable() {
        for (int j = 0; j < table.length; j++) {
            for (int k = 0; k < table[1]. length; k++) {
                setCardImage(getNode(j + 1, k + 1),"d");
            }
        }
        for (int j = 0; j < table.length; j++) {
            for (int k = 0; k < table[1]. length; k++) {
                if (!Cell.hasFNContainsCard2(String.valueOf(j) + String.valueOf(k))) {
                    setCardImage(getNode(j + 1, k + 1), table[j][k].getValue());
                } else if(!table[j][k].getValue().equals("d")) {
                    setCardImage(getNode(j + 1, k + 1), table[j][k].getValue());
                }
                if (Cell.hasFNContainsCard(String.valueOf(j) + String.valueOf(k))) {
                    displayFiveNeighbours(j, k);
                }
            }
        }
    }
    
    private void displayFiveNeighbours(int rowIndex, int colIndex) {
        for (int i = rowIndex - 1; i <= rowIndex + 1; i ++) {
            for (int j = colIndex - 1; j <= colIndex + 1; j++) {
                if (inRange(i, 0, 4) && inRange(j, 0, 4) && !(rowIndex == i && colIndex == j)) {
                    if (table[i][j].getValue().equals("d") && !Cell.GuaranteedNFNContainsCard(String.valueOf(i) + String.valueOf(j))) {
                        if (cards.getAvailableCardsOf5() > 0) {
                            setCardImage(getNode(i + 1, j + 1), "s");
                        }
                    }
                }
            }
        }
    }
    
    private void setCardImage(Node node, String cardName) {
        Image     i;
        ImageView v;
        
        i = getImage(cardName);
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
    
    private void increaseStatusOfCard(String cardName) {
        switch(cardName) {
            case "1":
                cards.increaseAvailableCardsOf1();
                break;
            case "2":
                cards.increaseAvailableCardsOf2();
                break;
            case "3":
                cards.increaseAvailableCardsOf3();
                break;
            case "4":
                cards.increaseAvailableCardsOf4();
                break;
            case "5":
                cards.increaseAvailableCardsOf5();
                break;
            case "k":
                cards.increaseAvailableCardsOfk();
        }
        
        refreshStatusTable();
    }
    
    private boolean decreaseStatusOfCard(String cardName) {
        boolean b;
        
        switch(cardName) {
            case "1":
                b = cards.decreaseAvailableCardsOf1();
                break;
            case "2":
                b = cards.decreaseAvailableCardsOf2();
                break;
            case "3":
                b = cards.decreaseAvailableCardsOf3();
                break;
            case "4":
                b = cards.decreaseAvailableCardsOf4();
                break;
            case "5":
                b = cards.decreaseAvailableCardsOf5();
                break;
            case "k":
                b = cards.decreaseAvailableCardsOfk();
                break;
            default:
                b = false;
        }
        
        refreshStatusTable();
        
        return b;
    }
    
    private String convertColIndexToCard(Integer colIndex) {
        switch(colIndex) {
            case(1):
                return "d";
            case(2):
                return "1";
            case(3):
                return "2";
            case(4):
                return "3";
            case(5):
                return "4";
            case(6):
                return "5";
            case(7):
                return "k";
            default:
                return "d";
        }
    }
    
    private void resetTable() {
        ObservableList<Node> n;
        
        n = visualTable.getChildren();
        
        for (Node node : n) {
            setCardImage(node, "d");
        }
    }
    
    private Image getImage(String cardName) {
        Image  i;
        String n;
        
        n = "img_catchtheking_card_" + cardName + ".png";
        i = new Image("/resources/img/catchtheking/" + n, false);
        
        return i;
    }
}
