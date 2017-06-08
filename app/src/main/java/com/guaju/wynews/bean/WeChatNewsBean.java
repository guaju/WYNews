package com.guaju.wynews.bean;

import java.util.ArrayList;

/**
 * Created by root on 17-6-2.
 */

public class WeChatNewsBean {
    private int code;
    private String msg;
    private ArrayList<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(ArrayList<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-06-02
         * title : 每7分钟就有1人患癌，医学专家总结了10条不得癌建议，一定要知道！
         * description : 说钱
         * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-25320118.static/640
         * url : https://mp.weixin.qq.com/s?__biz=MzIwNTQxMzk5Nw==&idx=5&mid=2247489754&sn=be5e515daf35c1b6e9c5c087079f56b9
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
