package prakt5_bin_baum;

public class Suchbaum {

    private Knoten root;
    
    public Suchbaum(int... werte) throws IllegalAccessException {
        for (int wert : werte) {
            addKnoten(wert);
        }
    }
    
    public void addKnoten(int wert) throws IllegalAccessException {
        if (wert < 0) {
            throw new IllegalAccessException();
        }
        if (root == null) {
            root = new Knoten(wert);
        } else {
            addKnoten(wert, root);            
        }
    }
    
    public int getSummeZwischen(int grenzeLinks, int grenzeRechts) throws IllegalAccessException {
        if (grenzeLinks > grenzeRechts) {
            throw new IllegalAccessException();
        }
        return getKleinMSumme(grenzeLinks-0.5, root)
                - getKleinMSumme(grenzeRechts+0.5, root);
        
        
        
        
        
        

        
    }
    
    private boolean addKnoten(int wert, Knoten knoten) {
        boolean erfolg = true;
        if (knoten.getWert() == wert) {
            System.out.println("Wert schon enthalten");
            erfolg = false;
        } else if (wert < knoten.getWert()) {
                knoten.addSummeKnotenLinks(wert);                
            if (knoten.getLinks() == null) {
                knoten.setLinks(new Knoten(wert));
            } else {
                if (!addKnoten(wert, knoten.getLinks())){
                    erfolg = false;
                    knoten.addSummeKnotenLinks(-wert); 
                }              
            }
        } else {
                knoten.addSummeKnotenRechts(wert);                
            if (knoten.getRechts() == null) {
                knoten.setRechts(new Knoten(wert));
            } else {
                if (!addKnoten(wert, knoten.getRechts())){
                    erfolg = false;
                    knoten.addSummeKnotenRechts(-wert);             
                }
            }
        }
        return erfolg;
    }
    
    public Knoten getKleinM(int wertLinkeGrenze, Knoten knoten) {
        Knoten kleinM;
        if (wertLinkeGrenze == knoten.getWert()) {
            kleinM = knoten;
        } else if (wertLinkeGrenze < knoten.getWert()) {
            if (knoten.getLinks() == null) {
                kleinM = knoten;
            } else {
                kleinM = getKleinM(wertLinkeGrenze, knoten.getLinks());
                if (kleinM == null) {
                    kleinM = knoten;
                }
            }
        } else {
            if (knoten.getRechts() == null){
                kleinM = null;
            } else {
                kleinM = getKleinM(wertLinkeGrenze, knoten.getRechts());
            }
        }
        return kleinM;
    }
    
    private int getKleinMSumme(double wertLinkeGrenze, Knoten knoten) {
        int summe = 0;
        if (Math.abs(wertLinkeGrenze - knoten.getWert()) < 0.1) {
            summe = knoten.getWert()+knoten.getSummeKnotenRechts();
        } else if (wertLinkeGrenze < knoten.getWert()) {
            summe = knoten.getSummeKnotenRechts() + knoten.getWert();
            if (knoten.getLinks() != null) {
                summe += getKleinMSumme(wertLinkeGrenze, knoten.getLinks());
            }
        } else {
            if (knoten.getRechts() != null){
                summe = getKleinMSumme(wertLinkeGrenze, knoten.getRechts());
            }
        }
        return summe;
    }
    
    
    public Knoten getGrossM(int wert, Knoten knoten) {
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
            if (knoten.getLinks() == null) {
                grossM = null;
            } else {
                grossM = getGrossM(wert, knoten.getLinks());
            }
        }
        return grossM;
    }
    
    private int getGrossMSumme(int wertRechteGrenze, Knoten knoten) {
        int summe = 0;
        if (wertRechteGrenze == knoten.getWert()) {
            summe = knoten.getWert()+knoten.getSummeKnotenLinks();
        } else if (wertRechteGrenze > knoten.getWert()) {
            summe = knoten.getSummeKnotenLinks() + knoten.getWert();
            if (knoten.getRechts() != null) {
                summe += getGrossMSumme(wertRechteGrenze, knoten.getRechts());
            }
        } else {
            if (knoten.getLinks() != null){
                summe = getGrossMSumme(wertRechteGrenze, knoten.getLinks());
            }
        }
        return summe;
    }
    
    
    ////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws IllegalAccessException {
        Suchbaum baum = new Suchbaum(7,3,10,0,5,9,12,1);
        System.out.println(baum.getKleinM(3, baum.root));
        System.out.println(baum.getSummeZwischen(3, 6));
        System.out.println("---------------");
        System.out.println(baum.getKleinMSumme(0, baum.root));
        System.out.println(baum.getKleinMSumme(5, baum.root));
        System.out.println("------------");
        System.out.println(baum.getGrossMSumme(-1, baum.root.getLinks()));
        
    }
    
}
