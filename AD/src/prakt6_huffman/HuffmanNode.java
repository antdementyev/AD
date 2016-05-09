package prakt6_huffman;

public class HuffmanNode {

    private final char character;
    private int frequence;
    private final HuffmanNode left;
    private final HuffmanNode right;
    
    public HuffmanNode(char character, int frequence, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequence = frequence;
        this.left  = left;
        this.right = right;
    }

    public void increaseFrequence() {
        frequence++;
    }

    @Override
    public String toString() {
        return character + " - " + frequence;
    }
    
    public char getCharacter() {
        return character;
    }

    public int getFrequence() {
        return frequence;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }
}
