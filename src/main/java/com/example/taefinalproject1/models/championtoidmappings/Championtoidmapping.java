
package com.example.taefinalproject1.models.championtoidmappings;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
public class Championtoidmapping implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Creator<Championtoidmapping> CREATOR = new Creator<Championtoidmapping>() {


        public Championtoidmapping createFromParcel(Parcel in) {
            Championtoidmapping instance = new Championtoidmapping();
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.version = ((String) in.readValue((String.class.getClassLoader())));
            instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
            return instance;
        }

        public Championtoidmapping[] newArray(int size) {
            return (new Championtoidmapping[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(version).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Championtoidmapping) == false) {
            return false;
        }
        Championtoidmapping rhs = ((Championtoidmapping) other);
        return new EqualsBuilder().append(type, rhs.type).append(version, rhs.version).append(data, rhs.data).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(version);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
