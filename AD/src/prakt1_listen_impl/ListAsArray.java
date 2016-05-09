package prakt1_listen_impl;

public class ListAsArray<T> extends Counter implements IList<T>{
    private int size;
    private Object[] array; 
    
    public ListAsArray(){
        array = new Object[0];
    }
    
    public ListAsArray(int size){
        array = new Object[size];
    }
    
    @Override
    public void insert(int pos, T elem) {
        
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("wrong position");
        }
        if (size >= array.length){
            Object[] newArray = new Object[array.length + 10];
            count(3); // vergleichen + Laenge berechnen + Array erzeugen
            for(int i = 0; i < array.length; i++){
                newArray[i] = array[i];
                count(1);
            }
            array = newArray;
        }
        for (int i = size-1; i >= pos; --i) {
            array[i+1] = array[i];
            count(2);  //i+1, =
        }
        array[pos] = elem;
        ++size;
        count(2);
    }

    @Override
    public void delete(int pos) {
        assert pos >= 0 && pos < array.length : "position out of bounds";

        for (int i = pos; i < size; i++) {
            array[i] = array[i + 1];
            count(2);
        }
        array[size] = null; // letzte Element = null
        size--;
        count(2);
    }

    @Override
    public int find(T elem) {
        assert elem != null && size != 0 : "Precondition offended : elem != null && size != 0";
        
        for(int i = 0; i < size; i++) {
            count(1);
            if(elem.equals(array[i])) {
                count(1);
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T retrieve(int pos) {
        assert pos >= 0 && pos < array.length : "Precondition offended: pos > 0 && pos <= list.length";
        count(1);
        return (T) array[pos];
    }

    @Override
    public void concat(IList<T> otherList) {
        if (otherList == null) {
            return;
        }
        int length = otherList.size() + this.size();
        Object[] newList = new Object[length];
        for (int i = 0; i < this.size; ++i) {
            newList[i] = this.retrieve(i);
            count(1);
        }
        for (int i = 0; i < otherList.size(); ++i) {
            newList[i + this.size] = otherList.retrieve(i);
            count(2);
        }
        this.array = newList;
        size = length;
    }

    @Override
    public int size() {
        count(1);
        return size;
    }

}
