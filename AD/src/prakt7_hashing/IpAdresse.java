package prakt7_hashing;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IpAdresse {
    
    private final String TRENN_ZEICHEN = " -- ";
    private final int ASCII_START = 65;
    private final int ASCII_END = 122;
    private final double MAX_LOAD_FACTOR = 0.8;
    private final double MIN_LOAD_FACTOR = 0.5;
    
    private String log;
    private List<String>[] hashTabelle;
    private int plaetzeBelegt;
  //  private List<Integer> anzahlKolis = new LinkedList<Integer>();
  //  int tableUpdates = -1;
    private int kollisionen = 0;

    public IpAdresse(int entriesQty) {
        long startTime = System.nanoTime();
        log = "";
        resetHashTable(getNextPrimzahlAb(entriesQty));
        addRandomLogEintrag(entriesQty);
        System.out.println(entriesQty + " Eintraege waehrend " 
                    + (System.nanoTime()-startTime)/1000000000 + " sek erzeugt.");
    }
    
    public void addRandomLogEintrag(int entriesQty) {
        for (int i=0; i<entriesQty; i++) {
            String eintrag = createRandomLogEintrag();
            if (!log.isEmpty()) {
                log += "\n";
            }
            log += eintrag;
            updateHashTabelle(eintrag);
 /*           if (i%1000 == 0) {
                System.out.println(i);
            }
  */      }    }
    
    private String createRandomLogEintrag() {
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
            int sizeNewTable = getNextPrimzahlAb((int)(plaetzeBelegt / MIN_LOAD_FACTOR));
            resetHashTable(sizeNewTable); 
            // resave all log entries
            for (String zeile : log.split("\n")) {
                saveLogEntryIntoTable(zeile);
            }
            System.out.println("[INFO] Anzahl der Eintraege " + plaetzeBelegt 
                    + ". Vergroessere Tabelle bis " + sizeNewTable 
                    + " und rehashe. Kollisionen: " + kollisionen);        
        } else {
            saveLogEntryIntoTable(newEntry);
        }
    }
    
    public List<String> findAllEntriesFor(double ip) {
        int index = findIndexForIp(ip);
        return hashTabelle[index];
    }
    
    @SuppressWarnings("unchecked")
    private void resetHashTable(int qty) {
        hashTabelle = new List[qty];
        for (int i=0; i<hashTabelle.length; i++) {
            hashTabelle[i] = new LinkedList<String>();
        }
        // neue Tabelle - neue Statistik
     //   tableUpdates++;
     //   anzahlKolis.add(tableUpdates);
    }
    
    private void saveLogEntryIntoTable(String entry) {
        double ip = getIpFromLogEntry(entry);
        hashTabelle[findIndexForIp(ip)].add(entry);
    }
    
    private int findIndexForIp(double ip) {
        int key = getIndexWithSondFunktion(ip);
        while (!hashTabelle[key].isEmpty() 
                && getIpFromLogEntry(hashTabelle[key].get(0)) != ip) {
       //     int kol = anzahlKolis.get(tableUpdates);
       //     kol++;
            kollisionen++;
       //     anzahlKolis.set(tableUpdates, kol);
            int step = getStepWithSondFunktion2(ip);
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
        double ipDouble = 0;
        for (String ipTeil : ipString.split("\\.")) {
            ipDouble = ipDouble*1000 + Integer.valueOf(ipTeil);
        }
        return ipDouble;
    }
    
    private int getIndexWithSondFunktion(double ip) {
        return (int)(ip%hashTabelle.length);
    }
    
    private int getStepWithSondFunktion2(double ip) {
        return 1 + (int) (ip%(hashTabelle.length-1));
    }
    
    public void handleInput(String input) {
        final String IPADDRESS_PATTERN = 
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                 "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                 "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                 "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        
        if (Pattern.matches(IPADDRESS_PATTERN, input)) {
            // Audgabe des gespeicherten Eintrags
            double ipDouble = 0;
            kollisionen = 0;
            for (String ipTeil : input.split("\\.")) {
                ipDouble = ipDouble*1000 + Integer.valueOf(ipTeil);
            }
            System.out.println(findAllEntriesFor(ipDouble));
            System.out.println("[INFO] Kollisionen bei der Suche " + kollisionen);
            
        } else if (Pattern.matches("\\d{1,7}", input)) {
            // Fuege n Eintraege hinzu
            kollisionen = 0;
            addRandomLogEintrag(Integer.valueOf(input));
            System.out.println("[INFO] " + input + " entries added. Kollisionen: " + kollisionen);
            
        } else if (input.equals("log")) {
            // zeige log Datei
            System.out.println(log);
            System.out.println("INFO " + plaetzeBelegt + " entries");
            
        } else if (input.equals("hash")) {
            // zeige hashTabelle
            for (List<String> ipEintrag : hashTabelle) {
                System.out.println(ipEintrag);                
            }
            
        } else if (input.equals("exit")) {
            System.out.println("Ende.");
            
        } else {
            System.out.println("Unsinn");
        }
    }
        
    public static void main(String[] args) {

        IpAdresse ip = new IpAdresse(00);

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")) {
            input = scanner.nextLine();
            ip.handleInput(input);    
        }
        scanner.close();
    }
    
}