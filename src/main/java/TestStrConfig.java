import org.junit.Assert;
import org.junit.Test;

/**
 * @author Daniel McCoy Stephenson
 * @since June 4th, 2022
 */
public class TestStrConfig {
    private final StrConfig strConfig = new StrConfig();

    @Test
    public void testPlacementAndRetrieval() {
        String key = "test key";
        String value = "test value";

        // test placement
        place(key, value);
        boolean valueIsPresent = strConfig.isSet(key);
        Assert.assertTrue(valueIsPresent);

        // test retrieval
        String retrievedValue = null;
        try {
            retrievedValue = retrieve(key);
        } catch (StrConfig.KeyNotPresentException e) {
            Assert.fail();
        }
        Assert.assertEquals(value, retrievedValue);
    }

    @Test
    public void testReplacement() {
        String key = "test key";
        String value = "test value";
        String replacementValue = "replacement test value";

        // place
        place(key, value);

        // replace
        place(key, replacementValue);

        // retrieve new value and verify it is correct
        String retrievedValue = null;
        try {
            retrievedValue = retrieve(key);
        } catch (StrConfig.KeyNotPresentException e) {
            Assert.fail();
        }
        Assert.assertEquals(replacementValue, retrievedValue);
    }

    /**
     * Helper method to simplify test code.
     */
    private void place(String key, String value) {
        strConfig.set(key, value);
    }

    /**
     * Helper method to simplify test code.
     */
    private String retrieve(String key) throws StrConfig.KeyNotPresentException {
        return strConfig.get(key);
    }
}