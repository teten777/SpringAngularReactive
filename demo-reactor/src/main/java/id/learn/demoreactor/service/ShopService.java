package id.learn.demoreactor.service;

import id.learn.demoreactor.entity.Shop;
import reactor.core.publisher.Flux;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

public interface ShopService {

    Flux<Shop> getShopRemote(String bookId);

}
