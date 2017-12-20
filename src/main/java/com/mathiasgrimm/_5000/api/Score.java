package com.mathiasgrimm._5000.api;

import java.util.List;

class Score {
    private Integer points = 0;
    private List<Integer> rest;
    private Boolean star = false;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void addPoints(Integer points) {
        this.points += points;
    }

    public List<Integer> getRest() {
        return rest;
    }

    public void setRest(List<Integer> rest) {
        this.rest = rest;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    @Override
    public String toString() {
        String rest = "";

        if (this.rest != null && this.rest.size() > 0) {
            for (Integer dice : this.rest) {
                rest = rest + dice + " ";
            }
        }
        rest = rest.trim();
        rest = "[" + rest + "]";

        return "points: " + this.points + " star: " + this.star + " rest: " + rest;
    }
}
