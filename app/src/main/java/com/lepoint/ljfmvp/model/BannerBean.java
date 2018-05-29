package com.lepoint.ljfmvp.model;

import java.util.List;

/**
 * Created by admin on 2016/10/17.
 */
public class BannerBean extends BaseModel{

    /**
     * advertismentList : [{"imageUrl":"https://www.baidu.com/img/bd_logo1.png","linkAddr":"http://www.baidu.com","remark":"百度1"},{"imageUrl":"https://www.baidu.com/img/bd_logo1.png","linkAddr":"http://www.baidu.com","remark":"百度2"},{"imageUrl":"https://www.baidu.com/img/bd_logo1.png","linkAddr":"http://www.baidu.com","remark":"百度3"},{"imageUrl":"https://www.baidu.com/img/bd_logo1.png","linkAddr":"http://www.baidu.com","remark":"百度4"}]
     * message : 查询成功
     * resultCode : 0
     */

    /**
     * imageUrl : https://www.baidu.com/img/bd_logo1.png
     * linkAddr : http://www.baidu.com
     * remark : 百度1
     */

    private List<AdvertismentListBean> advertismentList;


    public List<AdvertismentListBean> getAdvertismentList() {
        return advertismentList;
    }

    public void setAdvertismentList(List<AdvertismentListBean> advertismentList) {
        this.advertismentList = advertismentList;
    }

    public static class AdvertismentListBean {
        private String imageUrl;
        private String linkAddr;
        private String remark;
        private String shareConfigId;
        private String eventId;
        private String eventName;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLinkAddr() {
            return linkAddr;
        }

        public void setLinkAddr(String linkAddr) {
            this.linkAddr = linkAddr;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getShareConfigId() {
            return shareConfigId;
        }

        public void setShareConfigId(String shareConfigId) {
            this.shareConfigId = shareConfigId;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }
    }
}
