/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.domain;

/**
 *
 * @author chai
 */
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	// Inproceedings fields from Wikipedia, http://en.wikipedia.org/wiki/BibTeX
	private String author;			// The name(s) of the author(s) (in the case of more than one author, separated by and)
	private String editor;			// The name(s) of the editor(s)
	private String title;			// The title of the work
	private String booktitle;		// The title of the book, if only part of it is being cited
	private String pages;			// Page numbers, separated either by commas or double-hyphens.
	private String volume;			// The volume of a journal or multi-volume book
	private String number;			// The "(issue) number" of a journal, magazine, or tech-report, if applicable. (Most publications have a "volume", but no "number" field.)
	private String series;			// The series of books the book was published in (e.g. "The Hardy Boys" or "Lecture Notes in Computer Science")
		
	private String publisher;		// The publisher's name
	private String address;			// publisher's address or city
	private Integer year;			// The year of publication (or, if unpublished, the year of creation)
	private Integer month;			// The month of publication (or, if unpublished, the month of creation)

	private String organization;	// The conference sponsor
	private String note;			// Miscellaneous extra information

	private String key;				// A hidden field used for specifying or overriding the alphabetical order of entries (when the "author" and "editor" fields are missing). Note that this is very different from the key (mentioned just after this list) that is used to cite or cross-reference the entry.


	public Reference ()
	{
	}


	public Integer getId ()
	{
		return id;
	}


	public void setId (Integer id)
	{
		this.id = id;
	}


	public String getAddress ()
	{
		return address;
	}


	public void setAddress (String address)
	{
		this.address = address;
	}


	public String getAuthor ()
	{
		return author;
	}


	public void setAuthor (String author)
	{
		this.author = author;
	}


	public String getBooktitle ()
	{
		return booktitle;
	}


	public void setBooktitle (String booktitle)
	{
		this.booktitle = booktitle;
	}


	public String getEditor ()
	{
		return editor;
	}


	public void setEditor (String editor)
	{
		this.editor = editor;
	}


	public String getKey ()
	{
		return key;
	}


	public void setKey (String key)
	{
		this.key = key;
	}


	public Integer getMonth ()
	{
		return month;
	}


	public void setMonth (Integer month)
	{
		this.month = month;
	}


	public String getNote ()
	{
		return note;
	}


	public void setNote (String note)
	{
		this.note = note;
	}


	public String getNumber ()
	{
		return number;
	}


	public void setNumber (String number)
	{
		this.number = number;
	}


	public String getOrganization ()
	{
		return organization;
	}


	public void setOrganization (String organization)
	{
		this.organization = organization;
	}


	public String getPages ()
	{
		return pages;
	}


	public void setPages (String pages)
	{
		this.pages = pages;
	}


	public String getPublisher ()
	{
		return publisher;
	}


	public void setPublisher (String publisher)
	{
		this.publisher = publisher;
	}


	public String getSeries ()
	{
		return series;
	}


	public void setSeries (String series)
	{
		this.series = series;
	}


	public String getTitle ()
	{
		return title;
	}


	public void setTitle (String title)
	{
		this.title = title;
	}


	public String getVolume ()
	{
		return volume;
	}


	public void setVolume (String volume)
	{
		this.volume = volume;
	}


	public Integer getYear ()
	{
		return year;
	}


	public void setYear (Integer year)
	{
		this.year = year;
	}
}
