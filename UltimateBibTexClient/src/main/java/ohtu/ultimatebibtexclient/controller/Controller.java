/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.controller;

/**
 *
 * @author chai
 */
import ohtu.ultimatebibtexclient.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ReferenceService ReferenceService;

    @RequestMapping(value = "vihaiset", method = RequestMethod.GET)
    public String listReferences() {
        // ReferenceService.fetch(); //?? en oo varma
        return "redirect:/vihaiset";
    }

    @RequestMapping(value = "vihaiset/create.html", method = RequestMethod.GET)
    public String showCreationForm() {
        return "";
    }

    @RequestMapping(value = "vihaiset/reference", method = RequestMethod.POST)
    public String createReference() {
        //ReferenceService.create(); //?? en oo varma
        return "redirect:/vihaiset";
    }

    @RequestMapping(value = "vihaiset/reference/{refID}", method = RequestMethod.GET)
    public String showModificationForm(@PathVariable int refID) {
        return "";
    }

    @RequestMapping(value = "vihaiset/reference/{refID}", method = RequestMethod.POST)
    public String modifyReference(@PathVariable int refID) {
        //ReferenceService.modify(Reference); //?? en oo varma
        return "redirect:/vihaiset";
    }
}