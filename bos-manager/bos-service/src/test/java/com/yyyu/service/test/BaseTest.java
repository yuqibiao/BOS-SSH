package com.yyyu.service.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/7/19.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml "})
public abstract class BaseTest {
    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j2.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }

    @Before
    public abstract void initTest();

    protected void log(String str){
        System.out.println("test==="+str);
    }
}
