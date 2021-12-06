package cn.ocxx.property;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;

import java.util.Properties;


public class PropertyUtil {

    private static final Map<String, String> propertiesCache = new HashMap<>();
    private static final String CUSTOM_FILE_NAME = "cu.properties";
    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtil.class);

    public static String getCustomPropertyValue(String key, String defaultValue) {
        String value = propertiesCache.get(key);
        if (value != null) {
            return value;
        }
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(CUSTOM_FILE_NAME));
            String property = properties.getProperty(key);
            LOG.info("abc: {} {} {}", key, defaultValue, property);
            System.out.println(String.format("pabc %s %s %s", key, defaultValue, property));

            propertiesCache.put(key, property);
            return property;
        } catch (IOException e) {
            propertiesCache.put(key, defaultValue);
            LOG.info("def: {} {} {}", key, defaultValue, e.getMessage());
            System.out.println(String.format("pdef %s %s %s", key, defaultValue, e.getMessage()));
            return defaultValue;
        }
    }


}
