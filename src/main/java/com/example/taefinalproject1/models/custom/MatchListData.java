package com.example.taefinalproject1.models.custom;

import java.util.Date;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class MatchListData {
    String championName;
    String lane;
    String rankedQueue;
    String season;
    String region;
    String role;
    Long matchid;
    Date date;

    public MatchListData(String championName, String lane, String rankedQueue, String season, String region, String role, Long matchid, Date date) {
        this.championName = championName;
        this.lane = lane;
        this.rankedQueue = rankedQueue;
        this.season = season;
        this.region = region;
        this.role = role;
        this.matchid = matchid;
        this.date = date;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRankedQueue() {
        return rankedQueue;
    }

    public void setRankedQueue(String rankedQueue) {
        this.rankedQueue = rankedQueue;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDate() {
        return date;
    }

    public Long getMatchid() {
        return matchid;
    }

    public void setMatchid(Long matchid) {
        this.matchid = matchid;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
