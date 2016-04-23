package prakt4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quicksort.Data;

public class MySortTest {

    Data[] daten = {
            new Data(400, ""), 
            new Data(0, ""),
            new Data(-2, ""),
            new Data(-7, ""),
            new Data(0, ""),
            new Data(3, ""),
            new Data(1, "")
    };
    
    @Test
    public void sort() {
        // hier sort
        assertEquals(-7, daten[0].getKey());
        assertEquals(-2, daten[1].getKey());
        assertEquals(0, daten[2].getKey());
        assertEquals(0, daten[3].getKey());
        assertEquals(1, daten[4].getKey());
        assertEquals(3, daten[5].getKey());
        assertEquals(400, daten[6].getKey());
    }
    
}
