package listen_impl;

public class ListAsLinkArray<T> extends Counter implements IList<T> {

    private int size;
    private Object[] array;
    private int arrayIndexOfFirstElement;
    
    public ListAsLinkArray() {
        size = 0;
        array = new Object[10];
        arrayIndexOfFirstElement = -1;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void insert(int pos, T content) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        
        // wenn überhaupt das erste Element in der Liste
        if (size == 0) {
            array[0] = new ArrayElement<T>(0, -1, -1, content);
            arrayIndexOfFirstElement = 0;
            size++;
            count(3);
            return;
        }
        
        //Parameter 
        int arrayIndexToSave = getFreeArrayIndex();
        int prevIndex = -1;
        int nextIndex = -1;
        count(3);
                
        if (pos == 0) {
            // wenn am Anfang einfuegen
            ArrayElement<T> firstElement = getElementOfPosition(pos);
            firstElement.setPrevIndex(arrayIndexToSave);
            nextIndex = firstElement.getIndex();
            arrayIndexOfFirstElement = arrayIndexToSave;
            count(5);
        } else if (pos == size) {
            // wenn am Ende
            ArrayElement<T> lastElement = getElementOfPosition(pos-1);
            lastElement.setNextIndex(arrayIndexToSave);
            prevIndex = lastElement.getIndex();
            count(5);
        } else {
            // in der Mitte der Liste
            ArrayElement<T> posElement = getElementOfPosition(pos);
            ArrayElement<T> prevElement = (ArrayElement<T>) array[posElement.getPrevIndex()];
            posElement.setPrevIndex(arrayIndexToSave);
            prevElement.setNextIndex(arrayIndexToSave);
            prevIndex = prevElement.getIndex();
            nextIndex = posElement.getIndex();
            count(7);
        }
        
        array[arrayIndexToSave] = new ArrayElement<T>(
                arrayIndexToSave, nextIndex, prevIndex, content);
        size++; 
        count(2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        ArrayElement<T> arrEl = getElementOfPosition(pos);
        count(1);
        
        if (size == 1) {
            // wenn das Element das einzige in der Liste ist
            arrayIndexOfFirstElement = -1;
            count(2);
        } else if (pos == 0) {
            // wenn das Element ganz vorne steht
            ArrayElement<T> next = (ArrayElement<T>) array[arrEl.getNextIndex()];
            next.setPrevIndex(-1);
            arrayIndexOfFirstElement = next.getIndex();
            count(6);
        } else if (pos == size-1) {
            // wenn das Element ganz am Ende steht
            ArrayElement<T> prev = (ArrayElement<T>) array[arrEl.getPrevIndex()];
            prev.setNextIndex(-1);
            count(7);
        } else {
            // wenn das Element in der Mitte der Liste steht
            ArrayElement<T> next = (ArrayElement<T>) array[arrEl.getNextIndex()];
            ArrayElement<T> prev = (ArrayElement<T>) array[arrEl.getPrevIndex()];
            prev.setNextIndex(next.getIndex());
            next.setPrevIndex(prev.getIndex());
            count(8);
        }
        array[arrEl.getIndex()] = null;
        size--;
        count(2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int find(T elem) {
        if (size == 0) {
            count(1);
            return -1;
        }
        ArrayElement<T> arrayElement = (ArrayElement<T>) array[arrayIndexOfFirstElement];
        if (arrayElement.getContent().equals(elem)) {
            return 0;
        }
        count(2);
        int index = -1;
        for (int i=1; i<size; i++) {
            count(2);
            arrayElement = (ArrayElement<T>) array[arrayElement.getNextIndex()];
            if (arrayElement.getContent().equals(elem)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public T retrieve(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        count(1);
        return getElementOfPosition(pos).getContent();
    }

    @Override
    public void concat(IList<T> otherList) {
        if (otherList == null) {
            count(1);
            return;
        }
        // stelle sicher, dass es genug Platz im Array zum Kopieren gibt  
        if (array.length - size < otherList.size()) {
            Object[] newArray = new Object[size + otherList.size()];
            System.arraycopy( array, 0, newArray, 0, array.length );
            array = newArray;
            count(size+2);
        }
        // kopiere alle Elemente
        for (int i = 0; i < otherList.size(); i++) {
            insert(size, otherList.retrieve(i));
            count(2);
        }
    }

    @Override
    public int size() {
        count(1);
        return size;
    }

    /**
     * Returns the first free index in the array.
     * If the array is full, increases the length for 10 positions
     * @return The first free index in the array.
     */
    private int getFreeArrayIndex() {
        int i;
        for (i=0; i<array.length; i++) {
            count(2);
            if (array[i] == null) {
                return i;
            }
        }
        // array war voll -> vergröße es um 10
        count(array.length+2);
        Object[] newArray = new Object[array.length+10];
        System.arraycopy( array, 0, newArray, 0, array.length );
        array = newArray;
        return i+1;
    }
    
    @SuppressWarnings("unchecked")
    private ArrayElement<T> getElementOfPosition(int pos) {
        ArrayElement<T> arrayElement = (ArrayElement<T>) array[arrayIndexOfFirstElement];
        count(1);
        for (int i=0; i<pos; i++) {
            arrayElement = (ArrayElement<T>) array[arrayElement.getNextIndex()];
            count(2);
        }
        return arrayElement;
    }
    

    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ListAsLinkArray<String> list = new ListAsLinkArray<String>();
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(0, "c");
        list.delete(1);
        list.insert(1, "d");
        
        System.out.println(list.arrayIndexOfFirstElement);
        for (int i=0; i<list.array.length; i++) {
            System.out.print((ArrayElement<String>)(list.array[i]) + ", ");
        }
        
        
    }
    
    
}
