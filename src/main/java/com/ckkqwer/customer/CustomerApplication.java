package com.ckkqwer.customer;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class CustomerApplication
{
    private final CustomerRepository customerRepository;

    public CustomerApplication(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age){}

    record UpdateCustomerRequest(String name, String email, Integer age){ }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody UpdateCustomerRequest request){
        Customer updateCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateCustomer.setName(request.name());
        updateCustomer.setEmail(request.email());
        updateCustomer.setAge(request.age());

        customerRepository.save(updateCustomer);
    }
//region depre
//    @GetMapping("/")
//    @ResponseBody
//    public String root(){
//        return "demo app: ckkqwer";
//    }

//    @GetMapping("greet")
//    @ResponseBody
//    public GreetingResponse greet(@RequestParam String name){
//        GreetingResponse greetingResponse = new GreetingResponse("Hello, " + name,
//                                                                 List.of("Java", "C#", "JS"),
//                                                                 new Person("Alex", 22, 18000));
//        return greetingResponse;
//    }
//
//    record Person(String name, int age, double salary){}
//    record GreetingResponse(String greet,
//                            List<String> favLanguage,
//                            Person person){}
//    class GreetingResponse{
//        private final String greet;
//
//        public GreetingResponse(String greet)
//        {
//            this.greet = greet;
//        }
//
//        public String getGreet()
//        {
//            return greet;
//        }
//
//        @Override
//        public String toString()
//        {
//            return "GreetingResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o)
//        {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetingResponse that = (GreetingResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode()
//        {
//            return Objects.hash(greet);
//        }
//    }
//endregion
}
