package prakt4;

import java.util.LinkedList;
import java.util.List;

import quicksort.Data;
import quicksort.QuickSort;
import quicksort.QuickSort.PivotChoice;

public class MySort {
 
    private int compareCalls;
    private int swapCalls;
    private int indexToResave;

    public Data[] generateDataArray(int size) {
        Data[] dataArray = new Data[size];
        for (int i=0; i<size; i++) {
            dataArray[i] = new Data((int)((Math.random()+7)*100*size), "");

        }        
        return dataArray;
    }
    
    public void sort(Data[] dataArray) {
        List<List<Data>> gruppen = sortToGroups(dataArray);
        sortEachGroupAndRewriteArray(gruppen, dataArray);
  //      rewriteArray(dataArray, gruppen);
    }
        
    private List<List<Data>> sortToGroups(Data[] dataArray) {
        List<List<Data>> gruppen = new LinkedList<List<Data>>();
        for (int i=0; i<dataArray.length; i++) {
            gruppen.add(new LinkedList<Data>());
        }
        for (Data data : dataArray) {
            int groupNr = (int) (data.getKey()-700*dataArray.length)/100;
            if (groupNr == dataArray.length) {
                groupNr--;
            }
            gruppen.get(groupNr).add(data);
            compareCalls++;
        }       
   //     System.out.println(gruppen);
        return gruppen;
    }
    
    private void sortEachGroupAndRewriteArray(List<List<Data>> gruppen, Data[] dataArray) {
        QuickSort quickSort = new QuickSort();
        for (List<Data> gruppe : gruppen) {
            if (gruppe.size() > 1) {
                quickSort.quickSort(gruppe, PivotChoice.MEDIAN);                
            }
            compareCalls++;
            for (int i=0; i<gruppe.size(); i++) {
                compareCalls++;
                dataArray[indexToResave] = gruppe.get(i);
                indexToResave++;
            }
        }
        compareCalls += quickSort.getCompareCounter();
        swapCalls += quickSort.getSwapCounter();
    }
    
    public void printDataArray(Data[] dataArray) {
        System.out.print("[");
        for (int i=0; i<dataArray.length; i++) {
            System.out.print(dataArray[i] + ", ");
        }
        System.out.println("]");
    }
    
////////////////////////////////////////////////////////////////////////////////////////////    
    public static void main(String[] args) {
        
        ////////////////////
        int n = 100;
        /////////////////////
        
        
        MySort mySort = new MySort();
        Data[] dataArray = mySort.generateDataArray(n);
        
        List<Data> list = new LinkedList<Data>();
        for (Data data : dataArray) {
            list.add(data);
        }
        
      //  mySort.printDataArray(dataArray);
        mySort.sort(dataArray);
        mySort.printDataArray(dataArray);
        
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(list, PivotChoice.MEDIAN);    
        System.out.println(list);
        
        System.out.println(mySort.compareCalls + " + " + mySort.swapCalls + 
                " = " + (mySort.compareCalls + mySort.swapCalls));
        
        System.out.println("--------------------------------------");

    
        System.out.println(quickSort.getCompareCounter() + " + " + quickSort.getSwapCounter() + 
                " = " + (quickSort.getCompareCounter() + quickSort.getSwapCounter()));

    }
}
