package com.sports.analysis.dao;

import com.sports.analysis.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeamDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Team> getTeamsByEventId(Long eventId) {
        String sql = """
            SELECT t.id, t.name, t.country
            FROM teams t
            JOIN event_teams et ON t.id = et.team_id
            WHERE et.event_id = ?
        """;
        return jdbcTemplate.query(sql, new TeamRowMapper(), eventId);
    }
}
  class TeamRowMapper implements RowMapper<Team> {
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();
        team.setId(rs.getLong("id"));
        team.setName(rs.getString("name"));
        team.setCountry(rs.getString("country"));
        return team;
    }
}
