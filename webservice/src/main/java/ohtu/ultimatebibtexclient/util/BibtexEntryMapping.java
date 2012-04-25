/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import ohtu.ultimatebibtexclient.domain.Reference;
import org.jbibtex.*;


/**
 *
 * @author tsnorri
 */
abstract class BibtexEntryMapping
{
    public abstract String reftypeName();
    public abstract Key reftypeKey();


    private static class FieldSpec
    {
        private String name;
        private Key key;
        private String stringValue;


        /**
         *
         * @param name
         * @param key
         * @param stringVal
         */
        public FieldSpec(String name, Key key, String stringVal)
        {
            this.name = name;
            this.key = key;
            this.stringValue = stringVal;
        }


        /**
         *
         * @param entry
         * @param printer
         */
        public void addTo(BibTeXEntry entry, LaTeXPrinter printer, BibtexEscaping writer)
        {
            if (null != stringValue)
            {
                String escaped = writer.escape(stringValue);
                StringValue value = new StringValue(escaped, StringValue.Style.QUOTED);
                entry.addField(key, value);
            }
        }
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


    public static BibtexEntryMapping mappingForEntry(BibTeXEntry entry)
    {
        BibtexEntryMapping retval = null;
        final Key entryType = entry.getType();
        if (null == entryType)
            throw new NullPointerException (String.format("Entry type not set for %s.", entry));
        
        if (entryType.equals(BibTeXEntry.TYPE_ARTICLE))
            retval = new ArticleEntryMapping();
        else if (entryType.equals(BibTeXEntry.TYPE_BOOK))
            retval = new BookEntryMapping();
        else if (entryType.equals(BibTeXEntry.TYPE_INPROCEEDINGS))
            retval = new InproceedingsEntryMapping();

        return retval;
    }
    
    
    public static BibtexEntryMapping mappingForReference(Reference ref)
    {
        BibtexEntryMapping retval = null;
        final String reftype = ref.getType();
        if (null == reftype)
            throw new NullPointerException(String.format("Reference type not set for %s.", ref));

        if (reftype.equals("article"))
            retval = new ArticleEntryMapping();
        else if (reftype.equals("book"))
            retval = new BookEntryMapping();
        else if (reftype.equals("inproceedings"))
            retval = new InproceedingsEntryMapping();
        return retval;
    }


    public BibTeXEntry createEntry(Reference ref, LaTeXPrinter printer, BibtexEscaping writer)
    {
        // Command pattern.
        BibTeXEntry entry = new BibTeXEntry(this.reftypeKey(), new Key(ref.getRefkey()));
        for (FieldSpec spec : this.fieldSpecsForReference(ref))
            spec.addTo(entry, printer, writer);
        return entry;
    }


    private FieldSpec[] fieldSpecsForReference(Reference ref)
    {
        // Apparently different pieces of software use different fields in a given entry type.
        // We allow any field in any entry.
        FieldSpec[] retval =
        {
            new FieldSpec("Address", BibTeXEntry.KEY_ADDRESS, ref.getAddress()),
            new FieldSpec("Author", BibTeXEntry.KEY_AUTHOR, ref.getAuthor()),
            new FieldSpec("Booktitle", BibTeXEntry.KEY_BOOKTITLE, ref.getBooktitle()),
            new FieldSpec("Edition", BibTeXEntry.KEY_EDITION, ref.getEdition()),
            new FieldSpec("Editor", BibTeXEntry.KEY_EDITOR, ref.getEditor()),
            new FieldSpec("Journal", BibTeXEntry.KEY_JOURNAL, ref.getJournal()),
            new FieldSpec("Key", BibTeXEntry.KEY_KEY, ref.getKey()),
            new FieldSpec("Month", BibTeXEntry.KEY_MONTH, String.format("%d", ref.getMonth())),
            new FieldSpec("Note", BibTeXEntry.KEY_NOTE, ref.getNote()),
            new FieldSpec("Number", BibTeXEntry.KEY_NUMBER, ref.getNumber()),
            new FieldSpec("Organization", BibTeXEntry.KEY_ORGANIZATION, ref.getOrganization()),
            new FieldSpec("Pages", BibTeXEntry.KEY_PAGES, ref.getPages()),
            new FieldSpec("Publisher", BibTeXEntry.KEY_PUBLISHER, ref.getPublisher()),
            new FieldSpec("Series", BibTeXEntry.KEY_SERIES, ref.getSeries()),
            new FieldSpec("Title", BibTeXEntry.KEY_TITLE, ref.getTitle()),
            new FieldSpec("Volume", BibTeXEntry.KEY_VOLUME, ref.getVolume()),
            new FieldSpec("Year", BibTeXEntry.KEY_YEAR, String.format("%d", ref.getYear())),
        };
        return retval;
    }


    public Reference createReference(BibTeXEntry entry)
    {
        // Apparently different pieces of software use different fields in a given entry type.
        // We allow any field in any entry.
        Reference ref = new Reference();
        ref.setType(this.reftypeName());
        ref.setRefkey(entry.getKey().getValue());

        ref.setAddress(stringValue(entry, BibTeXEntry.KEY_ADDRESS));
        ref.setAuthor(stringValue(entry, BibTeXEntry.KEY_AUTHOR));
        ref.setBooktitle(stringValue(entry, BibTeXEntry.KEY_BOOKTITLE));
        ref.setEdition(stringValue(entry, BibTeXEntry.KEY_EDITION));
        ref.setEditor(stringValue(entry, BibTeXEntry.KEY_EDITOR));
        ref.setJournal(stringValue(entry, BibTeXEntry.KEY_JOURNAL));
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


    private static class ArticleEntryMapping extends BibtexEntryMapping
    {
        @Override
        public String reftypeName()
        {
            return "article";
        }


        @Override
        public Key reftypeKey()
        {
            return BibTeXEntry.TYPE_ARTICLE;
        }
    }


    private static class BookEntryMapping extends BibtexEntryMapping
    {
        @Override
        public String reftypeName()
        {
            return "book";
        }


        @Override
        public Key reftypeKey()
        {
            return BibTeXEntry.TYPE_BOOK;
        }
    }


    private static class InproceedingsEntryMapping extends BibtexEntryMapping
    {
        @Override
        public String reftypeName()
        {
            return "inproceedings";
        }


        @Override
        public Key reftypeKey()
        {
            return BibTeXEntry.TYPE_INPROCEEDINGS;
        }
    }
}
