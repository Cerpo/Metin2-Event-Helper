package metin2.oc;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private Map<Integer, Boolean> yellowCards;
    private Map<Integer, Boolean> blueCards  ;
    private Map<Integer, Boolean> redCards   ;
    
    public Card() {
        refillCards();
    }
    
    public final void refillCards() {
        yellowCards = new HashMap<>();
        blueCards   = new HashMap<>();
        redCards    = new HashMap<>();
        addDefaultCards(yellowCards);
        addDefaultCards(blueCards);
        addDefaultCards(redCards);
    }
    
    private void addDefaultCards(Map cards) {
        for (int i = 1; i < 9; i++) {
            cards.put(i, Boolean.TRUE);
        }
    }
    
    public boolean cardAvailable(String color, int number) {
        switch (color) {
            case "yellow":
                return yellowCards.get(number);
            case "blue":
                return blueCards.get(number);
            case "red":
                return redCards.get(number);
            default:
                return false;
        }
    }
    
    public void setCardAvailability(String color, int number, boolean available) {
        switch (color) {
            case "yellow":
                yellowCards.put(number, available);
                break;
            case "blue":
                blueCards.put(number, available);
                break;
            case "red":
                redCards.put(number, available);
        }
    }
}
