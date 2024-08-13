 
package model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateSessionFiles {

    public static void main(String[] args) {
        createFile("available_sessions.txt", new String[]{
                "Math,Mon,09:00,1h,Room 101,30,25",
                "Science,Wed,11:00,2h,Room 102,25,20",
                "History,Fri,14:00,1.5h,Room 103,20,18",
                "English,Tue,10:00,1h,Room 104,30,28",
                "Physics,Thu,13:00,1.5h,Room 105,20,19"
        });

        createFile("enrolled_sessions.txt", new String[]{
                "Math,Mon,09:00,1h,Room 101,30,25",
                "History,Fri,14:00,1.5h,Room 103,20,18",
                "Physics,Thu,13:00,1.5h,Room 105,20,19"
        });
    }

    private static void createFile(String fileName, String[] data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
