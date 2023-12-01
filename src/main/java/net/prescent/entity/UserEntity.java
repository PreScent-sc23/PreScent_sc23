package net.prescent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userKey;

    @Column(nullable = false)
    private String password;

    public Integer getUserKey() {
        return userKey;
    }
    public String getPassword() {
        return password;
    }
    public abstract String getUserType();

}