import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanities.PerformingArts;
import model.SectionItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static SectionItem sectionItem;
    static Document doc;
    static String url_wiki = "https://en.wikipedia.org/wiki/Outline_of_academic_disciplines";

    public static void main(String[] args) throws IOException {
        sectionItem = new SectionItem(
                "Outline_of_academic_disciplines",
                "The following outline is provided as an overview of and topical guide to academic disciplines",
                "https://en.wikipedia.org/wiki/Outline_of_academic_disciplines");
        doc = Jsoup.connect(url_wiki).get();

        Elements elements = doc.select("h2");
        ArrayList<SectionItem> sectionItems1 = new ArrayList<>();
        for(Element row : elements){
            if(row.toString().contains("See_also"))
                break;

            if(row.getAllElements().hasClass("mw-headline")){
                String title = row.getElementsByClass("mw-headline").get(0).id();
                SectionItem sectionItem1 = new SectionItem(title,"","");
                //System.out.println(row.getElementsByClass("mw-headline").get(0).id());

                sectionItem1.setSectionItems(get_H3_items(title));
                sectionItems1.add(sectionItem1);

            }
        }
        sectionItem.setSectionItems(sectionItems1);
        createGSON();
    }

    private static ArrayList<SectionItem> get_H3_items(String parentTitle) {

        ArrayList<SectionItem> innerSectionItem = new ArrayList<>();
        switch (parentTitle){

            case "Humanities":
                SectionItem item1 = new SectionItem("Arts","","");
                SectionItem item1_performingArgs = new SectionItem("Performing Arts","","");
                item1_performingArgs.setSectionItems(PerformingArts.getPerformingArtsList(doc));
                ArrayList<SectionItem> performingArts = new ArrayList<>();
                performingArts.add(item1_performingArgs);
                item1.addItem(item1_performingArgs);
                item1.addItem(new SectionItem("Visual Arts","",""));
                SectionItem item2 = new SectionItem("History","","");
                SectionItem item3 = new SectionItem("Languages and literature","","");
                SectionItem item4 = new SectionItem("Law","","");
                SectionItem item5 = new SectionItem("Philosophy","","");
                SectionItem item6 = new SectionItem("Theology","","");
                innerSectionItem.add(item1);
                innerSectionItem.add(item2);
                innerSectionItem.add(item3);
                innerSectionItem.add(item4);
                innerSectionItem.add(item5);
                innerSectionItem.add(item6);
                break;

            case "Social_sciences":
                item1 = new SectionItem("Anthropology","","");
                item2 = new SectionItem("Archaeology","","");
                item3 = new SectionItem("Economics","","");
                item4 = new SectionItem("Human geography","","");
                item5 = new SectionItem("Political science","","");
                item6 = new SectionItem("Psychology","","");
                SectionItem item7 = new SectionItem("Sociology","","");
                SectionItem item8 = new SectionItem("Social Work","","");
                innerSectionItem.add(item1);
                innerSectionItem.add(item2);
                innerSectionItem.add(item3);
                innerSectionItem.add(item4);
                innerSectionItem.add(item5);
                innerSectionItem.add(item6);
                innerSectionItem.add(item7);
                innerSectionItem.add(item8);
                break;
            case "Natural_Sciences":
                item1 = new SectionItem("Biology","","");
                item2 = new SectionItem("Chemistry","","");
                item3 = new SectionItem("Earth science","","");
                item4 = new SectionItem("Space sciences","","");
                item5 = new SectionItem("Physics","","");
                innerSectionItem.add(item1);
                innerSectionItem.add(item2);
                innerSectionItem.add(item3);
                innerSectionItem.add(item4);
                innerSectionItem.add(item5);
                break;

            case "Formal_Sciences":
                item1 = new SectionItem("Computer Science","","");
                item2 = new SectionItem("Mathematics","","");
                item3 = new SectionItem("Statistics","","");
                innerSectionItem.add(item1);
                innerSectionItem.add(item2);
                innerSectionItem.add(item3);
                break;

            case "Applied_Sciences":
                item1 = new SectionItem("Business","","");
                item2 = new SectionItem("Engineering and technology","","");
                item3 = new SectionItem("Medicine and health","","");
                innerSectionItem.add(item1);
                innerSectionItem.add(item2);
                innerSectionItem.add(item3);
                break;

        }

        return new ArrayList<>(innerSectionItem);
    }


    static private void createGSON(){
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();
        String jsonString = gson.toJson(sectionItem);
        System.out.println(jsonString);
    }

}

