package net.prescent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Integer role;
    public LoginResponse(String token, Integer role) {
        this.token = token;
        this.role = role;
    }
}