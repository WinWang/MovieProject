package com.lepoint.ljfmvp.model;

/**
 * Created by Administrator on 2016-08-10.
 */
public class UpdateBean extends BaseModel {
    //app名字
    private String appName;
    //服务器版本
    private int versionNo;
    //发布时间
    private long releaseDate;
    //包名
    private String packageName;
    //版本号
    private String version;
    //强制升级
    private boolean forceUpdate;
    //app最新版本地址
    private String downloadUrl;
    //升级信息
    private String notice;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "UpdateBean{" +
                "appName='" + appName + '\'' +
                ", versionNo=" + versionNo +
                ", releaseDate=" + releaseDate +
                ", packageName='" + packageName + '\'' +
                ", version='" + version + '\'' +
                ", forceUpdate=" + forceUpdate +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
