package start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

    public static void main(String[] args) {
        Member member = createMember("memberA", "Dec7");

        // 준 영속상태에서 변경
        member.setUsername("NewDec7");

        mergeMember(member);
    }

    private static Member createMember(String id, String name) {

        // 영속성 컨텍스트1 시작
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(name);

        em1.persist(member);
        tx1.commit();


        // 영속성 컨텍스트1 종료, member는 준영속 상태로 변환
        em1.close();

        return member;
    }

    private static void mergeMember(Member member) {
        // 영속성 컨텍스트2 시작

        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        // 준영속 상태
        System.out.println("member = " + member.getUsername());

        // 영속 상태
        System.out.println("mergeMember = " + mergeMember.getUsername());

        System.out.println("em2 contains member = " + em2.contains(member));
        System.out.println("em2 contains mergeMember = " + em2.contains(mergeMember));

        // 영속성 컨텍스트2 종료
        em2.close();
    }
}
