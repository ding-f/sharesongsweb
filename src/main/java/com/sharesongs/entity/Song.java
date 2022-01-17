package com.sharesongs.entity;


import java.util.Date;
public class Song {
    private int id;
    private int user_id;
    private String songname;
    private String singername;
    private String yourname;
    private String photo;
    private String song;
    private Date up_time;

    public Song() {
    }

    public Song(int id, int user_id, String songname, String singername, String yourname, String photo, String song, Date up_time) {
        this.id = id;
        this.user_id = user_id;
        this.songname = songname;
        this.singername = singername;
        this.yourname = yourname;
        this.photo = photo;
        this.song = song;
        this.up_time = up_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }


    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getYourname() {
        return yourname;
    }

    public void setYourname(String yourname) {
        this.yourname = yourname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Date getUp_time() {
        return up_time;
    }

    public void setUp_time(Date up_time) {
        this.up_time = up_time;
    }
}
