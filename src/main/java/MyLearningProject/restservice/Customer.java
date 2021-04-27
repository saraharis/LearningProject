package MyLearningProject.restservice;

import javax.persistence.*;

@Entity
//@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long id;

    //@Column(name = "first_name")
    String firstName;

    //@Column(name = "lastName")
    String lastName;

    String password;


    protected Customer() {};

    public Customer(String firstName, String secondName){
        this.firstName = firstName;
        this.lastName = secondName;

    }

    @Override

    public String toString(){
        return String.format( "Customer[id = %d, fistName = %s, secondName = %s]", id, firstName, lastName);

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
