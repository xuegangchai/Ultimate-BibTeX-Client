/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.controller.integration;


import com.gargoylesoftware.htmlunit.javascript.host.Element;
import java.io.*;
import ohtu.ultimatebibtexclient.util.HtmlUnitDriver;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.http.HttpRequest;


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

        element = driver.findElement(By.id("inproceedingsButton"));
        element.click();
        element = driver.findElement(By.name("refkey"));
        element.sendKeys("Public2012");
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
    
    
    @Test
    public void testUploading() throws UnsupportedEncodingException, IOException
    {
        // Given we have a reference to upload
        String input = "@inproceedings{Doudi:2010:1877808,\n"
                       + "title = {3DOR '10: Proceedings of the ACM workshop on 3D object retrieval},\n"
                       + "year = {2010},\n"
                       + "isbn = {978-1-4503-0160-2},\n"
                       + "location = {Firenze, Italy},\n"
                       + "note = {433107},\n"
                       + "publisher = {ACM},\n"
                       + "address = {New York, NY, USA},\n"
                       + "} ";
        
        // When the reference is uploaded
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8088/read-bibtex");
        HttpEntity ent = new StringEntity(input, "text/x-bibtex", "UTF-8");
        post.setHeader("Content-Type", "text/x-bibtex");
        post.setEntity(ent);
        HttpResponse response = client.execute(post);
        // Then the reference will be added.

        /*
        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        String theString = writer.toString();
        */
        
        assertEquals(200, response.getStatusLine().getStatusCode());
        
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:8088");
        
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("3DOR"));
        assertTrue(pageSource.contains("2010"));
    }
}
