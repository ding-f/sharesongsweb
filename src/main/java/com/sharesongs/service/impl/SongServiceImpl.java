package com.sharesongs.service.impl;



import com.sharesongs.dao.SongDao;
import com.sharesongs.dao.imp.SongDaoImpl;
import com.sharesongs.entity.Song;
import com.sharesongs.service.SongService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 *获取dao的交互结果,将之处理成servlet想要的数据
 */
public class SongServiceImpl implements SongService {

    SongDao songDao=new SongDaoImpl();
    @Override
    public boolean checkSongName(String songname) throws SQLException {

        Song song=songDao.SearchBySongName(songname);
        Boolean b= (song==null?true:false);
        return b;
    }

    @Override
    public int checkAddSong(Song s) throws SQLException {
        int result=songDao.AddSong(s);
        return result;
    }

    @Override
    public List<Song> checkAllSong() throws SQLException {

        List<Song> list=songDao.TakeAllSongs();

        return list;
    }
}
