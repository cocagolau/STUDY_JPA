package start;

import start.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

    public static void main(String[] args) {

        saveTeamOneToMany();

        saveManyToMany();
        find();
        findInverse();

        saveIdClass();
        findIdClass();

        saveOrder();
        findOrder();

        System.exit(0);
    }

    private static void findOrder() {
        EntityManager em = emf.createEntityManager();

        Order order = em.find(Order.class, 1L);

        Member member5 = order.getMember();
        Item item2 = order.getItem();

        System.out.println("==== member.username = " + member5.getUsername());
        System.out.println("==== item.name = " + item2.getName());
        System.out.println("==== amount = " + order.getOrderAmount());

        em.close();
    }

    private static void saveOrder() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member5 = new Member("m5", "member5");
        em.persist(member5);

        Item item2 = new Item("i2", "item2");
        em.persist(item2);

        Order order = new Order(member5, item2, 2);
        em.persist(order);

        tx.commit();
        em.close();
    }

    private static void findIdClass() {
        EntityManager em = emf.createEntityManager();

        MemberItemId memberItemId = new MemberItemId("m4", "i1");

        MemberItem memberItem = em.find(MemberItem.class, memberItemId);
        Member member4 = memberItem.getMember();
        Item item1 = memberItem.getItem();

        System.out.println("==== member.username = " + member4.getUsername());
        System.out.println("==== item.name = " + item1.getName());
        System.out.println("==== amount = " + memberItem.getOrderAmount());

        em.close();

    }

    private static void saveIdClass() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member4 = new Member("m4", "member4");
        em.persist(member4);

        Item item1 = new Item("i1", "item1");
        em.persist(item1);

        MemberItem memberItem = new MemberItem(member4, item1, 2);
        em.persist(memberItem);

        tx.commit();
        em.close();
    }

    private static void findInverse() {
        EntityManager em = emf.createEntityManager();

        Product product = em.find(Product.class, "p1");
        List<Member> members = product.getMembers();
        for (Member m : members) {
            System.out.println("=== member.name = " + m.getUsername());
        }

        em.close();
    }

    private static void find() {
        EntityManager em = emf.createEntityManager();

        Member member3 = em.find(Member.class, "m3");
        List<Product> products = member3.getProducts();
        for (Product p : products) {
            System.out.println("=== product.name = " + p.getName());
        }

        em.close();
    }

    private static void saveManyToMany() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Product product1 = new Product("p1", "product1");
        em.persist(product1);

        Member member1 = new Member("m3", "member3");
        member1.getProducts().add(product1);
        em.persist(member1);

        tx.commit();
        em.close();
    }

    private static void saveTeamOneToMany() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member1 = new Member("m1", "member1");
        Member member2 = new Member("m2", "member2");

        TeamOneToMany team1 = new TeamOneToMany("t1", "team1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1);
        em.persist(member2);
        em.persist(team1);

        tx.commit();
        em.close();
    }


}
