package hellojpa.entity;

import javax.persistence.*;

@Entity // javax.persistence  이것이 자바 표준
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="USERNAME")
    private String name;

    private int age;



    // 테이블 중심
//    @Column(name="TEAM_ID")
//    private Long teamId;


    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn(name="TEAM_ID")
    private Team team;


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                ", memberType=" + memberType +
                '}';
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
