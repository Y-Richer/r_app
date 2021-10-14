package com.richer.wa.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * "apkLink": "",
 *                 "audit": 1,
 *                 "author": "",
 *                 "canEdit": false,
 *                 "chapterId": 198,
 *                 "chapterName": "基础概念",
 *                 "collect": false,
 *                 "courseId": 13,
 *                 "desc": "",
 *                 "descMd": "",
 *                 "envelopePic": "",
 *                 "fresh": false,
 *                 "host": "",
 *                 "id": 20086,
 *                 "link": "https://juejin.cn/post/7008335634263703589",
 *                 "niceDate": "1天前",
 *                 "niceShareDate": "2天前",
 *                 "origin": "",
 *                 "prefix": "",
 *                 "projectLink": "",
 *                 "publishTime": 1633866541000,
 *                 "realSuperChapterId": 167,
 *                 "selfVisible": 0,
 *                 "shareDate": 1633862774000,
 *                 "shareUser": "鸿洋",
 *                 "superChapterId": 168,
 *                 "superChapterName": "基础知识",
 *                 "tags": [],
 *                 "title": "Android Framework 源码下载",
 *                 "type": 0,
 *                 "userId": 2,
 *                 "visible": 1,
 *                 "zan": 0
 */
public class ArticleInfo {
    private boolean top;

    @SerializedName("apkLink")
    private String apkLink;

    @SerializedName("audit")
    private int audit;

    @SerializedName("author")
    private String author;

    @SerializedName("canEdit")
    private boolean canEdit;

    @SerializedName("chapterId")
    private int chapterId;

    @SerializedName("chapterName")
    private String chapterName;

    @SerializedName("collect")
    private boolean collect;

    @SerializedName("courseId")
    private int courseId;

    @SerializedName("desc")
    private String desc;

    @SerializedName("descMd")
    private String descMd;

    @SerializedName("envelopePic")
    private String envelopePic;

    @SerializedName("fresh")
    private boolean fresh;

    @SerializedName("host")
    private String host;

    @SerializedName("id")
    private int id;

    @SerializedName("link")
    private String link;

    @SerializedName("niceDate")
    private String niceDate;

    @SerializedName("niceShareDate")
    private String niceShareDate;

    @SerializedName("origin")
    private String origin;

    @SerializedName("prefix")
    private String prefix;

    @SerializedName("projectLink")
    private String projectLink;

    @SerializedName("publishTime")
    private long publishTime;

    @SerializedName("realSuperChapterId")
    private int realSuperChapterId;

    @SerializedName("selfVisible")
    private int selfVisible;

    @SerializedName("shareDate")
    private long shareDate;

    @SerializedName("shareUser")
    private String shareUser;

    @SerializedName("superChapterId")
    private int superChapterId;

    @SerializedName("superChapterName")
    private String superChapterName;

    @SerializedName("tags")
    private List<ArticleTag> tags;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private int type;

    @SerializedName("userId")
    private int userId;

    @SerializedName("visible")
    private int visible;

    @SerializedName("zan")
    private int zan;

    public static class ArticleTag {
        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescMd() {
        return descMd;
    }

    public void setDescMd(String descMd) {
        this.descMd = descMd;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getRealSuperChapterId() {
        return realSuperChapterId;
    }

    public void setRealSuperChapterId(int realSuperChapterId) {
        this.realSuperChapterId = realSuperChapterId;
    }

    public int getSelfVisible() {
        return selfVisible;
    }

    public void setSelfVisible(int selfVisible) {
        this.selfVisible = selfVisible;
    }

    public long getShareDate() {
        return shareDate;
    }

    public void setShareDate(long shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public List<ArticleTag> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTag> tags) {
        this.tags = tags;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

}
