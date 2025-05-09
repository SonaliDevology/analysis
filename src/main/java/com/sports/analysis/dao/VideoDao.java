package com.sports.analysis.dao;

import com.sports.analysis.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VideoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Video> getVideosByEventId(Long eventId) {
        String sql = "SELECT id, event_id, url, description FROM videos WHERE event_id = ?";
        return jdbcTemplate.query(sql, new VideoRowMapper(), eventId);
    }

}

 class VideoRowMapper implements RowMapper<Video> {
    @Override
    public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
        Video video = new Video();
        video.setId(rs.getLong("id"));
        video.setEventId(rs.getLong("event_id"));
        video.setUrl(rs.getString("url"));
        video.setDescription(rs.getString("description"));
        return video;
    }
}
