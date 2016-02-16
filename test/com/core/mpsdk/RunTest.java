package com.core.mpsdk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.core.mpsdk.api.APITest;
import com.core.mpsdk.core.CoreTest;
import com.core.mpsdk.mvc.MVCTest;
import com.core.mpsdk.qqwx_mp_aes.AESTest;
import com.core.mpsdk.utils.UtilTest;
import com.core.mpsdk.vo.VOTest;

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
