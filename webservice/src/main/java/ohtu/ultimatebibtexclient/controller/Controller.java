/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.controller;


/**
 *
 * @author chai
 */
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.service.ReferenceService;
import ohtu.ultimatebibtexclient.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@org.springframework.stereotype.Controller
public class Controller
{
    @Autowired
    private ReferenceService referenceService;


    /**
     *
     * @param model
     * @return reference to list.jps -page
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listReferences(Model model)
    {
        model.addAttribute("references", referenceService.fetch());
        model.addAttribute("searchword", "");
        return "list";
    }


    /**
     *
     * @param model
     * @return reference to modify.jsp -page
     */
    @RequestMapping(value = "create.html", method = RequestMethod.GET)
    public String showCreationForm(Model model)
    {
        model.addAttribute("title", "Luo uusi viite");
        model.addAttribute("action", "reference");
        model.addAttribute("button", "lisää");
        model.addAttribute("reference", new Reference());
        return "modify";
    }


    /**
     *
     * @param model
     * @param ref
     * @param res
     * @return a reference to front page or - in case of an error - back to modify.jsp-page
     */
    @RequestMapping(value = "reference", method = RequestMethod.POST)
    public String createReference(Model model, @Valid @ModelAttribute("reference") Reference ref, BindingResult res)
    {
        String retval = "redirect:/";
        if (res.hasErrors())
        {
            // model.addAttribute("ref", ref);
            // model.addAttribute("errorFormatter", new ErrorFormatter(res));
            model.addAttribute("title", "Luo uusi viite");
            model.addAttribute("action", "reference");
            model.addAttribute("button", "lisää");
            retval = "modify";
        }
        else
        {
            // Zero automatically filled fields.
            ref.setId(null);

            referenceService.modify(ref);
        }
        return retval;
    }


    /**
     *
     * @param model
     * @param refID
     * @return reference to modify.jsp-page
     */
    @RequestMapping(value = "reference/{refID}", method = RequestMethod.GET)
    public String showModificationForm(Model model, @PathVariable Integer refID)
    {
        Reference ref = referenceService.fetchByID(refID);
        if (null == ref)
        {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("title", "Muokkaa viitettä");
        model.addAttribute("action", String.format("reference/%d", refID));
        //model.addAttribute("ref", ref);
        model.addAttribute("button", "tallenna");
        model.addAttribute("reference", ref);
        return "modify";
    }


    /**
     *
     * @param model
     * @param refID
     * @param ref
     * @param res
     * @return a reference to front page or - in case of an error - back to modify.jsp-page
     */
    @RequestMapping(value = "reference/{refID}", method = RequestMethod.POST)
    public String modifyReference(Model model, @PathVariable int refID, @Valid @ModelAttribute("reference") Reference ref, BindingResult res)
    {
        String retval = "redirect:/";

        if (null == referenceService.fetchByID(refID))
        {
            throw new ResourceNotFoundException();
        }

        if (res.hasErrors())
        {
            //model.addAttribute("ref", ref);
            model.addAttribute("errorFormatter", new ErrorFormatter(res));
            model.addAttribute("title", "Muokkaa viitettä");
            model.addAttribute("action", String.format("reference/%d", refID));
            model.addAttribute("button", "tallenna");
            retval = "modify";
        }
        else
        {
            ref.setId(refID);
            referenceService.modify(ref);
        }

        return retval;
    }


    /**
     *
     * @param refID
     * @return to front page
     */
    @RequestMapping(value = "delete/{refID}")
    public String deleteReference(Model model, @PathVariable int refID, @Valid @ModelAttribute("reference") Reference ref)
    {
        if (null == referenceService.fetchByID(refID))
        {
            throw new ResourceNotFoundException();
        }
        ref.setId(refID);
        referenceService.delete(ref);
        return "redirect:/";
    }


    /**
     *
     * @param model
     * @param keywords
     * @return a reference to list.jsp-page
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchReferences(Model model, @RequestParam String keywords)
    {
        String content = keywords;
        String[] kwArray = content.split("\\s+");
        Collection<Reference> refs = referenceService.findByKeywords(Arrays.asList(kwArray));
        model.addAttribute("references", refs);
        model.addAttribute("searchword", content);
        return "list";
    }


    /**
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "bibtex", method = RequestMethod.GET)
    public void fetchReferencesAsBibtex(HttpServletResponse response) throws IOException
    {
        BibtexWriter writer = new BibtexWriterImpl();
        Collection<Reference> refs = referenceService.fetch();
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader("Content-Type", "text/x-bibtex");
        writer.write(refs, outputStream);
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "read-bibtex", method = RequestMethod.OPTIONS)
    public ResponseEntity addBibtexOptions()
    {
        MediaType[] mediaTypes =
        {
            new MediaType("text", "x-bibtex")
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setAllow(EnumSet.of(HttpMethod.POST));
        headers.set("Access-Control-Allow-Origin", "http://dl.acm.org");
        headers.set("Access-Control-Allow-Methods", "POST");
        headers.set("Access-Control-Allow-Headers", "Content-Type");
        headers.setAccept(Arrays.asList(mediaTypes));
        return new ResponseEntity(headers, HttpStatus.OK);
    }


    /**
     *
     * @param content
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "read-bibtex", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addBibtex(@RequestBody String content) throws Throwable
    {
        Reader reader = new StringReader(content);
        BibtexReader bibReader = new BibtexReaderImpl();
        Collection<Reference> refs = bibReader.read(reader);
        for (Reference ref : refs)
        {
            try
            {
                referenceService.modify(ref);
            }
            catch (ConstraintViolationException exc)
            {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("text", "plain"));
                
                StringBuilder responseVal = new StringBuilder();
                responseVal.append("Constraint violations:");
                
                Set<ConstraintViolation<?>> constraintViolations = exc.getConstraintViolations();
                for (ConstraintViolation<?> violation : constraintViolations)
                {
                    responseVal.append(String.format("\n%s: %s. Got '%s'.",
                                                     violation.getPropertyPath().toString(),
                                                     violation.getMessage(),
                                                     violation.getInvalidValue()));
                }
                
                return new ResponseEntity(responseVal.toString(), headers, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
}
