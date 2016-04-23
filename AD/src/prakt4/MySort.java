package prakt4;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import quicksort.Data;
import quicksort.QuickSort;
import quicksort.QuickSort.PivotChoice;

public class MySort {

    private Set<Data> dataSet;
    private Data[] array;   
    private int compareCalls;
    private int swapCalls;
    
    /**
     * Generiert ein Set mit Daten.
     */
    public void generateDataSet(int size) {
        dataSet = new HashSet<Data>();
        for (int i=0; i<size; i++) {
       //     dataSet.add(new Data(i*100+705*size, ""));
            
            dataSet.add(new Data((int)((Math.random()+7)*100*size+0.1), ""));
        }        
        System.out.println(dataSet);
    }
    
    public void sort(Set<Data> dataSet) {
        List<List<Data>> gruppen = sortToGroups(dataSet);
        QuickSort quickSort = new QuickSort();
        for (List<Data> gruppe : gruppen) {
            quickSort.quickSort(gruppe, PivotChoice.MEDIAN);   
        }
        compareCalls += quickSort.getCompareCounter();
        swapCalls += quickSort.getSwapCounter();
    }
        
    private List<List<Data>> sortToGroups(Set<Data> dataSet) {
        List<List<Data>> gruppen = new LinkedList<List<Data>>();
        for (int i=0; i<dataSet.size(); i++) {
            gruppen.add(new LinkedList<Data>());
        }
        for (Data data : dataSet) {
            int groupNr = (int) (data.getKey()/dataSet.size()-700);
            gruppen.get(groupNr).add(data);
        }
        System.out.println(gruppen);
        
        
        
        
        
        
  ///////////////////////////////////////////      
   /*     int gruppenAnzahl = getGruppenAnzahl();
        List<List<Data>> gruppen = new LinkedList<List<Data>>();
        for (int i=0; i<gruppenAnzahl; i++) {
            gruppen.add(new LinkedList<Data>());
        }
        for (Data data : dataSet) {
            int groupToSave = 0;
            int keyMitte = 50;
            compareCalls += (int)(Math.log(gruppenAnzahl)/Math.log(2));
            for (int i=(int)(Math.log(gruppenAnzahl)/Math.log(2))-1; i>=0; i--) {
                if (data.getKey() < (keyMitte+700)*dataSet.size()) {
                    keyMitte -= keyMitte/2;
                } else {
                    keyMitte += keyMitte/2;
                    groupToSave += Math.pow(2, i); 
                }
            }
            gruppen.get(groupToSave).add(data);
        }
        System.out.println(gruppen);
 */ //      System.out.println(compareCalls);
        return gruppen;
    }
    
    private int getGruppenAnzahl() {
        double gruppenAnzahl = dataSet.size(); //Math.sqrt(dataSet.size()); //
        int potenz = 0;
        while (gruppenAnzahl > 1) {
            gruppenAnzahl /= 2;
            potenz++;
        }
        gruppenAnzahl = dataSet.size();//Math.sqrt(dataSet.size()); //
       /*
        if (gruppenAnzahl - Math.pow(2, potenz-1)
                < Math.pow(2, potenz) - gruppenAnzahl) {
            potenz--;
        }
        */
        return (int)Math.pow(2, potenz);        
    }
    
    private void sortList(List<Data> list) {
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(list, PivotChoice.MEDIAN);
        compareCalls += quickSort.getCompareCounter();
        swapCalls += quickSort.getSwapCounter();
    }
    
    
    public static void main(String[] args) {
        MySort mySort = new MySort();
        mySort.generateDataSet(100);
        System.out.println(mySort.getGruppenAnzahl());
        
        mySort.sort(mySort.dataSet);
        
  //      System.out.println(mySort.dataSet);
        
        System.out.println("--------------------------------------");
        
        System.out.println(mySort.compareCalls + " + " + mySort.swapCalls + 
                " = " + (mySort.compareCalls + mySort.swapCalls));
        
        List<Data> list = new LinkedList<Data>();
        for (Data data : mySort.dataSet) {
            list.add(data);
        }
        QuickSort quickSort = new QuickSort();
        
        Collections.shuffle(list);
        
        quickSort.quickSort(list, PivotChoice.MEDIAN);
        
        System.out.println(quickSort.getCompareCounter() + " + " + quickSort.getSwapCounter() + 
                " = " + (quickSort.getCompareCounter() + quickSort.getSwapCounter()));
        

        

    }
}
