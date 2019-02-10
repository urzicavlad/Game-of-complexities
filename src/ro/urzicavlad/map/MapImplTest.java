package ro.urzicavlad.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class MapImplTest {

    private Map<String, String> map;

    @Before
    public void setUp() {
        map = new MapImpl<>();
    }

    @Test
    public void put() {
        int expectedSize = populate();
        testSize(expectedSize);
    }

    @Test
    public void remove() {
        int actualSize = populate();
        int j = 0;
        for (char i = 'A'; i <= 'z'; i++) {
            if (i % 2 == 0) {
                final String key = String.valueOf(i);
                final String value = map.get(key);
                map.remove(String.valueOf(i));
                j++;
                System.out.println(
                        "Key: [" + i + "]" + "--" +
                                "Value: [" + value + "] removed.");
            }
        }
        testSize(actualSize - j);
    }

    @Test
    public void get() {
        populate();
        int j = 0;
        for (char key = 'A'; key <= 'z'; key++, j++) {
            assertEquals(String.valueOf(j), map.get(String.valueOf(key)));
            System.out.println("Key: [" + key + "]" + "--" +
                    "Value: [" + j + "] was found.");
        }
        final String key = "NOT_HERE!";
        assertNull(map.get(key));
        System.out.println("Key: [" + key + "]" + " wasn't found.");
    }

    @Test
    public void containsKey() {
        populate();
        for (char key = 'A'; key <= 'z'; key++) {
            assertTrue(map.containsKey(String.valueOf(key)));
            System.out.println("Key: [" + key + "]" + " was found.");
        }
        final String key = "NOT_HERE!";
        assertFalse(map.containsKey(key));
        System.out.println("Key: [" + key + "]" + " wasn't found.");

    }

    @Test
    public void containsValue() {
        populate();
        int dif = 'z' - 'A';
        int value = 0;
        while (value <= dif){
            assertTrue(map.containsValue(String.valueOf(value)));
            System.out.println("Value: [" + value + "]" + " was found.");
            value++;
        }
        final String notThere = "NOT_HERE!";
        assertFalse(map.containsValue(notThere));
        System.out.println("Key: [" + notThere + "]" + " wasn't found.");
    }

    @Test
    public void keySet() {
        final int expectedSize = populate();
        final Set<String> ks = map.keySet();
        assertEquals(expectedSize, ks.size());
        for (char key = 'A'; key <= 'z'; key++) {
            assertTrue(ks.contains(String.valueOf(key)));
            System.out.println("Key: [" + key + "] was found in key-set.");
        }
    }

    @Test
    public void entrySet() {
        final int expectedSize = populate();
        final Set<Map.Entry<String, String>> es = map.entrySet();
        assertEquals(expectedSize, es.size());
    }

    @Test
    public void isEmpty() {
        assertEquals(0, map.size());
    }

    private int populate() {
        int j = 0;
        for (char i = 'A'; i <= 'z'; i++, j++) {
            map.put(String.valueOf(i), String.valueOf(j));
            System.out.println("Key: [" + i + "]" + "--" + "Value: [" + j + "] added.");
        }
        return j;
    }

    private void testSize(int size) {
        System.out.println("Expected size: " + size);
        System.out.println("Actual size : " + map.size());
        assertEquals(size, map.size());
    }
}