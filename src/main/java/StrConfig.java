import java.util.HashMap;

/**
 * @author Daniel McCoy Stephenson
 * @since June 4th, 2022
 */
public class StrConfig {
    private final HashMap<String, String> map = new HashMap<>();

    /**
     * Retrieve a value associated with a key.
     * @param key The key of the value to retrieve
     * @return The value associated with the given key.
     * @throws KeyNotPresentException This will be thrown if the key is not present.
     */
    public String get(String key) throws KeyNotPresentException {
        String value = map.get(key);
        if (value == null) {
            throw new KeyNotPresentException();
        }
        return value;
    }

    /**
     * Set a value associated with a key.
     * @param key The key that will be used to store the given value.
     * @param value The value associated with the given key.
     */
    public void set(String key, String value) {
        if (isSet(key)) {
            map.replace(key, value);
        }
        else {
            map.put(key, value);
        }
    }

    /**
     * Check if a key exists.
     * @param key The key we're checking the presence of.
     * @return Whether or not the key was present.
     */
    public boolean isSet(String key) {
        try {
            get(key);
            return true;
        } catch(KeyNotPresentException e) {
            return false;
        }
    }

    /**
     * Thrown when a key is not present in the underlying map.
     */
    public static class KeyNotPresentException extends Exception {
        // no body needed
    }
}