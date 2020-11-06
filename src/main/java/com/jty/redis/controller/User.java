package com.jty.redis.controller;

import java.io.Serializable;

/**
 * @author :jty
 * @date :20-9-28
 */
public class User implements Serializable {
    private String name;
    private String add;

    public User(String name, String add) {
        this.name = name;
        this.add = add;
    }

    public User() {
        this.name = null;
        this.add = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", add='" + add + '\'' +
                '}';
    }
}
