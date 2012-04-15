/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.repository;


import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;



/**
 *
 * @author tsnorri
 */
public interface ReferenceRepositoryCustom
{
    public Collection<Reference> findByKeywords (Collection<String> keyword);
}