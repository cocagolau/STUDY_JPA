package start.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<Member>();

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
