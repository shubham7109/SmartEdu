package model;

import com.google.gson.annotations.Expose;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SectionItem {

    @Expose
    private String title;
    @Expose
    private String summary;
    @Expose
    private String url;
    @Expose
    private String thumbnailImage;
    @Expose
    private String originalImage;
    @Expose
    private ArrayList<SectionItem> sectionItems;

    public SectionItem(String slugURL) {

        if(slugURL.equals("Formal_Sciences"))
            slugURL = "Formal_science";
        else if(slugURL.equals("Applied_Sciences"))
            slugURL = "Applied_science";

        slugURL = slugURL.replace("/wiki/","");
        slugURL = slugURL.replaceAll(" ", "%20");

        sectionItems = new ArrayList<>();
        url = "https://en.wikipedia.org/wiki/" + slugURL;
        try {
            setSummaryAndImage(slugURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSummaryAndImage(String slugURL) throws IOException {
        String requestString = "https://en.wikipedia.org/api/rest_v1/page/summary/" + slugURL;
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestString);
        HttpResponse response = httpclient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseString);

        JSONObject jsonObject = new JSONObject(responseString);
        title = jsonObject.getString("title");
        summary = jsonObject.getString("extract");
        try {
            thumbnailImage = jsonObject.getJSONObject("thumbnail").getString("source");
        } catch (JSONException ignored) {}

        try{
            originalImage = jsonObject.getJSONObject("originalimage").getString("source");
        } catch (JSONException ignored) {}
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
