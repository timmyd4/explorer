import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    /*
     * Reachable Area
     * Tests: 3
     * Author: Tim Williams
     */

    @Test
    public void testReachableArea_allReachable() {
        int[][] island = {
            {1,1,1},
            {1,0,1},
            {1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(9, actual);
    }

    @Test
    public void testReachableArea_mostReachable() {
        int[][] island = {
            {3,2,1,1,1},
            {1,1,1,2,1},
            {1,2,3,2,1},
            {1,1,0,2,1},
            {1,2,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(17, actual);
    }

    @Test
    public void testReachableArea_mostUnreachable() {
        int[][] island = {
            {3,2,1,1,1},
            {1,2,1,2,1},
            {1,2,3,2,1},
            {1,1,0,2,1},
            {3,2,1,3,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(6, actual);
    }

    /*
     * Explore Moves
     * Tests: 4
     * Author: Tim Williams
     */

    @Test
    public void testExplorableMoves_AllDirections()
    {
        int[][] island = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };

        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.explorableMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void testExplorableMoves_HalfAvailable()
    {
        int[][] island = {
            {3,2,3},
            {2,0,1},
            {3,1,2}
        };

        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.explorableMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void testExplorableMoves_HalfAvailableStartingTopLeft()
    {
        int[][] island = {
            {0,1,3},
            {1,3,3},
            {3,2,2}
        };

        int[] location = {0,0};
        List<int[]> moves = ExplorerSearch.explorableMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("1,0"));
    }

    @Test
    public void testExplorableMoves_noMoves()
    {
        int[][] island = {
            {0,2,3},
            {3,3,3},
            {3,2,2}
        };

        int[] location = {0,0};
        List<int[]> moves = ExplorerSearch.explorableMoves(island, location);
        assertTrue(moves.isEmpty());
    }


    /*
     * Explore location 
     * Tests: 5
     * Author: Tim Williams
     */

    @Test
    public void testExploreLocation_MiddleOfGrid()
    {
        int[][] island = {
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
        };
        int[] actual = ExplorerSearch.explorableLocations(island);
        int[] expected = {2,3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExploreLocation_TopMiddleOfGrid()
    {
        int[][] island = {
            {1,1,1,0,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
        };
        int[] actual = ExplorerSearch.explorableLocations(island);
        int[] expected = {0,3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExploreLocation_BottomRightOfGrid()
    {
        int[][] island = {
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,0},
        };
        int[] actual = ExplorerSearch.explorableLocations(island);
        int[] expected = {4,6};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExploreLocation_TopRightOfGrid()
    {
        int[][] island = {
            {1,1,1,1,1,1,0},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
        };
        int[] actual = ExplorerSearch.explorableLocations(island);
        int[] expected = {0,6};
        assertArrayEquals(expected, actual);
    }


    @Test
    public void testExploreLocation_NoValidLocation()
    {
        int[][] island = {
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1},
        };
        assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.explorableLocations(island);
        });
    }



    // Add more tests here!
    // Come up with varied cases



    //Method I noticed in the testing part of salamander on our introduction to this type of DSA.
    /*
     * Method returns a set, utilizes a (List) parameter.
     * intiliazes set,
     * loops through values of given values in list
     * added to the set as a (String) in the format (Example,Example) (1,1)
     * returns set for us to compare with our assetEquals
     */
     private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
