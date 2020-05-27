package parser;

import model.SectionItem;
import org.jsoup.nodes.Document;
import sections.humanities.History;
import sections.humanities.LanguageAndLiterature;
import sections.humanities.Law;
import sections.humanities.Philosophy;
import sections.humanities.arts.PerformingArts;
import sections.humanities.arts.VisualArts;

import java.util.ArrayList;

public class ParserHandler {

    private Document doc;

    public ParserHandler(Document doc) {
        this.doc = doc;
    }

    public SectionItem parseArts(){
        SectionItem item1 = new SectionItem("Arts","","");
        SectionItem item1Inner = new SectionItem("Performing Arts","","");
        item1Inner.setSectionItems(PerformingArts.getList(doc));
        ArrayList<SectionItem> performingArts = new ArrayList<>();
        performingArts.add(item1Inner);
        item1.addItem(item1Inner);

        item1Inner = new SectionItem("Visual Arts","","");
        item1Inner.setSectionItems(VisualArts.getList(doc));
        ArrayList<SectionItem> visualArts = new ArrayList<>();
        visualArts.add(item1Inner);
        item1.addItem(item1Inner);

        return item1;
    }

    public SectionItem parseHistory(){
        SectionItem item = new SectionItem("History","","");
        item.setSectionItems(History.getList(doc));
        return item;
    }

    public SectionItem parseLanguageAndLiterature(){
        SectionItem item = new SectionItem("Language and Literature","","");
        item.setSectionItems(LanguageAndLiterature.getList(doc));
        return item;
    }

    public SectionItem parseLaw(){
        SectionItem item = new SectionItem("Law","","");
        item.setSectionItems(Law.getList(doc));
        return item;
    }

    public SectionItem parsePhilosophy(){
        SectionItem item = new SectionItem("Philosophy","","");
        item.setSectionItems(Philosophy.getList(doc));
        return item;
    }

}
