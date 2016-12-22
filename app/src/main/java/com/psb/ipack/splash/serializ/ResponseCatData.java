package com.psb.ipack.splash.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mehdi on 12/1/16.
 */

public class ResponseCatData {


    @SerializedName("ParentId")
    @Expose
    private Integer parentId;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("PersianTitle")
    @Expose
    private String persianTitle;
    @SerializedName("LatinTitle")
    @Expose
    private String latinTitle;
    @SerializedName("ImageUrl")
    @Expose
    private Object imageUrl;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsMovable")
    @Expose
    private Boolean isMovable;
    @SerializedName("SortOrder")
    @Expose
    private Integer sortOrder;
    @SerializedName("ModifiedOn")
    @Expose
    private String modifiedOn;
    @SerializedName("DefaultPrice")
    @Expose
    private Integer defaultPrice;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseCatData() {
    }

    /**
     *
     * @param isMovable
     * @param isActive
     * @param id
     * @param modifiedOn
     * @param parentId
     * @param imageUrl
     * @param sortOrder
     * @param latinTitle
     * @param defaultPrice
     * @param persianTitle
     */
    public ResponseCatData(Integer parentId, Integer id, String persianTitle, String latinTitle, Object imageUrl, Boolean isActive, Boolean isMovable, Integer sortOrder, String modifiedOn, Integer defaultPrice) {
        super();
        this.parentId = parentId;
        this.id = id;
        this.persianTitle = persianTitle;
        this.latinTitle = latinTitle;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.isMovable = isMovable;
        this.sortOrder = sortOrder;
        this.modifiedOn = modifiedOn;
        this.defaultPrice = defaultPrice;
    }

    /**
     *
     * @return
     * The parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     *
     * @param parentId
     * The ParentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The persianTitle
     */
    public String getPersianTitle() {
        return persianTitle;
    }

    /**
     *
     * @param persianTitle
     * The PersianTitle
     */
    public void setPersianTitle(String persianTitle) {
        this.persianTitle = persianTitle;
    }

    /**
     *
     * @return
     * The latinTitle
     */
    public String getLatinTitle() {
        return latinTitle;
    }

    /**
     *
     * @param latinTitle
     * The LatinTitle
     */
    public void setLatinTitle(String latinTitle) {
        this.latinTitle = latinTitle;
    }

    /**
     *
     * @param imageUrl
     * The ImageUrl
     */
    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The IsActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public void setIsActive(int isActive) {
        if(isActive==1){
            this.isActive = true;
        }else{
            this.isActive=false;
        }

    }
    /**
     *
     * @return
     * The isMovable
     */
    public Boolean getIsMovable() {
        return isMovable;
    }

    /**
     *
     * @param isMovable
     * The IsMovable
     */
    public void setIsMovable(Boolean isMovable) {
        this.isMovable = isMovable;
    }
    public void setIsMovable(int isMovable) {
        if(isMovable==1){
            this.isMovable = true;
        }else{
            this.isMovable = false;
        }

    }
    /**
     *
     * @return
     * The sortOrder
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     *
     * @param sortOrder
     * The SortOrder
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     *
     * @return
     * The modifiedOn
     */
    public String getModifiedOn() {
        return modifiedOn;
    }

    /**
     *
     * @param modifiedOn
     * The ModifiedOn
     */
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    /**
     *
     * @return
     * The defaultPrice
     */
    public Integer getDefaultPrice() {
        return defaultPrice;
    }

    /**
     *
     * @param defaultPrice
     * The DefaultPrice
     */
    public void setDefaultPrice(Integer defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
    
    public String getImageUrl() {
        if(imageUrl instanceof String){
            return (String)imageUrl;    
        }else{
            return "";
        }
        
    }
}
