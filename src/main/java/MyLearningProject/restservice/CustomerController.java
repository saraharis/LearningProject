package MyLearningProject.restservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import sun.security.provider.SecureRandom;

//import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.security.*;

@Controller
public class CustomerController {

    /*SecureRandom r = new SecureRandom();
    byte[] salt = new byte[16];
    r.nextBytes(salt);
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(salt);*/

    private static int workload = 12;

    public static boolean loggedIn = false;
    //public static boolean loggedIn = true;


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



    @GetMapping("/")
    public String viewFirstPage(Model model) {
        //model.addAttribute("customers", customerRepository.findAll());
        loggedIn = false;
        //loggedIn = true;
        return "login";
    }

    @PostMapping("/loginPage")
    public String loginPage(Model model, @ModelAttribute("firstName") String firstName, @ModelAttribute("password") String password) throws ExecutionException, NoSuchAlgorithmException {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        List<Customer> currentUsernameList = customerRepository.findByFirstName(firstName);

        if ((firstName.isEmpty()) || (password.isEmpty())){
            System.out.println("incomplete name or password");
            return "login";
        }


        if (currentUsernameList.isEmpty()){
            System.out.println("user not found");
            return "sign_up_page";
        }


        for( Customer user : currentUsernameList ){

            if (password.equals(user.getPassword())){
                loggedIn = true;
                return "chat";
            }


            String pwdStored = user.getPwd();


            if(null == pwdStored || !pwdStored.startsWith("$2a$"))
                throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

            if (BCrypt.checkpw(password, pwdStored)){
                loggedIn = true;
                return "chat";
            }





        }

        /*System.out.println("found in repo" + customerRepository.findByFirstName(firstName));
        System.out.println(firstName);
        System.out.println(password);
        System.out.println(" incorrect password entered for user " + firstName);*/

        return "login";
    }

    @GetMapping("/chat")
    public String viewHome(Model model) {

        if (loggedIn){
            System.out.println(loggedIn);
            return "chat";
        }

        return "login";
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



        System.out.println(" New user signer in " + firstName + password);

        if ((firstName.isEmpty()) || (password.isEmpty())){
            System.out.println("incomplete name or password");
            return "sign_up_page";
        }


        ///// hashing ////
        /*SecureRandom r = new SecureRandom();
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        customer.setPwd(md.digest(password.getBytes(StandardCharsets.UTF_8)));*/

        ///**************************************///////

        String salt = BCrypt.gensalt(workload);
        String pwd = BCrypt.hashpw(password, salt);
        customer.setPwd(pwd);


        //save customer to database
        loggedIn = true;
        customerRepository.save(customer);
        //return "redirect:/";
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





}