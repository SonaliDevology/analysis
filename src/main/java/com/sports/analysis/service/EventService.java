package com.sports.analysis.service;

import com.sports.analysis.dao.EventDao;
import com.sports.analysis.dao.TeamDao;
import com.sports.analysis.dao.VideoDao;
import com.sports.analysis.model.Event;
import com.sports.analysis.model.Team;
import com.sports.analysis.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private VideoDao videoDao;

    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public Event getEventById(Long eventId) {
        // Fetch the event
        Event event = eventDao.getEventById(eventId);
        if (event != null) {
            // Fetch the teams for the event
            List<Team> teams = teamDao.getTeamsByEventId(eventId);
            event.setTeams(teams);

            // Fetch the videos related to the event
            List<Video> videos = videoDao.getVideosByEventId(eventId);
            event.setVideos(videos);
        }
        return event;
    }
}