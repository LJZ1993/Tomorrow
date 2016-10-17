package com.example.administrator.tomorrow.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ArticleListBean  {
    public boolean error;
    public List<ResultBean> resultBeanList;

    /*
    * {
      "_id": "57e4c303421aa95bc7f06a9d",
      "createdAt": "2016-09-23T13:52:03.800Z",
      "desc": "Android Linker \u4e0e SO \u52a0\u58f3\u6280\u672f",
      "publishedAt": "2016-10-13T11:30:10.490Z",
      "source": "chrome",
      "type": "Android",
      "url": "http://mp.weixin.qq.com/s?__biz=MzA3NTYzODYzMg==&mid=2653577840&idx=1&sn=df50a9ba89673655d0ddc4b3c1bb30e6&scene=0#rd",
      "used": true,
      "who": "LHF"
    }, */
    public static class ResultBean  {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }

}
