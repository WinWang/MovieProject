package com.lepoint.ljfmvp.model;

import java.util.List;

public class NewsDetailBean extends BaseModel {
    /**
     * sharePic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2018/05/31/1527775485111049487.jpg
     * informationIds :
     * infoId : ff808081630080600163b3cd87cb03ed
     * author :
     * columnId :
     * synopsis : 根据天下霸唱的鬼吹灯系列小说改编，著名导演非行执导的电影《云南虫谷》于今日重磅发布“摸金归来”版海报和预告，宣布影片正式定档9月30日，进军国庆档。
     * title : 鬼吹灯之《云南虫谷》定档国庆档 摸金校尉归来胡八一云南古墓寻宝
     * url : http://h5.svipmovie.com/information/ff808081630080600163b3cd87cb03ed.shtml
     * labels : [{"labelId":"ff8080815e2c69cf015e2c90049e4f50","labelName":"电影"}]
     * readingQuantity : 210
     * performerIds :
     * picList : []
     * digest :
     * publisher :
     * shareUrl : http://h5.svipmovie.com/informationshare/ff808081630080600163b3cd87cb03ed.shtml
     * contentIds :
     * time : 2018-05-31
     * columnLogo :
     * columnName :
     */

    private RetBean ret;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public static class RetBean {
        private String sharePic;
        private String informationIds;
        private String infoId;
        private String author;
        private String columnId;
        private String synopsis;
        private String title;
        private String url;
        private int readingQuantity;
        private String performerIds;
        private String digest;
        private String publisher;
        private String shareUrl;
        private String contentIds;
        private String time;
        private String columnLogo;
        private String columnName;
        /**
         * labelId : ff8080815e2c69cf015e2c90049e4f50
         * labelName : 电影
         */

        private List<LabelsBean> labels;
        private List<?> picList;

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public String getInformationIds() {
            return informationIds;
        }

        public void setInformationIds(String informationIds) {
            this.informationIds = informationIds;
        }

        public String getInfoId() {
            return infoId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getColumnId() {
            return columnId;
        }

        public void setColumnId(String columnId) {
            this.columnId = columnId;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getReadingQuantity() {
            return readingQuantity;
        }

        public void setReadingQuantity(int readingQuantity) {
            this.readingQuantity = readingQuantity;
        }

        public String getPerformerIds() {
            return performerIds;
        }

        public void setPerformerIds(String performerIds) {
            this.performerIds = performerIds;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getContentIds() {
            return contentIds;
        }

        public void setContentIds(String contentIds) {
            this.contentIds = contentIds;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getColumnLogo() {
            return columnLogo;
        }

        public void setColumnLogo(String columnLogo) {
            this.columnLogo = columnLogo;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public List<?> getPicList() {
            return picList;
        }

        public void setPicList(List<?> picList) {
            this.picList = picList;
        }

        public static class LabelsBean {
            private String labelId;
            private String labelName;

            public String getLabelId() {
                return labelId;
            }

            public void setLabelId(String labelId) {
                this.labelId = labelId;
            }

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }
        }
    }
}
