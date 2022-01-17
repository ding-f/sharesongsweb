package com.sharesongs.dao.imp;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sharesongs.dao.SongDao;
import com.sharesongs.entity.Song;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class SongDaoImpl implements SongDao {
    //初始化连接池
    ComboPooledDataSource ds = new ComboPooledDataSource();
    //获取QueryRunner对象
    QueryRunner qr = new QueryRunner(ds);
    //创建最终需要返回的数据
    Song song = null;

    @Override
    public Song SearchBySongName(String songname) throws SQLException {
        String sql ="select * from songs where songname=?;";
        Song song=qr.query(sql, new BeanHandler<>(Song.class),songname);        //查询歌曲是否重复
        return song;
    }

    @Override
    public int AddSong(Song s) throws SQLException {
        String sql="INSERT INTO songs VALUES (?,?,?,?,?,?,?,?);";       //插入歌曲

        int song=qr.update(sql,s.getId(),s.getUser_id(),s.getSongname(),s.getSingername(),s.getYourname(),s.getPhoto(),s.getSong(),s.getUp_time());
        
        return song;
    }

    @Override
    public List<Song> TakeAllSongs() throws SQLException {
        List<Song> songs = null;
        String sql="SELECT * FROM songs order by up_time desc;";        //查询所有歌曲后倒序 按时间
        songs=qr.query(sql,new BeanListHandler<>(Song.class));
        return songs;
    }

//    @Override
//    public Song SearchByYourName(String yourname) {
//        String sql ="select * from songs where yourname=?";
//        Song
//        return null;
//    }
}

