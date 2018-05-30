package service;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.zy.test.service.ExceptionServiceBase;

import base.BaseTest;

public class ExceptionServiceTest extends BaseTest{
    @Mock 
    private ExceptionServiceBase ESB;
    
    @Test  
    public void simpleTest(){  
        try {
            ESB.test();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("aaaa");
            e.printStackTrace();
        }
    }  
}
