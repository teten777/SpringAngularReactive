package id.learn.demoreactor.entity;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/9/2020
 *
 * @author Teten Nugraha
 */

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private String id;
    private String name;
    private int age;
    private boolean active;



}
