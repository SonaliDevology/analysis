package com.sports.analysis.model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Video {

    private Long id;
    private String url;
    private String description;
    private Long eventId;
}
