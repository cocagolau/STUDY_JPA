package start.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(MemberItemId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderAmount;
}
