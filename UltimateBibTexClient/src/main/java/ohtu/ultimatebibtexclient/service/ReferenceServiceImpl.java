/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.service;


import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author tsnorri
 */
@Service
public class ReferenceServiceImpl implements ReferenceService
{
	@Autowired ReferenceRepository referenceRepository;
	
	
	@Override
    @Transactional
	public Reference create ()
	{
		Reference ref = new Reference ();
		return referenceRepository.save (ref);
	}


	@Override
    @Transactional (readOnly = true)
	public Collection<Reference> fetch ()
	{
		return referenceRepository.findAll ();
	}


	@Override
    @Transactional
	public void modify (Reference ref)
	{
		referenceRepository.save (ref);
	}
}
