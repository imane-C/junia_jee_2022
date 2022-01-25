package junia.lab04.web.controller;

import junia.lab04.core.dao.CompanyDAO;
import junia.lab04.core.entity.Company;
import junia.lab04.core.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String getHome(){
        return "index";
    }

    @GetMapping(value = "/list")
    public String getListOfCompanies(ModelMap modelMap){
        List<Company> companiesList = service.findAllWithProjects();
        modelMap.put("companies", companiesList);

        return "companiesList";
    }

    @GetMapping(value = "{companyId}/delete")
    public String removeCompany(@PathVariable long companyId){
        service.deleteById(companyId);

        return "redirect:../list";
    }

    @GetMapping(value = "/form")
    public String getForm(ModelMap modelMap){
        Company company = new Company();
        modelMap.put("company", company);

        return "companyForm";
    }

    @PostMapping(value = "/form")
    public String submitForm(@ModelAttribute("company") Company company){
        service.save(company);

        return "redirect:list";
    }
}
