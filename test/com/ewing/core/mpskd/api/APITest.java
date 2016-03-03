package com.ewing.core.mpskd.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * API 包测试
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CredentialAPITest.class,
    MenuAPITest.class,
    MediaAPITest.class,
    GroupsAPITest.class,
    QRCodeAPITest.class,
    UserAPITest.class,
    TemplateAPITest.class
})
public class APITest {

}
