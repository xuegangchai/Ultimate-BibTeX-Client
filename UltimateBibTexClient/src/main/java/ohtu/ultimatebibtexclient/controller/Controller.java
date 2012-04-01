/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ultimatebibtexclient.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Controller {
    
    @RequestMapping (value = "vihaiset", method = "GET")
    public String listReferences (){
        
    }


    @RequestMapping (value = "vihaiset/create.html", method = "GET")
    public String showCreationForm (){
    }


    @RequestMapping (value = "vihaiset/reference", method = "POST")
    public String createReference (){
    }


    @RequestMapping (value = "vihaiset/reference/{refID}", method = "GET")
    public String showModificationForm (@PathVariable int refID){
    }


    @RequestMapping (value = "vihaiset/reference/{refID}", method = "POST")
    public String modifyReference (@PathVariable int refID){
    }
}