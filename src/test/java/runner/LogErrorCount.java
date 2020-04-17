package runner;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LogErrorCount {


    @Test
    public void printErrorCount() {

        String filePath = System.getProperty("user.dir") + File.separator + "task_1_application.log";
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<>(Arrays.asList(content.split("\n")));
        Map<String, Integer> errCountMap = new HashMap<>();
        String err;
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

        System.out.println(errCountMap.toString());

    }

}
