package com.sports.analysis.service;

import com.sports.analysis.dao.VideoDao;
import com.sports.analysis.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    VideoDao videoDao;

    public List<Video> getVideosByEventId(Long eventId) {
        return videoDao.getVideosByEventId(eventId);
    }
}
