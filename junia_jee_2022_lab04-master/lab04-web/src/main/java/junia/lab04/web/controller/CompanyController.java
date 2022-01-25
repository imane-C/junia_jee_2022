package junia.lab04.web.controller;

import junia.lab04.core.entity.Company;
import junia.lab04.core.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CompanyController {

    public CompanyService service;

    @GetMapping(value = "/list")
    public String getListOfCompanies(ModelMap modelMap){

        //returns list object
        List<Company> companiesList = service.findAllWithProjects();

        // TO DO : iterate loaded data and append to map
        modelMap.put("companies", companiesList);

        return "companiesList";
    }
}
