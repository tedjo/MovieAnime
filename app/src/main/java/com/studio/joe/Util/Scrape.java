package com.studio.joe.Util;

import com.studio.joe.Model.Pos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Scrape {

    public static ArrayList<Pos> loadDataPos(String  url)
    {
        ArrayList<Pos> postAll = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements data = doc.getAllElements().select(Pos.TAG_PAGE);
            Elements core = data.select("div.episode-card");
            int size = core.size();

            for (int i = 0; i < size; i++) {
                String Title = data.select("div.episode-detail")
                        .select("div.episode-detail")
                        .select("h3.text-h4")
                        .eq(i)
                        .text();
                String Image = data.select("div.episode-ratio")
                        .eq(i)
                        .attr("style");
                String Episot = data.select("div.episode-ratio")
                        .select("div.episode-number")
                        .eq(i)
                        .text();
                String NextPage = data.select("div.col-6")
                        .select("a")
                        .eq(i)
                        .attr("href");

                String[] splitImg = Image.split("background-image: url");
                String[] splitImg1 = splitImg[1].split("()'");

                Pos post = new Pos();
                post.setTitle(Title);
                post.setImage(splitImg1[1]);
                post.setEpisode("Episode Ke " + Episot);
                post.setLinkPage(NextPage);
                postAll.add(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return postAll;
    }
}
