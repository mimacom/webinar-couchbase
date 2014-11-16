package com.mimacom.polls.domain;

import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
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

    private String title;

    private Date createdOn;

    private List<Object> options = new ArrayList<>();

    private boolean closed = false;

    private String selectedOption;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean opened) {
        this.closed = opened;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
