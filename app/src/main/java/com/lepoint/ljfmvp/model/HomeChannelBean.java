package com.lepoint.ljfmvp.model;

import java.util.List;

public class HomeChannelBean extends BaseModel {

    private RetBean ret;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public static class RetBean {
        /**
         * loadType : videoList
         * dataId : 402834815584e463015584e538140009
         * htmlUrl :
         * loadUrl : http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e538140009&information=information
         * scriptPic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2018/02/25/1519493183422041610.png
         * title : 精彩推荐
         * type : 0
         */

        private List<ColumnListBean> columnList;

        public List<ColumnListBean> getColumnList() {
            return columnList;
        }

        public void setColumnList(List<ColumnListBean> columnList) {
            this.columnList = columnList;
        }

        public static class ColumnListBean {
            private String loadType;
            private String dataId;
            private String htmlUrl;
            private String loadUrl;
            private String scriptPic;
            private String title;
            private int type;

            public String getLoadType() {
                return loadType;
            }

            public void setLoadType(String loadType) {
                this.loadType = loadType;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getHtmlUrl() {
                return htmlUrl;
            }

            public void setHtmlUrl(String htmlUrl) {
                this.htmlUrl = htmlUrl;
            }

            public String getLoadUrl() {
                return loadUrl;
            }

            public void setLoadUrl(String loadUrl) {
                this.loadUrl = loadUrl;
            }

            public String getScriptPic() {
                return scriptPic;
            }

            public void setScriptPic(String scriptPic) {
                this.scriptPic = scriptPic;
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
    }
}
