package com.sports.analysis.model;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
public class Event {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private String location;
    private List<Video> videos;
    private List<Team> teams;
}
