package id.learn.demoreactor.repository;

import id.learn.demoreactor.entity.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/8/2020
 *
 * @author Teten Nugraha
 */

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
