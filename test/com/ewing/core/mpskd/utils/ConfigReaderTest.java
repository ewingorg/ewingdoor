package com.ewing.core.mpskd.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.ewing.utils.ConfigReaderUtils;

/**
 * ConfigReader Testing
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class ConfigReaderTest {

    private static final int INTEGER = 1;
    private static final boolean BOOL = true;
    private static final long LONG = 100000000000000l;

    private ConfigReaderUtils reader;

    @Before
    public void init() {
        reader = new ConfigReaderUtils("/ErrorCode.properties");
        reader.put("int", String.valueOf(INTEGER));
        reader.put("bool", String.valueOf(BOOL));
        reader.put("long", String.valueOf(LONG));
    }

    @After
    public void testClear() {
        reader.clear();
        assertNull(reader.get("0"));
    }

    @Test
    public void testAll() {
        List<String> keys = reader.keys();
        Collection<String> values = reader.values();
        assertEquals(keys.size(), values.size());
    }

    @Test
    public void testGet() {
        assertNotNull(reader.get("0"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullFile() {
       try {
        new ConfigReaderUtils(ResourceUtils.getURL("classpath:conf/biz/properties/mpconf.properties").getFile());
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    @Test(expected = NullPointerException.class)
    public void testNullKey() {
        reader.get(null);
    }

    @Test
    public void testGetInt() {
        assertEquals(reader.getInt("int"), INTEGER);
    }

    @Test
    public void testGetLong() {
        assertEquals(reader.getLong("long"), LONG);
    }

    @Test
    public void testGetBoolean() {
        assertTrue(reader.getBoolean("bool"));
    }

}
