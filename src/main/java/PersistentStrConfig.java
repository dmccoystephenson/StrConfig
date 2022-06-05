import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Daniel McCoy Stephenson
 * @since June 4th, 2022
 */
public class PersistentStrConfig extends StrConfig {
    private final String saveDirectoryPath;

    public PersistentStrConfig(String saveDirectoryPath) {
        this.saveDirectoryPath = saveDirectoryPath;
    }

    public void save() throws StrConfig.SaveFailureException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(saveDirectoryPath);

            for (String key : map.keySet()) {
                fileWriter.append(key).append("=").append(map.get(key));
            }

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new StrConfig.SaveFailureException();
        }
    }

    public void load() throws StrConfig.LoadFailureException {
        File file = new File(saveDirectoryPath);
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] keyValuePair = line.split("=");
                if (keyValuePair.length != 2) {
                    throw new StrConfig.LoadFailureException();
                }
                String key = keyValuePair[0];
                String value = keyValuePair[1];
                set(key, value);
            }

            scanner.close();
        } catch(FileNotFoundException e) {
            throw new StrConfig.LoadFailureException();
        }
    }
}