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
@Table(name = "team")
public class TeamOneToMany {

    @Id
    @Column(name = "team_id")
    private String id;

    private String name;

    @OneToMany
    @JoinColumn(name = "team_id") // member 테이블의 team_id (fk)
    private List<Member> members = new ArrayList<Member>();

    public TeamOneToMany(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
