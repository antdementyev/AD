package quicksort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import quicksort.QuickSort.PivotChoice;

public class QSortTest {

    QuickSort sort = new QuickSort();
    List<Data> daten;
    
    private void initDaten() {
        daten = new ArrayList<Data>();
        Data d1 = new Data(4, "");
        Data d2 = new Data(1, "");
        Data d3 = new Data(-1, "");
        Data d4 = new Data(0, "");
        Data d5 = new Data(2, "");
        Data d6 = new Data(-5, "");
        Data d7 = new Data(0, "");
        daten.add(d1);
        daten.add(d2);
        daten.add(d3);
        daten.add(d4);
        daten.add(d5);
        daten.add(d6);   
        daten.add(d7);   
    }
    
    private void testList() {
        assertEquals(-5,daten.get(0).getKey(), 0.1);
        assertEquals(-1,daten.get(1).getKey(), 0.1);
        assertEquals(0,daten.get(2).getKey(), 0.1);
        assertEquals(0,daten.get(3).getKey(), 0.1);
        assertEquals(1,daten.get(4).getKey(), 0.1);
        assertEquals(2,daten.get(5).getKey(), 0.1);
    }
    
    @Test
    public void quickSortPivotMedian() {     
        initDaten();
        sort.quickSort(daten, PivotChoice.MEDIAN);
        testList();
    }
    
    @Test
    public void quickSortPivotFirst() {
        initDaten();
        sort.quickSort(daten, PivotChoice.FIRST);
        testList();
    }
    
    @Test
    public void quickSortPivotRandom() {
        initDaten();
        sort.quickSort(daten, PivotChoice.RANDOM);
        testList();
    }
     
}
