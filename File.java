import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File {
    // saves file
    public void saveMe(ArrayList<String> myItem) {
        System.out.println(myItem);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ScanResults.txt"));
            writer.write(myItem.get(0));
            for (int i=1; i<myItem.size(); i++) {
                writer.write(myItem.get(i));
            }
            // writer.write(myItem);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }



    }
}