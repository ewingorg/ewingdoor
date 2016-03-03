package com.ewing.core.mpskd.mvc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * mvc 包测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    HttpServletSupportTest.class,
    WechatWebSupportTest.class
})
public class MVCTest {

}
