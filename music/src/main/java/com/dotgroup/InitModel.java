package com.dotgroup;

import com.dotgroup.classes.Song;

public class InitModel {
    public static void main(String[] args) {
        SongDB db = new SongDB();

        db.addSong(new Song("Its you By Keshi", "Keshi", "Its you By Keshi", 173));
        db.addSong(new Song("Lonely By Akon", "Akon", "Lonely By Akon", 263));
        db.addSong(new Song("Moongazing", "Moongazing", "Moongazing", 252));
        db.addSong(new Song("Sprinter", "Sprinter", "Sprinter", 409));
        db.addSong(new Song("Uhaw By Dilaw", "Uhaw By Dilaw", "Uhaw By Dilaw", 241));
        db.addSong(new Song("Wonders of You", "Wonders of You", "Wonders of You", 146));
        db.addSong(new Song("Gangnam Style", "Gangnam Style", "Gangnam_Style", 219));
        db.close();
    }
}
