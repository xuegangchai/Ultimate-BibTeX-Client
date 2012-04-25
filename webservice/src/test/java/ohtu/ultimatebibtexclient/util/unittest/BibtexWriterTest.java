/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util.unittest;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.util.BibtexWriter;
import ohtu.ultimatebibtexclient.util.BibtexWriterImpl;
import ohtu.ultimatebibtexclient.util.SpringTestBase;
import org.jbibtex.*;
import static org.junit.Assert.*;
import org.junit.*;


/**
 *
 * @author tsnorri
 */
public class BibtexWriterTest extends SpringTestBase
{
    private BibTeXDatabase parseBibtex(ByteArrayOutputStream os) throws IOException, ParseException
    {
        String content = os.toString("UTF-8");
        assertTrue(80 < content.length());
        Reader reader = new StringReader(content);

        BibTeXParser parser = new BibTeXParser();
        BibTeXDatabase db = parser.parse(reader);
        return db;
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

        // Setting all the values to test BibtexWriter's mapping.
        ref.setRefkey("refkey");
        ref.setType("inproceedings");
        ref.setAddress("address");
        ref.setAuthor("author");
        ref.setBooktitle("booktitle");
        ref.setEditor("editor");
        ref.setKey("key");
        ref.setMonth(5);
        ref.setNote("note");
        ref.setNumber("number");
        ref.setOrganization("organization");
        ref.setPages("pages");
        ref.setPublisher("publisher");
        ref.setSeries("series");
        ref.setTitle("title");
        ref.setVolume("volume");
        ref.setYear(1994);

        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        BibTeXDatabase db = parseBibtex(stream);
        Map<Key, BibTeXEntry> entries = db.getEntries();
        assertEquals(1, entries.size());

        Key[] keys =
        {
            BibTeXEntry.KEY_ADDRESS,
            BibTeXEntry.KEY_AUTHOR,
            BibTeXEntry.KEY_BOOKTITLE,
            BibTeXEntry.KEY_EDITOR,
            BibTeXEntry.KEY_KEY,
            BibTeXEntry.KEY_MONTH,
            BibTeXEntry.KEY_NOTE,
            BibTeXEntry.KEY_NUMBER,
            BibTeXEntry.KEY_ORGANIZATION,
            BibTeXEntry.KEY_PAGES,
            BibTeXEntry.KEY_PUBLISHER,
            BibTeXEntry.KEY_SERIES,
            BibTeXEntry.KEY_TITLE,
            BibTeXEntry.KEY_VOLUME,
            BibTeXEntry.KEY_YEAR,
        };

        String[] expectedValues =
        {
            "address",
            "author",
            "booktitle",
            "editor",
            "key",
            "5",
            "note",
            "number",
            "organization",
            "pages",
            "publisher",
            "series",
            "title",
            "volume",
            "1994"
        };

        BibTeXEntry entry = entries.get(new Key("refkey"));
        assertNotNull(entry);
        assertEquals(BibTeXEntry.TYPE_INPROCEEDINGS, entry.getType());
        assertEquals(keys.length, expectedValues.length);
        for (int i = 0, length = keys.length; i < length; i++)
        {
            assertEquals(expectedValues[i], entry.getField(keys[i]).toUserString());
        }
    }


    @Test
    public void simple2() throws IOException, ParseException
    {
        Reference ref = new Reference();

        // Setting all the values to test BibtexWriter's mapping.
        ref.setRefkey("refkey");
        ref.setType("book");
        ref.setAuthor("author");
        ref.setTitle("title");
        ref.setVolume("volume");
        ref.setSeries("series");
        ref.setPublisher("publisher");
        ref.setAddress("address");
        ref.setYear(1994);
        ref.setMonth(5);
        ref.setEdition("edition");
        ref.setNote("note");
        ref.setKey("key");

        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        BibTeXDatabase db = parseBibtex(stream);
        Map<Key, BibTeXEntry> entries = db.getEntries();
        assertEquals(1, entries.size());

        Key[] keys =
        {
            BibTeXEntry.KEY_AUTHOR,
            BibTeXEntry.KEY_TITLE,
            BibTeXEntry.KEY_VOLUME,
            BibTeXEntry.KEY_SERIES,
            BibTeXEntry.KEY_PUBLISHER,
            BibTeXEntry.KEY_ADDRESS,
            BibTeXEntry.KEY_YEAR,
            BibTeXEntry.KEY_MONTH,
            BibTeXEntry.KEY_EDITION,
            BibTeXEntry.KEY_NOTE,
            BibTeXEntry.KEY_KEY,
        };

        String[] expectedValues =
        {
            "author",
            "title",
            "volume",
            "series",
            "publisher",
            "address",
            "1994",
            "5",
            "edition",
            "note",
            "key"
        };

        BibTeXEntry entry = entries.get(new Key("refkey"));
        assertNotNull(entry);
        assertEquals(BibTeXEntry.TYPE_BOOK, entry.getType());
        assertEquals(keys.length, expectedValues.length);
        for (int i = 0, length = keys.length; i < length; i++)
        {
            assertEquals(expectedValues[i], entry.getField(keys[i]).toUserString());
        }
    }


    @Test
    public void simple3() throws IOException, ParseException
    {
        Reference ref = new Reference();

        // Setting all the values to test BibtexWriter's mapping.
        ref.setRefkey("refkey");
        ref.setType("article");
        ref.setAuthor("author");
        ref.setTitle("title");
        ref.setPages("pages");
        ref.setVolume("volume");
        ref.setNumber("number");
        ref.setYear(1994);
        ref.setMonth(5);
        ref.setJournal("journal");
        ref.setNote("note");
        ref.setKey("key");

        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        BibTeXDatabase db = parseBibtex(stream);
        Map<Key, BibTeXEntry> entries = db.getEntries();
        assertEquals(1, entries.size());

        Key[] keys =
        {
            BibTeXEntry.KEY_AUTHOR,
            BibTeXEntry.KEY_TITLE,
            BibTeXEntry.KEY_PAGES,
            BibTeXEntry.KEY_VOLUME,
            BibTeXEntry.KEY_NUMBER,
            BibTeXEntry.KEY_YEAR,
            BibTeXEntry.KEY_MONTH,
            BibTeXEntry.KEY_JOURNAL,
            BibTeXEntry.KEY_NOTE,
            BibTeXEntry.KEY_KEY,
        };

        String[] expectedValues =
        {
            "author",
            "title",
            "pages",
            "volume",
            "number",
            "1994",
            "5",
            "journal",
            "note",
            "key"
        };

        BibTeXEntry entry = entries.get(new Key("refkey"));
        assertNotNull(entry);
        assertEquals(BibTeXEntry.TYPE_ARTICLE, entry.getType());
        assertEquals(keys.length, expectedValues.length);
        for (int i = 0, length = keys.length; i < length; i++)
        {
            assertEquals(expectedValues[i], entry.getField(keys[i]).toUserString());
        }
    }


    @Test
    public void multiple() throws IOException, ParseException
    {
        Reference ref1 = new Reference();
        Reference ref2 = new Reference();

        ref1.setType("inproceedings");
        ref2.setType("book");
        ref1.setRefkey("ref1");
        ref2.setRefkey("ref2");
        ref1.setTitle("title1");
        ref2.setTitle("title2");

        Reference[] refs =
        {
            ref1,
            ref2
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        BibTeXDatabase db = this.parseBibtex(stream);
        Map<Key, BibTeXEntry> entries = db.getEntries();
        assertEquals(2, entries.size());

        BibTeXEntry e1 = entries.get(new Key("ref1"));
        BibTeXEntry e2 = entries.get(new Key("ref2"));
        assertNotNull(e1);
        assertNotNull(e2);

        assertEquals("title1", e1.getField(BibTeXEntry.KEY_TITLE).toUserString());
        assertEquals("title2", e2.getField(BibTeXEntry.KEY_TITLE).toUserString());
    }


    @Test
    public void escaping() throws IOException, ParseException
    {
        Reference ref = new Reference();
        ref.setType("inproceedings");
        ref.setRefkey("test");
        ref.setAuthor("{}@\"");
        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        BibTeXDatabase db = parseBibtex(stream);
        Map<Key, BibTeXEntry> entries = db.getEntries();
        assertEquals(1, entries.size());

        BibTeXEntry e = entries.get(new Key("test"));
        assertEquals("\\{\\}@{\"}", e.getField(BibTeXEntry.KEY_AUTHOR).toUserString());
    }


    @Test
    public void specialChar() throws IOException, ParseException
    {
        Reference ref = new Reference();
        ref.setType("inproceedings");
        ref.setRefkey("test");
        ref.setAuthor("åäöÅÄÖ");
        Reference[] refs =
        {
            ref
        };

        BibtexWriter writer = new BibtexWriterImpl();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writer.write(Arrays.asList(refs), stream);

        BibTeXDatabase db = parseBibtex(stream);
        Map<Key, BibTeXEntry> entries = db.getEntries();
        assertEquals(1, entries.size());

        BibTeXEntry e = entries.get(new Key("test"));
        assertEquals("{\\r{a}}{\\\"{a}}{\\\"{o}}{\\r{A}}{\\\"{A}}{\\\"{O}}",
                     e.getField(BibTeXEntry.KEY_AUTHOR).toUserString());
    }
}
