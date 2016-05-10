package prakt6_huffman;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HuffmanEncoding {

    private HuffmanNode tree;
    private Map<Character, String> charCodeTable;

    public HuffmanEncoding(String string) {
        List<HuffmanNode> nodes = translateStringToNodes(string);
        createTree(nodes);
        charCodeTable = new HashMap<Character, String>();
        createTable(tree, "");
    }

    public String codeText(String textToCode) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < textToCode.length(); i++) {
            String charAsCode = charCodeTable.get(textToCode.charAt(i));
            code.append(charAsCode).append(" ");
        }
        return code.toString().trim();
    }

    public String decodeCode(String codeToDecode) {
        StringBuilder decode = new StringBuilder();
        String[] charsAsCode = codeToDecode.split(" ");
        for (int i = 0; i < charsAsCode.length; i++) {
            String charAsCode = charsAsCode[i];
            HuffmanNode node = tree;
            try {
                for (int j = 0; j < charAsCode.length(); j++) {
                    if (charAsCode.charAt(j) == '0') {
                        node = node.getLeft();
                    } else {
                        node = node.getRight();
                    }
                }
                decode.append(node.getCharacter());
            } catch (NullPointerException e) {
                decode.append((char) 0);
            }
        }
        return decode.toString();
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
                return node1.getFrequence() - node2.getFrequence();
            }
        });
        if (nodes.get(1) == null) {
            tree = nodes.get(0);
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

    private void createTable(HuffmanNode node, String code) {
        if (node.getCharacter() != 0) {
            charCodeTable.put(node.getCharacter(), code);
            return;
        }
        HuffmanNode nodeLeft = node.getLeft();
        HuffmanNode nodeRight = node.getRight();
        if (nodeLeft != null) {
            createTable(nodeLeft, code + 0);
        }
        if (nodeRight != null) {
            createTable(nodeRight, code + 1);
        }
    }

    public static void main(String[] args) {
        HuffmanEncoding huffman = new HuffmanEncoding("beep boop beer!");
        System.out.println(huffman.codeText("pepe"));
        System.out.println(huffman.decodeCode("101 1111 101 10111"));
    }
}