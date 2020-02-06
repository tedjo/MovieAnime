package com.studio.joe.Model;

public class Pos {

    public static final String URL = "https://animeindo.to/";
    public static final String TAG_PAGE = "div.col-6";

    private String title;
    private String episode;
    private String image;
    private String videoView;
    private String linkPage;
    private String linkServer;

    public Pos() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideoView() {
        return videoView;
    }

    public void setVideoView(String videoView) {
        this.videoView = videoView;
    }

    public String getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(String linkPage) {
        this.linkPage = linkPage;
    }

    public String getLinkServer() {
        return linkServer;
    }

    public void setLinkServer(String linkServer) {
        this.linkServer = linkServer;
    }
}
