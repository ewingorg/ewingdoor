package com.core.mpsdk.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * core 包测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    JsonMsgBuilderTest.class,
    MessageHandlerTest.class,
    XmlMsgBuilderTest.class,
    WechatKernelTest.class
})
public class CoreTest {

}
