package id.learn.demoreactor.web;

import id.learn.demoreactor.entity.Book;
import id.learn.demoreactor.entity.Shop;
import id.learn.demoreactor.repository.BookRepository;
import id.learn.demoreactor.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShopService shopService;

    @GetMapping()
    public Flux<Book> getAll() {
        return bookRepository.findAll()
                .log()
                .doOnNext(book -> log.info("Getting book : "+book.getTitle()));
    }

    @GetMapping(value = "/{id}/shops", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Shop> getShop(@PathVariable("id") String id) {
        log.info(">>> GET /books/{id}/shops received", id);
        return bookRepository.findById(id)
                .log()
                .flatMapMany(book -> shopService.getShopRemote(book.getId()))
                .doOnNext(shop -> log.info("Shop name : "+shop.getName()));
    }

}
