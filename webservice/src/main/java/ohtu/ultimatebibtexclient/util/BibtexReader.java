/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.io.Reader;
import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;



/**
 *
 * @author tsnorri
 */
public interface BibtexReader
{
    /**
     * 
     * @param reader
     * @return
     * @throws Throwable 
     */
    public Collection<Reference> read(Reader reader) throws Throwable;
}
