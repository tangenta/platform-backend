package com.tangenta.data.pojo.graphql;

public class TopStudent {
    private String name;
    private String group;
    private Long score;

    public TopStudent(String name, String group, Long score) {
        this.name = name;
        this.group = group;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public Long getScore() {
        return score;
    }
}
