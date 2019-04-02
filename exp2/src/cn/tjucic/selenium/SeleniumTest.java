package cn.tjucic.selenium;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



@RunWith(value = Parameterized.class)
public class SeleniumTest {
	private static WebDriver driver;
	private static final String baseUrl = "http://121.193.130.195:8800";
	private static String filePath = System.getProperty("user.dir") + "/src/File/Èí¼þ²âÊÔÃûµ¥.xlsx";
	private static String driverPath = System.getProperty("user.dir") + "/src/File/geckodriver.exe";
	private static boolean acceptNextAlert = true;
	private String sid;
	private String spassword;
	private String sinformation;
	public  SeleniumTest(String sid,String sinformation) {
		this.sid = sid;
		this.spassword = this.sid.substring(this.sid.length()-6, this.sid.length());
		this.sinformation = sinformation;
	}	
	/*@Before
	public void setUp() throws Exception {
		String driverPath = System.getProperty("user.dir") + "/src/resources/driver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}*/
	public static String getInformation(String sid,String spassword) {		
		driver.get(baseUrl + "/");
		WebElement id = driver.findElement(By.name("id"));
		WebElement password = driver.findElement(By.name("password"));
		//WebElement login = driver.findElement(By.className("btn btn-primary"));   
		id.click();
		//driver.findElement(By.id("kw")).click();
		id.clear();
		id.sendKeys(sid);
		password.click();
		password.clear();
		password.sendKeys(spassword);
		WebElement login = driver.findElement(By.id("login_form"));
		login.submit();
		if(isAlertPresent()) {
		    System.out.println(closeAlertAndGetItsText());
		}
		    /*if(isElementPresent(By.id("found-section"))) {
		    	System.out.println("µÇÂ½Ê§°Ü");
		    }
		    else {
		    	System.out.println("µÇÂ½³É¹¦");
		    }*/
		String res = "";
		if(isElementPresent(By.id("pricing-section"))){
		    res = driver.findElement(By.id("student-git")).getText();
		    logoutFromInformation();
		}		
		return res;
	}
	public static void logoutFromInformation() {
		WebElement logout = driver.findElement(By.id("btn_logout"));
		logout.click();
		WebElement returnToPage = driver.findElement(By.id("btn_return"));
		returnToPage.click();
	}
	private static boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	private static boolean isAlertPresent() {
        try {
	        driver.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	}

	private static String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	}
	private static List<Object[]> getData(List<List<String>> li){
		List<Object[]> dataList = new ArrayList<Object[]>();		
		for(int i=0;i<li.size();i++) {
			String[] rowList = {"",""};
			rowList[0]=(li.get(i).get(1));
			rowList[1]=(li.get(i).get(3));
			if(i>=2&&i<145) {
				dataList.add(rowList);
			}
		}
		return dataList;
	}
	@Parameters
	public static List<Object[]> data(){
		  /*return Arrays.asList(new Object[][] {
			  {"3016218051","https://github.com/popboykingdjko"},
			  {"3016218052","https://github.com/xiaoxuaoj"}
		  });*/
		//System.out.println(getData(ExcelUtil.getDataFromFile(path)));
		return getData(ExcelUtil.getDataFromFile(filePath));
	  }
	@Test
	public void test() {
		assertEquals(this.sinformation,getInformation(this.sid,this.spassword));
	}
	@BeforeClass
	public static void setup() throws Exception {
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
}
