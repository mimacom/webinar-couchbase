package com.mimacom.polls.domain;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public class Vote {
    private String option;
    private Boolean selected;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
