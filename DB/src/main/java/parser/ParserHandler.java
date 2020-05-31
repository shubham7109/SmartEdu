package parser;

import model.SectionItem;
import org.jsoup.nodes.Document;
import sections.SampleSection;
import sections.humanities.History;
import sections.humanities.LanguageAndLiterature;
import sections.humanities.Law;
import sections.humanities.Philosophy;
import sections.humanities.arts.PerformingArts;
import sections.humanities.arts.VisualArts;

import java.util.ArrayList;

public class ParserHandler {

    private static Document doc;

    public ParserHandler(Document doc) {
        ParserHandler.doc = doc;
    }

    public static class Humanities{

        public static SectionItem parseArts(){
            SectionItem item1 = new SectionItem("Arts");
            SectionItem item1Inner = new SectionItem("Performing Arts");
            item1Inner.setSectionItems(PerformingArts.getList(doc));
            ArrayList<SectionItem> performingArts = new ArrayList<>();
            performingArts.add(item1Inner);
            item1.addItem(item1Inner);

            item1Inner = new SectionItem("Visual Arts");
            item1Inner.setSectionItems(VisualArts.getList(doc));
            ArrayList<SectionItem> visualArts = new ArrayList<>();
            visualArts.add(item1Inner);
            item1.addItem(item1Inner);

            return item1;
        }

        public static SectionItem parseHistory(){
            SectionItem item = new SectionItem("History");
            item.setSectionItems(History.getList(doc));
            return item;
        }

        public static SectionItem parseLanguageAndLiterature(){
            SectionItem item = new SectionItem("Language and Literature");
            item.setSectionItems(LanguageAndLiterature.getList(doc));
            return item;
        }

        public static SectionItem parseLaw(){
            SectionItem item = new SectionItem("Law");
            item.setSectionItems(Law.getList(doc));
            return item;
        }

        public static SectionItem parsePhilosophy(){
            SectionItem item = new SectionItem("Philosophy");
            item.setSectionItems(Philosophy.getList(doc));
            return item;
        }

        public static SectionItem parseTheology(){
            SectionItem item = new SectionItem("Theology");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > ul:nth-child(35)"));
            return item;
        }

    }

    public static class SocialSciences{

        public static SectionItem parseAnthropology(){
            SectionItem item = new SectionItem("Anthropology");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > ul:nth-child(39)"));
            return item;
        }

        public static SectionItem parseArchaeology(){
            SectionItem item = new SectionItem("Archaeology");
            return item;
        }

        public static SectionItem parseEconomics(){
            SectionItem item = new SectionItem("Economics");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > div:nth-child(43) > ul"));
            return item;
        }

        public static SectionItem parseHumanGeography(){
            SectionItem item = new SectionItem("Human Geography");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > div:nth-child(45) > ul"));
            return item;
        }

        public static SectionItem parsePoliticalScience(){
            SectionItem item = new SectionItem("Political Science");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > div:nth-child(48) > ul"));
            return item;
        }

        public static SectionItem parsePsychology(){
            SectionItem item = new SectionItem("Psychology");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > div:nth-child(52) > ul"));
            return item;
        }

        public static SectionItem parseSociology(){
            SectionItem item = new SectionItem("Sociology");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > div:nth-child(55) > ul"));
            return item;
        }

        public static SectionItem parseSocialWork(){
            SectionItem item = new SectionItem("Social Work");
            item.setSectionItems(SampleSection.getList(doc,"#mw-content-text > div > ul:nth-child(57)"));
            return item;
        }

    }


}
