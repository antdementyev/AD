package prakt7_hashing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IpAdresse {
    
    private final String TRENN_ZEICHEN = " -- ";
    private final int ASCII_START = 32;
    private final int ASCII_END = 126;
    private final double MAX_LOAD_FACTOR = 0.8;
    private final double MIN_LOAD_FACTOR = 0.5;
    
    private String log;
    private List<String>[] hashTabelle;
    private int plaetzeBelegt;
    private List<Integer> anzahlKolis = new LinkedList<Integer>();
    int tableUpdates = -1;
    

    public IpAdresse(int entriesQty) {
        log = "";
        resetHashTable(getNextPrimzahlAb(entriesQty));
        for (int i=0; i<entriesQty; i++) {
            addRandomLogEintrag();
        }
    }
    
    private void addRandomLogEintrag() {
        String eintrag = getRandomLogEintrag();
        if (!log.isEmpty()) {
            log += "\n";
        }
        log += eintrag;
        updateHashTabelle(eintrag);    }
    
    private String getRandomLogEintrag() {
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
        return zeile.toString();
    }
    
    private void updateHashTabelle(String newEntry) {
        plaetzeBelegt++;
        if ((double)plaetzeBelegt/hashTabelle.length > MAX_LOAD_FACTOR) {
            // initialisere Funktionen neu
            int sizeNewTable = (int)(plaetzeBelegt / MIN_LOAD_FACTOR);
            resetHashTable(getNextPrimzahlAb(sizeNewTable)); 
            // resave all log entries
            for (String zeile : log.split("\n")) {
                saveLogEntryIntoTable(zeile);
            }
        } else {
            saveLogEntryIntoTable(newEntry);
        }
    }
    
    private List<String> findAllEntriesLike(String logEntry) {
        double ip = getIpFromLogEntry(logEntry);
        int index = findIndexForIp(ip);
        return hashTabelle[index];
    }
    
    private void resetHashTable(int qty) {
        hashTabelle = new List[qty];
        for (int i=0; i<hashTabelle.length; i++) {
            hashTabelle[i] = new LinkedList<String>();
        }
        // neue Tabelle - neue Statistik
        tableUpdates++;
        anzahlKolis.add(tableUpdates);
    }
    
    private void saveLogEntryIntoTable(String entry) {
        double ip = getIpFromLogEntry(entry);
        hashTabelle[findIndexForIp(ip)].add(entry);
    }
    
    private int findIndexForIp(double ip) {
        int key = getKey(ip);
        while (!hashTabelle[key].isEmpty() 
                && getIpFromLogEntry(hashTabelle[key].get(0)) != ip) {
            int kol = anzahlKolis.get(tableUpdates);
            kol++;
            anzahlKolis.set(tableUpdates, kol);
            int step = getStep(ip);
            key = (key + step) % hashTabelle.length;
        }
        return key;
    }
    
    private int getNextPrimzahlAb(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }
    
    private boolean isPrime(int n) {
        if (n<2) return false;
        for (int i=2; i<n; i++) {
            if (n%i==0) return false;
        }
        return true;
    }
    
    /** 
     * wandelt ip als Text in Zahl um.
     */
    private double getIpFromLogEntry(String zeile) {
        String ipString = zeile.substring(0, zeile.indexOf(TRENN_ZEICHEN));
        double ipInt = 0;
        for (String ipTeil : ipString.split("\\.")) {
            ipInt = ipInt*1000 + Integer.valueOf(ipTeil);
        }
        return ipInt;
    }
    
    private int getKey(double ip) {
        return (int)(ip%hashTabelle.length);
    }
    
    private int getStep(double ip) {
        return 1 + (int) (ip%(hashTabelle.length-1));
    }
    
    public static void main(String[] args) {
        IpAdresse ip = new IpAdresse(1);
//        System.out.println(ip.findAllEntriesLike(ip.hashTabelle[0].get(0)));
        for (int i=0; i<2000; i++) {
            ip.addRandomLogEintrag();
        }
        
        System.out.println(ip.anzahlKolis);
    }
    
}
