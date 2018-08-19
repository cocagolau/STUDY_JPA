package start.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;

    private String username;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @OneToOne(mappedBy = "member")
    private Account account;


    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<Product>();

    @OneToMany(mappedBy = "member")
    private List<MemberItem> memberItems;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
