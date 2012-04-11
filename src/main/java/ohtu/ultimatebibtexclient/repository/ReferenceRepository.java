/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.repository;


import ohtu.ultimatebibtexclient.domain.Reference;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 *
 * @author tsnorri
 */
public interface ReferenceRepository extends JpaRepository<Reference, Integer>, ReferenceRepositoryCustom
{
}