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
import java.util.List;
import java.util.Scanner;
import ohtu.ultimatebibtexclient.domain.Reference;



/**
 *
 * @author tsnorri
 */
public class BibtexWriterImpl implements BibtexWriter
{
	private String escape (String val)
	{
		String retval = val;
		if (val.matches (".*[{}\"],*"))
		{
			StringBuilder sb = new StringBuilder ();
			final CharacterIterator it = new StringCharacterIterator (val);
			for (char c = it.first (); c != CharacterIterator.DONE; c = it.next ())
			{
				switch (c)
				{
					case '{':
						sb.append ("\\{");
						break;
					case '}':
						sb.append ("\\}");
						break;
					case '"':
						sb.append ("{\"}");
						break;
					default:
						sb.append (c);
						break;
				}
			}
			retval = sb.toString ();
		}
		return retval;
	}
	
	
	private void addif (String key, Object val, PrintWriter pf)
	{
		if (null != val)
		{
			String str = val.toString ();
			pf.format ("\t%s = \"%s\",\n", key, escape (str));
		}
	}
	
	
	@Override
	public void write (List<Reference> refs, OutputStream stream) throws IOException
	{
		OutputStreamWriter os = new OutputStreamWriter (stream, "UTF-8");
		PrintWriter pw = new PrintWriter (os);
		
		pw.append ("@comment { Automatically generated with Ultimate BibTeX client. }\n\n");
		
		for (Reference ref : refs)
		{
			pw.append ("@inproceedings {\n");
			pw.format ("\t%s,\n", ref.getRefkey ());
			
			addif ("Author",		ref.getAuthor (),		pw);
			addif ("Editor",		ref.getEditor (),		pw);
			addif ("Title",			ref.getTitle (),		pw);
			addif ("Booktitle",		ref.getBooktitle (),	pw);
			addif ("Pages",			ref.getPages (),		pw);
			addif ("Volume",		ref.getVolume (),		pw);
			addif ("Number",		ref.getNumber (),		pw);
			addif ("Series",		ref.getSeries (),		pw);
			addif ("Publisher",		ref.getPublisher (),	pw);
			addif ("Address",		ref.getAddress (),		pw);
			addif ("Year",			ref.getYear (),			pw);
			addif ("Month",			ref.getMonth (),		pw);
			addif ("Organization",	ref.getOrganization (),	pw);
			addif ("Note",			ref.getNote (),			pw);
			addif ("Key",			ref.getKey (),			pw);
			
			pw.append ("}\n");
		}
	}
}
