package MyLearningProject.restservice.customerService;

import MyLearningProject.restservice.models.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    void saveCustomer(Customer customer);

}
