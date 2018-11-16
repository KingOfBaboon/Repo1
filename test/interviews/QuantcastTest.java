package interviews;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuantcastTest {
    Quantcast main = new Quantcast();

    @Test
    public void identification() {
        String[] strs1 = {
                "def function():",
                "  print(\"Hello world\")",
                "  print(\"Hello world\")",
                "  if i==1:",
                "    print(\"asdf\")"
        };

        String[] strs2 = {
                "def function():",
                "  print(\"Hello world\")",
                "    print(\"Hello world\")",
                "  if i==1:",
                "    print(\"asdf\")"
        };

        String[] strs3 = {
                "def function():",
                "  print(\"Hello world\")",
                "  #  print(\"Hello world\")",
                "  if i==1:",
                "    print(\"asdf\")"
        };

        String[] strs4 = {
                "def function():",
                "  print(\"Hello world\")",
                "  #  print(\"Hello world\")",
                "  if i==1:",
                "    print(\"asdf\"):"
        };

        assertEquals(main.identification(strs1), true);
        assertEquals(main.identification(strs2), false);
        assertEquals(main.identification(strs3), true);
        assertEquals(main.identification(strs4), false);
    }


    /*
    in the second problem Disjoint sets from https://www.hackerrank.com/tests/32g9f75h183

    with following input:
    1,2
    4,3
    2,3
    6,7
    4,5
    5,6
    8,9

    the output should be:
    1,2 2,3 4,3 4,5 5,6 6,7
    8,9
     */
    @Test
    public void disjointSets() {
        List<Quantcast.Node> nodes = new ArrayList<>();
        nodes.add(new Quantcast.Node(1, 2));
        nodes.add(new Quantcast.Node(4, 3));
        nodes.add(new Quantcast.Node(2, 3));
        nodes.add(new Quantcast.Node(6, 7));
        nodes.add(new Quantcast.Node(4, 5));
        nodes.add(new Quantcast.Node(5, 6));
        nodes.add(new Quantcast.Node(5, 6));
        nodes.add(new Quantcast.Node(8, 9));

        String s = main.disjointSets(nodes);
        assertEquals("1,2 2,3 4,3 4,5 5,6 6,7 \n8,9 \n", s);
    }

    @Test
    public void disjointSetsDSU() {
        List<Quantcast.Node> nodes = new ArrayList<>();
        nodes.add(new Quantcast.Node(1, 2));
        nodes.add(new Quantcast.Node(4, 3));
        nodes.add(new Quantcast.Node(2, 3));
        nodes.add(new Quantcast.Node(6, 7));
        nodes.add(new Quantcast.Node(4, 5));
        nodes.add(new Quantcast.Node(5, 6));
        nodes.add(new Quantcast.Node(5, 6));
        nodes.add(new Quantcast.Node(8, 9));

        String s = main.disjointSetsDSU(nodes);
        assertEquals("1,2 2,3 4,3 4,5 5,6 6,7 \n8,9 \n", s);
    }
}