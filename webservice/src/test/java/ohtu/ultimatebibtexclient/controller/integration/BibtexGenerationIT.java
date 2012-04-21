/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.controller.integration;


import com.gargoylesoftware.htmlunit.javascript.host.Element;
import ohtu.ultimatebibtexclient.util.HtmlUnitDriver;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 *
 * @author tsnorri
 */
public class BibtexGenerationIT
{
    public BibtexGenerationIT()
    {
    }


    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }


    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    

    @Before
    public void setUp()
    {
    }
    

    @After
    public void tearDown()
    {
    }
    
    
    @Test
    public void testDownloading()
    {
        // Given a reference has been created
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        WebElement element = driver.findElement(By.linkText("Luo uusi viite"));
        element.click();

        element = driver.findElement(By.name("author"));
        element.sendKeys("John Q. Public");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Ohtu2012");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("OhtuLabra");
        element = driver.findElement(By.name("pages"));
        element.sendKeys("31-33");
        element = driver.findElement(By.name("year"));
        element.sendKeys("2012");
        element = driver.findElement(By.id("submit"));
        element.submit();

        // When the download link is clicked
        driver.get("http://localhost:8088");
        element = driver.findElement(By.linkText("Lataa viitteet BibTeX-muodossa"));
        element.click();

        // Then the BibTeX file will be created
        assertEquals ("text/x-bibtex", driver.getMimeType());
        String source = driver.getPageSource();
        assertTrue(-1 != source.indexOf("John Q. Public"));
    }
}
