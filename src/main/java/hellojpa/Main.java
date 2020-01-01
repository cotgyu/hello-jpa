package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String args[]){
        System.out.println("hello");

        // persistence.xml 에 명시된 persistence-unit name 에 맞는 정보를 가져와서 로딩하기
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");


        // 요청이 올떄마다 생성해서 쓰면 됨.
        EntityManager em =  emf.createEntityManager();

        // 트랜잭션 설정
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);


            Member member = new Member();
            //member.setId(102L);
            member.setName("hello");
            //member.setTeamId(team.getId());

            // 단방향 연관관계 설정, 참조 저장
            member.setTeam(team);

            em.persist(member);

           // team.getMembers().add(member);

            // 캐쉬 초기화
            em.flush();
            em.clear();

            // 멤버의 팀 찾기  ( 식별자로 다시 조회, 객체지향적인 방법은 아니다. (데이터베이스에 맞게 셋팅하는 경우) )
            Member findMember  = em.find(Member.class, member.getId());
//            Long teamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, teamId);

            // 준영속 테스트
            em.detach(findMember);
            //em.clear();

            // 변경감지 테스
            findMember.setName("T아키데미");

            Team findTeam = findMember.getTeam();

            findTeam.getName();


            List<Member> members = findTeam.getMembers();

            for(Member member1 : members){
                System.out.println("member1= "+ member1);
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            // entitymanager 닫기
            em.close();

        }

        // 종료
        emf.close();
    }
}
