/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.service;

import java.lang.ref.Reference;
import java.util.Collection;

/**
 *
 * @author ewk
 */
public interface ReferenceService 
{
    Reference create ();                    // Create a reference.
    Collection<Reference> fetch ();         // Fetch all stored references.
    void modify (Reference ref);            // Save changes to a previously created reference.
}