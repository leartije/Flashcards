package flashcards.io;

import flashcards.entity.Flashcard;

import java.io.*;
import java.util.List;
import java.util.Map;

public class SaveLoad {

    public void save(String fileName, Map<String, Flashcard> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            data.forEach((key, value) -> {
                try {
                    bw.write(key + "|" + value + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Log.println(data.size() + " cards have been saved.");

        } catch (IOException e) {
            Log.println("File not found");
        }
    }

    public void load(String fileName, Map<String, Flashcard> data) {
        int num = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String current;
            while ((current = br.readLine()) != null) {
                String[] split = current.split("\\|");

                data.put(split[0], new Flashcard(split[0], split[2], Integer.parseInt(split[3])));
                num++;

            }

            Log.println((num + " cards have been loaded."));

        } catch (IOException e) {
            Log.println("File not found");
        }
    }

    public void saveLog(String fileName, List<String> log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            log.forEach(s -> {
                try {
                    bw.write(s + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            bw.flush();
        } catch (IOException e) {
            Log.println("File not found");
        }
    }
}
