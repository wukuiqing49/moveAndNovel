package com.wkq.net.model;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/28
 * <p>
 * 简介:
 */
public class MoveDbReviewsInfo {

    /**
     * id : 278
     * page : 1
     * results : [{"author":"elshaarawy","content":"very good movie 9.5/10 محمد الشعراوى","id":"5723a329c3a3682e720005db","url":"https://www.themoviedb.org/review/5723a329c3a3682e720005db"},{"author":"John Chard","content":"Some birds aren't meant to be caged.\r\n\r\nThe Shawshank Redemption is written and directed by Frank Darabont. It is an adaptation of the Stephen King novella Rita Hayworth and Shawshank Redemption. Starring Tim Robbins and Morgan Freeman, the film portrays the story of Andy Dufresne (Robbins), a banker who is sentenced to two life sentences at Shawshank State Prison for apparently murdering his wife and her lover. Andy finds it tough going but finds solace in the friendship he forms with fellow inmate Ellis \"Red\" Redding (Freeman). While things start to pick up when the warden finds Andy a prison job more befitting his talents as a banker. However, the arrival of another inmate is going to vastly change things for all of them.\r\n\r\nThere was no fanfare or bunting put out for the release of the film back in 94, with a title that didn't give much inkling to anyone about what it was about, and with Columbia Pictures unsure how to market it, Shawshank Redemption barely registered at the box office. However, come Academy Award time the film received several nominations, and although it won none, it stirred up interest in the film for its home entertainment release. The rest, as they say, is history. For the film finally found an audience that saw the film propelled to almost mythical proportions as an endearing modern day classic. Something that has delighted its fans, whilst simultaneously baffling its detractors. One thing is for sure, though, is that which ever side of the Shawshank fence you sit on, the film continues to gather new fans and simply will never go away or loose that mythical status.\r\n\r\nIt's possibly the simplicity of it all that sends some haters of the film into cinematic spasms. The implausible plot and an apparent sentimental edge that makes a nonsense of prison life, are but two chief complaints from those that dislike the film with a passion. Yet when characters are this richly drawn, and so movingly performed, it strikes me as churlish to do down a human drama that's dealing in hope, friendship and faith. The sentimental aspect is indeed there, but that acts as a counterpoint to the suffering, degradation and shattering of the soul involving our protagonist. Cosy prison life you say? No chance. The need for human connection is never more needed than during incarceration, surely? And given the quite terrific performances of Robbins (never better) & Freeman (sublimely making it easy), it's the easiest thing in the world to warm to Andy and Red.\r\n\r\nThose in support aren't faring too bad either. Bob Gunton is coiled spring smarm as Warden Norton, James Whitmore is heart achingly great as the \"Birdman Of Shawshank,\" Clancy Brown is menacing as antagonist Capt. Byron Hadley, William Sadler amusing as Heywood & Mark Rolston is impressively vile as Bogs Diamond. Then there's Roger Deakins' lush cinematography as the camera gracefully glides in and out of the prison offering almost ethereal hope to our characters (yes, they are ours). The music pings in conjunction with the emotional flow of the movie too. Thomas Newman's score is mostly piano based, dovetailing neatly with Andy's state of mind, while the excellently selected soundtrack ranges from the likes of Hank Williams to the gorgeous Le Nozze di Figaro by Mozart.\r\n\r\nIf you love Shawshank then it's a love that lasts a lifetime. Every viewing brings the same array of emotions - anger - revilement - happiness - sadness - inspiration and a warmth that can reduce the most hardened into misty eyed wonderment. Above all else, though, Shawshank offers hope - not just for characters in a movie - but for a better life and a better world for all of us. 10/10","id":"578193f29251417c28001764","url":"https://www.themoviedb.org/review/578193f29251417c28001764"},{"author":"tmdb73913433","content":"Make way for the best film ever made people. **Make way.**","id":"5a0712aec3a3687914014a4b","url":"https://www.themoviedb.org/review/5a0712aec3a3687914014a4b"},{"author":"thommo_nz","content":"There is a reason why this movie is at the top of any popular list your will find.\r\nVery strong performances from lead actors and a story line from the literary brilliance of Stephen King (and no, its not a horror).\r\nSufficient drama and depth to keep you interested and occupied without stupefying your brain. It is the movie that has something for everyone.","id":"5ae800510e0a264fa7000c70","url":"https://www.themoviedb.org/review/5ae800510e0a264fa7000c70"},{"author":"Andrew Gentry","content":"It's still puzzling to me why this movie exactly continues to appear in every single best-movies-of-all-time chart. There's a great story, perfect cast, and acting. It really moves me in times when I'm finding myself figuring out things with my annual tax routine reading <a href=\"https://www.buzzfeed.com/davidsmithjd/what-is-form-w-2-and-how-does-it-work-3n31d\">this article<\/a>, and accidentally catching myself wondering what my life should be if circumstances had changed so drastically. This movie worth a rewatch by all means, but yet, there's no unique vibe or something - there are thousands of other ones as good as this one.","id":"5bc8a200c3a36829c8019817","url":"https://www.themoviedb.org/review/5bc8a200c3a36829c8019817"},{"author":"Matthew Dixon","content":"I will not say that the film is predictable, because it is not quite so, due to the fact that we only know Andy\u2019s intentions at the end, and during the film, it is important to understand that there was not a hint of it, but let's not forget our guys who so imprudently localized it. I will not be the first, but the word 'redemption' is translated as 'redemption'. I don\u2019t know the reason why our localizers decided to spoil the impression to all Russian-speaking people.","id":"5d3fff0c34e1521fb8e86abf","url":"https://www.themoviedb.org/review/5d3fff0c34e1521fb8e86abf"}]
     * total_pages : 1
     * total_results : 6
     */

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * author : elshaarawy
         * content : very good movie 9.5/10 محمد الشعراوى
         * id : 5723a329c3a3682e720005db
         * url : https://www.themoviedb.org/review/5723a329c3a3682e720005db
         */

        private String author;
        private String content;
        private String id;
        private String url;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
