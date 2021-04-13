package MyLearningProject.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "homepage";

    }

    @GetMapping("/NewCustomerForm")
    public  String NewCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";

    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("new_customer") Customer customer){

        //save customer to database
        customerRepository.save(customer);
        return "redirect:/";


    }

}
