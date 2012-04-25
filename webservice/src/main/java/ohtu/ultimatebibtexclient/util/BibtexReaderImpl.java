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
    /**
     *
     */
    public BibtexReaderImpl()
    {
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
            BibtexEntryMapping spec = BibtexEntryMapping.mappingForEntry(entry);
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
