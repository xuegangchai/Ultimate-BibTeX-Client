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
        assertEquals("article", ref.getType());
        assertEquals("2012:2159531", ref.getRefkey());
        assertEquals("ACM Trans. Comput. Logic", ref.getJournal());
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
        String input = "@Book{abramowitz+stegun,\n"
                       + "author    = \"Milton Abramowitz and Irene A. Stegun\",\n"
                       + "title     = \"Handbook of Mathematical Functions with Formulas, Graphs, and Mathematical Tables\",\n"
                       + "publisher = \"Dover\",\n"
                       + "year      = \"1964\",\n"
                       + "address   = \"New York\",\n"
                       + "edition   = \"ninth Dover printing, tenth GPO printing\"\n"
                       + "}";

        BibtexReader reader = new BibtexReaderImpl();
        StringReader stringReader = new StringReader(input);
        Collection<Reference> refCollection = reader.read(stringReader);

        Reference[] refs = new Reference[1];
        refs = refCollection.toArray(refs);
        assertEquals(1, refs.length);

        Reference ref = refs[0];
        assertEquals("book", ref.getType());
        assertEquals("abramowitz+stegun", ref.getRefkey());
        assertEquals("Milton Abramowitz and Irene A. Stegun", ref.getAuthor());
        assertEquals("Handbook of Mathematical Functions with Formulas, Graphs, and Mathematical Tables", ref.getTitle());
        assertEquals("Dover", ref.getPublisher());
        assertEquals(new Integer(1964), ref.getYear());
        assertEquals("New York", ref.getAddress());
        assertEquals("ninth Dover printing, tenth GPO printing", ref.getEdition());
    }


    @Test
    public void read3() throws Throwable
    {
        String input = "@article{Gettys90,\n"
                       + "author = {Jim Gettys and Phil Karlton and Scott McGregor},\n"
                       + "title = {The X Window System, Version 11},\n"
                       + "journal = {Software Practice and Experience},\n"
                       + "volume = {20},\n"
                       + "number = {S2},\n"
                       + "year = {1990},\n"
                       + "abstract = {A technical overview of the X11 functionality.  This is an update of the X10 TOG paper by Scheifler & Gettys.}\n"
                       + "}";

        BibtexReader reader = new BibtexReaderImpl();
        StringReader stringReader = new StringReader(input);
        Collection<Reference> refCollection = reader.read(stringReader);

        Reference[] refs = new Reference[1];
        refs = refCollection.toArray(refs);
        assertEquals(1, refs.length);

        Reference ref = refs[0];
        assertEquals("article", ref.getType());
        assertEquals("Gettys90", ref.getRefkey());
        assertEquals("Jim Gettys and Phil Karlton and Scott McGregor", ref.getAuthor());
        assertEquals("The X Window System, Version 11", ref.getTitle());
        assertEquals("Software Practice and Experience", ref.getJournal());
        assertEquals("20", ref.getVolume());
        assertEquals("S2", ref.getNumber());
        assertEquals(new Integer(1990), ref.getYear());
        // abstract
    }
    
    
    @Test
    public void read4() throws Throwable
    {
        String input = "@inproceedings{Kurhila:2011:MST:2047594.2047596,\n"
                       + "author = {Kurhila, Jaakko and Vihavainen, Arto},\n"
                       + "title = {Management, structures and tools to scale up personal advising in large programming courses},\n"
                       + "booktitle = {Proceedings of the 2011 conference on Information technology education},\n"
                       + "series = {SIGITE '11},\n"
                       + "year = {2011},\n"
                       + "isbn = {978-1-4503-1017-8},\n"
                       + "location = {West Point, New York, USA},\n"
                       + "pages = {3--8},\n"
                       + "numpages = {6},\n"
                       + "url = {http://doi.acm.org/10.1145/2047594.2047596},\n"
                       + "doi = {10.1145/2047594.2047596},\n"
                       + "acmid = {2047596},\n"
                       + "publisher = {ACM},\n"
                       + "address = {New York, NY, USA},\n"
                       + "keywords = {continuous feedback, course cost, individual education, instructional design, programming education, resource allocation},\n"
                       + "}";

        BibtexReader reader = new BibtexReaderImpl();
        StringReader stringReader = new StringReader(input);
        Collection<Reference> refCollection = reader.read(stringReader);

        Reference[] refs = new Reference[1];
        refs = refCollection.toArray(refs);
        assertEquals(1, refs.length);

        Reference ref = refs[0];
        assertEquals("inproceedings", ref.getType());
        assertEquals("Kurhila:2011:MST:2047594.2047596", ref.getRefkey());
        assertEquals("Kurhila, Jaakko and Vihavainen, Arto", ref.getAuthor());
        assertEquals("Management, structures and tools to scale up personal advising in large programming courses", ref.getTitle());
        assertEquals("Proceedings of the 2011 conference on Information technology education", ref.getBooktitle());
        assertEquals("SIGITE '11", ref.getSeries());
        assertEquals(new Integer(2011), ref.getYear());
        // isbn
        // location
        assertEquals("3--8", ref.getPages());
        // numpages
        // url
        // doi
        // acmid
        assertEquals("ACM", ref.getPublisher());
        assertEquals("New York, NY, USA", ref.getAddress());
        // keywords
    }
}
