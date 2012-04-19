/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.CharacterIterator;
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
    private void addif(String key, Object val, BibTeXEntry entry, Key key)
    {
        if (null != val)
        {
            
            entry.addField(key,
            
            
            String str = val.toString();
            pf.format("\t%s = \"%s\",\n", key, escape(str));
        }
    }


    @Override
    public void write(Collection<Reference> refs, OutputStream stream) throws IOException
    {
        BibTeXDatabase db = new BibTeXDatabase();
        
        {
            StringValue commentVal = new StringValue("Automatically generated with Ultimate BibTeX client.", StringValue.Style.BRACED);
            BibTeXComment comment = new BibTeXComment(commentVal);
            db.addObject(comment);
        }
        
        for (Reference ref : refs)
        {
            BibTeXEntry entry = new BibTeXEntry(new Key("inproceedings "), new Key(ref.getRefkey()));
            addif("Author", ref.getAuthor(), entry, BibTeXEntry.KEY_AUTHOR);
            addif("Editor", ref.getEditor(), entry, BibTeXEntry.KEY_EDITOR);
            addif("Title", ref.getTitle(), entry, BibTeXEntry.KEY_TITLE);
            addif("Booktitle", ref.getBooktitle(), entry, BibTeXEntry.KEY_BOOKTITLE);
            addif("Pages", ref.getPages(), entry, BibTeXEntry.KEY_PAGES);
            addif("Volume", ref.getVolume(), entry, BibTeXEntry.KEY_VOLUME);
            addif("Number", ref.getNumber(), entry, BibTeXEntry.KEY_NUMBER);
            addif("Series", ref.getSeries(), entry, BibTeXEntry.KEY_SERIES);
            addif("Publisher", ref.getPublisher(), entry, BibTeXEntry.KEY_PUBLISHER);
            addif("Address", ref.getAddress(), entry, BibTeXEntry.KEY_ADDRESS);
            addif("Year", ref.getYear(), entry, BibTeXEntry.KEY_YEAR);
            addif("Month", ref.getMonth(), entry, BibTeXEntry.KEY_MONTH);
            addif("Organization", ref.getOrganization(), entry, BibTeXEntry.KEY_ORGANIZATION);
            addif("Note", ref.getNote(), entry, BibTeXEntry.KEY_NOTE);
            addif("Key", ref.getKey(), entry, BibTeXEntry.KEY_KEY);

        }
        
        
        
        
        OutputStreamWriter os = new OutputStreamWriter(stream, "UTF-8");
        PrintWriter pw = new PrintWriter(os);

        pw.append("@comment { Automatically generated with Ultimate BibTeX client. }\n\n");

        int nullcount = 0;
        for (Reference ref : refs)
        {
            pw.append("@inproceedings {\n");

            String refkey = ref.getRefkey();
            if (null == refkey)
            {
                refkey = String.format("unset-refkey-%d", nullcount);
                nullcount++;
            }
            pw.format("\t%s,\n", refkey);

            addif("Author", ref.getAuthor(), pw);
            addif("Editor", ref.getEditor(), pw);
            addif("Title", ref.getTitle(), pw);
            addif("Booktitle", ref.getBooktitle(), pw);
            addif("Pages", ref.getPages(), pw);
            addif("Volume", ref.getVolume(), pw);
            addif("Number", ref.getNumber(), pw);
            addif("Series", ref.getSeries(), pw);
            addif("Publisher", ref.getPublisher(), pw);
            addif("Address", ref.getAddress(), pw);
            addif("Year", ref.getYear(), pw);
            addif("Month", ref.getMonth(), pw);
            addif("Organization", ref.getOrganization(), pw);
            addif("Note", ref.getNote(), pw);
            addif("Key", ref.getKey(), pw);

            pw.append("}\n");
        }

        pw.flush();
        os.flush();
    }
}
