/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.service;

import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;

/**
 *
 * @author ewk
 */
public interface ReferenceService 
{
	Reference fetchByID (Integer id);		// Fetch a reference by ID.
    Collection<Reference> fetch ();         // Fetch all stored references.
    Reference modify (Reference ref);            // Save changes to a previously created reference.
	void delete (Reference ref);			// Delete a reference.
	long count ();							// Get number of references.
}