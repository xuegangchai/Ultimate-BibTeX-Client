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
import javax.validation.constraints.*;


@Entity
public class Reference implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /*
     * Viiteavaimen validointi:
     * NotNull() - Pakollinen kenttä
     * Pituus 3-15
     * Sallitaan ainostaan suomalaiset aakkoskirjaimet sekä numerot
     */
    @NotNull()
    @Size(min=3, max=15)
    @Pattern(regexp="^[a-zA-Z0-9åäöÅÄÖ]+$", message="NO special characters allowed, including spaces.")
    private String refkey;			// Shorthand for accessing this reference from LaTeX.
    private String type;              //The type of the recerence (article, book, inproceedings)
    /*
     * Inproceedings fields from Wikipedia, http://en.wikipedia.org/wiki/BibTeX
     *
     */
    
    /**
     * (scope: article, inproceedings) in book: 'author/editor'. 
     * The name(s) of the author(s) (in the case of more than one author, separated by and)
     */
    private String author;	 
    
    /**
     * (scope: inproceedings)
     * The name(s) of the editor(s)
     */
    private String editor;	
    
    /**
     * (scope: article, book, inproceedings)
     * The title of the work
     */
    private String title;	
    
    /**
     * (scope: inproceedings)
     * The title of the book, if only part of it is being cited
     */
    private String booktitle;
    
    /**
     * (scope: article, inproceedings)
     * Page numbers, separated either by commas or double-hyphens.
     */
    private String pages;
    
    /**
     * (scope: article) in book and inproceedings: 'volume/number'
     * The volume of a journal or multi-volume book
     */
    private String volume;
    
    /**
     * (scope: article)
     * The "(issue) number" of a journal, magazine, or tech-report, 
     * if applicable. (Most publications have a "volume", but no "number" field.)
     */
    private String number;
    
    /**
     * (scope: book, inproceedings)
     * The series of books the book was published in (e.g. "The Hardy Boys" or "Lecture Notes in Computer Science")
     */
    private String series;
    
    /**
     * (scope: book, inproceedings)
     * The publisher's name
     */
    private String publisher;
    
    /**
     * (scope: book, inproceedings)
     * publisher's address or city
     */
    private String address;
    
    /**
     * (scope: article, book, inproceedings)
     * The year of publication (or, if unpublished, the year of creation)
     */
    private Integer year; 
    @Min(1)
    @Max(12)
    
    /**
     * (scope: article, book, inproceedings)
     * The month of publication (or, if unpublished, the month of creation)
     */
    private Integer month;
    
    /**
     * (scope: inproceedings)
     * The conference sponsor
     */
    private String organization;
    
    /**
     * (scope: article)
     */
    private String journal;
    
    /**
     * (scope: book)
     */
    private String edition;
    
    /**
     * (scope: article, book, inproceedings)
     * Miscellaneous extra information
     */
    private String note;
    
    /**
     * (scope: article, book, inproceedings)
     * A hidden field used for specifying or overriding the alphabetical order of entries 
     * (when the "author" and "editor" fields are missing). Note that this is very different 
     * from the key (mentioned just after this list) that is used to cite or cross-reference the entry.
     */
    private String key;  
    
    /**
     * (scope: article, book, inproceedings)
     * tags that user can use sor sorting references
     */
    private String tags;     

    public Reference()
    {
    }


    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getRefkey()
    {
        return refkey;
    }


    public void setType(String type)
    {
        this.type = type;
    }


    public String getType()
    {
        return type;
    }


   
    public void setTags(String tags)
    {
        this.tags = tags;
    }


    public String getTags()
    {
        return tags;
    }


    public void setRefkey(String refkey)
    {
        this.refkey = refkey;
    }


    public String getAddress()
    {
        return address;
    }


    public void setAddress(String address)
    {
        this.address = address;
    }


    public String getAuthor()
    {
        return author;
    }


    public void setAuthor(String author)
    {
        this.author = author;
    }


    public String getBooktitle()
    {
        return booktitle;
    }


    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }


    public String getEditor()
    {
        return editor;
    }


    public void setEditor(String editor)
    {
        this.editor = editor;
    }


    public String getKey()
    {
        return key;
    }


    public void setKey(String key)
    {
        this.key = key;
    }


    public Integer getMonth()
    {
        return month;
    }


    public void setMonth(Integer month)
    {
        this.month = month;
    }


    public String getNote()
    {
        return note;
    }


    public void setNote(String note)
    {
        this.note = note;
    }


    public String getNumber()
    {
        return number;
    }


    public void setNumber(String number)
    {
        this.number = number;
    }


    public String getOrganization()
    {
        return organization;
    }


    public void setOrganization(String organization)
    {
        this.organization = organization;
    }


    public String getPages()
    {
        return pages;
    }


    public void setPages(String pages)
    {
        this.pages = pages;
    }


    public String getPublisher()
    {
        return publisher;
    }


    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }


    public String getSeries()
    {
        return series;
    }


    public void setSeries(String series)
    {
        this.series = series;
    }


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getVolume()
    {
        return volume;
    }


    public void setVolume(String volume)
    {
        this.volume = volume;
    }


    public Integer getYear()
    {
        return year;
    }


    public void setYear(Integer year)
    {
        this.year = year;
    }


    public String getEdition()
    {
        return edition;
    }


    public String getJournal()
    {
        return journal;
    }


    public void setEdition(String edition)
    {
        this.edition = edition;
    }


    public void setJournal(String journal)
    {
        this.journal = journal;
    }
    
    /**
     * Method tests if the minimum of the required fields are used in this reference
     * It does not validate any other fields
     * @return true, if the required fields of a type are set, false, if not
     */
    public boolean isValidReference(){
        
        if(this.type.equals("article")){
            if(this.author == null) return false;
            if(this.title == null) return false;
            if(this.journal == null) return false;
            if(this.year == null) return false;
            return true;
        }
        if(this.type.equals("book")){
            if(this.author == null) return false;
            if(this.title == null) return false;
            if(this.publisher == null) return false;
            if(this.year == null) return false;
            return true;
        }
        if(this.type.equals("inproceedings")){
            if(this.author == null) return false;
            if(this.title == null) return false;
            if(this.booktitle == null) return false;
            if(this.year == null) return false;
            return true;
        }
        return false;
    }
}