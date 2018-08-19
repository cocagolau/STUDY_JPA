package start;

import start.entity.Account;
import start.entity.Board;
import start.entity.Member;
import start.entity.Note;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb");

    public static void main(String[] args) {
        Member member = createMember("memberA", "Dec7");
        Board board = createBoard("board1");
        Account account = createAccount("account1");
        Note note = createNote("note1");
    }

    private static Note createNote(String data) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Note note = new Note();
        note.setData(data);

        tx.begin();

        em.persist(note);
        System.out.println("===== note id = " + note.getId());

        tx.commit();
        em.close();

        return note;
    }

    private static Account createAccount(String data) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Account account = new Account();
        account.setData(data);

        tx.begin();

        em.persist(account);
        System.out.println("===== account id = " + account.getId());

        tx.commit();
        em.close();

        return account;
    }

    private static Board createBoard(String data) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Board board = new Board();
        board.setData(data);

        tx.begin();

        em.persist(board);
        System.out.println("===== board id = " + board.getId());

        tx.commit();
        em.close();

        return board;
    }

    private static Member createMember(String id, String name) {

        // 영속성 컨텍스트1 시작
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        //member.setId(id);
        member.setUsername(name);

        em1.persist(member);
        tx1.commit();

        System.out.println(member.getId());


        // 영속성 컨텍스트1 종료, member는 준영속 상태로 변환
        em1.close();

        return member;
    }
}
