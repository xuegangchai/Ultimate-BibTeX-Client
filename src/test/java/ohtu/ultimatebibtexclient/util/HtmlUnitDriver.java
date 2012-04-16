/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebConnection;
import com.gargoylesoftware.htmlunit.WebResponse;



/**
 *
 * @author tsnorri
 */
public class HtmlUnitDriver extends org.openqa.selenium.htmlunit.HtmlUnitDriver
{
    public String getMimeType()
    {
        Page lastPage = this.lastPage();
        WebResponse webResponse = lastPage.getWebResponse();
        String contentType = webResponse.getResponseHeaderValue("Content-Type");
        String[] parts = contentType.split(";");
        return parts[0];
    }
}
