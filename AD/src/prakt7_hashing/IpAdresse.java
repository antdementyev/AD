package prakt7_hashing;

import java.util.Arrays;

public class IpAdresse {
    
    private final String TRENN_ZEICHEN = " -- ";
    private final int ASCII_START = 32;
    private final int ASCII_END = 126;
    private final double LOAD_FACTOR = 0.7;

    public String generiereLog(int anzahlZeilen) {
        StringBuilder log = new StringBuilder();
        // generiere eine Logzeile
        for (int i=0; i<anzahlZeilen; i++) {
            StringBuilder zeile = new StringBuilder();
            // random xxx.xxx.xxx.xxx
            for (int j=0; j<4; j++) {
                int adressTeil = (int)(Math.random()*256);
                zeile.append(adressTeil);
                if (j != 3) {
                    zeile.append(".");
                }
            }
            zeile.append(TRENN_ZEICHEN);
            // random log Text 
            for (int j=0; j<5; j++) {
                int text = (int)(Math.random()*Integer.MAX_VALUE);
                while (text > 1) {
                    char zeichen = (char)(text%(ASCII_END - ASCII_START) + ASCII_START);
                    text = text/100;
                    zeile.append(zeichen);
                }
            }
            log.append(zeile).append("\n");
        }
        return log.toString();
    }
    
    private int getNextPrimzahlAb(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }
    
    private boolean isPrime(int n) {
        if (n<2) {
            return false;
        }
        for (int i=2; i<n; i++) {
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }
    
    
    
    public static void main(String[] args) {
        IpAdresse ip = new IpAdresse();
        System.out.println(ip.generiereLog(4));
        System.out.println(ip.getNextPrimzahlAb(-87));
    }
    
}
