package model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class SectionItem {

    @Expose
    private String title;
    @Expose
    private String summary;
    @Expose
    private String url;
    @Expose
    private ArrayList<SectionItem> sectionItems;

    public SectionItem(String title, String summary, String url) {
        this.title = title;
        this.summary = summary;
        this.url = url;
        sectionItems = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<SectionItem> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(ArrayList<SectionItem> sectionItems) {
        this.sectionItems = sectionItems;
    }

    public void addItem(SectionItem sectionItem){
        sectionItems.add(sectionItem);
    }
}
