package com.ewing.core.mpskd;

import org.junit.Assert;
import org.junit.Test;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.ewing.core.mpsdk.Mpsdk4j;

/**
 * 测试Mpsdk4j
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class Mpsdk4jTest {

    private static final Log log = Logs.get();

    /**
     * Test method for {@link io.github.elkan1788.mpsdk4j.Mpsdk4j#version()}.
     */
    @Test
    public void testVersion() {
        new Mpsdk4j(); // Just cover testing
        log.debug(Mpsdk4j.version());
        String curver = "2.b.1";
        Assert.assertEquals(curver, Mpsdk4j.version());
    }

}
