package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();
    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) props.load(is);
        } catch (Exception ignored) {}
    }
    public static String get(String key) {
        String v = props.getProperty(key);
        if (v != null && !v.isBlank()) return v;
        v = System.getProperty(key);
        return (v != null && !v.isBlank()) ? v : null;
    }
    public static String getOrDefault(String key, String defVal) {
        String v = get(key);
        return (v == null || v.isBlank()) ? defVal : v;
    }
}
