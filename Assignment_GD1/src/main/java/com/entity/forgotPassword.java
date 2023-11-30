package com.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class forgotPassword {
    String username;
    String forgotToken;
    LocalDateTime tokenTime;
    String password;
    String enterForgotToken;

    public void resetToken() {
        this.username = null;
        this.forgotToken = null;
        this.tokenTime = null;
        this.password = null;
        this.enterForgotToken = null;
    }
}
