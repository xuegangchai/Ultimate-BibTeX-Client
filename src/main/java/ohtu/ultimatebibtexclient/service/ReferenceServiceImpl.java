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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author tsnorri
 */
@Service
public class ReferenceServiceImpl implements ReferenceService
{
	@Autowired ReferenceRepository referenceRepository;
	
	
	public ReferenceServiceImpl ()
	{
	}
	
	
	@Override
    @Transactional (readOnly = true)
	public Collection<Reference> fetch ()
	{
		return referenceRepository.findAll ();
	}


	@Override
	@Transactional (readOnly = true)
	public Reference fetchByID (Integer id)
	{
		return referenceRepository.findOne (id);
	}


	@Override
	@Transactional (propagation = Propagation.REQUIRED)
	public Reference modify (Reference ref)
	{
		return referenceRepository.save (ref);
	}


	@Override
	@Transactional (propagation = Propagation.REQUIRED)
	public void delete (Reference ref)
	{
		referenceRepository.delete (ref);
	}


	@Override
	public long count ()
	{
		return referenceRepository.count ();
	}
}