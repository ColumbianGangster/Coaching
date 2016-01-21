
package com.example.taefinalproject1.models.summonermatchesbyseason;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class MatchlistBySummoner implements Parcelable
{

    @SerializedName("matches")
    @Expose
    private List<Match> matches = new ArrayList<Match>();
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("endIndex")
    @Expose
    private Integer endIndex;
    @SerializedName("totalGames")
    @Expose
    private Integer totalGames;
    public final static Creator<MatchlistBySummoner> CREATOR = new Creator<MatchlistBySummoner>() {


        public MatchlistBySummoner createFromParcel(Parcel in) {
            MatchlistBySummoner instance = new MatchlistBySummoner();
            in.readList(instance.matches, (com.example.taefinalproject1.models.summonermatchesbyseason.Match.class.getClassLoader()));
            instance.startIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.endIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalGames = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public MatchlistBySummoner[] newArray(int size) {
            return (new MatchlistBySummoner[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * 
     * @param matches
     *     The matches
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * 
     * @return
     *     The startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * 
     * @param startIndex
     *     The startIndex
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 
     * @return
     *     The endIndex
     */
    public Integer getEndIndex() {
        return endIndex;
    }

    /**
     * 
     * @param endIndex
     *     The endIndex
     */
    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * 
     * @return
     *     The totalGames
     */
    public Integer getTotalGames() {
        return totalGames;
    }

    /**
     * 
     * @param totalGames
     *     The totalGames
     */
    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(matches).append(startIndex).append(endIndex).append(totalGames).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MatchlistBySummoner) == false) {
            return false;
        }
        MatchlistBySummoner rhs = ((MatchlistBySummoner) other);
        return new EqualsBuilder().append(matches, rhs.matches).append(startIndex, rhs.startIndex).append(endIndex, rhs.endIndex).append(totalGames, rhs.totalGames).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(matches);
        dest.writeValue(startIndex);
        dest.writeValue(endIndex);
        dest.writeValue(totalGames);
    }

    public int describeContents() {
        return  0;
    }

}
