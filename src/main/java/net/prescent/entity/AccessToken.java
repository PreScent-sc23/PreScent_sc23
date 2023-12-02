package net.prescent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "access_token")
public class AccessToken {
    @Id
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_key")
    private UserEntity user;

    public AccessToken(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }
}
