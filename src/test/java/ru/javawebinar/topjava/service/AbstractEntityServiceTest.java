package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractEntityServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final Logger resultLog = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            resultLog.info(result + " ms\n");
        }
    };

    @BeforeClass
    public static void clearResult() {
        results.setLength(0);
    }

    @AfterClass
    public static void printResult() {
        resultLog.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------\n" +
                results +
                "---------------------------------\n");
    }


    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Test
    public abstract void testDelete() throws Exception;

    @Test
    public abstract void testDeleteNotFound() throws Exception;

    @Test
    public abstract void testCreate() throws Exception;

    @Test
    public abstract void testGet() throws Exception;

    @Test
    public abstract void testGetNotFound() throws Exception;

    @Test
    public abstract void testUpdate() throws Exception;

    @Test
    public abstract void testGetAll() throws Exception;


}
