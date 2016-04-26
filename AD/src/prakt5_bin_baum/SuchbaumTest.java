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
     
       // int[] werte = {7,3,10,0,5,9,12,12,1,1};
        
        int linkeGrenze = 300;
        int rechteGrenze = 1000;
        
        int summeSoll = 0;
        for (int wert : werte) {
            if (wert >= linkeGrenze && wert <= rechteGrenze){
                summeSoll += wert;
            }
        }
        
        Suchbaum baum = new Suchbaum(werte);
        System.out.println(summeSoll);
        System.out.println(baum.getSummeZwischen(linkeGrenze, rechteGrenze));
        assertEquals(summeSoll, baum.getSummeZwischen(linkeGrenze, rechteGrenze));
        
        
    }
    
}
