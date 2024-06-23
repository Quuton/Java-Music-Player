// This is the API for communicating between the SQL Server
package com.dotgroup;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.dotgroup.classes.Song;


public class SongDB {
    private final String SCHEMA_NAME = "music_list";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(SCHEMA_NAME);
    EntityManager entityManager = emf.createEntityManager();
    public SongDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.exit(-1);
        }
        
    }

    public List<Song> getSongList() {

        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s", Song.class);
        List<Song> songList = query.getResultList();


        return songList;
    }

    public void addSong(Song song) {
        entityManager.getTransaction().begin();
        entityManager.persist(song);
        entityManager.getTransaction().commit();
    }

    public void updateSong(Song updatedSong) {

        entityManager.getTransaction().begin();

        Song song = entityManager.find(Song.class, updatedSong.getSongID());
        if (song != null) {
            song.setSongTitle(updatedSong.getSongTitle());
            song.setSongAuthor(updatedSong.getSongAuthor());
            entityManager.merge(song);
        }

        entityManager.getTransaction().commit();

    }

    public void deleteSong(Integer songID) {

        entityManager.getTransaction().begin();

        Song song = entityManager.find(Song.class, songID);
        if (song != null) {
            entityManager.remove(song);
        }

        entityManager.getTransaction().commit();

    }

    public void toggleFavourite(Integer songID) {

        entityManager.getTransaction().begin();

        Song song = entityManager.find(Song.class, songID);
        song.toggleFavourite();

        entityManager.getTransaction().commit();

    }
    public void close() {
        entityManager.close();
        emf.close();
    }

    
}
