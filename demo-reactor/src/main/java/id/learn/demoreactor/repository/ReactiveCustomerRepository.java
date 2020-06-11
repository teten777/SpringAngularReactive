package id.learn.demoreactor.repository;

import id.learn.demoreactor.entity.Customer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

@Repository
public interface ReactiveCustomerRepository extends ReactiveMongoRepository<Customer, String> {

//    Mono<Customer> findByLastName(String lastName);
    Flux<Customer> findByName(String name);

//    @Query("{ 'firstname': ?0, 'lastname': ?1}")
//    Mono<Customer> findByFirstNameAndLastName(String firstname, String lastName);

}
