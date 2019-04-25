import java.util.Arrays;

import org.junit.*;


public class BubbleSortTest {
	private BubbleSort bubblesort;
	
    @Before
    public void setUp() throws Exception{
    	bubblesort = new BubbleSort();
    }
    
    @After
    public void tearDown() throws Exception{
    	bubblesort = null;
    }
    
    @Test
    public void testBubbleSort(){
    	Assert.assertEquals(Arrays.toString(new int[]{1,2,2,5,6}),Arrays.toString(bubblesort.BubbleSort(new int[] {1,5,2,6,2})));
    }
}
