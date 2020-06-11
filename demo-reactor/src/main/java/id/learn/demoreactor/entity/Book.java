package id.learn.demoreactor.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Project Name     : demo-reactor
 * Date Time        : 6/8/2020
 *
 * @author Teten Nugraha
 */

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;

}
