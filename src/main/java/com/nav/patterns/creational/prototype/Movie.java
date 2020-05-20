package com.nav.patterns.creational.prototype;

public class Movie extends Item {

    private String runtime;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @Override
    public String toString() {
        return "Movie{" + super.toString() +
                " runtime='" + runtime + '\'' +
                '}';
    }
}
