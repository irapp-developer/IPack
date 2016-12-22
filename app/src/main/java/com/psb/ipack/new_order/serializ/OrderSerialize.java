package com.psb.ipack.new_order.serializ;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mehdi on 12/10/16.
 */

public class OrderSerialize implements Serializable, Parcelable {
    @SerializedName("ServerRequestId")
    @Expose
    private Integer ServerRequestId;
    @SerializedName("SecurityCode")
    @Expose
    private String SecurityCode;
    @SerializedName("SourceGeo")
    @Expose
    private GeoModel sourceGeo;
    @SerializedName("DestenationGeo")
    @Expose
    private GeoModel destenationGeo;
    @SerializedName("FCMToken")
    @Expose
    private String fcmToken;
    @SerializedName("CategoryId")
    @Expose
    private Integer orderId;
    @SerializedName("orderName")
    @Expose
    private String orderName = "";
    @SerializedName("startPoint")
    @Expose
    private LatLng startPoint;
    @SerializedName("endPoint")
    @Expose
    private LatLng endPoint;
    @SerializedName("price")
    @Expose
    private String price = "";
    @SerializedName("DistancePerKM")
    @Expose
    private Integer distance;
    @SerializedName("userName")
    @Expose
    private String userName = "";
    @SerializedName("firstName")
    @Expose
    private String firstName = "";
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber = "";
    @SerializedName("Address")
    @Expose
    private String address = "";
    @SerializedName("Description")
    @Expose
    private String descr = "";
    @SerializedName("defaultPrice")
    @Expose
    private int defaultPrice = 0;
    @SerializedName("finalPrice")
    @Expose
    private int finalPrice = 0;
    @SerializedName("createTime")
    @Expose
    private String createTime = "";

    @Expose(serialize = false, deserialize = false)
    private List<LatLng> arrRoutes;
    
    @Expose(serialize = false, deserialize = false)
    private String routeRawString;


    public final static Parcelable.Creator<OrderSerialize> CREATOR = new Creator<OrderSerialize>() {
        @SuppressWarnings({
                "unchecked"
        })
        @Override
        public OrderSerialize createFromParcel(Parcel in) {
            OrderSerialize instance = new OrderSerialize();
            instance.ServerRequestId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.SecurityCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.sourceGeo = ((GeoModel) in.readValue((GeoModel.class.getClassLoader())));
            instance.destenationGeo = ((GeoModel) in.readValue((GeoModel.class.getClassLoader())));
            instance.fcmToken = ((String) in.readValue((String.class.getClassLoader())));
            instance.orderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.orderName = ((String) in.readValue((String.class.getClassLoader())));
            instance.startPoint = ((LatLng) in.readValue((LatLng.class.getClassLoader())));
            instance.endPoint = ((LatLng) in.readValue((LatLng.class.getClassLoader())));
            instance.price = ((String) in.readValue((String.class.getClassLoader())));
            instance.distance = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.firstName = ((String) in.readValue((String.class.getClassLoader())));
            instance.userName = ((String) in.readValue((String.class.getClassLoader())));
            instance.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.descr = ((String) in.readValue((String.class.getClassLoader())));
            instance.defaultPrice = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.finalPrice = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.arrRoutes = ((List<LatLng>) in.readValue((List.class.getClassLoader())));
            instance.createTime = ((String) in.readValue((String.class.getClassLoader()))); 
            instance.routeRawString = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        @Override
        public OrderSerialize[] newArray(int size) {
            return (new OrderSerialize[size]);
        }

    };
    
    private final static long serialVersionUID = -5884798807830150597L;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ServerRequestId);
        dest.writeValue(SecurityCode);
        dest.writeValue(sourceGeo);
        dest.writeValue(destenationGeo);
        dest.writeValue(fcmToken);
        dest.writeValue(orderId);
        dest.writeValue(orderName);
        dest.writeValue(startPoint);
        dest.writeValue(endPoint);
        dest.writeValue(price);
        dest.writeValue(distance);
        dest.writeValue(firstName);
        dest.writeValue(userName);
        dest.writeValue(phoneNumber);
        dest.writeValue(address);
        dest.writeValue(descr);
        dest.writeValue(defaultPrice);
        dest.writeValue(finalPrice);
        dest.writeValue(arrRoutes);
        dest.writeValue(createTime);
        dest.writeValue(routeRawString);
    }
    /**
     * No args constructor for use in serialization
     */
    public OrderSerialize() {
    }

    /**
     * @param distance
     * @param price
     * @param phoneNumber
     * @param endPoint
     * @param address
     * @param startPoint
     * @param orderName
     * @param firstName
     * @param descr
     * @param orderId
     */
    public OrderSerialize(Integer orderId, String orderName, LatLng startPoint, LatLng endPoint, String price, Integer distance, String firstName, String phoneNumber, String address, String descr, int defaultPrice, int finalPrice, List<LatLng> arrRoutes) {
        super();
        this.orderId = orderId;
        this.orderName = orderName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.price = price;
        this.distance = distance;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.descr = descr;
        this.defaultPrice = defaultPrice;
        this.finalPrice = finalPrice;
        this.arrRoutes = arrRoutes;
    }

    /**
     * @param securityCode The orderId
     */
    public void setSecurityCode(String securityCode) {
        this.SecurityCode = securityCode;
    }

    /**
     * @param destenationGeo The orderId
     */
    public void setDestenationGeo(GeoModel destenationGeo) {
        this.destenationGeo = destenationGeo;
    }

    /**
     * @param fcmToken The orderId
     */
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    /**
     * @param sourceGeo The orderId
     */
    public void setSourceGeo(GeoModel sourceGeo) {
        this.sourceGeo = sourceGeo;
    }

    /**
     * @return The orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param serverRequestId The serverRequestId
     */
    public void setServerRequestId(int serverRequestId) {
        this.ServerRequestId = serverRequestId;
    }

    /**
     * @return The ServerRequestId
     */
    public int getServerRequestId() {
        return ServerRequestId;
    }

    /**
     * @param createTime The orderId
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return The createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param orderId The orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return The orderName
     */
    public String getRouteRawString() {
        return routeRawString;
    }

    /**
     * @param routeRawString The orderName
     */
    public void setRouteRawString(String routeRawString) {
        this.routeRawString = routeRawString;
    }

    /**
     * @return The orderName
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName The orderName
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return The stratPoint
     */
    public LatLng getStartPoint() {
        return startPoint;
    }

    /**
     * @param startLatLng The stratPoint
     */
    public void setStartPoint(LatLng startLatLng) {
        this.startPoint = startLatLng;
    }

    /**
     * @return The endPoint
     */
    public LatLng getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint The endPoint
     */
    public void setEndPoint(LatLng endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @return The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return The distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName The firstName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber The phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr The descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * @return The defaultPrice
     */

    public int geDefaultPrice() {
        return defaultPrice;
    }


    /**
     * @param defaultPrice The descr
     */
    public void setDefaultPrice(int defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    /**
     * @return The defaultPrice
     */

    public int getFinalPrice() {
        return finalPrice;
    }

    /**
     * @param finalPrice The descr
     */
    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }


    /**
     * @param arrRoutes The descr
     */

    public void setArrRoutes(List<LatLng> arrRoutes) {
        this.arrRoutes = arrRoutes;
    }

    /**
     * @return The arrRoutes
     */
    public List<LatLng> getArrRoutes() {
        return arrRoutes;
    }



    public int describeContents() {
        return 0;
    }

}
