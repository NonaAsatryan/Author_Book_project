package author.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book implements Serializable {

    private int id;
    private String serialID;
    private String title;
    private String description;
    private double price;
    private int count;
    private Author author;
    private Set<String> tags;

    public Book(String serialID, String title, String description, double price, int count, Author author) {
        this.serialID = serialID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.count = count;
        this.author = author;
    }
}
