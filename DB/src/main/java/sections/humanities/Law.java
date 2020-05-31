package sections.humanities;

import model.SectionItem;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class Law {
    public static ArrayList<SectionItem> getList(Document doc){

        ArrayList<SectionItem> arrayList = new ArrayList<>();
        Element list = doc.getElementsByClass("div-col columns column-width").get(4).child(0);

        for(Node node : list.childNodes()){

            if(node instanceof Element){
                Element element = (Element) node;
                String title = element.child(0).attr("href");
                //System.out.println(title);
                SectionItem item1 = new SectionItem(title);
                try {
                    item1.setSectionItems(getInnerList(element.childNodes().get(2).childNodes()));
                }catch (Exception ignored){}
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
                    item1.setSectionItems(getMoreInnerList(element.childNodes().get(getULIndex(element.childNodes())).childNodes()));
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

    private static int getULIndex(List<Node> childNodes){
        for(int i=0; i<childNodes.size(); i++){
            String val = childNodes.get(i).toString();
            if(childNodes.get(i).toString().contains("<ul>")){
                return i;
            }
        }
        return 0;
    }

}