package base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(value = SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring-mvc-config.xml",
        "file:src/main/webapp/WEB-INF/config/applicationContext.xml"})  
public class BaseTest {
    
}
