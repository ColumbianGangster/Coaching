
package com.example.taefinalproject1.models.summonermatchesbyseason;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Match implements Parcelable
{

    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("platformId")
    @Expose
    private String platformId;
    @SerializedName("matchId")
    @Expose
    private Integer matchId;
    @SerializedName("champion")
    @Expose
    private Integer champion;
    @SerializedName("queue")
    @Expose
    private String queue;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("lane")
    @Expose
    private String lane;
    @SerializedName("role")
    @Expose
    private String role;
    public final static Creator<Match> CREATOR = new Creator<Match>() {


        public Match createFromParcel(Parcel in) {
            Match instance = new Match();
            instance.region = ((String) in.readValue((String.class.getClassLoader())));
            instance.platformId = ((String) in.readValue((String.class.getClassLoader())));
            instance.matchId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.champion = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.queue = ((String) in.readValue((String.class.getClassLoader())));
            instance.season = ((String) in.readValue((String.class.getClassLoader())));
            instance.timestamp = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.lane = ((String) in.readValue((String.class.getClassLoader())));
            instance.role = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Match[] newArray(int size) {
            return (new Match[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 
     * @return
     *     The platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * 
     * @param platformId
     *     The platformId
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /**
     * 
     * @return
     *     The matchId
     */
    public Integer getMatchId() {
        return matchId;
    }

    /**
     * 
     * @param matchId
     *     The matchId
     */
    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    /**
     * 
     * @return
     *     The champion
     */
    public Integer getChampion() {
        return champion;
    }

    /**
     * 
     * @param champion
     *     The champion
     */
    public void setChampion(Integer champion) {
        this.champion = champion;
    }

    /**
     * 
     * @return
     *     The queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * 
     * @param queue
     *     The queue
     */
    public void setQueue(String queue) {
        this.queue = queue;
    }

    /**
     * 
     * @return
     *     The season
     */
    public String getSeason() {
        return season;
    }

    /**
     * 
     * @param season
     *     The season
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The lane
     */
    public String getLane() {
        return lane;
    }

    /**
     * 
     * @param lane
     *     The lane
     */
    public void setLane(String lane) {
        this.lane = lane;
    }

    /**
     * 
     * @return
     *     The role
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @param role
     *     The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(region).append(platformId).append(matchId).append(champion).append(queue).append(season).append(timestamp).append(lane).append(role).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Match) == false) {
            return false;
        }
        Match rhs = ((Match) other);
        return new EqualsBuilder().append(region, rhs.region).append(platformId, rhs.platformId).append(matchId, rhs.matchId).append(champion, rhs.champion).append(queue, rhs.queue).append(season, rhs.season).append(timestamp, rhs.timestamp).append(lane, rhs.lane).append(role, rhs.role).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(region);
        dest.writeValue(platformId);
        dest.writeValue(matchId);
        dest.writeValue(champion);
        dest.writeValue(queue);
        dest.writeValue(season);
        dest.writeValue(timestamp);
        dest.writeValue(lane);
        dest.writeValue(role);
    }

    public int describeContents() {
        return  0;
    }

}
