package utils;

import org.apache.commons.codec.Charsets;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AcademicCollections {

    private JSONArray jsonArray;
    private ArrayList<String> failedList = new ArrayList<>();

    public AcademicCollections(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public void startUpdate() throws IOException, URISyntaxException {

        for(int i=0; i<jsonArray.length(); i++){

            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            makeJSON(jsonObject1);
            JSONArray jsonArray1 = jsonObject1.getJSONArray("sectionItems");

            for(int j=0; j< jsonArray1.length(); j++){

                JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                makeJSON(jsonObject2);
                JSONArray jsonArray2 = jsonObject2.getJSONArray("sectionItems");

                for(int k=0; k< jsonArray2.length(); k++){

                    JSONObject jsonObject3 = jsonArray2.getJSONObject(k);
                    makeJSON(jsonObject3);
                    JSONArray jsonArray3 = jsonObject3.getJSONArray("sectionItems");

                    for(int l=0; l< jsonArray3.length(); l++){

                        JSONObject jsonObject4 = jsonArray3.getJSONObject(l);
                        makeJSON(jsonObject4);
                        JSONArray jsonArray4 = jsonObject4.getJSONArray("sectionItems");

                        for(int m=0; m< jsonArray4.length(); m++){

                            JSONObject jsonObject5 = jsonArray4.getJSONObject(m);
                            makeJSON(jsonObject5);

                        }

                    }
                }
            }
        }


        for (String output : failedList) {
            System.out.println(output);
        }
    }

    public void makeJSON(JSONObject jsonObject) throws IOException, URISyntaxException {
        SectionItem sectionItem = new SectionItem();
        sectionItem.setTitle(jsonObject.getString("title"));
        sectionItem.setSummary(jsonObject.getString("summary"));
        sectionItem.setUrl(jsonObject.getString("url"));
        try{
            sectionItem.setThumbnailImage(jsonObject.getString("thumbnailImage"));
            sectionItem.setOriginalImage(jsonObject.getString("originalImage"));
        }catch (Exception ignored){}
        StringEntity params =new StringEntity(JSONString(sectionItem));
        updateCollection(params,sectionItem);
    }

    public void updateCollection(StringEntity params, SectionItem item) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setParameter("access_token", Utils.API_TOKEN)
                .setScheme("https")
                .setHost(Utils.WEBFLOW_API)
                .setPath(Utils.COLLECTIONS_PATH);
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(builder.build());
        httppost.addHeader("content-type", "application/json");
        httppost.addHeader("accept-version","1.0.0");
        httppost.setEntity(params);

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        System.out.println(response.getStatusLine().getStatusCode() + ": " + item.getTitle());

        //SLEEP FOR A SECOND
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(response.getStatusLine().getStatusCode() == 400){
            failedList.add("Failed: " + item.getTitle());
            System.out.println("Failed: " + item.getTitle());


            Header encodingHeader = entity.getContentEncoding();

            // you need to know the encoding to parse correctly
            Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8 :
                    Charsets.toCharset(encodingHeader.getValue());

            // use org.apache.http.util.EntityUtils to read json as string
            String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

            JSONObject o = new JSONObject(json);
            System.out.println(o.toString());

        }


    }

    private String JSONString(SectionItem item){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",item.getTitle());
        jsonObject.put("summary", item.getSummary().replace("\n",""));
        jsonObject.put("url", item.getUrl());
        jsonObject.put("thumbnail-image", item.getThumbnailImage());
        jsonObject.put("image", item.getOriginalImage());
        jsonObject.put("_archived",false);
        jsonObject.put("_draft",false);

        JSONObject jsonObject1 = new JSONObject().put("fields",jsonObject);
        return jsonObject1.toString();
    }
}

