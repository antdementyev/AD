package prakt6_huffman;

import static org.junit.Assert.*;

import org.junit.Test;

public class HuffmanEncodingTest {

    private HuffmanEncoding huffman;
    private String stringToTest = "If you give someone a program you will frustrate "
            + "them for a day? If you teach them how toprogram, you will frustrate "
            + "them for a lifetime. The computing scientist?s main challenge is not "
            + "to get confused by the complexities of his own making. Beauty is more "
            + "important in computing than anywhere else in technology because software "
            + "is so complicated. Beauty is the ultimate defence against complexity.";
    
    @Test
    public void codeDecode() {
        huffman = new HuffmanEncoding(stringToTest);
        String codedString = huffman.codeText(stringToTest);
        assertEquals(stringToTest, huffman.decodeCode(codedString));
    }
}