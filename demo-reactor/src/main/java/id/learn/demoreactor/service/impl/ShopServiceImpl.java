package id.learn.demoreactor.service.impl;

import id.learn.demoreactor.entity.Shop;
import id.learn.demoreactor.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    @Override
    public Flux<Shop> getShopRemote(String bookId) {

        Shop s1 = Shop.builder().name("Jeff's BookShop").currency("AUD").build();
        Shop s2 = Shop.builder().name("Randwick BookShop").currency("AUD").build();
        Shop s3 = Shop.builder().name("Sydney BookShop").currency("AUD").build();

        final int delayfactor = new Random().nextInt(5);

        return Flux.just(s1, s2, s3)
                .delayElements(Duration.ofSeconds(delayfactor))
                .doOnNext(shop -> {
                    log.info("<< Sop Getting available");
                });
    }
}
