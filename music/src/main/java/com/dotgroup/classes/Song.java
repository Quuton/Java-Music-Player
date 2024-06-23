package com.dotgroup.classes;
import javax.persistence.*;

@Entity
@Table(name = "Songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songID;

    @Column(name = "songTitle", nullable = false)
    private String songTitle;

    @Column(name = "songFileName", nullable = false)
    private String songFileName;

    @Column(name = "songAuthor", nullable = true)
    private String songAuthor;

    @Column(name = "songDuration", nullable = false)
    private Integer songDuration;

    @Column(name = "favourite", nullable = false)
    private Boolean favourite;

    //DEBUGGING
    public Song(Integer songID, String title, String author, String fileName, Integer duration) {
        this.songID = songID;
        this.songTitle = title;
        this.songAuthor = author;
        this.songFileName = fileName;
        this.songDuration = duration;
        this.favourite = false;
    }

    public Song(String title, String author, String fileName, Integer duration) {
        this.songTitle = title;
        this.songAuthor = author;
        this.songFileName = fileName;
        this.songDuration = duration;
        this.favourite = false;
    }

    public Song() {
        this.favourite = false;
    }

    public Integer getSongID() {
        return this.songID;
    }

    public void setSongID(Integer songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return this.songTitle;
    }
    
    public void setSongTitle(String songName) {
        this.songTitle = songName;
    }

    public String getSongAuthor() {
        return this.songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSongFileName() {
        return this.songFileName;
    }

    public void setSongFileName(String songFileName) {
        this.songFileName = songFileName;
    }

    public Integer getSongDuration() {
        return this.songDuration;
    }
    
    public void setSongDuration(Integer songDuration) {
        this.songDuration = songDuration;
    }
    
    public Boolean getFavourite() {
        return this.favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public void toggleFavourite() {
        this.favourite = !(this.favourite);
    }

}
