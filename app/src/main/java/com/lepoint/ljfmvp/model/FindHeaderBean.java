package com.lepoint.ljfmvp.model;

import java.util.List;

public class FindHeaderBean extends BaseModel {
    private RetBean ret;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public static class RetBean {
        /**
         * icon : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/10/24/1508814987517089456.png
         * loadURL : /front/columns/getVideoList.do?catalogId=ff808081577013d801577053a3be002c&liveTag=liveTag&information=information
         * title : 直播
         * type : 2
         */

        private List<MenuListBean> menuList;
        /**
         * airTime : 0
         * duration : 00:02:19
         * loadType : video
         * score : 0
         * angleIcon :
         * dataId : 223_de43c776a65e47be90dcf1607de2f5bc
         * description : 蔡卓妍谈与钟欣潼姐妹情，自曝婚礼现场从头哭到尾。
         * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=223_de43c776a65e47be90dcf1607de2f5bc
         * shareURL : http://m.svipmovie.com/#/moviedetails/223_de43c776a65e47be90dcf1607de2f5bc
         * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2018/05/30/1527671516116093137.jpg
         * title : 阿娇终于嫁了!阿Sa泪洒现场！
         * roomId :
         */

        private List<BannerListBean> bannerList;

        public List<MenuListBean> getMenuList() {
            return menuList;
        }

        public void setMenuList(List<MenuListBean> menuList) {
            this.menuList = menuList;
        }

        public List<BannerListBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListBean> bannerList) {
            this.bannerList = bannerList;
        }

        public static class MenuListBean {
            private String icon;
            private String loadURL;
            private String title;
            private int type;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getLoadURL() {
                return loadURL;
            }

            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class BannerListBean {
            private int airTime;
            private String duration;
            private String loadType;
            private int score;
            private String angleIcon;
            private String dataId;
            private String description;
            private String loadURL;
            private String shareURL;
            private String pic;
            private String title;
            private String roomId;

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadType() {
                return loadType;
            }

            public void setLoadType(String loadType) {
                this.loadType = loadType;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLoadURL() {
                return loadURL;
            }

            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}
