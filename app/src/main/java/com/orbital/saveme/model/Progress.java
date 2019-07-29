package com.orbital.saveme.model;

public class Progress {
    private int progress;

    public Progress(int progress) {
        this.progress = progress;
    }

    public Progress() {
        this.progress = 100;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int newProgress) {
        this.progress = newProgress;
    }
}
