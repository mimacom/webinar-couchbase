package com.mimacom.polls.util;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public enum Constants {
    DESIGN_DOC_COMMON("common"),
    DESIGN_DOC_POLL("poll"),
    DESIGN_DOC_PARTICIPANT("participant"),
    VIEW_BY_POLL_FK("byPollFk"),
    VIEW_BY_USER_NAME("byUserName"),
    VIEW_SEQUENCE_ID("sequenceId"),
    VIEW_TOTAL_VOTES("totalVotes"),
    VIEW_ALL("all");

    private String value;

    Constants(String v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
