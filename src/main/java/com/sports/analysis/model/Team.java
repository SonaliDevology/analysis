package com.sports.analysis.model;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Team {

    private Long id;
    private String name;
    private String country;
}