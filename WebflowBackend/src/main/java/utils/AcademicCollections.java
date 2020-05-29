package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AcademicCollections {

    private JSONArray jsonArray;

    public AcademicCollections(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public void updateCollection() throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setParameter("access_token", Utils.API_TOKEN)
                .setScheme("https")
                .setHost(Utils.WEBFLOW_API)
                .setPath(Utils.COLLECTIONS_PATH);
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(builder.build());


        StringEntity params =new StringEntity(JSONString());
        httppost.addHeader("content-type", "application/json");
        httppost.addHeader("accept-version","1.0.0");
        httppost.setEntity(params);

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        System.out.println(entity);

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // do something useful
                System.out.println(instream);
            }
        }
        System.out.println(response.getStatusLine().getStatusCode());
    }

    private String JSONString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","ITEM4");
        jsonObject.put("summary","item4 summary...");
        jsonObject.put("_archived",false);
        jsonObject.put("_draft",false);

        JSONObject jsonObject1 = new JSONObject().put("fields",jsonObject);
        System.out.println(jsonObject1.toString());
        return jsonObject1.toString();
    }
}
