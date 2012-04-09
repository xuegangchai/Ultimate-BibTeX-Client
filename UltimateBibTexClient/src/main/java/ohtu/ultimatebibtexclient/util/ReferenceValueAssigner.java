/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import ohtu.ultimatebibtexclient.domain.Reference;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.springframework.stereotype.Component;



/**
 *
 * @author tsnorri
 */
@Component
public class ReferenceValueAssigner
{
	BeanUtilsBean bean;
	
	
	public ReferenceValueAssigner () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
		// We use BeanUtils to set properties by name. However, the default
		// implementation substitutes null with 0 in certain classes, which
		// we don't want.
		
		ConvertUtilsBean convertBean = new ConvertUtilsBean ();
		convertBean.register (false, true, 0);
		this.bean = new BeanUtilsBean (convertBean);
	}
	
	
	public void assignReferenceVariables (HttpServletRequest req, Reference ref) throws IllegalAccessException, InvocationTargetException
	{
		// We require special handling for empty values, hence not using @ModelAttribute.
		// Specifically, if the value is null, we don't set it. If the value is an
		// empty string, we set it to null.
		// Also, this approach may be safer. See http://erratasec.blogspot.com/2012/03/rubygithub-hack-translated.html

		String[] stringKeys =
		{
			"author",
			"editor",
			"title",
			"booktitle",
			"pages",
			"volume",
			"number",
			"series",
			"publisher",
			"address",
			"organization",
			"note",
			"key",
		};

		String[] integerKeys =
		{
			// Integer keys
			"year",
			"month"
		};

		
		for (String key : stringKeys)
		{
			String val = req.getParameter (key);
			if (null != val)
			{
				if (0 == val.length ())
					bean.setProperty (ref, key, null);
				else
					bean.setProperty (ref, key, val);
			}
		}
		
		for (String key : integerKeys)
		{
			String stringVal = req.getParameter (key);
			if (null != stringVal)
			{
				if (0 == stringVal.length ())
					bean.setProperty (ref, key, null);
				else
				{
					Integer val = Integer.valueOf (stringVal);
					bean.setProperty (ref, key, val);
				}
			}
		}
	}
}
