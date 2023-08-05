package metin2.ctk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
    private String  value;
    
    private static Map<String, List<String>> guaranteedNoFiveNeighbour;
    private static Map<String, List<String>> hasFiveNeighbour         ;
    
    public Cell() {
        value                     = "d"            ;
        guaranteedNoFiveNeighbour = new HashMap<>();
        hasFiveNeighbour          = new HashMap<>();
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public static void addToGuaranteedNFN(String guarantor, List<String> cards) {
        guaranteedNoFiveNeighbour.put(guarantor, cards);
    }
    
    public static void addToHasFN(String guarantor, List<String> cards) {
        hasFiveNeighbour.put(guarantor, cards);
    }
    
    public static boolean hasFNContainsCard(String guarantor) {
        return hasFiveNeighbour.containsKey(guarantor);
    }
    
    public static boolean hasFNContainsCard2(String guarantor) {
        boolean b;
        
        b = false;
        for(Map.Entry<String, List<String>> entry : hasFiveNeighbour.entrySet()) {
            if (entry.getValue().contains(guarantor)) {
                b = true;
            }
        }
        
        return b;
    }
    
    public static boolean GuaranteedNFNContainsCard(String guarantor) {
        boolean b;
        
        b = false;
        for(Map.Entry<String, List<String>> entry : guaranteedNoFiveNeighbour.entrySet()) {
            if (entry.getValue().contains(guarantor)) {
                b = true;
            }
        }
        
        return b;
    }
    
    public static void removeCardFromContainers(String guarantor) {
        guaranteedNoFiveNeighbour.remove(guarantor);
        hasFiveNeighbour         .remove(guarantor);
    }
    
    public static void removeFromHasFNList(String card) {
        for(Map.Entry<String, List<String>> entry : hasFiveNeighbour.entrySet()) {
            entry.getValue().remove(card);
        }
    }
    
    public static void displayHashMaps() {
        for(Map.Entry<String, List<String>> entry : hasFiveNeighbour.entrySet()) {
            for (String l : entry.getValue()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + l);
            }
        }
        
        /*for(Map.Entry<String, List<String>> entry : guaranteedNoFiveNeighbour.entrySet()) {
            System.out.println("Garanteed Key: " + entry.getKey());
        }*/
    }
}
