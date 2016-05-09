package prakt3_quicksort;

public class Data {
    
    public double key;
    public Object content;

    public Data(int key, Object content) {
        this.key = key;
        this.content = content;
    }

    @Override
    public String toString() {
        return key + " " + content.toString();
    }
    
    public double getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
    
    
    
}
