package com.example.jpa.start;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.transaction.Transactional;

import java.util.List;

@SpringBootApplication
public class JpaMain implements CommandLineRunner {

    @Autowired
    private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(JpaMain.class, args);
    }

    @Override
    @Transactional // 트랜잭션 관리
    public void run(String... args) throws Exception {
        logic(em);
    }

    private void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        // 등록
        em.persist(member);
        // 수정
        member.setAge(20);
        // 한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        // 목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("member.size=" + members.size());
        // 삭제
        em.remove(member);
    }
}
