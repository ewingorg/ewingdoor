package com.ewing.core.mpskd;

import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

import com.ewing.utils.ConfigReaderUtils;

/**
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class TestSupport {

    protected static ConfigReaderUtils _cr;

    static {
      try {
        _cr = new ConfigReaderUtils(ResourceUtils.getURL("classpath:config/properties/mpconf.properties").getFile());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
}
