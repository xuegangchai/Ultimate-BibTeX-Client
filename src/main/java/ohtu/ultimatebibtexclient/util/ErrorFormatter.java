/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.util;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.util.HtmlUtils;



/**
 *
 * @author tsnorri
 */
public class ErrorFormatter
{
    protected BindingResult bindingResult;
    
    
    public ErrorFormatter(BindingResult res)
    {
        bindingResult = res;
    }
    
    
    public String formatErrors(String field)
    {
        String retval = "";
        List<FieldError> fieldErrors = bindingResult.getFieldErrors(field);
        if (0 < fieldErrors.size())
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.write("<ul class=\"errors\">");
            for (FieldError error : fieldErrors)
            {
                String escaped = HtmlUtils.htmlEscape(error.getDefaultMessage());
                pw.format("<li>%s</li>", escaped);
            }
            pw.write("</ul>");
            pw.flush();
            sw.flush();
            retval = sw.toString();
        }
        return retval;
    }
}
