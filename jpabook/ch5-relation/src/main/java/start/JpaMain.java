package start;

import org.hibernate.metamodel.binding.EntityIdentifier;
import start.entity.Member;
import start.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

    public static void main(String[] args) {
        saveTeam();

        selectTeamByReference();
        selectTeamByJPQL();

        updateRelation();

        deleteRelation();

        selectBiDirection();

        saveBiDirection();
        saveBiDirectionNonOwner();

        System.exit(0);
    }

    private static void saveBiDirectionNonOwner() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member5 = new Member("m5", "member5", null);
        Member member6 = new Member("m6", "member6", null);

        Team team4 = new Team("t4", "team4");
        team4.getMembers().add(member5);
        team4.getMembers().add(member6);

        em.persist(team4);

        tx.commit();
        em.close();
    }

    private static void saveBiDirection() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team3 = new Team("t3", "team3");
        em.persist(team3);

        Member member3 = new Member("m3", "member3", team3);
        em.persist(member3);

        Member member4 = new Member("m4", "member4", team3);
        em.persist(member4);

        tx.commit();
        em.close();
    }

    private static void selectBiDirection() {
        EntityManager em = emf.createEntityManager();

        Team team1 = em.find(Team.class, "t1");
        List<Member> members = team1.getMembers();

        for (Member m : members) {
            System.out.println("==== member.username = " + m.getUsername());
        }

        em.close();
    }

    private static void deleteRelation() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member1 = em.find(Member.class, "m1");
        member1.setTeam(null);

        tx.commit();
        em.close();
    }

    private static void updateRelation() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Team team2 = new Team("t2", "team2");
        em.persist(team2);

        Member member1 = em.find(Member.class, "m1");
        member1.setTeam(team2);

        tx.commit();

        em.close();
    }

    private static void selectTeamByJPQL() {
        EntityManager em = emf.createEntityManager();

        String jpql = "select m from Member m join m.team t where t.name=:teamName";

        List<Member> memberList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "team1")
                .getResultList();

        for (Member m : memberList) {
            System.out.println("==== [query] member.username = " + m.getUsername());
        }

        em.close();
    }

    private static void selectTeamByReference() {
        EntityManager em = emf.createEntityManager();

        Member member1 = em.find(Member.class, "m1");
        Team team1 = member1.getTeam();

        System.out.println("===== team1 name = " + team1.getName());
        em.close();
    }

    private static void saveTeam() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 팀1 저장
        Team team1 = new Team("t1", "team1");
        em.persist(team1);

        Member member1 = new Member("m1", "member1", team1);
        em.persist(member1);

        Member member2 = new Member("m2", "member2", team1);
        em.persist(member2);

        tx.commit();
        em.close();
    }

}
