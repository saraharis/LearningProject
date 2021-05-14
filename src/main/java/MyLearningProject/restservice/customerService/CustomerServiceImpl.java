package MyLearningProject.restservice.customerService;

import MyLearningProject.restservice.CustomerRepository;
import MyLearningProject.restservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer){
        customerRepository.save(customer);

    }
}
