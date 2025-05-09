package com.sports.analysis.controller;

import com.sports.analysis.model.Event;
import com.sports.analysis.model.Team;
import com.sports.analysis.model.Video;
import com.sports.analysis.service.EventService;
import com.sports.analysis.service.TeamService;
import com.sports.analysis.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    TeamService teamService;
    @Autowired
    VideoService videoService;

    @GetMapping
    public List<Event> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        for (Event event : events) {
            List<Video> videos = videoService.getVideosByEventId(event.getId());
            List<Team> teams = teamService.getTeamsByEventId(event.getId());
            event.setVideos(videos);
            event.setTeams(teams);
        }
        return events;
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        event.setVideos(videoService.getVideosByEventId(id));
        event.setTeams(teamService.getTeamsByEventId(id));
        return event;
    }
}