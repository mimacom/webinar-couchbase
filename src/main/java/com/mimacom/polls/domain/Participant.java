package com.mimacom.polls.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public class Participant {
    @Id
    private String id;

    private String name;

    private String pollFk;

    private List<Vote> votes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPollFk() {
        return pollFk;
    }

    public void setPollFk(String pollFk) {
        this.pollFk = pollFk;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
