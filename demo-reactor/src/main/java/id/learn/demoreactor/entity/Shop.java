package id.learn.demoreactor.entity;

import lombok.*;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {

    private String name;
    private String currency;

}
