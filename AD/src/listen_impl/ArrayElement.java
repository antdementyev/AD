package listen_impl;

public class ArrayElement<T> {
   
    /**
     * Arrayindex, wo Element steht
     */
    private int index;
    
    /**
     * Arrayindex des naechsten Elements.
     * -1 wenn das Element das letzte in der Liste ist.
     */
    private int nextIndex;
    
    /**
     * Arrayindex des Vorelements.
     * -1 wenn das Element das erste in der Liste ist.
     */
    private int prevIndex;
    
    /**
     * Inhalt des Elements
     */
    private T content;
    
    
    public ArrayElement(int index, int nextIndex, int prevIndex, T content) {
        this.index = index;
        this.nextIndex = nextIndex;
        this.prevIndex = prevIndex;
        this.content = content;
    }

    public String toString() {
        return content + " " + index + " " + nextIndex + " " + prevIndex;
    }
    
    public int getIndex() {
        return index;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public int getPrevIndex() {
        return prevIndex;
    }

    public T getContent() {
        return content;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public void setPrevIndex(int prevIndex) {
        this.prevIndex = prevIndex;
    }

    public void setContent(T content) {
        this.content = content;
    }  
    
    
    
}
