package com.mimacom.polls.domain;

import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public class Poll {
    @Id
    private String id;

    @NotNull
    private String userName;

    @NotNull
    private String emailAddress;

    private List<Object> options = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getOptions() {
        return options;
    }

    public void setOptions(List<Object> options) {
        this.options = options;
    }
}
