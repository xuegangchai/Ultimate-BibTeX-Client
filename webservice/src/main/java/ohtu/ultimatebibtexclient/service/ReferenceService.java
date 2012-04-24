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
public interface ReferenceService {

    
    /**
     * Fetch a reference by ID
     * @param id
     * @return a Refrence matching the given ID
     */
    Reference fetchByID(Integer id);

    
    /**
     * Fetch all stored references.
     * @return all stored references
     */
    Collection<Reference> fetch();

    
    /**
     * Save changes to a previously created reference.
     * @param ref
     * @return the modified reference
     */
    Reference modify(Reference ref);

    
    /**
     * Delete a reference.
     * @param ref - the reference to delete
     */
    void delete(Reference ref);

    /**
     * Get number of references.
     * @return number of references
     */
    long count();
    
    
    /**
     * Find the references that match the given keywords.
     * @param keywords
     * @return a collection of references that have the given keywords in any of the fields
     */
    Collection<Reference> findByKeywords(Collection<String> keywords);

}
