package com.lepoint.ljfmvp.model;

import java.util.List;

public class HistoryListBean extends BaseModel {

    /**
     * pnum : 1
     * totalRecords : 2
     * records : 50
     * list : [{"airTime":2012,"angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png","historyRemainTime":"00:00:00","description":"曾荣获奥斯卡金像奖的著名导演安德鲁·斯坦顿带来全新力作《异星战场》，一部以神秘壮丽的火星为背景的史诗动作冒险巨制。影片根据埃德加·赖斯·巴勒斯于20世纪初创作的经典科幻小说《火星公主》改编，故事 讲述美国内战时期，饱受创伤的前军官约翰·卡特（泰勒·克奇 Taylor Kitsch 饰）无意间穿越到了火星。由于引力不同，约翰成为了力大无穷、弹跳如飞的\u201c超人\u201d，也因此卷入当地几大族群的冲突，周旋于美丽的公主德佳·索丽斯（琳恩·柯林斯 Lynn Collins 饰），英勇的盟友塔斯·塔卡斯（威廉·达福 Willem Dafoe 饰）与狡猾的敌人萨博·赞恩之间。然而地球上战火纷飞带来的痛苦，早已让约翰无心恋战；虽然几大族群都将他视为能够拯救火星的关键人物，可他真的能抛下过去，担负起保卫人民的重任，拯救这个水深火热的世界，扭转异星战场的命运吗？","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/08/1496905897720078142.jpg","title":"异星战场","duration":"02:02:00","loadtype":"video","score":0,"dataId":"77390a4754d94e0494a23490c7bdfe77","createTime":"今天","lastPlayTime":"0","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=77390a4754d94e0494a23490c7bdfe77&deviceId=352284043595770&userId=352284043595770","shareURL":"http://m.svipmovie.com/#/moviedetails/77390a4754d94e0494a23490c7bdfe77"},{"airTime":2016,"angleIcon":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png","historyRemainTime":"00:00:00","description":"患有短期健忘症的多莉（艾伦·德杰尼勒斯 Ellen DeGeneres 配音）曾经也拥有美好的家庭，然而一连串的意外让她与和蔼可亲的父母失散。此去经年，多莉邂逅了小丑鱼父子马林（艾伯特·布鲁克斯 Albert Brooks 配音）和尼莫（海登·罗兰斯 Hayden Rolence 配音），与他们经历了前所未有的大冒险。平静的日子里，童年时期的片段记忆突然闯入多莉的脑海，于是她再度踏上寻找父母的旅程。这一次，她来到了海洋生物博物馆，遇到了不打算回到大海的章鱼汉克（艾德·奥尼尔 Ed O'Neill 配音）以及童年时的朋友鲸鲨运儿（凯特琳·奥尔森 Kaitlin Olson 配音）。循着记忆中的蛛丝马迹，她离父母越来越近。然而在这一过程中，她又和马林父子失散了。 \r\n　　对友情、亲情的执着，让这群小家伙无论千难万险也要坚持到底\u2026\u2026","pic":"http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/08/1496905591136030222.jpg","title":"海底总动员2：多莉去哪儿","duration":"01:33:08","loadtype":"video","score":0,"dataId":"5ba7246292a24d1f8add25e4a983b3ed","createTime":"今天","lastPlayTime":"0","loadURL":"http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=5ba7246292a24d1f8add25e4a983b3ed&deviceId=352284043595770&userId=352284043595770","shareURL":"http://m.svipmovie.com/#/moviedetails/5ba7246292a24d1f8add25e4a983b3ed"}]
     * totalPnum : 1
     */

    private RetBean ret;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public static class RetBean {
        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        /**
         * airTime : 2012
         * angleIcon : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296614609066838.png
         * historyRemainTime : 00:00:00
         * description : 曾荣获奥斯卡金像奖的著名导演安德鲁·斯坦顿带来全新力作《异星战场》，一部以神秘壮丽的火星为背景的史诗动作冒险巨制。影片根据埃德加·赖斯·巴勒斯于20世纪初创作的经典科幻小说《火星公主》改编，故事 讲述美国内战时期，饱受创伤的前军官约翰·卡特（泰勒·克奇 Taylor Kitsch 饰）无意间穿越到了火星。由于引力不同，约翰成为了力大无穷、弹跳如飞的“超人”，也因此卷入当地几大族群的冲突，周旋于美丽的公主德佳·索丽斯（琳恩·柯林斯 Lynn Collins 饰），英勇的盟友塔斯·塔卡斯（威廉·达福 Willem Dafoe 饰）与狡猾的敌人萨博·赞恩之间。然而地球上战火纷飞带来的痛苦，早已让约翰无心恋战；虽然几大族群都将他视为能够拯救火星的关键人物，可他真的能抛下过去，担负起保卫人民的重任，拯救这个水深火热的世界，扭转异星战场的命运吗？
         * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/06/08/1496905897720078142.jpg
         * title : 异星战场
         * duration : 02:02:00
         * loadtype : video
         * score : 0
         * dataId : 77390a4754d94e0494a23490c7bdfe77
         * createTime : 今天
         * lastPlayTime : 0
         * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=77390a4754d94e0494a23490c7bdfe77&deviceId=352284043595770&userId=352284043595770
         * shareURL : http://m.svipmovie.com/#/moviedetails/77390a4754d94e0494a23490c7bdfe77
         */

        private List<ListBean> list;

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotalPnum() {
            return totalPnum;
        }

        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int airTime;
            private String angleIcon;
            private String historyRemainTime;
            private String description;
            private String pic;
            private String title;
            private String duration;
            private String loadtype;
            private String score;
            private String dataId;
            private String createTime;
            private String lastPlayTime;
            private String loadURL;
            private String shareURL;

            public int getAirTime() {
                return airTime;
            }

            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }

            public String getAngleIcon() {
                return angleIcon;
            }

            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }

            public String getHistoryRemainTime() {
                return historyRemainTime;
            }

            public void setHistoryRemainTime(String historyRemainTime) {
                this.historyRemainTime = historyRemainTime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadtype() {
                return loadtype;
            }

            public void setLoadtype(String loadtype) {
                this.loadtype = loadtype;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getLastPlayTime() {
                return lastPlayTime;
            }

            public void setLastPlayTime(String lastPlayTime) {
                this.lastPlayTime = lastPlayTime;
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
        }
    }
}
