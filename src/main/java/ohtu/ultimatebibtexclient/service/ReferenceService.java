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
     * Fetch a reference by ID.
     */
    Reference fetchByID(Integer id);

    /**
     * Fetch all stored references.
     */
    Collection<Reference> fetch();

    /**
     * Save changes to a previously created reference.
     */
    Reference modify(Reference ref);

    /**
     * Delete a reference.
     */
    void delete(Reference ref);

    /**
     * Get number of references.
     */
    long count();
}