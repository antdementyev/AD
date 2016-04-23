package quicksort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
	
	private int callTimes;
	private int swapCounter;
	private int compareCounter;
	public enum PivotChoice {MEDIAN, FIRST, RANDOM};
	
	
	public void quickSort(List<Data> daten, PivotChoice pivot) {
	//    System.out.println(daten);
	    switch (pivot){
	        case RANDOM:
	            sortRandom(daten, 0, daten.size() - 1);
	            break;
	        case FIRST:
	            sortFirst(daten, 0, daten.size() - 1);
                break;
	        case MEDIAN:
                sortMedian(daten, 0, daten.size() - 1);   
	    }
    }

	/**
	 * Quicksort mit der Pivot Wahl in der Mitte der Liste. 
	 */
    private void sortRandom(List<Data> daten, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        callTimes++;
        int left = startIndex;
        int right = endIndex;
        int pivotIndex = (int)(startIndex + Math.random()*(endIndex - startIndex + 1));
        double pivotKey = daten.get(pivotIndex).getKey();
   //     System.out.println(pivotKey);
        
        while (left < right) {
            while ((left < pivotIndex) && (daten.get(left).getKey() <= pivotKey)) {
                left++;
                compareCounter++;
            }
            while ((pivotIndex < right)  && (pivotKey <= daten.get(right).getKey())) {
                right--;
                compareCounter++;
            }
            compareCounter += 2;
            if (left != right) {
                swap(daten, left, right);
                if (left == pivotIndex)     //wenn true, dann wurde das Pivot nach rechts verschoben
                    pivotIndex = right;
                else if (right == pivotIndex) //wenn true, dann wurde das Pivot nach links verschoben
                    pivotIndex = left;
            }
        }
      
        sortRandom(daten, startIndex, pivotIndex);
        sortRandom(daten, pivotIndex+1, endIndex);
    }
    
    /**
     * Quicksort mit der Pivot Wahl am Anfang der Liste. 
     */
    private void sortFirst(List<Data> daten, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        callTimes++;
        int pivotIndex = startIndex;
        int left = pivotIndex + 1;
        int right = endIndex;
        double pivotKey = daten.get(pivotIndex).getKey();
        
        while (left < right) {
            while ((left < right) && (daten.get(left).getKey() <= pivotKey)) {
                left++;
                compareCounter++;
            }
            while ((left < right) && (pivotKey <= daten.get(right).getKey())) {
                right--;
                compareCounter++;
            }
            compareCounter += 2;
            if (left != right) {
                swap(daten, left, right);
            }       
        }
        
        // alle Elemente waren >= Pivot
        if (left == pivotIndex + 1 && pivotKey <= daten.get(left).getKey()) {
            sortFirst(daten, left, endIndex);
            
        //alle Elemente waren <= Pivot
        } else if (right == endIndex && pivotKey >= daten.get(right).getKey()) {
            swap(daten, pivotIndex, endIndex);
            sortFirst(daten, startIndex, endIndex-1);
         
        //sortiere links und rechts    
        } else {
            swap(daten, pivotIndex, left-1);
            sortFirst(daten, startIndex, left-2);
            sortFirst(daten, left, endIndex);
        }
    }
    
    /**
     * Quicksort mit der Pivot Wahl als Median. 
     */
    private void sortMedian(List<Data> daten, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        callTimes++;
        
        float pivotMedianKey = 0;
        for (int i=startIndex; i<=endIndex; i++) {
            pivotMedianKey += daten.get(i).getKey();
        }
        pivotMedianKey = pivotMedianKey/(endIndex-startIndex+1);
        
        int left = startIndex;
        int right = endIndex;
        
        while (left < right) {
            while ((left < right) && (daten.get(left).getKey() <= pivotMedianKey)) {
                left++;
                compareCounter++;
            }
            while ((left < right)  && (pivotMedianKey <= daten.get(right).getKey())) {
                right--;
                compareCounter++;
            }
            compareCounter++;
            if (left < right) {
                compareCounter++;               
            }
            if (daten.get(left).getKey() == daten.get(right).getKey() 
                    && left < right) {
                left++;
                continue;
            }
            if (left != right) {
                swap(daten, left, right);
                left++;
            }
        }
        sortMedian(daten, startIndex, left-1);
        sortMedian(daten, left, endIndex);
    }

	/**
	 * Vertauscht 2 Elemente in der Liste.
	 */
    private void swap(List<Data> daten, int i, int j) {
        swapCounter++;
        Data tmp = daten.get(i);
        daten.set(i, daten.get(j));
        daten.set(j, tmp);
     //   System.out.println(daten);
    }
    
    public int getSortCounter() {
        return callTimes;
    }
    
    public int getSwapCounter() {
        return swapCounter;
    }
    
    public int getCompareCounter() {
        return compareCounter;
    }
    
    public void resetCounter() {
        callTimes = 0;
        swapCounter = 0;
        compareCounter = 0;
    }
	
    
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        
        List<Data> daten = new ArrayList<Data>();
        daten.add(new Data(71098, ""));
        daten.add(new Data(71062, ""));
        daten.add(new Data(71078, ""));
        /*
        int n = 1000;
        for (int i=0; i<n; i++) {
            daten.add(new Data((int)(Math.random()*n), ""));
        }
        */
        
        sort.quickSort(daten, PivotChoice.MEDIAN);
        System.out.println(daten);
        System.out.format("tiefe %d, swap %d, compare %d", sort.getSortCounter(), sort.getSwapCounter(), sort.getCompareCounter());
        sort.resetCounter();

        
    }
}
