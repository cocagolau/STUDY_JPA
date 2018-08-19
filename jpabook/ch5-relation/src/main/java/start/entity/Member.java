package start.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id") // 왜래키를 맵핑할 때 사용, 생략 가능
    private Team team;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setTeam(Team team) {

        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;

        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
