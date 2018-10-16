package com.zh.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zh2683
 */
@Data
@NoArgsConstructor
public class Session {

    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
