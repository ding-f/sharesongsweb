package com.sharesongs.service;

import com.sharesongs.entity.Song;

import java.sql.SQLException;
import java.util.List;

public interface SongService {

    public boolean checkSongName(String songname) throws SQLException;
    public int checkAddSong(Song s) throws SQLException;
    public List<Song> checkAllSong() throws SQLException;
}
