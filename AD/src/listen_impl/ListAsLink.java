package listen_impl;

public class ListAsLink<T> extends Counter implements IList<T> {

    private Node<T> first;
    private int size;
    
    public ListAsLink() {
        first = new Node<T>();
    }
    
    @Override
    public void insert(int pos, T elem) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> nodeToAdd = new Node<T>(elem);
        count(1);
        if (pos == 0) {
            nodeToAdd.setNext(first);
            first = nodeToAdd;
            count(2);
        } else {
            Node<T> nodeBefore = getNode(pos-1);
            Node<T> nodeActuell = nodeBefore.getNext();
            nodeBefore.setNext(nodeToAdd);
            nodeToAdd.setNext(nodeActuell);
            count(5);
        }
        size++;
        count(1);
    }

    @Override
    public void delete(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (pos == 0) {
            first = first.getNext();
            count(1);
        } else {
            getNode(pos-1).setNext(getNode(pos+1));
            count(4);
        }
        size--;
        count(1);
    }

    @Override
    public int find(T elem) {
        int index = -1;
        Node<T> node = first;
        count(2);
        for (int i=0; i<size; i++) {
            if (node.getContent().equals(elem)) {
                index = i;
                count(2);
                break;
            }
            node = node.getNext();
            count(2);
        }
        return index;
    }

    @Override
    public T retrieve(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = getNode(pos);
        count(1);
        return node.getContent();
    }

    @Override
    public void concat(IList<T> other) {
        if (other == null) {
            count(1);
            return;
        }
        Node<T> lastNode = getNode(size-1);
        count(2);
        for (int i=0; i < other.size(); i++) {
            if (size == 0) {
                first = new Node<T>(other.retrieve(i));
                lastNode = first;
                size++;
                count(5);
                continue;
            }
            lastNode.setNext(new Node<T>(other.retrieve(i)));
            lastNode = lastNode.getNext();
            size++;
            count(4);
        }
    }

    @Override
    public int size() {
        count(1);
        return size;
    }

    
    private Node<T> getNode(int pos) {
        Node<T> nodePos = first;
        count(1);
        for (int i = 0; i < pos; i++) {
            nodePos = nodePos.getNext();
            count(1);
        }
        return nodePos;
    }
    
    public static void main(String[] args){

        IList<String> list = new ListAsLink<String>();
        list.insert(0,"");
        System.out.println("insert 1. Elementes: " + list.getCounter() + " ns");
        list.resetCounter();
        list.insert(1,"");
        System.out.println("insert 2. Elementes: " + list.getCounter() + " ns");
        list.resetCounter();
        list.insert(2,"");
        System.out.println("insert 3. Elementes: " + list.getCounter() + " ns");
        list.resetCounter();
        list.insert(0,"");
        System.out.println("insert 4. Elementes: " + list.getCounter() + " ns");
        list.resetCounter();
        list.insert(4,"");
        System.out.println("insert 5. Elementes: " + list.getCounter() + " ns");
        list.resetCounter();
    }
    
}
