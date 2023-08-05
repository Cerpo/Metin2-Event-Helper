package metin2.ctk;

public class ChangeCard {
    private String  cardName;
    private Boolean fiveNeighbour;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Boolean isFiveNeighbour() {
        return fiveNeighbour;
    }

    public void setFiveNeighbour(Boolean fiveNeighbour) {
        this.fiveNeighbour = fiveNeighbour;
    }
    
    public ChangeCard() {
        cardName        = null;
        fiveNeighbour   = null;
    }
    
    public void reset() {
        cardName        = null;
        fiveNeighbour   = null;
    }
}
