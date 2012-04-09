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
    private String name;


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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
