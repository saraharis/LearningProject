package MyLearningProject.restservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.security.*;

@Controller
public class CustomerController {


    private static int workload = 12;


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

// **************************************************************************************************************************//



    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        //model.addAttribute("customers", customerRepository.findAll());
        //loggedIn = false;
        //loggedIn = true;
        return "login";
    }

    @GetMapping("/")
    public String viewFirstPage(Model model) {
        return "login";
    }


    @PostMapping("/loginPage")
    public String loginPage(@ModelAttribute("firstName") String firstName, @ModelAttribute("password") String password) throws ExecutionException, NoSuchAlgorithmException {

        Customer customer = new Customer();


        if ((firstName.isEmpty()) || (password.isEmpty())){
            System.out.println("incomplete name or password");
            return "login";
        }

        customer = customerRepository.findByFirstName(firstName);
        if (customer == null){
            System.out.println("user not found");
            return "sign_up_page";
        }

        String pwdStored = customer.getPwd();


        if(null == pwdStored || !pwdStored.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        if (BCrypt.checkpw(password, pwdStored)) {
            return "chat";
        }

        return "login";
    }

    @GetMapping("/chat")
    public String viewHome(Model model) {
        return "chat";
    }

    @RequestMapping("/newUser")
    public String registerUser(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "sign_up_page";

    }

    @RequestMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) throws NoSuchAlgorithmException {

        String firstName = customer.getFirstName();
        String password = customer.getPassword();



        System.out.println(" New user signed up " + firstName + password);

        if ((firstName.isEmpty()) || (password.isEmpty())){
            System.out.println("username and password fields are empty");
            return "sign_up_page";
        }


        String salt = BCrypt.gensalt(workload);
        String pwd = BCrypt.hashpw(password, salt);
        customer.setPwd(pwd);



        try {
            customerRepository.save(customer);

        } catch (Exception e) {
            System.out.println("username already exists");
            return "sign_up_page";

        }

        return "save_customer";


    }



















    /* @RequestMapping("/searchCustomer")
    public String searchCustomer(@ModelAttribute("lastName") String lastName, Model model) throws ExecutionException {
        System.out.println(" searching for person with last name as " + lastName);
        List<Customer> names = customerCache.get(lastName);
        names.stream().forEach(x-> System.out.println(x.firstName));
        model.addAttribute("customers_found", names);
        return "search_result";
    }

    @GetMapping("/CustomerList")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "homepage";

    }

    @GetMapping("/NewCustomerForm")
    public  String NewCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";

    }*/

    ///// hashing ////
        /*SecureRandom r = new SecureRandom();
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        customer.setPwd(md.digest(password.getBytes(StandardCharsets.UTF_8)));*/

    ///**************************************///////





}