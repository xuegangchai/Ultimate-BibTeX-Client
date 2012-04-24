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
     * @param entry
     * @param key
     * @return 
     */
    private String stringValue(BibTeXEntry entry, Key key)
    {
		String retval = null;
        Value val = entry.getField(key);
		if(null != val)
			retval = val.toUserString();
        return retval;
    }
    
    /**
     * 
     * @param entry
     * @param key
     * @return 
     */
    private Integer integerValue(BibTeXEntry entry, Key key)
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
        for (Map.Entry<Key, BibTeXEntry> entry : entries.entrySet())
        {
            Reference ref = new Reference();
            ref.setRefkey(entry.getKey().getValue());

            BibTeXEntry bibEntry = entry.getValue();
            ref.setAddress(stringValue(bibEntry, BibTeXEntry.KEY_ADDRESS));
            ref.setAuthor(stringValue(bibEntry, BibTeXEntry.KEY_AUTHOR));
            ref.setBooktitle(stringValue(bibEntry, BibTeXEntry.KEY_BOOKTITLE));
            ref.setEditor(stringValue(bibEntry, BibTeXEntry.KEY_EDITOR));
            ref.setKey(stringValue(bibEntry, BibTeXEntry.KEY_KEY));
            ref.setMonth(integerValue(bibEntry, BibTeXEntry.KEY_MONTH));
            ref.setNote(stringValue(bibEntry, BibTeXEntry.KEY_NOTE));
            ref.setNumber(stringValue(bibEntry, BibTeXEntry.KEY_NUMBER));
            ref.setOrganization(stringValue(bibEntry, BibTeXEntry.KEY_ORGANIZATION));
            ref.setPages(stringValue(bibEntry, BibTeXEntry.KEY_PAGES));
            ref.setPublisher(stringValue(bibEntry, BibTeXEntry.KEY_PUBLISHER));
            ref.setSeries(stringValue(bibEntry, BibTeXEntry.KEY_SERIES));
            ref.setTitle(stringValue(bibEntry, BibTeXEntry.KEY_TITLE));
            ref.setVolume(stringValue(bibEntry, BibTeXEntry.KEY_VOLUME));
            ref.setYear(integerValue(bibEntry, BibTeXEntry.KEY_YEAR));
            
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