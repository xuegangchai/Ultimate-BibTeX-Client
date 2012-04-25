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
public class BibtexWriterImpl implements BibtexWriter, BibtexEscaping
{
    /**
     *
     * @param val
     * @return
     */
    @Override
    public String escape(String val)
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
            BibtexEntryMapping spec = BibtexEntryMapping.mappingForReference(ref);
            if (null == ref)
            {
                StringValue commentVal = new StringValue(String.format("Entry with title '%s' not written; type unknown.", ref.getTitle()),
                                                         StringValue.Style.BRACED);
                BibTeXComment comment = new BibTeXComment(commentVal);
                db.addObject(comment);
            }
            else
            {
                BibTeXEntry entry = spec.createEntry(ref, printer, this);
                db.addObject(entry);
            }
        }

        OutputStreamWriter writer = new OutputStreamWriter(stream);
        BibTeXFormatter formatter = new BibTeXFormatter();
        formatter.format(db, writer);
        writer.flush();
    }
}
