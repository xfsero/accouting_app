package com.stupidwind.myaccounting.model;

/**
 * Created by 蠢风 on 2018/4/5.
 */

public class AccoutingDetail {

    /**
     * 图片ID
     */
    private int imageId;

    /**
     * 记账名称
     */
    private String accoutingName;

    /**
     * 记账类型
     */
    private String accoutingType;

    /**
     * 记账数值
     */
    private Integer accoutingValue;

    public String getAccoutingName() {
        return accoutingName;
    }

    public void setAccoutingName(String accoutingName) {
        this.accoutingName = accoutingName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAccoutingType() {
        return accoutingType;
    }

    public void setAccoutingType(String accoutingType) {
        this.accoutingType = accoutingType;
    }

    public Integer getAccoutingValue() {
        return accoutingValue;
    }

    public void setAccoutingValue(Integer accoutingValue) {
        this.accoutingValue = accoutingValue;
    }
}
