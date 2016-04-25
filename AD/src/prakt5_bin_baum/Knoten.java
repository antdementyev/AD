package prakt5_bin_baum;

public class Knoten {

    private final int wert;
    private Knoten links;
    private Knoten rechts;
    private int summeKnotenLinks;
    private int summeKnotenRechts;
    
    public Knoten(int wert) {
        this.wert = wert;
    }

    @Override
    public String toString() {
        return String.format("wert %d, linke Summe %d, rechte Summe %d",
                wert, summeKnotenLinks, summeKnotenRechts);
    }
    
    public int getWert() {
        return wert;
    }

    public Knoten getLinks() {
        return links;
    }

    public Knoten getRechts() {
        return rechts;
    }

    public void setLinks(Knoten links) {
        this.links = links;
    }

    public void setRechts(Knoten rechts) {
        this.rechts = rechts;
    }

    public int getSummeKnotenRechts() {
        return summeKnotenRechts;
    }

    public void addSummeKnotenRechts(int kindWert) {
        summeKnotenRechts += kindWert;
    }

    public int getSummeKnotenLinks() {
        return summeKnotenLinks;
    }

    public void addSummeKnotenLinks(int kindWert) {
        summeKnotenLinks += kindWert;
    }   
}