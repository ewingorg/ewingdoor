package com.ewing.core.mpskd;

import com.ewing.utils.ConfigReaderUtils;

/**
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class TestSupport {

    protected static ConfigReaderUtils _cr;

    static {
        _cr = new ConfigReaderUtils("conf/biz/properties/mpconf.properties");
    }
}
