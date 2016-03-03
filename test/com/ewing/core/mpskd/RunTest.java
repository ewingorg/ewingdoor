package com.ewing.core.mpskd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ewing.core.mpskd.api.APITest;
import com.ewing.core.mpskd.core.CoreTest;
import com.ewing.core.mpskd.mvc.MVCTest;
import com.ewing.core.mpskd.qqwx_mp_aes.AESTest;
import com.ewing.core.mpskd.utils.UtilTest;
import com.ewing.core.mpskd.vo.VOTest;

/**
 * Test all class
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Mpsdk4jTest.class,
    UtilTest.class,
    VOTest.class,
    APITest.class,
    CoreTest.class,
    AESTest.class,
    MVCTest.class
})
public class RunTest {

}
