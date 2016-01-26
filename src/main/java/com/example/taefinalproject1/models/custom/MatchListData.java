package com.example.taefinalproject1.models.custom;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by TAE_user2 on 25/01/2016.
 */
public class MatchListData implements Parcelable {
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

    protected MatchListData(Parcel in) {
        championName = in.readString();
        lane = in.readString();
        rankedQueue = in.readString();
        season = in.readString();
        region = in.readString();
        role = in.readString();
    }

    public static final Creator<MatchListData> CREATOR = new Creator<MatchListData>() {
        @Override
        public MatchListData createFromParcel(Parcel in) {
            return new MatchListData(in);
        }

        @Override
        public MatchListData[] newArray(int size) {
            return new MatchListData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(championName);
        dest.writeString(lane);
        dest.writeString(rankedQueue);
        dest.writeString(season);
        dest.writeString(region);
        dest.writeString(role);
    }
}
