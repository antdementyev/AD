package prakt5_bin_baum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuchbaumTest {

    
    @Test
    public void getSummeZwieschen() throws IllegalAccessException {
        int[] werte = new int[50];
        for (int i=0; i<werte.length; i++) {
            werte[i] = (int)(Math.random()*1000);
        }
        
        int linkeGrenze = 5;
        int rechteGrenze = 1005;
        
        int summeSoll = 0;
        for (int wert : werte) {
            if (wert >= linkeGrenze && wert <= rechteGrenze){
                summeSoll += wert;
            }
        }
        
        Suchbaum baum = new Suchbaum(werte);
        assertEquals(summeSoll, baum.getSummeZwieschen(linkeGrenze, rechteGrenze));
        
        
    }
    
}
