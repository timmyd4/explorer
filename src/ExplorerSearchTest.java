import static org.junit.Assert.*;
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

    // Add more tests here!
    // Come up with varied cases
}
