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
}
