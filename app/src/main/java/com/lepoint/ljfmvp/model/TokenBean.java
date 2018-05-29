package com.lepoint.ljfmvp.model;

/**
 * Created by admin on 2018/4/23.
 */

public class TokenBean extends BaseModel {
    private String secretKey;
    private String accessToken;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
