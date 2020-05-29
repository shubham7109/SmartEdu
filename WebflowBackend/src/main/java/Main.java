
import org.json.JSONObject;
import utils.AcademicCollections;

import java.io.*;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException {
        JSONObject jsonObject  = new JSONObject(readFromFile());

        AcademicCollections academicCollections = new AcademicCollections(jsonObject.getJSONArray("sectionItems"));
        try {
            academicCollections.updateCollection();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile() throws IOException {
        StringBuilder fileText = new StringBuilder();
        File fileToRead = new File("../DB/out.json");
        try(FileReader fileStream = new FileReader( fileToRead );
            BufferedReader bufferedReader = new BufferedReader( fileStream ) ) {
            String line;
            while( (line = bufferedReader.readLine()) != null ) {
                fileText.append(line);
            }
        } catch ( IOException ex ) {
            //exception Handling
        }
        return fileText.toString();
    }

}
