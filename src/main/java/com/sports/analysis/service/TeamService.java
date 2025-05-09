package com.sports.analysis.service;

import com.sports.analysis.dao.TeamDao;
import com.sports.analysis.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamDao teamDao;

    public List<Team> getTeamsByEventId(Long eventId) {
        return teamDao.getTeamsByEventId(eventId);
    }

}
