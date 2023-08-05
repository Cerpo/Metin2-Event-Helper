package metin2.ctk;

public class Card {
    private int availableCardsOf1;
    private int availableCardsOf2;
    private int availableCardsOf3;
    private int availableCardsOf4;
    private int availableCardsOf5;
    private int availableCardsOfk;
    
    public Card() {
        availableCardsOf1 = MAX_CARD_NUMBER_1;
        availableCardsOf2 = MAX_CARD_NUMBER_2;
        availableCardsOf3 = MAX_CARD_NUMBER_3;
        availableCardsOf4 = MAX_CARD_NUMBER_4;
        availableCardsOf5 = MAX_CARD_NUMBER_5;
        availableCardsOfk = MAX_CARD_NUMBER_K;
    }

    public int getAvailableCardsOf1() {
        return availableCardsOf1;
    }

    public int getAvailableCardsOf2() {
        return availableCardsOf2;
    }

    public int getAvailableCardsOf3() {
        return availableCardsOf3;
    }

    public int getAvailableCardsOf4() {
        return availableCardsOf4;
    }

    public int getAvailableCardsOf5() {
        return availableCardsOf5;
    }

    public int getAvailableCardsOfk() {
        return availableCardsOfk;
    }
    
    public void increaseAvailableCardsOf1() {
        availableCardsOf1++;
    }
    
    public void increaseAvailableCardsOf2() {
        availableCardsOf2++;
    }
    
    public void increaseAvailableCardsOf3() {
        availableCardsOf3++;
    }
    
    public void increaseAvailableCardsOf4() {
        availableCardsOf4++;
    }
    
    public void increaseAvailableCardsOf5() {
        availableCardsOf5++;
    }
    
    public void increaseAvailableCardsOfk() {
        availableCardsOfk++;
    }
    
    public boolean decreaseAvailableCardsOf1() {
        if (availableCardsOf1 > 0) {
            availableCardsOf1--;
            return true;
        }
        return false;
    }
    
    public boolean decreaseAvailableCardsOf2() {
        if (availableCardsOf2 > 0) {
            availableCardsOf2--;
            return true;
        }
        return false;
    }
    
    public boolean decreaseAvailableCardsOf3() {
        if (availableCardsOf3 > 0) {
            availableCardsOf3--;
            return true;
        }
        return false;
    }
    
    public boolean decreaseAvailableCardsOf4() {
        if (availableCardsOf4 > 0) {
            availableCardsOf4--;
            return true;
        }
        return false;
    }
    
    public boolean decreaseAvailableCardsOf5() {
        if (availableCardsOf5 > 0) {
            availableCardsOf5--;
            return true;
        }
        return false;
    }
    
    public boolean decreaseAvailableCardsOfk() {
        if (availableCardsOfk > 0) {
            availableCardsOfk--;
            return true;
        }
        return false;
    }
    
    public void reset() {
        availableCardsOf1 = MAX_CARD_NUMBER_1;
        availableCardsOf2 = MAX_CARD_NUMBER_2;
        availableCardsOf3 = MAX_CARD_NUMBER_3;
        availableCardsOf4 = MAX_CARD_NUMBER_4;
        availableCardsOf5 = MAX_CARD_NUMBER_5;
        availableCardsOfk = MAX_CARD_NUMBER_K;
    }
    
    public static final int MAX_CARD_NUMBER_1 = 7;
    public static final int MAX_CARD_NUMBER_2 = 4;
    public static final int MAX_CARD_NUMBER_3 = 5;
    public static final int MAX_CARD_NUMBER_4 = 5;
    public static final int MAX_CARD_NUMBER_5 = 3;
    public static final int MAX_CARD_NUMBER_K = 1;
}
