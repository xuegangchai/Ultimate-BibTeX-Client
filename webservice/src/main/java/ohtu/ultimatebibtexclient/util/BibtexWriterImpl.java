/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.CharacterIterator;
import java.text.Normalizer;
import java.text.StringCharacterIterator;
import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;
import org.jbibtex.*;



/**
 *
 * @author tsnorri
 */
public class BibtexWriterImpl implements BibtexWriter
{
    private class FieldSpec
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
        public void addTo(BibTeXEntry entry, LaTeXPrinter printer)
        {
            if(null != stringValue)
            {
                String escaped = BibtexWriterImpl.escape(stringValue);
                StringValue value = new StringValue(escaped, StringValue.Style.QUOTED);
                entry.addField(key, value);
            }
        }
    }
    
    /**
     * 
     * @param val
     * @return 
     */
    private static String escape(String val)
    {
        val = Normalizer.normalize(val, Normalizer.Form.NFC);
        StringBuilder sb = new StringBuilder();
        final CharacterIterator it = new StringCharacterIterator(val);
        for (char c = it.first(); c != CharacterIterator.DONE; c = it.next())
        {
            switch (c)
            {
                case '{':
                    sb.append("\\{");
                    break;
                case '}':
                    sb.append("\\}");
                    break;
                case '"':
                    sb.append("{\"}");
                    break;
                case 'å':
                    sb.append("{\\r{a}}");
                    break;
                case 'Å':
                    sb.append("{\\r{A}}");
                    break;
                case 'ä':
                    sb.append("{\\\"{a}}");
                    break;
                case 'Ä':
                    sb.append("{\\\"{A}}");
                    break;
                case 'ö':
                    sb.append("{\\\"{o}}");
                    break;
                case 'Ö':
                    sb.append("{\\\"{O}}");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
    
    /**
     * 
     * @param refs
     * @param stream
     * @throws IOException 
     */
    @Override
    public void write(Collection<Reference> refs, OutputStream stream) throws IOException
    {
        BibTeXDatabase db = new BibTeXDatabase();
        LaTeXPrinter printer = new LaTeXPrinter();
        
        {
            StringValue commentVal = new StringValue("Automatically generated with Ultimate BibTeX client.", StringValue.Style.BRACED);
            BibTeXComment comment = new BibTeXComment(commentVal);
            db.addObject(comment);
        }
        
        for (Reference ref : refs)
        {
            // Command pattern.
            FieldSpec[] specs = {
                new FieldSpec("Author", BibTeXEntry.KEY_AUTHOR, ref.getAuthor()),
                new FieldSpec("Editor", BibTeXEntry.KEY_EDITOR, ref.getEditor()),
                new FieldSpec("Title", BibTeXEntry.KEY_TITLE, ref.getTitle()),
                new FieldSpec("Booktitle", BibTeXEntry.KEY_BOOKTITLE, ref.getBooktitle()),
                new FieldSpec("Pages", BibTeXEntry.KEY_PAGES, ref.getPages()),
                new FieldSpec("Volume", BibTeXEntry.KEY_VOLUME, ref.getVolume()),
                new FieldSpec("Number", BibTeXEntry.KEY_NUMBER, ref.getNumber()),
                new FieldSpec("Series", BibTeXEntry.KEY_SERIES, ref.getSeries()),
                new FieldSpec("Publisher", BibTeXEntry.KEY_PUBLISHER, ref.getPublisher()),
                new FieldSpec("Address", BibTeXEntry.KEY_ADDRESS, ref.getAddress()),
                new FieldSpec("Year", BibTeXEntry.KEY_YEAR, String.format("%d", ref.getYear())),
                new FieldSpec("Month", BibTeXEntry.KEY_MONTH, String.format ("%d", ref.getMonth())),
                new FieldSpec("Organization", BibTeXEntry.KEY_ORGANIZATION, ref.getOrganization()),
                new FieldSpec("Note", BibTeXEntry.KEY_NOTE, ref.getNote()),
                new FieldSpec("Key", BibTeXEntry.KEY_KEY, ref.getKey()),
            };

            BibTeXEntry entry = new BibTeXEntry(new Key("inproceedings"), new Key(ref.getRefkey()));
            for (FieldSpec spec : specs)
            {
                spec.addTo(entry, printer);
            }
            
            db.addObject(entry);
        }
        
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        BibTeXFormatter formatter = new BibTeXFormatter();
        formatter.format(db, writer);
        writer.flush();
    }
}
