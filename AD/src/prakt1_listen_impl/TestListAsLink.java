package prakt1_listen_impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestListAsLink {
    
    IList<String> list;
    
    private IList<String> getNewList() {
        return new ListAsLink<String>();
    }
    
    @Test
    public void insert() {
        list = getNewList();
        
        try {
            list.insert(-1, "");
            fail("insert() wirft keine IndexOutOfBoundsException, "
                    + "wenn eine negative Position anegeben ist");
        } catch (IndexOutOfBoundsException e) {
        }
        
        try {
            list.insert(1, "");
            fail("insert() wirft keine IndexOutOfBoundsException, "
                    + "wenn die Position > size ist");
        } catch (IndexOutOfBoundsException e) {
        }
        
        try {
            list.insert(0, "a");
            assertEquals("Bei der Einfügung des Elements am Index 0, steht es nicht am Index 0",
                    "a", list.retrieve(0));
            list.insert(0, "b");
            assertEquals("Das eingefügte Element steht nicht an seiner Stelle",
                    "b", list.retrieve(0));
            assertEquals("Einfügen verschiebt die rechts stehenden Elemente nicht",
                    "a", list.retrieve(1));
        } catch (Exception e) {
            fail("insert() wirft eine Exception umsonst");
        }
        
        assertEquals("insert() hat keine Laufzeit", list.getCounter() > 0, true);
        
        assertEquals("Die Größe der Liste nach insert stimmt nicht", 2, list.size());

    }
    
    @Test
    public void delete() {
        list = getNewList();
        
        try {
            list.delete(-1);
            fail("delete() wirft keine IndexOutOfBoundsException, "
                    + "wenn eine negative Position angegeben ist");
        } catch (IndexOutOfBoundsException e) {
        }
        
        try {
            list.delete(1);
            fail("delete() wirft keine IndexOutOfBoundsException, "
                    + "wenn die Position > size ist");
        } catch (IndexOutOfBoundsException e) {
        }
        
        try {
            list.delete(0);
            fail("delete() wirft keine IndexOutOfBoundsException, "
                    + "wenn versuche das 0. Element aus der leeren Liste zu löschen");
        } catch (IndexOutOfBoundsException e) {
        }
        
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(2, "c");
        
        list.delete(1);
        assertEquals("delete verschiebt die rechts stehenden Elemente nach links nicht",
                "c", list.retrieve(1));
        assertEquals("delete() hat keine Laufzeit", list.getCounter() > 0, true);
        
        list.delete(1);
        assertEquals("delete() verkleinet die Größe der Liste nicht", 1, list.size());
    }
    
    @Test
    public void find() {
        list = getNewList();
        assertEquals("Liefert keine -1 zurück bei der leeren Liste", -1, list.find(""));
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(2, "b");
        assertEquals("Liefert keine -1 bei der nicht erfolglosen Suche", -1, list.find("c"));
        assertEquals("Findet nicht das erst vorkommende Element", 1, list.find("b"));
        assertEquals("find() hat keine Laufzeit", list.getCounter() > 0, true);
    }
    
    @Test
    public void retrieve() {
        list = getNewList();
        try {
            list.retrieve(-1);
            fail("retrieve() wirft keine IndexOutOfBoundsException, "
                    + "wenn eine negative Position anegeben ist");
        } catch (IndexOutOfBoundsException e) {
        }
        
        try {
            list.retrieve(1);
            fail("retrieve() wirft keine IndexOutOfBoundsException, "
                    + "wenn die Position > size ist");
        } catch (IndexOutOfBoundsException e) {
        }
        
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(2, "c");
        
        try {
            assertEquals("retrieve() findet kein 0. Element", "a", list.retrieve(0));
            assertEquals("retrieve() findet das gesuchte Element nicht ", "c", list.retrieve(2));
        } catch (Exception e) {
            fail("retrieve() wirft eine Exception umsonst");
        }
        assertEquals("retrieve() hat keine Laufzeit", list.getCounter() > 0, true);
        
        assertEquals("retrieve() verändert die Größe der Liste", 3, list.size());
    }    
    
    @Test
    public void concat() {
        list = getNewList();
        
        try {
            list.concat(null); 
        } catch (Exception e) {
            fail("concat() passt nicht auf, ob die Parameter Liste nicht null ist");
        }
        
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(2, "c");
        
        IList<String> list2 = getNewList();
        list2.insert(0, "d");
        list2.insert(1, "e");
        list2.insert(2, "f");
        
        list.concat(list2);
        assertEquals("concat() verliert Elemente der ersten Liste", "c", list.retrieve(2));
        try {
            assertEquals("concat() fügt die Elemente der Parameterliste nicht hinzu", "f", list.retrieve(5));
            assertEquals("die Größe der Gesamtliste ist falsch", 6, list.size());                 
        } catch (IndexOutOfBoundsException e) {
            fail("concat() vergrößert die Liste nicht");
        } catch (NullPointerException e) {
            fail("concat() hat \"null\" anstatt eines Elements, das kopiert werden musste");
        }
        assertEquals("concat() verändert die Parameterliste", "f", list2.retrieve(2));
        assertEquals("concat() verändert die Größe der Parameterliste", 3, list2.size());
        assertEquals("concat() hat keine Laufzeit", list.getCounter() > 0, true);
        
        list.concat(getNewList());
        assertEquals("concat(): Einfügen einer leeren Liste verändert die ursprungliche Liste",
                "f", list.retrieve(5));
        assertEquals("concat(): Einfügen einer leeren Liste verändert die Größe der "
                + "ursprunglichen Liste", 6, list.size()); 
        
        list = getNewList();
        list.concat(list2);
        assertEquals("concat(): Nach Einfügen zu einer leeren Liste ist das 0. Element "
                + "nicht das 0.te aus der Parameterliste", "d", list.retrieve(0));
        assertEquals("concat(): Einfügen zu einer leeren Liste funktioniert "
                + "nicht", "f", list.retrieve(2));
        
        list = getNewList();
        list.concat(getNewList());
        try {
            list.retrieve(0);
            fail("concat(): Eine leere Liste bleibt nicht leer, wenn sie mit einer anderer leeren "
                    + "Liste geconcatet wird");            
        } catch (IndexOutOfBoundsException e) {
        }
        assertEquals("Beim concat zweier leeren Listen wird die Größe verändert", 
                0, list.size()); 
    }
    
    @Test
    public void size() {
        list = getNewList();
        assertEquals("Size der leere Liste ist nicht 0", 0, list.size());
        assertEquals("size() hat keine Laufzeit", list.getCounter() > 0, true);
        //size nach den anderen Methoden ist in den selben Methoden bereits getestet
      }
    
    @Test
    public void counter() {
        list = getNewList();
        assertEquals("Counter der neuen Liste ist nicht 0", 0, list.getCounter());
         
        list.resetCounter();
        assertEquals("resetCounter() setzt den Zeitcounter auf 0 nicht", list.getCounter(), 0);
        
    }
    
}
