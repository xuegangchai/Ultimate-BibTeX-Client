/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.controller;

/**
 *
 * @author chai
 */
import ohtu.ultimatebibtexclient.domain.Reference;
import ohtu.ultimatebibtexclient.service.ReferenceService;
import ohtu.ultimatebibtexclient.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ReferenceService referenceService;
	
	
    @RequestMapping(value = "vihaiset", method = RequestMethod.GET)
    public String listReferences(Model model)
	{
		model.addAttribute ("references", referenceService.fetch ());
        return "list";
    }

	
    @RequestMapping(value = "vihaiset/create.html", method = RequestMethod.GET)
    public String showCreationForm(Model model)
	{
		model.addAttribute ("title", "Luo uusi viite");
		model.addAttribute ("action", "vihaiset/reference");
        return "modify";
    }

	
    @RequestMapping(value = "vihaiset/reference", method = RequestMethod.POST)
    public String createReference()
	{
        //ReferenceService.create(); //?? en oo varma
        return "redirect:/vihaiset";
    }

	
    @RequestMapping(value = "vihaiset/reference/{refID}", method = RequestMethod.GET)
    public String showModificationForm(Model model, @PathVariable Integer refID)
	{
		Reference ref = referenceService.fetchByID (refID);
		if (null == ref)
			throw new ResourceNotFoundException ();
		
		model.addAttribute ("title", "Muokkaa viitettä");
		model.addAttribute ("action", String.format ("vihaiset/reference/%d", refID));
		model.addAttribute ("ref", ref);
        return "modify";
    }

	
    @RequestMapping(value = "vihaiset/reference/{refID}", method = RequestMethod.POST)
    public String modifyReference(@PathVariable int refID) {
        //ReferenceService.modify(Reference); //?? en oo varma
        return "redirect:/vihaiset";
    }
}