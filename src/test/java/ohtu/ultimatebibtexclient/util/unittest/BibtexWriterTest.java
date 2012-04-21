/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util.unittest;


import java.io.*;
import java.util.Arrays;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.util.BibtexWriter;
import ohtu.ultimatebibtexclient.util.BibtexWriterImpl;
import ohtu.ultimatebibtexclient.util.SpringTestBase;
import org.jbibtex.BibTeXDatabase;
import org.junit.*;
import static org.junit.Assert.*;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;


/**
 *
 * @author tsnorri
 */
public class BibtexWriterTest extends SpringTestBase
{
    private void parseBibtex(ByteArrayOutputStream os) throws IOException, ParseException
    {
        String content = os.toString("UTF-8");
        assertTrue(80 < content.length());
        Reader reader = new StringReader(content);
        
        BibTeXParser parser = new BibTeXParser();
        BibTeXDatabase parse = parser.parse(reader);
        assertNotNull(parser);
    }


    public BibtexWriterTest()
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
    public void simple() throws IOException, ParseException
    {
        Reference ref = new Reference();
        ref.setRefkey("refkey");
        ref.setBooktitle("booktitle");
        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        parseBibtex(stream);
    }


    @Test
    public void escaping() throws IOException, ParseException
    {
        Reference ref = new Reference();
        ref.setRefkey("test");
        ref.setAuthor("{");
        ref.setBooktitle("}");
        ref.setAddress("@");
        ref.setNote("\"");
        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        parseBibtex(stream);
    }
    
    
    @Test
    public void specialChar() throws IOException, ParseException
    {
        Reference ref = new Reference();
        ref.setRefkey("test");
        ref.setAuthor("å");
        ref.setBooktitle("ä");
        ref.setAddress("ö");
        ref.setNote("Å");
        ref.setSeries("Ä");
        ref.setVolume("Ö");
        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        parseBibtex(stream);
    }
}
