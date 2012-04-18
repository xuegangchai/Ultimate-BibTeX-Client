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
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.service.ReferenceService;
import ohtu.ultimatebibtexclient.util.BibtexWriter;
import ohtu.ultimatebibtexclient.util.BibtexWriterImpl;
import ohtu.ultimatebibtexclient.util.ReferenceValueAssigner;
import ohtu.ultimatebibtexclient.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@org.springframework.stereotype.Controller
public class Controller
{
    @Autowired
    private ReferenceService referenceService;
    @Autowired
    private ReferenceValueAssigner valueAssigner;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listReferences(Model model)
    {
        model.addAttribute("references", referenceService.fetch());
        return "list";
    }


    @RequestMapping(value = "create.html", method = RequestMethod.GET)
    public String showCreationForm(Model model)
    {
        model.addAttribute("title", "Luo uusi viite");
        model.addAttribute("action", "reference");
        return "modify";
    }


    @Transactional
    @RequestMapping(value = "reference", method = RequestMethod.POST)
    public String createReference(HttpServletRequest req) throws IllegalAccessException, InvocationTargetException
    {
        Reference ref = new Reference();
        valueAssigner.assignReferenceVariables(req, ref);
        referenceService.modify(ref);
        return "redirect:/";
    }


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
        model.addAttribute("ref", ref);
        return "modify";
    }


    @Transactional
    @RequestMapping(value = "reference/{refID}", method = RequestMethod.POST)
    public String modifyReference(HttpServletRequest req, @PathVariable int refID) throws IllegalAccessException, InvocationTargetException
    {
        Reference ref = referenceService.fetchByID(refID);
        if (null == ref)
        {
            throw new ResourceNotFoundException();
        }
        valueAssigner.assignReferenceVariables(req, ref);
        referenceService.modify(ref);
        return "redirect:/";
    }
    
    
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchReferences(Model model, @RequestParam String keywords) throws IllegalAccessException, InvocationTargetException
    {
        String content = keywords;
        String[] kwArray = content.split("\\s+");
        Collection<Reference> refs = referenceService.findByKeywords(Arrays.asList(kwArray));
        model.addAttribute("references", refs);
        return "list";
    }


    @RequestMapping(value = "bibtex", method = RequestMethod.GET)
    public void fetchReferencesAsBibtex(HttpServletResponse response) throws IOException
    {
        BibtexWriter writer = new BibtexWriterImpl();
        Collection<Reference> refs = referenceService.fetch();
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader("Content-Type", "text/x-bibtex");
        writer.write(refs, outputStream);
    }
}