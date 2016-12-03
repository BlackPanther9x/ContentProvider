package com.example.vaio.everythingme;

public class Song {
    private String Title;
    private String Artist;

    public Song(String title, String artist) {
        Title = title;
        Artist = artist;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }
}
