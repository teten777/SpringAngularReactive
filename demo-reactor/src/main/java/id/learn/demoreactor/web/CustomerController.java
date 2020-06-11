package id.learn.demoreactor.web;

import id.learn.demoreactor.entity.Customer;
import id.learn.demoreactor.repository.ReactiveCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private ReactiveCustomerRepository customerRepository;

    @GetMapping("")
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll()
                .log()
                .doOnNext(cust -> log.info("Getting cust : "+cust.getName()));
    }

    @PostMapping("/create")
    public Mono<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable("id") String id,
                                                         @RequestBody Customer customer) {
        System.out.println("Update Customer with ID = " + id + "...");

        return customerRepository.findById(id)
                .log()
                .flatMap(customerData -> {
            customerData.setName(customer.getName());
            customerData.setAge(customer.getAge());
            customerData.setActive(customer.isActive());
            return customerRepository.save(customerData).log();
        }).map(updatedcustomer -> new ResponseEntity<>(updatedcustomer, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
        try{
            customerRepository.deleteById(id).log();
        }catch (Exception e) {
            return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAllCustomers() {
        try{
            customerRepository.deleteAll().log().subscribe();
        }catch (Exception e) {
            return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
    }

    @GetMapping("/findbyname")
    public Flux<Customer> findByName(@RequestParam String name) {

        return customerRepository.findByName(name)
                .log()
                .delayElements(Duration.ofMillis(1000));
    }
}
