package start;

import org.junit.Test;
import start.entity.Member;
import start.entity.Team;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JpaMainTest {

    @Test
    public void test_순수한객체_양방향() {
        Team team1 = new Team("t1", "team1");
        Member member1 = new Member("m1", "member1");
        Member member2 = new Member("m2", "member2");

        member1.setTeam(team1);

        member2.setTeam(team1);

        List<Member> members = team1.getMembers();
        assertThat(members.size(), equalTo(2));
    }

    @Test
    public void test_소유관계_변경시() {
        Team team1 = new Team("t1", "team1");
        Team team2 = new Team("t1", "team1");

        assertThat(team1.getMembers().size(), equalTo(0));
        assertThat(team2.getMembers().size(), equalTo(0));

        Member member1 = new Member("m1", "member1");

        member1.setTeam(team1);
        assertThat(team1.getMembers().size(), equalTo(1));
        assertThat(team2.getMembers().size(), equalTo(0));

        member1.setTeam(team2);
        assertThat(team1.getMembers().size(), equalTo(0));
        assertThat(team2.getMembers().size(), equalTo(1));
    }
}
