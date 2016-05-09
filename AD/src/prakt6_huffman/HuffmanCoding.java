package prakt6_huffman;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HuffmanCoding {

    private HuffmanNode root;
    
    private void createTree(String string) {
        List<HuffmanNode> nodes = translateStringToNodes(string);
        createTree(nodes);
    }

    private List<HuffmanNode> translateStringToNodes(String string) {
        List<HuffmanNode> huffmanNodes = new LinkedList<HuffmanNode>();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            boolean isCharNew = true;
            for (HuffmanNode node : huffmanNodes) {
                if (node.getCharacter() == ch) {
                    isCharNew = false;
                    node.increaseFrequence();
                    break;
                }
            }
            if (isCharNew) {
                huffmanNodes.add(new HuffmanNode(ch, 1, null, null));
            }
        }
        return huffmanNodes;
    }
    
    private void createTree(List<HuffmanNode> nodes) {
        Collections.sort(nodes, (node1, node2) -> {
            if (node1 == null) {
                return node2 == null ? 0 : 1;
            } else if (node2 == null) {
                return -1;
            } else {
                return node1.getFrequence()-node2.getFrequence();                
            }
        });
        if (nodes.get(1) == null) {
            root = nodes.get(0);
            return;
        }
        HuffmanNode node0 = nodes.get(0);
        HuffmanNode node1 = nodes.get(1);
        HuffmanNode newNode = new HuffmanNode((char) 0,
                node0.getFrequence() + node1.getFrequence(), node0, node1);
        nodes.set(0, newNode);
        nodes.set(1, null);
        createTree(nodes);
        return;
    }
    
    
    public static void main(String[] args) {
        
        HuffmanCoding huffman = new HuffmanCoding();
        List<HuffmanNode> nodes = huffman.translateStringToNodes("beep boop beer!");
        Collections.sort(nodes, (node1, node2) -> {
            if (node1 == null) {
                return node2 == null ? 0 : 1;
            } else if (node2 == null) {
                return -1;
            } else {
                return node1.getFrequence()-node2.getFrequence();                
            }
        });
        System.out.println(nodes);
        huffman.createTree("beep boop beer!");
        System.out.println();
    }
}
