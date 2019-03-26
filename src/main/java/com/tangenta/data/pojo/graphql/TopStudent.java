package com.tangenta.data.pojo.graphql;

public class TopStudent {
    private String name;
    private String group;
    private Double score;

    public TopStudent(String name, String group, Double score) {
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

    public Double getScore() {
        return score;
    }
}
