import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.SectionItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.ParserHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static SectionItem sectionItem;
    static Document doc;
    static String url_wiki = "https://en.wikipedia.org/wiki/Outline_of_academic_disciplines";
    static ParserHandler parserHandler;

    public static void main(String[] args) throws IOException {
        sectionItem = new SectionItem("Outline_of_academic_disciplines");
        doc = Jsoup.connect(url_wiki).get();
        parserHandler = new ParserHandler(doc);
        Elements elements = doc.select("h2");
        ArrayList<SectionItem> sectionItems1 = new ArrayList<>();
        for(Element row : elements){
            if(row.toString().contains("See_also"))
                break;

            if(row.getAllElements().hasClass("mw-headline")){
                String title = row.getElementsByClass("mw-headline").get(0).id();
                SectionItem sectionItem1 = new SectionItem(title);
                //System.out.println(row.getElementsByClass("mw-headline").get(0).id());

                sectionItem1.setSectionItems(get_H3_items(title));
                sectionItems1.add(sectionItem1);

            }
        }
        sectionItem.setSectionItems(sectionItems1);
        createGSON();
    }

    private static ArrayList<SectionItem> get_H3_items(String title) {

        ArrayList<SectionItem> innerSectionItem = new ArrayList<>();
        switch (title){

            case "Humanities":
                innerSectionItem.add(ParserHandler.Humanities.parseArts());
                innerSectionItem.add(ParserHandler.Humanities.parseHistory());
                innerSectionItem.add(ParserHandler.Humanities.parseLanguageAndLiterature());
                innerSectionItem.add(ParserHandler.Humanities.parseLaw());
                innerSectionItem.add(ParserHandler.Humanities.parsePhilosophy());
                innerSectionItem.add(ParserHandler.Humanities.parseTheology());
                break;

            case "Social_Sciences":
                innerSectionItem.add(ParserHandler.SocialSciences.parseAnthropology());
                innerSectionItem.add(ParserHandler.SocialSciences.parseArchaeology());
                innerSectionItem.add(ParserHandler.SocialSciences.parseEconomics());
                innerSectionItem.add(ParserHandler.SocialSciences.parseHumanGeography());
                innerSectionItem.add(ParserHandler.SocialSciences.parsePoliticalScience());
                innerSectionItem.add(ParserHandler.SocialSciences.parsePsychology());
                innerSectionItem.add(ParserHandler.SocialSciences.parseSociology());
                innerSectionItem.add(ParserHandler.SocialSciences.parseSocialWork());
                break;
                /*
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
                break;*/

        }

        return new ArrayList<>(innerSectionItem);
    }


    static private void createGSON(){
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();
        String jsonString = gson.toJson(sectionItem);
        //System.out.println(jsonString);

        writeToFile(jsonString);
    }

    private static void writeToFile(String jsonString) {
        try {
            FileWriter myWriter = new FileWriter("out.json");
            myWriter.write(jsonString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

