package com.example.demo.dto;

public class DashboardResponse {

    private long total;
    private long applied;
    private long interview;
    private long selected;
    private long rejected;

    public DashboardResponse(
            long total,
            long applied,
            long interview,
            long selected,
            long rejected) {

        this.total = total;
        this.applied = applied;
        this.interview = interview;
        this.selected = selected;
        this.rejected = rejected;
    }

    public long getTotal() {
        return total;
    }

    public long getApplied() {
        return applied;
    }

    public long getInterview() {
        return interview;
    }

    public long getSelected() {
        return selected;
    }

    public long getRejected() {
        return rejected;
    }
}
