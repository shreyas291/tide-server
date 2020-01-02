package com.sensei.app.service.dto;

/**
 * Created by prashanth on 16/5/17.
 */
public class AuthorityDTO {
    private String authorityName;

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String toString(){
        return "AuthorityDTO(" +
                "name:" + authorityName + ")";
    }
}
