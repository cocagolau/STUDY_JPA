package start;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        System.out.println("start");

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("testdb");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 트랜젝션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); // 트랜젝션 시작
            logic(em);  // 비지니스 로직 실행
            tx.commit();    // 트랜젝션 커밋
        } catch (Exception e) {
            tx.rollback();  // 트랜젝션 롤백
        } finally {
            em.close(); // 트랜젝션 종료
        }
        emf.close();    // 엔티티 매지저 팩토리 종료

        System.out.println("end");
    }

    private static void logic(EntityManager em) {
        // 비지니스 로직
        String id1 = "id1";

        // insert
        Member member1 = new Member();
        member1.setId(id1);
        member1.setUsername("Dec7");
        member1.setAge(30);
        em.persist(member1);

        Member member2 = new Member();
        member2.setId("id2");
        member2.setUsername("Dec8");
        member2.setAge(40);
        em.persist(member2);

        // select
        Member member1_selected = em.find(Member.class, id1);

        // select JPQL
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> members = query.getResultList();

        // update
        member1.setAge(31);
        em.persist(member1);

        // remove
        em.remove(member1);
    }
}
