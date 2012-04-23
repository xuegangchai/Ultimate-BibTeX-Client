/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.repository;


import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import ohtu.ultimatebibtexclient.domain.Reference;
import org.springframework.stereotype.Repository;


/**
 *
 * @author tsnorri
 */
@Repository
public class ReferenceRepositoryImpl implements ReferenceRepositoryCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 
     * @param val
     * @return String returnvalue
     */
    private String escape(String val)
    {
        String retval = val;
        if (val.matches(".*[%_].*"))
        {
            StringBuilder sb = new StringBuilder();
            final CharacterIterator it = new StringCharacterIterator(val);
            for (char c = it.first(); c != CharacterIterator.DONE; c = it.next())
            {
                switch (c)
                {
                    case '_':
                    case '%':
                        sb.append('\\');
                        sb.append(c);
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
            retval = sb.toString();
        }
        return retval;
    }

    /**
     * 
     * @param fields
     * @param keywords
     * @return a collection of references based on the given keywords and reference fields
     */
    @Override
    public Collection<Reference> findByKeywords(Collection<String> fields, Collection<String> keywords)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reference> cq = cb.createQuery(Reference.class);

        // select from Reference
        Root<Reference> from = cq.from(Reference.class);
        CriteriaQuery<Reference> select = cq.select(from);

        // Iterate the given fields and create a set of predicates like this one:
        // (refkey like keyword1 or author like keyword1 or ...) or (refkey like keyword2 or author like keyword2 or ...)
        // Also surround the keywords with wildcard characters, namely %, and escape any underscores and percent signs
        // in each keyword.
        Predicate[] predicates = new Predicate[keywords.size()];
        int i = 0;
        for (String keyword : keywords)
        {
            Predicate[] subpredicates = new Predicate[fields.size()];
            int j = 0;
            for (String field : fields)
            {
                Expression<String> lhs;
                Expression<String> rhs;
                
                {
                    Expression<String> fieldExp = from.get(field);
                    lhs = cb.lower(fieldExp);
                }
                
                {
                    String escaped = String.format("%%%s%%", escape(keyword));
                    Expression<String> literal = cb.literal(escaped);
                    rhs = cb.lower(literal);
                }
                
                subpredicates[j] = cb.like(lhs, rhs);
                j++;
            }

            predicates[i] = cb.or(subpredicates);
            i++;
        }

        select.where(cb.or(predicates));
        TypedQuery<Reference> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();
    }
}
