/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import ohtu.ultimatebibtexclient.domain.Reference;



/**
 *
 * @author tsnorri
 */
public interface BibtexWriter
{
	public void write (List<Reference> refs, OutputStream stream) throws IOException;
}
