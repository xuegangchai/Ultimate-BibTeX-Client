/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.service;


import java.util.Collection;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.test.util.SpringTestBase;
import static org.junit.Assert.*;
import org.junit.*;


/**
 *
 * @author tsnorri
 */
public class ReferenceServiceTest extends SpringTestBase
{
	private ReferenceService svc;


	@BeforeClass
	public static void setUpClass () throws Exception
	{
	}


	@AfterClass
	public static void tearDownClass () throws Exception
	{
	}


	@Before
	public void setUp ()
	{
		svc = applicationContext.getBean (ReferenceService.class);
		assertNotNull (svc);
	}


	@After
	public void tearDown ()
	{
		assertEquals (0, svc.count ());
	}


	@Test
	public void testCreate ()
	{
		Reference ref = svc.create ();
		assertNotNull (ref);
		
		assertNotNull (svc.fetchByID (ref.getId ()));
		
		svc.delete (ref);
	}
	
	
	@Test
	public void testFetchAll ()
	{
		Reference ref1 = svc.create ();
		Reference ref2 = svc.create ();
		assertNotNull (ref1);
		assertNotNull (ref2);
		
		Collection<Reference> refs = svc.fetch ();
		assertEquals (2, refs.size ());
		
		svc.delete (ref1);
		svc.delete (ref2);
	}
	
	
	@Test
	public void testModify ()
	{
		Reference ref = svc.create ();
		assertNotNull (ref);
		
		ref.setKey ("test");
		svc.modify (ref);
		assertEquals ("test", svc.fetchByID (ref.getId ()).getKey ());
		
		svc.delete (ref);
	}
}
