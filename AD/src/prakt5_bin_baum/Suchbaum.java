package prakt5_bin_baum;

public class Suchbaum {

    private Knoten root;
    
    public Suchbaum(int... werte) {
        for (int wert : werte) {
            addKnoten(wert);
        }
    }
    
    public void addKnoten(int wert) {
        if (root == null) {
            root = new Knoten(wert);
        } else {
            addKnoten(wert, root);            
        }
    }
    
    private void addKnoten(int wert, Knoten knoten) {
        if (knoten.getWert() == wert) {
            System.out.println("Wert schon enthalten");
        } else if (wert < knoten.getWert()) {
            knoten.addSummeKnotenLinks(wert);
            if (knoten.getLinks() == null) {
                knoten.setLinks(new Knoten(wert));
            } else {
                addKnoten(wert, knoten.getLinks());                
            }
        } else {
            knoten.addSummeKnotenRechts(wert);
            if (knoten.getRechts() == null) {
                knoten.setRechts(new Knoten(wert));
            } else {
                addKnoten(wert, knoten.getRechts());                
            }
        }
    }
    
    private Knoten getKleinM(int wert, Knoten knoten) {
        Knoten kleinM;
        if (wert == knoten.getWert()) {
            kleinM = knoten;
        } else if (wert < knoten.getWert()) {
            if (knoten.getLinks() == null) {
                kleinM = knoten;
            } else {
                kleinM = getKleinM(wert, knoten.getLinks());
                if (kleinM == null) {
                    kleinM = knoten;
                }
            }
        } else {
            if (knoten.getRechts() == null){
                kleinM = null;
            } else {
                kleinM = getKleinM(wert, knoten.getRechts());
            }
        }
        return kleinM;
    }
    
    private Knoten getGrossM(int wert, Knoten knoten) {
        Knoten grossM;
        if (wert == knoten.getWert()) {
            grossM = knoten;
        } else if (wert > knoten.getWert()) {
            if (knoten.getRechts() == null) {
                grossM = knoten;
            } else {
                grossM = getGrossM(wert, knoten.getRechts());
                if (grossM == null) {
                    grossM = knoten;
                }
            }
        } else {
            if (knoten.getLinks() == null){
                grossM = null;
            } else {
                grossM = getGrossM(wert, knoten.getLinks());
            }
        }
        return grossM;
    }
    
    ////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Suchbaum baum = new Suchbaum(7,3,10,0,5,9,12,1);
        System.out.println(baum.getGrossM(13, baum.root));
    }
    
}
