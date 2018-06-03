package com.lepoint.ljfmvp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

public class StoryListBean extends BaseModel {
    /**
     * booklist : [{"BookId":265,"BookType":0,"BookName":"《故事会文摘版》2018年5月","BookAbout":"《故事会文摘版》2018年5月","BookImg":"http://storyimg.storychina.cn//uploads/20180518/201805181105439219167.jpg","BookReader":16,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-05-18","BookTerm":"2018年5月","userFav":0,"userUp":0,"userBuy":0},{"BookId":264,"BookType":0,"BookName":"2018年5月下半月","BookAbout":"开卷故事\t\t 2\r\n笑话15则\t吴\u2003昆等\t 4\r\n新传说\r\n拜年有鲤\t贺小波\t8\r\n保健人生\t吴\u2003嫡\t11\r\n布局与点睛\t龙飞天 \t21\r\n金光穿洞\t魏\u2003炜\t25\r\n万能的老爹\t木逾石\t29\r\n开锁子\t崔建华\t33\r\n君子报仇\t孙国彦\t36\r\n诙段子\t\t15\r\n传闻轶事\r\n最后一笔生意\t林扶霄\t17\r\n情节聚焦\r\n讹的就是你\t曹景建\t39\r\n民间故事金库\r\n杨大郎断指\t李培竹\t43\r\n职场故事\r\n","BookImg":"http://storyimg.storychina.cn//uploads/20180515/201805151020366346462.jpg","BookReader":33,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-05-15","BookTerm":"5月下半月","userFav":0,"userUp":0,"userBuy":0},{"BookId":263,"BookType":0,"BookName":"2018年5月上半月","BookAbout":"开卷故事\t 2\r\n笑话15则\t何有财 等   4\r\n民间故事金库\r\n倒立着的老婆\t彭桂峰   8\r\n网文热读\r\n星际动物园\t爱德华.D.霍克  10\r\n残猴\t石  磊  58\r\n新传说\r\n\u201c布谷鸟\u201d叫了\t张  宇  12\r\n甜出眼泪的蛋糕\t任黎明  17\r\n换空调\t林火坤  21\r\n送外卖\t侯晓琪  24\r\n惹祸的野蘑菇\t任宏伟  29\r\n名人手印\t博陵人  82\r\n诙段子\t15\r\n外国文学故事鉴","BookImg":"http://storyimg.storychina.cn//uploads/20180428/201804280202257454848.jpg","BookReader":42,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-04-28","BookTerm":"2018年5月上","userFav":0,"userUp":0,"userBuy":0},{"BookId":262,"BookType":0,"BookName":"《故事会文摘版》2018年4月","BookAbout":"《故事会文摘版》2018年4月","BookImg":"http://storyimg.storychina.cn//uploads/20180420/201804201058276373680.jpg","BookReader":23,"BookScore":0,"BookWriter":null,"BookSizeWords":0,"BookMoneyAll":0,"BookMoneyMin":0,"BookOver":0,"BookDateUp":"2018-04-20","BookTerm":"2018年4月","userFav":0,"userUp":0,"userBuy":0}]
     * errcode : 0
     * errmsg : 执行成功
     * countNum : 87
     * maxPage : 22
     */

    private int errcode;
    private String errmsg;
    private int countNum;
    private int maxPage;
    /**
     * BookId : 265
     * BookType : 0
     * BookName : 《故事会文摘版》2018年5月
     * BookAbout : 《故事会文摘版》2018年5月
     * BookImg : http://storyimg.storychina.cn//uploads/20180518/201805181105439219167.jpg
     * BookReader : 16
     * BookScore : 0
     * BookWriter : null
     * BookSizeWords : 0
     * BookMoneyAll : 0
     * BookMoneyMin : 0
     * BookOver : 0
     * BookDateUp : 2018-05-18
     * BookTerm : 2018年5月
     * userFav : 0
     * userUp : 0
     * userBuy : 0
     */

    private List<BooklistBean> booklist;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<BooklistBean> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<BooklistBean> booklist) {
        this.booklist = booklist;
    }

    public static class BooklistBean {
        private int BookId;
        private int BookType;
        private String BookName;
        private String BookAbout;
        private String BookImg;
        private int BookReader;
        private int BookScore;
        private Object BookWriter;
        private int BookSizeWords;
        private int BookMoneyAll;
        private int BookMoneyMin;
        private int BookOver;
        private String BookDateUp;
        private String BookTerm;
        private int userFav;
        private int userUp;
        private int userBuy;

        public int getBookId() {
            return BookId;
        }

        public void setBookId(int BookId) {
            this.BookId = BookId;
        }

        public int getBookType() {
            return BookType;
        }

        public void setBookType(int BookType) {
            this.BookType = BookType;
        }

        public String getBookName() {
            return BookName;
        }

        public void setBookName(String BookName) {
            this.BookName = BookName;
        }

        public String getBookAbout() {
            return BookAbout;
        }

        public void setBookAbout(String BookAbout) {
            this.BookAbout = BookAbout;
        }

        public String getBookImg() {
            return BookImg;
        }

        public void setBookImg(String BookImg) {
            this.BookImg = BookImg;
        }

        public int getBookReader() {
            return BookReader;
        }

        public void setBookReader(int BookReader) {
            this.BookReader = BookReader;
        }

        public int getBookScore() {
            return BookScore;
        }

        public void setBookScore(int BookScore) {
            this.BookScore = BookScore;
        }

        public Object getBookWriter() {
            return BookWriter;
        }

        public void setBookWriter(Object BookWriter) {
            this.BookWriter = BookWriter;
        }

        public int getBookSizeWords() {
            return BookSizeWords;
        }

        public void setBookSizeWords(int BookSizeWords) {
            this.BookSizeWords = BookSizeWords;
        }

        public int getBookMoneyAll() {
            return BookMoneyAll;
        }

        public void setBookMoneyAll(int BookMoneyAll) {
            this.BookMoneyAll = BookMoneyAll;
        }

        public int getBookMoneyMin() {
            return BookMoneyMin;
        }

        public void setBookMoneyMin(int BookMoneyMin) {
            this.BookMoneyMin = BookMoneyMin;
        }

        public int getBookOver() {
            return BookOver;
        }

        public void setBookOver(int BookOver) {
            this.BookOver = BookOver;
        }

        public String getBookDateUp() {
            return BookDateUp;
        }

        public void setBookDateUp(String BookDateUp) {
            this.BookDateUp = BookDateUp;
        }

        public String getBookTerm() {
            return BookTerm;
        }

        public void setBookTerm(String BookTerm) {
            this.BookTerm = BookTerm;
        }

        public int getUserFav() {
            return userFav;
        }

        public void setUserFav(int userFav) {
            this.userFav = userFav;
        }

        public int getUserUp() {
            return userUp;
        }

        public void setUserUp(int userUp) {
            this.userUp = userUp;
        }

        public int getUserBuy() {
            return userBuy;
        }

        public void setUserBuy(int userBuy) {
            this.userBuy = userBuy;
        }
    }
}
