package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.spider.AppYsV2;
import com.github.catvod.spider.Bili;
import com.github.catvod.spider.XPath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppYsV2 aidi1 = new AppYsV2();
                aidi1.init(MainActivity.this, "https://vipmv.co/xgapp.php/v1/");
                String json = aidi1.homeContent(true);
                System.out.println("开始start");
                System.out.println(json);
                JSONObject homeContent = null;
                try {
                    homeContent = new JSONObject(aidi1.homeVideoContent());
                    System.out.println(homeContent.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(aidi1.categoryContent("1", "1", false, null));
                if (homeContent != null) {
                    try {
                        List<String> ids = new ArrayList<String>();
                        JSONArray array = homeContent.getJSONArray("list");
                        for (int i = 0; i < array.length() && i < 3; i++) {
                            try {
                                ids.clear();
                                ids.add(array.getJSONObject(i).getString("vod_id"));
                                System.out.println(aidi1.detailContent(ids));
                                JSONObject detailContent = new JSONObject(aidi1.detailContent(ids)).getJSONArray("list").getJSONObject(0);
                                String[] playFlags = detailContent.getString("vod_play_from").split("\\$\\$\\$");
                                String[] playUrls = detailContent.getString("vod_play_url").split("\\$\\$\\$");
                                for (int j = 0; j < playFlags.length; j++) {
                                    String pu = playUrls[j].split("#")[0].split("\\$")[1];
                                    System.out.println(aidi1.playerContent(playFlags[j], pu, new ArrayList<>()));
                                }
                            } catch (Throwable th) {

                            }
                        }
                    } catch (Throwable th) {

                    }
                }
                System.out.println(aidi1.searchContent("陪你一起", false));
                System.out.println(aidi1.searchContent("顶楼", false));

                XPath xPath = new XPath();
                xPath.init(MainActivity.this,"{\n" +
                        "  \"ua\": \"\",\n" +
                        "  \"homeUrl\": \"https://www.pgcms10.com\",\n" +
                        "  \"cateNode\": \"//div[@class='hidden-sm']/a[contains(@href, 'vodshow')]\",\n" +
                        "  \"cateName\": \"/text()\",\n" +
                        "  \"cateId\": \"/@href\",\n" +
                        "  \"cateIdR\": \"/vodtype/(\\\\d+).html\",\n" +
                        "  \"cateManual\": {\n" +
                        "    \n" +
                        "  },\n" +
                        "  \"homeVodNode\": \"//div[contains(@class, 'flickity-viewport') and @id='1']//a[@class='myui-vodlist__thumb']\",\n" +
                        "  \"homeVodName\": \"/@title\",\n" +
                        "  \"homeVodId\": \"/@href\",\n" +
                        "  \"homeVodIdR\": \"/voddetail/(\\\\w+).html\",\n" +
                        "  \"homeVodImg\": \"/div[contains(@background,'url')]/@background\",\n" +
                        "  \"homeVodImgR\": \"\\\\S+(http\\\\S+)\",\n" +
                        "  \"homeVodMark\": \"/span[@class='pack-prb hidden']/text()\",\n" +
                        "  \"cateUrl\": \"https://vipmv.co/vodshow/{cateId}--------{catePg}---.html\",\n" +
                        "  \"cateVodNode\": \"//div[contains(@class, 'vodlist hotgrow')]//a[@class='aplus-exp ecimgbor']\",\n" +
                        "  \"cateVodName\": \"/@title\",\n" +
                        "  \"cateVodId\": \"/@href\",\n" +
                        "  \"cateVodIdR\": \"/Moviedetail/(\\\\w+).html\",\n" +
                        "  \"cateVodImg\": \"/div[contains(@data-original,'http')]/@data-original\",\n" +
                        "  \"cateVodImgR\": \"\\\\S+(http\\\\S+)\",\n" +
                        "  \"cateVodMark\": \"/span[@class='pack-prb hidden']/text()\",\n" +
                        "  \"dtUrl\": \"https://vipmv.co/Moviedetail/{vid}.html\",\n" +
                        "  \"dtNode\": \"//body\",\n" +
                        "  \"dtName\": \"//div[@class='s-top-info-title cf wow fadeInDownBig']/h1/text()\",\n" +
                        "  \"dtImg\": \"//div[@class='s-cover box']/a/img/@src\",\n" +
                        "  \"dtCate\": \"//div[contains(@class,'s-top-info-detail')]//a[contains(@href,'/vod/search/class')]/text()\",\n" +
                        "  \"dtCateR\": \"\",\n" +
                        "  \"dtYear\": \"//div[contains(@class,'s-top-info-detail')]//a[contains(@href,'/vod/search/year')]/text()\",\n" +
                        "  \"dtYearR\": \"\",\n" +
                        "  \"dtArea\": \"//div[contains(@class,'s-top-info-detail')]//a[contains(@href,'/vod/search/area')]/text()\",\n" +
                        "  \"dtAreaR\": \"\",\n" +
                        "  \"dtMark\": \"\",\n" +
                        "  \"dtMarkR\": \"\",\n" +
                        "  \"dtActor\": \"//div[contains(@class,'s-top-info-detail')]//a[contains(@href,'/vod/search/actor')]/text()\",\n" +
                        "  \"dtActorR\": \"\",\n" +
                        "  \"dtDirector\": \"//div[contains(@class,'s-top-info-detail')]//a[contains(@href,'/vod/search/director')]/text()\",\n" +
                        "  \"dtDirectorR\": \"\",\n" +
                        "  \"dtDesc\": \"//div[contains(@class,'s-top-info-detail')]//span[@id='cText']/text()\",\n" +
                        "  \"dtDescR\": \"\",\n" +
                        "  \"dtFromNode\": \"//div[contains(@class, 'play_source')]/div[contains(@class,'play_source_tab')]/div/a\",\n" +
                        "  \"dtFromName\": \"/text()\",\n" +
                        "  \"dtUrlNode\": \"//div[contains(@class, 'play_source')]//div[contains(@class, 'play_list_box')]//ul\",\n" +
                        "  \"dtUrlSubNode\": \"/li/a\",\n" +
                        "  \"dtUrlId\": \"@href\",\n" +
                        "  \"dtUrlIdR\": \"/okplay/(\\\\S+).html\",\n" +
                        "  \"dtUrlName\": \"/text()\",\n" +
                        "  \"dtUrlNameR\": \"\",\n" +
                        "  \"playUrl\": \"https://vipmv.co/okplay/{playUrl}.html\",\n" +
                        "  \"playUa\": \"\",\n" +
                        "  \"searchUrl\": \"https://vipmv.co/index.php/ajax/suggest?mid=1&wd={wd}&limit=10\",\n" +
                        "  \"scVodNode\": \"json:list\",\n" +
                        "  \"scVodName\": \"name\",\n" +
                        "  \"scVodId\": \"id\",\n" +
                        "  \"scVodIdR\": \"\",\n" +
                        "  \"scVodImg\": \"pic\",\n" +
                        "  \"scVodMark\": \"\"\n" +
                        "}");
                System.out.println(xPath.homeContent(true));
                System.out.println(xPath.homeVideoContent());
                System.out.println(xPath.categoryContent("3", "1", false, null));

                XPath aidi = new XPath();
                aidi.init(MainActivity.this, "https://ghproxy.futils.com/https://github.com/king1161/jar-b/blob/050afb76b756cef5a3890c8734fb1bc6281f128c/app/XPath/vipmv.json");
                System.out.println("这是另外一个开始");
                System.out.println(aidi.homeContent(true));
                System.out.println(aidi.homeVideoContent());
                System.out.println(aidi.categoryContent("3", "1", false, null));
                List<String> ids = new ArrayList<String>();
                ids.add("160740");
                System.out.println(aidi.detailContent(ids));
                System.out.println(aidi.playerContent("", "160740", new ArrayList<>()));
                System.out.println(aidi.searchContent("陪你一起", false));
//
//
//                AppYs appYsV2 = new AppYs();
//                appYsV2.init(MainActivity.this,"https://shayuapi.com/api.php/provide/vod/");
//                System.out.println("这是另外一个");
//                System.out.println(appYsV2.homeContent(true));
//                System.out.println(appYsV2.homeVideoContent());
//                System.out.println(appYsV2.categoryContent("2", "1", false, null));
//                System.out.println(appYsV2.detailContent(ids));
//                System.out.println(appYsV2.playerContent("", "25603", new ArrayList<>()));
//                System.out.println(appYsV2.searchContent("陪你一起", false));


//                Aidi aidi = new Aidi();
//                aidi.init(MainActivity.this);
//                System.out.println(aidi.homeContent(true));
//                System.out.println(aidi.homeVideoContent());
//                System.out.println(aidi.categoryContent("1", "1", false, null));
//                List<String> ids = new ArrayList<String>();
//                ids.add("1354");
//                System.out.println(aidi.detailContent(ids));
//                System.out.println(aidi.playerContent("", "2607", new ArrayList<>()));
//                System.out.println(aidi.searchContent("陪你一起", false));
                //atHiRa0nccHiMw6aLnyg9lhuaoWRpLnR2L20zdTgvYXRIaVJhMG5jY0hpTXc2YUxueWc5bHF1ZW9ESXVZV1I1Y3k1MGRpOXdjSGwxYmw5dE0zVTRMMlUwTXpsa1pEVTBaalZrTVRRMlpHUTRabVJqTVRGak0ySTJObUZoWkRneC84MGJmMjYzODZhMWZkYzU4MDk3ZDA1YzRkYWZmNGE4MS8xNjU0ODU1NDE5Lm0zdTgO0O0O
                //atHiRa0nccHiMw6aLnyg9lhuaoWRpLnR2L20zdTgvYXRIaVJhMG5jY0hpTXc2YUxueWc5bHF1ZW9ESXVZV1I1Y3k1MGRpOXdjSGwxYmw5dE0zVTRMMlUwTXpsa1pEVTBaalZrTVRRMlpHUTRabVJqTVRGak0ySTJObUZoWkRneC83ZDU0OTY1YzVlMTExNDdiNmFiNzNlYjNiYzMwMGRiYS8xNjU0ODU1ODcyLm0zdTgO0O0O
                //atHiRa0nccHiMw6aLnyg9lqueoDIuYWR5cy50di9wcHl1bl9tM3U4L2U0MzlkZDU0ZjVkMTQ2ZGQ4ZmRjMTFjM2I2NmFhZDgx/18dc211e4f2896818366d9fe45806357/1654855778.m3u8
            }
        }).start();
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//
//
//            }
//        }).start();
          new Thread(new Runnable() {
            @Override
            public void run() {
                Bili bili = new Bili();
                bili.init(MainActivity.this);
                String json = bili.homeContent(true);
                System.out.println("开始start");
                System.out.println(json);
                try {
                    JSONObject jsonObject = new JSONObject(bili.homeVideoContent());
                    System.out.println(jsonObject.toString());

                    JSONObject jsonObject1 = new JSONObject(bili.categoryContent("1", "1", false, null));
                    System.out.println(jsonObject1.toString());
                    List<String> ids = new ArrayList<String>();
                    ids.add("");
                    System.out.println(bili.detailContent(ids));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


