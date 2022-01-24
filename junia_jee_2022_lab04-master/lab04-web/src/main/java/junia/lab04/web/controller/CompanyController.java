package junia.lab04.web.controller;

import junia.lab04.core.entity.Company;
import junia.lab04.core.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CompanyController {

    public CompanyService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String[] getListOfCompanies(ModelMap modelMap){

        //returns list object
        List<Company> companyList = service.findAllWithProjects();

        // TO DO : iterate loaded data and append to map
        //modelMap.put("key", "value");

        return new String[0];
    }
}
