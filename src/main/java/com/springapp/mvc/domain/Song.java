package com.springapp.mvc.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by papadimos on 23/10/2014.
 */
@Alias("song")
public class Song implements Serializable {

    private Long id;
    private String name;
    public static int songCounter = 0;
    private int numberOfSongs;

    public Song() {
        numberOfSongs = ++songCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
}
