package junia.lab04.web.controller;

import junia.lab04.core.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyController {

    public CompanyService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String[] getListOfCompanies(ModelMap modelMap){

        service.findAllWithProjects();

        return new String[0];
    }
}
