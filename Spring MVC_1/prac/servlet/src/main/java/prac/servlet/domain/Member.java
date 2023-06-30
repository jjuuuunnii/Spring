package prac.servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Member {

    private Long id;
    private String username;
    private int age;

    public Member() {}

    public Member(String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
