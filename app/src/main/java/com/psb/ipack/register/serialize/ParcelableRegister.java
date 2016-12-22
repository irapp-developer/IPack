
package com.psb.ipack.register.serialize;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParcelableRegister implements Serializable, Parcelable
{

    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Location")
    @Expose
    private Location location;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("FCMToken")
    @Expose
    private String fCMToken;
    @SerializedName("CityId")
    @Expose
    private Integer cityId;
    public final static Creator<ParcelableRegister> CREATOR = new Creator<ParcelableRegister>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ParcelableRegister createFromParcel(Parcel in) {
            ParcelableRegister instance = new ParcelableRegister();
            instance.securityCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
            instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.location = ((Location) in.readValue((Location.class.getClassLoader())));
            instance.userName = ((String) in.readValue((String.class.getClassLoader())));
            instance.password = ((String) in.readValue((String.class.getClassLoader())));
            instance.fCMToken = ((String) in.readValue((String.class.getClassLoader())));
            instance.cityId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public ParcelableRegister[] newArray(int size) {
            return (new ParcelableRegister[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1820773250772554630L;

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFCMToken() {
        return fCMToken;
    }

    public void setFCMToken(String fCMToken) {
        this.fCMToken = fCMToken;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(securityCode);
        dest.writeValue(phoneNumber);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(description);
        dest.writeValue(address);
        dest.writeValue(location);
        dest.writeValue(userName);
        dest.writeValue(password);
        dest.writeValue(fCMToken);
        dest.writeValue(cityId);
    }

    public int describeContents() {
        return  0;
    }

}
