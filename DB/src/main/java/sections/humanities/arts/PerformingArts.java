package sections.humanities.arts;

import model.SectionItem;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class PerformingArts {

    public static ArrayList<SectionItem> getList(Document doc){

        ArrayList<SectionItem> arrayList = new ArrayList<>();
        Element list = doc.getElementsByClass("div-col columns column-width").get(0).child(0);

        for(Node node : list.childNodes()){

            if(node instanceof Element){
                Element element = (Element) node;
                String title = element.child(0).attr("href");
                SectionItem item1 = new SectionItem(title);
                item1.setSectionItems(getInnerList(element.childNodes().get(4).childNodes()));
                arrayList.add(item1);
            }
        }


        return arrayList;
    }

    private static ArrayList<SectionItem> getInnerList(List<Node> nodes){

        ArrayList<SectionItem> sectionItems = new ArrayList<>();
        for(Node node : nodes){
            if(node instanceof Element){
                Element element = (Element) node;
                String title = element.child(0).attr("href");
                SectionItem item1 = new SectionItem(title);
                try{
                    item1.setSectionItems(getMoreInnerList(element.childNodes().get(2).childNodes()));
                }catch (Exception e){
                    //System.out.println("Exception Caught: Ignoring...");
                    //e.printStackTrace();
                }
                sectionItems.add(item1);
                //System.out.println(title);
            }
        }

        return sectionItems;
    }

    private static ArrayList<SectionItem> getMoreInnerList(List<Node> nodes){

        ArrayList<SectionItem> sectionItems = new ArrayList<>();

        for(Node node : nodes){
            if(node instanceof Element){
                Element element = (Element) node;
                String title = element.child(0).attr("href");
                SectionItem item1 = new SectionItem(title);
                sectionItems.add(item1);
                //System.out.println(title);
            }
        }

        return sectionItems;
    }

}
