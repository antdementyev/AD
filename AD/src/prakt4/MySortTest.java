package prakt4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quicksort.Data;

public class MySortTest {

    private Data[] generateDataArray(int size) {
        Data[] dataArray = new Data[size];
        for (int i=0; i<size; i++) {
            dataArray[i] = new Data((int)((Math.random()+7)*100*size), "");
        }        
        return dataArray;
    }
    
    
    @Test
    public void sort() {
        Data[] daten = generateDataArray(10000);
        
        MySort mySort = new MySort();
        mySort.sort(daten);
        mySort.printDataArray(daten);
        for (int i=0; i<daten.length-1; i++) {
            assertEquals( "" + daten[i].getKey(), true, daten[i].getKey() <= daten[i+1].getKey());
        }
    }
    
}
