/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util.unittest;


import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.util.BibtexReader;
import ohtu.ultimatebibtexclient.util.BibtexReaderImpl;
import org.junit.*;
import static org.junit.Assert.*;


/**
 *
 * @author tsnorri
 */
public class BibtexReaderTest
{
    public BibtexReaderTest()
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
    public void read1() throws Throwable
    {
        String input = "@Article{2012:2159531,\n"
                       + "journal = {ACM Trans. Comput. Logic},\n"
                       + "year = {2012},\n"
                       + "issn = {1529-3785},\n"
                       + "volume = {13},\n"
                       + "number = {2},\n"
                       + "issue_date = {April 2012},\n"
                       + "publisher = {ACM},\n"
                       + "address = {New York, NY, USA},\n"
                       + "key = {{$\\!\\!$}} ,\n"
                       + "}";
        BibtexReader reader = new BibtexReaderImpl();
        StringReader stringReader = new StringReader(input);
        Collection<Reference> refCollection = reader.read(stringReader);
        
        Reference[] refs = new Reference[1];
        refs = refCollection.toArray(refs);
        assertEquals(1, refs.length);
        
        Reference ref = refs[0];
        assertEquals("2012:2159531", ref.getRefkey());
        // journal
        assertEquals(new Integer(2012), ref.getYear());
        // issn
        assertEquals("13", ref.getVolume());
        assertEquals("2", ref.getNumber());
        // issue_date
        assertEquals("ACM", ref.getPublisher());
        assertEquals("New York, NY, USA", ref.getAddress());
        // The intent of the key isn't clear.
    }
    
    
    @Test
    public void read2() throws Throwable
    {
        String input = "@proceedings{Doudi:2010:1877808,\n"
                       + "title = {3DOR '10: Proceedings of the ACM workshop on 3D object retrieval},\n"
                       + "year = {2010},\n"
                       + "isbn = {978-1-4503-0160-2},\n"
                       + "location = {Firenze, Italy},\n"
                       + "note = {433107},\n"
                       + "publisher = {ACM},\n"
                       + "address = {New York, NY, USA},\n"
                       + "} ";

        BibtexReader reader = new BibtexReaderImpl();
        StringReader stringReader = new StringReader(input);
        Collection<Reference> refCollection = reader.read(stringReader);

        Reference[] refs = new Reference[1];
        refs = refCollection.toArray(refs);
        assertEquals(1, refs.length);

        Reference ref = refs[0];
        assertEquals("Doudi:2010:1877808", ref.getRefkey());
        assertEquals("3DOR '10: Proceedings of the ACM workshop on 3D object retrieval", ref.getTitle());
        assertEquals(new Integer(2010), ref.getYear());
        // isbn
        // location
        assertEquals("433107", ref.getNote());
        assertEquals("ACM", ref.getPublisher());
        assertEquals("New York, NY, USA", ref.getAddress());
    }
}
