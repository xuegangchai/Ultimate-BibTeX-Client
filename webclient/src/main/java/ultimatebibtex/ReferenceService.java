import java.util.Collection;


public interface ReferenceService
{
    Reference create ();                    // Create a reference.
    Collection<Reference> fetch ();         // Fetch all stored references.
    void modify (Reference ref);            // Save changes to a previously created reference.
}
