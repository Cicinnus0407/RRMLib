package com.cicinnus.retrofitmvprxjava2;

import java.util.List;

/**
 * Created by Cicinnus on 2017/3/29.
 */

public class ResultBean {

    /**
     * date : 20170329
     * stories : [{"images":["https://pic2.zhimg.com/v2-217d4dacc4c7dc3831bf1ddb5cc2448d.jpg"],"type":0,"id":9320956,"ga_prefix":"032915","title":"美食家和吃货的区别，就像赛车手和出租车司机"},{"images":["https://pic2.zhimg.com/v2-c054a5d240dd741b4f2e595f18efec4d.jpg"],"type":0,"id":9319924,"ga_prefix":"032914","title":"为什么手在用力之后会抖？"},{"images":["https://pic2.zhimg.com/v2-2ed863b3984fb829e84fad9107999285.jpg"],"type":0,"id":9321330,"ga_prefix":"032913","title":"大误 · 女娲究竟死得有多惨？"},{"images":["https://pic1.zhimg.com/v2-5d3ac48642ebcdc0962065b61dde4650.jpg"],"type":0,"id":9322370,"ga_prefix":"032913","title":"- 中国是第一文化输出国了？ - 得加上「产品」二字才行"},{"images":["https://pic3.zhimg.com/v2-252eeaa29d5a65591039abfacefedec2.jpg"],"type":0,"id":9320416,"ga_prefix":"032911","title":"「宁可信其有，不可信其无」错在哪儿？"},{"images":["https://pic2.zhimg.com/v2-5ff9ae26d3c2f0a9d721403c62acd98d.jpg"],"type":0,"id":9320401,"ga_prefix":"032910","title":"为了发表《白鹿原》，身为编辑的我们在 25 年前想了又想"},{"images":["https://pic4.zhimg.com/v2-7abbfa7edebb445b5cd17d34a048d4c7.jpg"],"type":0,"id":9321595,"ga_prefix":"032909","title":"如果股票涨跌都是随机的，分析师到底有什么用？"},{"images":["https://pic4.zhimg.com/v2-f10abcd3d1e2cee6f953c1e4b85b9b83.jpg"],"type":0,"id":9320411,"ga_prefix":"032908","title":"黄旭东：我们那一代人没有那么怕输"},{"title":"北京几个一般人不敢去、不知道的地方，清明假期可以试试","ga_prefix":"032907","images":["https://pic4.zhimg.com/v2-35fba3f16a0ab78a655722c41ab36693.jpg"],"multipic":true,"type":0,"id":9321615},{"images":["https://pic4.zhimg.com/v2-af86ad906304ab2331facc9f591d8753.jpg"],"type":0,"id":9320540,"ga_prefix":"032907","title":"「我不是天赋不够，只是不想努力而已」"},{"images":["https://pic3.zhimg.com/v2-91938e97cfc7a01280b0aacdbee6f78a.jpg"],"type":0,"id":9321564,"ga_prefix":"032907","title":"中国的文化娱乐产业，正在被腾讯和阿里跑马圈地"},{"images":["https://pic2.zhimg.com/v2-9028e0af555d142b66369c34b60131bd.jpg"],"type":0,"id":9319951,"ga_prefix":"032906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-ef0f3abd29fb7e2cc001c921a564f037.jpg","type":0,"id":9321564,"ga_prefix":"032907","title":"中国的文化娱乐产业，正在被腾讯和阿里跑马圈地"},{"image":"https://pic2.zhimg.com/v2-32c72c5fa05dde817e15feb96dde6c69.jpg","type":0,"id":9321018,"ga_prefix":"032820","title":"第四届「知乎盐 Club」后记：一座热闹非凡的城市"},{"image":"https://pic2.zhimg.com/v2-6dd3f5d272c052bfcaa3e15d1f411985.jpg","type":0,"id":9320702,"ga_prefix":"032819","title":"伊隆 · 马斯克的新计划，是把人脑和电脑连接起来"},{"image":"https://pic4.zhimg.com/v2-33e01ccdcddc12dc6354109c9c14513f.jpg","type":0,"id":9320329,"ga_prefix":"032814","title":"升级 iOS 10.3 后存储空间变多了，这都是 APFS 的功劳"},{"image":"https://pic2.zhimg.com/v2-1bb9959a1c81e7ddb2dc765e3328f315.jpg","type":0,"id":9317637,"ga_prefix":"032807","title":"程序员在十年后还会有今天的收入吗？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-217d4dacc4c7dc3831bf1ddb5cc2448d.jpg"]
         * type : 0
         * id : 9320956
         * ga_prefix : 032915
         * title : 美食家和吃货的区别，就像赛车手和出租车司机
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic4.zhimg.com/v2-ef0f3abd29fb7e2cc001c921a564f037.jpg
         * type : 0
         * id : 9321564
         * ga_prefix : 032907
         * title : 中国的文化娱乐产业，正在被腾讯和阿里跑马圈地
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
