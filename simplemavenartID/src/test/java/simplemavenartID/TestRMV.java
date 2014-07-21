package simplemavenartID;

import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;


public class TestRMV {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.rmv.de";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJUnitRMVXML001() throws Exception {
    
	driver.get(baseUrl + "/");
	driver.manage().window().maximize();
//	Thread.sleep(5000);
//	
//	 WebElement element = driver.findElement(By.xpath("//span[text()='Recherche Google']"));
//	 String strng = element.getText();
//	 System.out.println(strng);
//	 Assert.assertEquals("Recherche Google",strng);
//	 
//	 Thread.sleep(5000);
	 
	 
	 //driver.findElement(By.id("gs_htif0")).clear();
	 //driver.findElement(By.id("gbqfq")).clear();
	 //driver.findElement(By.id("gbqfq")).sendKeys("teHst");
	 //driver.findElement(By.id("gbqfb")).click();
	 
	 //Thread.sleep(15000);


	
    
    
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document dom = db.parse("D:\\Benutzer-Profile\\krishnanng\\git\\MainJUnitRepo\\MyJUnits\\File001.xml");
    //D:\Benutzer-Profile\krishnanng\git\LocalJUnitRepo\MyJUnits
    Element docEle = dom.getDocumentElement();
    NodeList nl = docEle.getChildNodes();
    if (nl != null && nl.getLength() > 0) {
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) nl.item(i);
                if (el.getNodeName().contains("set")) {
                    String uname = el.getElementsByTagName("uname").item(0).getTextContent();
                    String pword = el.getElementsByTagName("pword").item(0).getTextContent();
                    String city = el.getElementsByTagName("city").item(0).getTextContent();
                    //String area = el.getElementsByTagName("area").item(0).getTextContent();
                    //String city = el.getElementsByTagName("city").item(0).getTextContent();
                    
                    driver.findElement(By.id("myname")).clear();
                    driver.findElement(By.id("myname")).sendKeys(uname);
                    driver.findElement(By.id("mypw")).clear();
                    driver.findElement(By.id("mypw")).sendKeys(pword);
                    driver.findElement(By.xpath("//*[text()=\"Login\"]")).click();
                    driver.findElement(By.id("searchbar")).clear();
                    driver.findElement(By.id("searchbar")).sendKeys(city);
                    driver.findElement(By.xpath("//*[text()=\"Suchen\"]")).click();
                   // driver.findElement(By.xpath("//*[text()=\"Suchen\"]")).click();
                    //
                    String results = city;
                    
                    //String comp_xpath = "//h2[contains(text(),"Treffer")]";
                    //String result_count = driver.findElement(By.xpath("//h2[contains(text(),\"Treffer\")]")).getText();
                    String result_count = driver.findElement(By.xpath("//div[@Class=\"search-box\"]/following-sibling::h2")).getText();
                    
            		try {
            			 
            			//Output report file directory changed
            			File file = new File("D:\\Benutzer-Profile\\krishnanng\\Output_Search_Results.txt");
            			// if file doesnt exists, then create it
            			if (!file.exists()) {
            				file.createNewFile();
            			}

            			FileWriter fw = new FileWriter(file,true);
            			BufferedWriter bw = new BufferedWriter(fw);
            			 
            			java.util.Date date= new java.util.Date();
            			String ts = new Timestamp(date.getTime()).toString();
            			
            			if (i==1){bw.write("The Results for the execution at"+ts+"\n");}
            				
            			
            			bw.write(city+"\t\t\t"+result_count+"\n");
            			
            			bw.close();
             
            			System.out.println("Done");
             
            			} catch (IOException e) {
            				e.printStackTrace();
            			}
                    
                    
                    driver.findElement(By.linkText("Logout")).click();
                    
                    // Emercode
                    // First Cha....Actuall Cha...
                   
                }
            }
        }
    }
    
    // Intended Addition in Process - completed -- done
    
   // Emer addition done 
	
	/*
	try {
		 
		//Output report file directory changed
		File file = new File("D:\\Benutzer-Profile\\krishnanng\\Output_Search_Results_new.txt");
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file,true);
		BufferedWriter bw = new BufferedWriter(fw);
		 
		java.util.Date date= new java.util.Date();
		String ts = new Timestamp(date.getTime()).toString();
		
		{bw.write("The Results for the execution at"+ts+"\n");}
			
		
		bw.write("\n");
		
		bw.close();

		System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	*/
	
	
	
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
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
}
