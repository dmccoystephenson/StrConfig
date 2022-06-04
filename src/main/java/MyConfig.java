import java.util.HashMap;

/**
 * @author Daniel McCoy Stephenson
 * @since June 4th, 2022
 */
public class MyConfig {
    private final HashMap<String, String> map = new HashMap<>();

    /**
     *
     * @param key The key of the value to retrieve
     * @return
     * @throws KeyNotPresentException
     */
    public String get(String key) throws KeyNotPresentException {
        String value = map.get(key);
        if (value == null) {
            throw new KeyNotPresentException();
        }
        return value;
    }

    public void set(String key, String value) {
        if (isSet(key)) {
            map.replace(key, value);
        }
        else {
            map.put(key, value);
        }
    }

    public boolean isSet(String key) {
        try {
            get(key);
            return true;
        } catch(KeyNotPresentException e) {
            return false;
        }
    }

    public static class KeyNotPresentException extends Exception {
        // no body needed
    }
}