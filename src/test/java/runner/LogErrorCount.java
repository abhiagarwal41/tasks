package runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LogErrorCount {

    public static void main(String[] args) {

        try {
            System.out.println(getErrorCount("./task_1_application.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getErrorCount(String filePath) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        List<String> lines = new ArrayList<>(Arrays.asList(content.split("\n")));
        Map<String, Integer> errCountMap = new HashMap<>();
        String err = "";
        for (String line: lines) {
            if (line.contains("ERROR")) {
                err = line.substring(line.indexOf("ERROR") + 6);
                err = err.substring(0,err.indexOf(" "));
                if (errCountMap.containsKey(err))
                    errCountMap.put(err, errCountMap.get(err) + 1);
                else
                    errCountMap.put(err, 1);
            }
        }

        return errCountMap.toString();

    }

}
