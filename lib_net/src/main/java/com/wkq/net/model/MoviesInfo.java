package com.wkq.net.model;

import com.google.gson.annotations.SerializedName;
import com.wkq.net.BaseInfo;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesInfo {


    /**
     * subjects : [{"rating":{"max":10,"average":9,"details":{"1":145,"3":3807,"2":341,"5":34596,"4":18220},"stars":"45","min":0},"genres":["剧情","传记","犯罪"],"title":"爱尔兰人","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg"},"name_en":"Robert De Niro","name":"罗伯特·德尼罗","alt":"https://movie.douban.com/celebrity/1054445/","id":"1054445"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p645.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p645.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p645.jpg"},"name_en":"Al Pacino","name":"阿尔·帕西诺","alt":"https://movie.douban.com/celebrity/1054451/","id":"1054451"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1427205662.96.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1427205662.96.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1427205662.96.jpg"},"name_en":"Joe Pesci","name":"乔·佩西","alt":"https://movie.douban.com/celebrity/1004598/","id":"1004598"}],"durations":["210分钟"],"collect_count":76781,"mainland_pubdate":"","has_video":false,"original_title":"The Irishman","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg"},"name_en":"Martin Scorsese","name":"马丁·斯科塞斯","alt":"https://movie.douban.com/celebrity/1054425/","id":"1054425"}],"pubdates":["2019-09-27(纽约电影节)","2019-11-01(美国点映)","2019-11-27(美国网络)"],"year":"2019","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg"},"alt":"https://movie.douban.com/subject/6981153/","id":"6981153"},{"rating":{"max":10,"average":8.8,"details":{"1":2786,"3":34270,"2":4363,"5":184201,"4":126429},"stars":"45","min":0},"genres":["剧情","犯罪","惊悚"],"title":"小丑","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p241.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p241.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p241.jpg"},"name_en":"Joaquin Phoenix","name":"杰昆·菲尼克斯","alt":"https://movie.douban.com/celebrity/1047979/","id":"1047979"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg"},"name_en":"Robert De Niro","name":"罗伯特·德尼罗","alt":"https://movie.douban.com/celebrity/1054445/","id":"1054445"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1404968819.83.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1404968819.83.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1404968819.83.jpg"},"name_en":"Marc Maron","name":"马克·马龙","alt":"https://movie.douban.com/celebrity/1116943/","id":"1116943"}],"durations":["122分钟","118分钟(威尼斯电影节)"],"collect_count":449615,"mainland_pubdate":"","has_video":false,"original_title":"Joker","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26845.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26845.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p26845.jpg"},"name_en":"Todd Phillips","name":"托德·菲利普斯","alt":"https://movie.douban.com/celebrity/1036390/","id":"1036390"}],"pubdates":["2019-08-31(威尼斯电影节)","2019-10-04(美国)"],"year":"2019","images":{"small":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2567198874.jpg","large":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2567198874.jpg","medium":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2567198874.jpg"},"alt":"https://movie.douban.com/subject/27119724/","id":"27119724"},{"rating":{"max":10,"average":8.7,"details":{"1":135,"3":6554,"2":472,"5":29095,"4":28261},"stars":"45","min":0},"genres":["剧情"],"title":"82年生的金智英","casts":[{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1409765749.47.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1409765749.47.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1409765749.47.jpg"},"name_en":"Yu-mi Jung","name":"郑有美","alt":"https://movie.douban.com/celebrity/1276062/","id":"1276062"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1397060788.93.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1397060788.93.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1397060788.93.jpg"},"name_en":"Yoo Gong","name":"孔侑","alt":"https://movie.douban.com/celebrity/1011009/","id":"1011009"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32064.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32064.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32064.jpg"},"name_en":"Mi-kyung Kim","name":"金美京","alt":"https://movie.douban.com/celebrity/1315405/","id":"1315405"}],"durations":["118分钟"],"collect_count":85274,"mainland_pubdate":"","has_video":false,"original_title":"82년생 김지영","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1527672997.83.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1527672997.83.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1527672997.83.jpg"},"name_en":"Do-young Kim","name":"金度英","alt":"https://movie.douban.com/celebrity/1394430/","id":"1394430"}],"pubdates":["2019-10-23(韩国)","2019-11-07(中国香港)"],"year":"2019","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570137991.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570137991.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570137991.jpg"},"alt":"https://movie.douban.com/subject/30327842/","id":"30327842"},{"rating":{"max":10,"average":7.3,"details":{"1":333,"3":8198,"2":1226,"5":3638,"4":10383},"stars":"40","min":0},"genres":["剧情","喜剧"],"title":"好莱坞往事","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p470.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p470.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p470.jpg"},"name_en":"Leonardo DiCaprio","name":"莱昂纳多·迪卡普里奥","alt":"https://movie.douban.com/celebrity/1041029/","id":"1041029"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p39053.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p39053.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p39053.jpg"},"name_en":"Brad Pitt","name":"布拉德·皮特","alt":"https://movie.douban.com/celebrity/1054452/","id":"1054452"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1389939796.3.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1389939796.3.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1389939796.3.jpg"},"name_en":"Margot Robbie","name":"玛格特·罗比","alt":"https://movie.douban.com/celebrity/1272303/","id":"1272303"}],"durations":["165分钟","161分钟(戛纳电影节)","162分钟(院线版)","171分钟(加长版)"],"collect_count":97069,"mainland_pubdate":"","has_video":false,"original_title":"Once Upon a Time... in Hollywood","subtype":"movie","directors":[{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10798.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10798.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10798.jpg"},"name_en":"Quentin Tarantino","name":"昆汀·塔伦蒂诺","alt":"https://movie.douban.com/celebrity/1054444/","id":"1054444"}],"pubdates":["2019-05-21(戛纳电影节)","2019-07-26(美国)","2019(中国大陆)"],"year":"2019","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551119672.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551119672.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551119672.jpg"},"alt":"https://movie.douban.com/subject/27087724/","id":"27087724"},{"rating":{"max":10,"average":8.8,"details":{"1":64,"3":3425,"2":228,"5":19564,"4":17901},"stars":"45","min":0},"genres":["剧情","爱情"],"title":"婚姻故事","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p37050.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p37050.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p37050.jpg"},"name_en":"Scarlett Johansson","name":"斯嘉丽·约翰逊","alt":"https://movie.douban.com/celebrity/1054453/","id":"1054453"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1452758257.45.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1452758257.45.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1452758257.45.jpg"},"name_en":"Adam Driver","name":"亚当·德赖弗","alt":"https://movie.douban.com/celebrity/1322327/","id":"1322327"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1466230850.62.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1466230850.62.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1466230850.62.jpg"},"name_en":"Merritt Wever","name":"梅里特·韦弗","alt":"https://movie.douban.com/celebrity/1327603/","id":"1327603"}],"durations":["136分钟"],"collect_count":56898,"mainland_pubdate":"","has_video":false,"original_title":"Marriage Story","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1388905757.35.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1388905757.35.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1388905757.35.jpg"},"name_en":"Noah Baumbach","name":"诺亚·鲍姆巴赫","alt":"https://movie.douban.com/celebrity/1049678/","id":"1049678"}],"pubdates":["2019-08-29(威尼斯电影节)","2019-12-06(美国)"],"year":"2019","images":{"small":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2571760178.jpg","large":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2571760178.jpg","medium":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2571760178.jpg"},"alt":"https://movie.douban.com/subject/27202818/","id":"27202818"},{"rating":{"max":10,"average":6.7,"details":{"1":447,"3":12949,"2":2448,"5":2025,"4":8315},"stars":"35","min":0},"genres":["悬疑","惊悚","恐怖"],"title":"准备好了没","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522809217.05.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522809217.05.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522809217.05.jpg"},"name_en":"Samara Weaving","name":"萨玛拉·维文","alt":"https://movie.douban.com/celebrity/1267671/","id":"1267671"},{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8087.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8087.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8087.jpg"},"name_en":"Andie MacDowell","name":"安迪·麦克道威尔","alt":"https://movie.douban.com/celebrity/1048128/","id":"1048128"},{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1398791920.7.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1398791920.7.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1398791920.7.jpg"},"name_en":"Mark O'Brien","name":"马克·奥布莱恩","alt":"https://movie.douban.com/celebrity/1197745/","id":"1197745"}],"durations":["95分钟"],"collect_count":34146,"mainland_pubdate":"","has_video":false,"original_title":"Ready or Not","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557279675.91.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557279675.91.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557279675.91.jpg"},"name_en":"Matt Bettinelli-Olpin","name":"马特·贝蒂内利-奥尔平","alt":"https://movie.douban.com/celebrity/1337602/","id":"1337602"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557279722.95.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557279722.95.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557279722.95.jpg"},"name_en":"Tyler Gillett","name":"泰勒·吉勒特","alt":"https://movie.douban.com/celebrity/1337603/","id":"1337603"}],"pubdates":["2019-08-21(美国)"],"year":"2019","images":{"small":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2561370606.jpg","large":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2561370606.jpg","medium":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2561370606.jpg"},"alt":"https://movie.douban.com/subject/27594938/","id":"27594938"},{"rating":{"max":10,"average":8.6,"details":{"1":17,"3":942,"2":61,"5":3492,"4":3462},"stars":"45","min":0},"genres":["动画"],"title":"克劳斯：圣诞节的秘密","casts":[{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44006.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44006.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44006.jpg"},"name_en":"J.K. Simmons","name":"J·K·西蒙斯","alt":"https://movie.douban.com/celebrity/1147911/","id":"1147911"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3145.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3145.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3145.jpg"},"name_en":"Jason Schwartzman","name":"詹森·舒瓦兹曼","alt":"https://movie.douban.com/celebrity/1009269/","id":"1009269"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.jpg"},"name_en":"Rashida Jones","name":"拉什达·琼斯","alt":"https://movie.douban.com/celebrity/1031815/","id":"1031815"}],"durations":["96分钟"],"collect_count":10582,"mainland_pubdate":"","has_video":false,"original_title":"Klaus","subtype":"movie","directors":[{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499650191.88.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499650191.88.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499650191.88.jpg"},"name_en":"Sergio Pablos","name":"塞尔希奥·巴勃罗斯","alt":"https://movie.douban.com/celebrity/1293777/","id":"1293777"},{"avatars":{"small":"http://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"http://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"http://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name_en":"Carlos Martínez López","name":"卡洛斯·马丁内斯·洛佩斯","alt":"https://movie.douban.com/celebrity/1427369/","id":"1427369"}],"pubdates":["2019-11-08(西班牙)","2019-11-08(英国)"],"year":"2019","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570825762.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570825762.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570825762.jpg"},"alt":"https://movie.douban.com/subject/26858510/","id":"26858510"},{"rating":{"max":10,"average":7.5,"details":{"1":175,"3":5423,"2":892,"5":3555,"4":6689},"stars":"40","min":0},"genres":["动作","惊悚","冒险"],"title":"第一滴血5：最后的血","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p262.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p262.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p262.jpg"},"name_en":"Sylvester Stallone","name":"西尔维斯特·史泰龙","alt":"https://movie.douban.com/celebrity/1047996/","id":"1047996"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14395.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14395.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14395.jpg"},"name_en":"Paz Vega","name":"帕斯·贝加","alt":"https://movie.douban.com/celebrity/1025136/","id":"1025136"},{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1366583841.8.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1366583841.8.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1366583841.8.jpg"},"name_en":"Sergio Peris-Mencheta","name":"塞尔吉奥·佩里斯-门切塔","alt":"https://movie.douban.com/celebrity/1133455/","id":"1133455"}],"durations":["89分钟","101分钟"],"collect_count":27027,"mainland_pubdate":"","has_video":false,"original_title":"Rambo: Last Blood","subtype":"movie","directors":[{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1569031944.04.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1569031944.04.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1569031944.04.jpg"},"name_en":"Adrian Grunberg","name":"阿德里安·格鲁伯格","alt":"https://movie.douban.com/celebrity/1319574/","id":"1319574"}],"pubdates":["2019-09-18(印度尼西亚)","2019-09-20(美国)","2019-09-27(西班牙)"],"year":"2019","images":{"small":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2564519086.jpg","large":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2564519086.jpg","medium":"http://img9.doubanio.com/view/photo/s_ratio_poster/public/p2564519086.jpg"},"alt":"https://movie.douban.com/subject/10546436/","id":"10546436"},{"rating":{"max":10,"average":8.2,"details":{"1":50,"3":2424,"2":229,"5":4016,"4":5629},"stars":"40","min":0},"genres":["喜剧","悬疑"],"title":"行骗天下JP：浪漫篇","casts":[{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483767998.76.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483767998.76.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483767998.76.jpg"},"name_en":"Masami Nagasawa","name":"长泽雅美","alt":"https://movie.douban.com/celebrity/1018667/","id":"1018667"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1383841119.5.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1383841119.5.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1383841119.5.jpg"},"name_en":"Masahiro Higashide","name":"东出昌大","alt":"https://movie.douban.com/celebrity/1324960/","id":"1324960"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1397500109.13.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1397500109.13.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1397500109.13.jpg"},"name_en":"Fumiyo Kohinata","name":"小日向文世","alt":"https://movie.douban.com/celebrity/1009219/","id":"1009219"}],"durations":["116分钟"],"collect_count":17886,"mainland_pubdate":"","has_video":false,"original_title":"コンフィデンスマンJP","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526193660.71.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526193660.71.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526193660.71.jpg"},"name_en":"Ryô Tanaka","name":"田中亮","alt":"https://movie.douban.com/celebrity/1343858/","id":"1343858"}],"pubdates":["2019-05-17(日本)"],"year":"2019","images":{"small":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549891899.jpg","large":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549891899.jpg","medium":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549891899.jpg"},"alt":"https://movie.douban.com/subject/30241102/","id":"30241102"},{"rating":{"max":10,"average":7.5,"details":{"1":72,"3":4059,"2":496,"5":2098,"4":5781},"stars":"40","min":0},"genres":["喜剧","爱情"],"title":"纽约的一个雨天","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465300883.95.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465300883.95.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465300883.95.jpg"},"name_en":"Timothée Chalamet","name":"蒂莫西·柴勒梅德","alt":"https://movie.douban.com/celebrity/1325862/","id":"1325862"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13832.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13832.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13832.jpg"},"name_en":"Elle Fanning","name":"艾丽·范宁","alt":"https://movie.douban.com/celebrity/1025133/","id":"1025133"},{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506866168.38.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506866168.38.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506866168.38.jpg"},"name_en":"Selena Gomez","name":"赛琳娜·戈麦斯","alt":"https://movie.douban.com/celebrity/1047993/","id":"1047993"}],"durations":["92分钟"],"collect_count":18038,"mainland_pubdate":"","has_video":false,"original_title":"A Rainy Day in New York","subtype":"movie","directors":[{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p607.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p607.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p607.jpg"},"name_en":"Woody Allen","name":"伍迪·艾伦","alt":"https://movie.douban.com/celebrity/1054430/","id":"1054430"}],"pubdates":["2019-07-26(波兰)"],"year":"2019","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2556824333.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2556824333.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2556824333.jpg"},"alt":"https://movie.douban.com/subject/27089612/","id":"27089612"},{"rating":{"max":10,"average":6.4,"details":{"1":768,"3":13200,"2":4013,"5":2001,"4":6480},"stars":"35","min":0},"genres":["剧情","恐怖"],"title":"小丑回魂2","casts":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p93.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p93.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p93.jpg"},"name_en":"James McAvoy","name":"詹姆斯·麦卡沃伊","alt":"https://movie.douban.com/celebrity/1006958/","id":"1006958"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p54076.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p54076.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p54076.jpg"},"name_en":"Jessica Chastain","name":"杰西卡·查斯坦","alt":"https://movie.douban.com/celebrity/1000225/","id":"1000225"},{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372603385.77.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372603385.77.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372603385.77.jpg"},"name_en":"Bill Skarsgård","name":"比尔·斯卡斯加德","alt":"https://movie.douban.com/celebrity/1022798/","id":"1022798"}],"durations":["169分钟"],"collect_count":34699,"mainland_pubdate":"","has_video":false,"original_title":"It: Chapter Two","subtype":"movie","directors":[{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1550287016.81.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1550287016.81.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1550287016.81.jpg"},"name_en":"Andrés Muschietti","name":"安德斯·穆斯切蒂","alt":"https://movie.douban.com/celebrity/1326370/","id":"1326370"}],"pubdates":["2019-09-06(美国)"],"year":"2019","images":{"small":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2562980418.jpg","large":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2562980418.jpg","medium":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2562980418.jpg"},"alt":"https://movie.douban.com/subject/27133569/","id":"27133569"},{"rating":{"max":10,"average":8,"details":{"1":25,"3":749,"2":99,"5":1045,"4":1337},"stars":"40","min":0},"genres":["剧情","喜剧","爱情"],"title":"最初的梦想","casts":[{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391587890.4.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391587890.4.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391587890.4.jpg"},"name_en":"Sushant Singh Rajput","name":"苏尚特·辛格·拉吉普特","alt":"https://movie.douban.com/celebrity/1330066/","id":"1330066"},{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/pfw0oWbkTnQQcel_avatar_uploaded1390480585.48.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/pfw0oWbkTnQQcel_avatar_uploaded1390480585.48.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/pfw0oWbkTnQQcel_avatar_uploaded1390480585.48.jpg"},"name_en":"Shraddha Kapoor","name":"施拉达·卡普尔","alt":"https://movie.douban.com/celebrity/1337680/","id":"1337680"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pzm-hZ5Wd6fgcel_avatar_uploaded1574673518.65.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pzm-hZ5Wd6fgcel_avatar_uploaded1574673518.65.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pzm-hZ5Wd6fgcel_avatar_uploaded1574673518.65.jpg"},"name_en":"Varun Sharma","name":"瓦伦·沙玛","alt":"https://movie.douban.com/celebrity/1427052/","id":"1427052"}],"durations":["143分钟"],"collect_count":4833,"mainland_pubdate":"","has_video":false,"original_title":"Chhichhore","subtype":"movie","directors":[{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484120321.24.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484120321.24.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484120321.24.jpg"},"name_en":"Nitesh Tiwari","name":"涅提·蒂瓦里","alt":"https://movie.douban.com/celebrity/1366907/","id":"1366907"}],"pubdates":["2019-09-06(印度)"],"year":"2019","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570421055.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570421055.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2570421055.jpg"},"alt":"https://movie.douban.com/subject/34787747/","id":"34787747"}]
     * title : 豆瓣电影新片榜
     */

    private String title;
    private List<SubjectsBean> subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":9,"details":{"1":145,"3":3807,"2":341,"5":34596,"4":18220},"stars":"45","min":0}
         * genres : ["剧情","传记","犯罪"]
         * title : 爱尔兰人
         * casts : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg"},"name_en":"Robert De Niro","name":"罗伯特·德尼罗","alt":"https://movie.douban.com/celebrity/1054445/","id":"1054445"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p645.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p645.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p645.jpg"},"name_en":"Al Pacino","name":"阿尔·帕西诺","alt":"https://movie.douban.com/celebrity/1054451/","id":"1054451"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1427205662.96.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1427205662.96.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1427205662.96.jpg"},"name_en":"Joe Pesci","name":"乔·佩西","alt":"https://movie.douban.com/celebrity/1004598/","id":"1004598"}]
         * durations : ["210分钟"]
         * collect_count : 76781
         * mainland_pubdate :
         * has_video : false
         * original_title : The Irishman
         * subtype : movie
         * directors : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg"},"name_en":"Martin Scorsese","name":"马丁·斯科塞斯","alt":"https://movie.douban.com/celebrity/1054425/","id":"1054425"}]
         * pubdates : ["2019-09-27(纽约电影节)","2019-11-01(美国点映)","2019-11-27(美国网络)"]
         * year : 2019
         * images : {"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg"}
         * alt : https://movie.douban.com/subject/6981153/
         * id : 6981153
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String mainland_pubdate;
        private boolean has_video;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<String> durations;
        private List<DirectorsBean> directors;
        private List<String> pubdates;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getMainland_pubdate() {
            return mainland_pubdate;
        }

        public void setMainland_pubdate(String mainland_pubdate) {
            this.mainland_pubdate = mainland_pubdate;
        }

        public boolean isHas_video() {
            return has_video;
        }

        public void setHas_video(boolean has_video) {
            this.has_video = has_video;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<String> getDurations() {
            return durations;
        }

        public void setDurations(List<String> durations) {
            this.durations = durations;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public List<String> getPubdates() {
            return pubdates;
        }

        public void setPubdates(List<String> pubdates) {
            this.pubdates = pubdates;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 9
             * details : {"1":145,"3":3807,"2":341,"5":34596,"4":18220}
             * stars : 45
             * min : 0
             */

            private float max;
            private float average;
            private DetailsBean details;
            private String stars;
            private float min;

            public float getMax() {
                return max;
            }

            public void setMax(float max) {
                this.max = max;
            }

            public float getAverage() {
                return average;
            }

            public void setAverage(float average) {
                this.average = average;
            }

            public DetailsBean getDetails() {
                return details;
            }

            public void setDetails(DetailsBean details) {
                this.details = details;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public float getMin() {
                return min;
            }

            public void setMin(float min) {
                this.min = min;
            }

            public static class DetailsBean {
                /**
                 * 1 : 145
                 * 3 : 3807
                 * 2 : 341
                 * 5 : 34596
                 * 4 : 18220
                 */

                @SerializedName("1")
                private int _$1;
                @SerializedName("3")
                private int _$3;
                @SerializedName("2")
                private int _$2;
                @SerializedName("5")
                private int _$5;
                @SerializedName("4")
                private int _$4;

                public int get_$1() {
                    return _$1;
                }

                public void set_$1(int _$1) {
                    this._$1 = _$1;
                }

                public int get_$3() {
                    return _$3;
                }

                public void set_$3(int _$3) {
                    this._$3 = _$3;
                }

                public int get_$2() {
                    return _$2;
                }

                public void set_$2(int _$2) {
                    this._$2 = _$2;
                }

                public int get_$5() {
                    return _$5;
                }

                public void set_$5(int _$5) {
                    this._$5 = _$5;
                }

                public int get_$4() {
                    return _$4;
                }

                public void set_$4(int _$4) {
                    this._$4 = _$4;
                }
            }
        }

        public static class ImagesBean {
            /**
             * small : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg
             * large : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg
             * medium : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2568902055.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg"}
             * name_en : Robert De Niro
             * name : 罗伯特·德尼罗
             * alt : https://movie.douban.com/celebrity/1054445/
             * id : 1054445
             */

            private AvatarsBean avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg
                 * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg
                 * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p47221.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg"}
             * name_en : Martin Scorsese
             * name : 马丁·斯科塞斯
             * alt : https://movie.douban.com/celebrity/1054425/
             * id : 1054425
             */

            private AvatarsBeanX avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg
                 * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg
                 * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p601.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}