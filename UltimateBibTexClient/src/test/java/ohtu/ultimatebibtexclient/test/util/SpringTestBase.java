package ohtu.ultimatebibtexclient.test.util;


import static org.junit.Assert.assertNotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tsnorri
 */
public class SpringTestBase
{
	protected ApplicationContext applicationContext;
	
	
	public SpringTestBase ()
	{
		applicationContext = new FileSystemXmlApplicationContext ("src/main/webapp/WEB-INF/spring-context.xml");
		assertNotNull (applicationContext);
	}
}
