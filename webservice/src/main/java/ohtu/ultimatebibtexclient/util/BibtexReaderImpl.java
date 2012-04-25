/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import ohtu.ultimatebibtexclient.domain.Reference;
import org.jbibtex.*;


/**
 *
 * @author tsnorri
 */
public class BibtexReaderImpl implements BibtexReader
{
    private static abstract class EntrySpec
    {
        public abstract Reference createReference(BibTeXEntry entry);


        public static EntrySpec specForEntry(BibTeXEntry entry)
        {
            EntrySpec retval = null;
            final Key entryType = entry.getType();
            if (entryType.equals(BibTeXEntry.TYPE_ARTICLE))
                retval = new ArticleEntrySpec();
            else if (entryType.equals(BibTeXEntry.TYPE_BOOK))
                retval = new BookEntrySpec();
            else if (entryType.equals(BibTeXEntry.TYPE_INPROCEEDINGS))
                retval = new InproceedingsEntrySpec();

            return retval;
        }
    }


    private static class ArticleEntrySpec extends EntrySpec
    {
        public ArticleEntrySpec()
        {
        }


        @Override
        public Reference createReference(BibTeXEntry entry)
        {
            Reference ref = new Reference();
            ref.setType("article");
            ref.setRefkey(entry.getKey().getValue());
            
            ref.setAuthor(stringValue(entry, BibTeXEntry.KEY_AUTHOR));
            ref.setTitle(stringValue(entry, BibTeXEntry.KEY_TITLE));
            ref.setPages(stringValue(entry, BibTeXEntry.KEY_PAGES));
            ref.setVolume(stringValue(entry, BibTeXEntry.KEY_VOLUME));
            ref.setNumber(stringValue(entry, BibTeXEntry.KEY_NUMBER));
            ref.setMonth(integerValue(entry, BibTeXEntry.KEY_MONTH));
            ref.setYear(integerValue(entry, BibTeXEntry.KEY_YEAR));
            ref.setJournal(stringValue(entry, BibTeXEntry.KEY_JOURNAL));
            ref.setPublisher(stringValue(entry, BibTeXEntry.KEY_PUBLISHER));
            ref.setNote(stringValue(entry, BibTeXEntry.KEY_NOTE));
            ref.setKey(stringValue(entry, BibTeXEntry.KEY_KEY));
            
            return ref;
        }
    }
    

    private static class BookEntrySpec extends EntrySpec
    {
        public BookEntrySpec()
        {
        }


        @Override
        public Reference createReference(BibTeXEntry entry)
        {
            Reference ref = new Reference();
            ref.setType("book");
            ref.setRefkey(entry.getKey().getValue());
            
            ref.setAuthor(stringValue(entry, BibTeXEntry.KEY_AUTHOR));
            ref.setTitle(stringValue(entry, BibTeXEntry.KEY_TITLE));
            ref.setVolume(stringValue(entry, BibTeXEntry.KEY_VOLUME));
            ref.setSeries(stringValue(entry, BibTeXEntry.KEY_SERIES));
            ref.setPublisher(stringValue(entry, BibTeXEntry.KEY_PUBLISHER));
            ref.setAddress(stringValue(entry, BibTeXEntry.KEY_ADDRESS));
            ref.setMonth(integerValue(entry, BibTeXEntry.KEY_MONTH));
            ref.setYear(integerValue(entry, BibTeXEntry.KEY_YEAR));
            ref.setEdition(stringValue(entry, BibTeXEntry.KEY_EDITION));
            ref.setNote(stringValue(entry, BibTeXEntry.KEY_NOTE));
            ref.setKey(stringValue(entry, BibTeXEntry.KEY_KEY));
            
            return ref;
        }
    }


    private static class InproceedingsEntrySpec extends EntrySpec
    {
        public InproceedingsEntrySpec()
        {
        }


        @Override
        public Reference createReference(BibTeXEntry entry)
        {
            Reference ref = new Reference();
            ref.setType("inproceedings");
            ref.setRefkey(entry.getKey().getValue());

            ref.setAddress(stringValue(entry, BibTeXEntry.KEY_ADDRESS));
            ref.setAuthor(stringValue(entry, BibTeXEntry.KEY_AUTHOR));
            ref.setBooktitle(stringValue(entry, BibTeXEntry.KEY_BOOKTITLE));
            ref.setEditor(stringValue(entry, BibTeXEntry.KEY_EDITOR));
            ref.setKey(stringValue(entry, BibTeXEntry.KEY_KEY));
            ref.setMonth(integerValue(entry, BibTeXEntry.KEY_MONTH));
            ref.setNote(stringValue(entry, BibTeXEntry.KEY_NOTE));
            ref.setNumber(stringValue(entry, BibTeXEntry.KEY_NUMBER));
            ref.setOrganization(stringValue(entry, BibTeXEntry.KEY_ORGANIZATION));
            ref.setPages(stringValue(entry, BibTeXEntry.KEY_PAGES));
            ref.setPublisher(stringValue(entry, BibTeXEntry.KEY_PUBLISHER));
            ref.setSeries(stringValue(entry, BibTeXEntry.KEY_SERIES));
            ref.setTitle(stringValue(entry, BibTeXEntry.KEY_TITLE));
            ref.setVolume(stringValue(entry, BibTeXEntry.KEY_VOLUME));
            ref.setYear(integerValue(entry, BibTeXEntry.KEY_YEAR));
            
            return ref;
        }
    }


    /**
     *
     */
    public BibtexReaderImpl()
    {
    }


    /**
     *
     * @param entry
     * @param key
     * @return
     */
    private static String stringValue(BibTeXEntry entry, Key key)
    {
        String retval = null;
        Value val = entry.getField(key);
        if (null != val)
            retval = val.toUserString();
        return retval;
    }


    /**
     *
     * @param entry
     * @param key
     * @return
     */
    private static Integer integerValue(BibTeXEntry entry, Key key)
    {
        Integer retval = null;
        String stringVal = stringValue(entry, key);
        if (null != stringVal)
            retval = Integer.parseInt(stringVal);
        return retval;
    }


    /**
     *
     * @param db
     * @return
     */
    public Collection<Reference> read(BibTeXDatabase db)
    {
        Collection<Reference> retval = new ArrayList<Reference>();

        Map<Key, BibTeXEntry> entries = db.getEntries();
        for (Map.Entry<Key, BibTeXEntry> mapEntry : entries.entrySet())
        {
            BibTeXEntry entry = mapEntry.getValue();
            EntrySpec spec = EntrySpec.specForEntry(entry);
            Reference ref = spec.createReference(entry);
            if (null != ref)
                retval.add(ref);
        }

        return retval;
    }


    /**
     *
     * @param reader
     * @return
     * @throws Throwable
     */
    @Override
    public Collection<Reference> read(Reader reader) throws Throwable
    {
        BibTeXParser parser = new BibTeXParser();
        BibTeXDatabase db = parser.parse(reader);
        return read(db);
    }
}
