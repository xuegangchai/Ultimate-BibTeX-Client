/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.service;

import java.util.Arrays;
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
public class ReferenceServiceImpl implements ReferenceService {
    
    @Autowired
    ReferenceRepository referenceRepository;
    
    /**
     * 
     */
    public ReferenceServiceImpl() {
    }

    /**
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Reference> fetch() {
        return referenceRepository.findAll();
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public Reference fetchByID(Integer id) {
        return referenceRepository.findOne(id);
    }

    /**
     * 
     * @param ref
     * @return 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Reference modify(Reference ref) {
        return referenceRepository.save(ref);
    }

    /**
     * 
     * @param ref 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Reference ref) {
        referenceRepository.delete(ref);
    }

    /**
     * 
     * @return 
     */
    @Override
    public long count() {
        return referenceRepository.count();
    }

    /**
     * 
     * @param keywords
     * @return 
     */
    @Override
    public Collection<Reference> findByKeywords(Collection<String> keywords)
    {
        // The fields to be used when matching.
        String[] fields =
        {
            "refkey",
            "author",
            "editor",
            "title",
            "booktitle",
            // pages
            // volume
            // number
            // series
            "publisher",
            "address",
            // year
            // month
            "organization",
            "note",
            // key
            "tags",
        };

        return referenceRepository.findByKeywords(Arrays.asList(fields), keywords);
    }
}
