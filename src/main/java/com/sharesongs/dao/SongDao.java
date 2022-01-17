package com.sharesongs.dao;


import com.sharesongs.entity.Song;

import java.sql.SQLException;
import java.util.List;


public interface
SongDao {
    //根据电话号码查询用户信息,返回一个歌曲对象
    public Song SearchBySongName(String songname) throws SQLException;
    public int AddSong(Song s) throws SQLException;
    public List<Song> TakeAllSongs() throws SQLException;
}