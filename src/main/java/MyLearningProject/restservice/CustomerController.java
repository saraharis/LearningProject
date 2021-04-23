package MyLearningProject.restservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    LoadingCache<String, List<Customer>> customerCache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)                             // maximum 100 records can be cached
                    .expireAfterAccess(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                    .build(new CacheLoader<String, List<Customer>>() {  // build the cacheloader

                        @Override
                        public List<Customer> load(String lastName) throws Exception {
                            //make the expensive call
                            System.out.println("Loading from -------------------------- Database");
                            return customerRepository.findByLastName(lastName);
                        }
                    });


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

    @RequestMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        //save customer to database
        customerRepository.save(customer);
        //return "redirect:/";
        return "save_customer";
    }

    @RequestMapping("/searchCustomer")
    public String searchCustomer(@ModelAttribute("lastName") String lastName, Model model) throws ExecutionException {
        List<Customer> names = customerCache.get(lastName);
        names.stream().forEach(x-> System.out.println(x.firstName));
        model.addAttribute("customers_found", names);
        return "search_result";
    }





}