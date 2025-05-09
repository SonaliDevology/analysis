package com.sports.analysis.dao;
import com.sports.analysis.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Event> getAllEvents() {
        String sql = "SELECT id, name, date_time, location FROM events ORDER BY date_time DESC";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    public Event getEventById(Long eventId) {
        String sql = "SELECT id, name, date_time, location FROM events WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EventRowMapper(), eventId);
    }

    }

     class EventRowMapper implements RowMapper<Event> {
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();

            event.setId(rs.getLong("id"));
            event.setName(rs.getString("name"));
            event.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
            event.setLocation(rs.getString("location"));
            return event;
        }

}
