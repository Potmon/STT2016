import org.junit.*;

public class BackPackTest {
    private BackPack backpack;
    
    @Before
    public void setUp() throws Exception {
    	backpack = new BackPack();
    }
    
    @After
    public void tearDown() throws Exception {
    	backpack = null;
    }
    
	@Test
    public void testBackPack() {
    	Assert.assertEquals(ArrayToString(new int[][]{ {0,0,4,4,4,4,4,4,4,4},{0,0,4,5,5,5,9,9,9,9},{0,0,4,5,6,6,9,10,11,11}}),rArrayToString(backpack.BackPack_Solution(10, 3, new int[] {3,4,5}, new int[] {4,5,6})));
    }
	
	public String ArrayToString(int[][] array) {
		int row = array.length;
		int col = array[0].length;
		String result = "";
		String temp = "";
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				temp = String.valueOf(array[i][j]);
				result = result + temp + ",";
			}
		}
		return result;
	}
	public String rArrayToString(int[][] array) {
		int row = array.length;
		int col = array[0].length;
		String result = "";
		String temp = "";
		for(int i=1;i<row;i++) {
			for(int j=1;j<col;j++) {
				temp = String.valueOf(array[i][j]);
				result = result + temp + ",";
			}
		}
		return result;
	}
	
}
