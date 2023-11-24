package net.prescent.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userKey;

    /*
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
     */

    /*
    @Column(nullable = false, unique = true)
    private String idEmail;


    public String getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(String email) {
        this.idEmail = email;
    }
     */

    public abstract String getUserType();
}