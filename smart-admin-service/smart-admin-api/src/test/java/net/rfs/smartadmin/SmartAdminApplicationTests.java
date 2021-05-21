package net.rfs.smartadmin;

import com.alibaba.fastjson.JSON;
import net.rfs.smartadmin.module.business.erp.domain.dto.OrderStatisticsInputDto;
import net.rfs.smartadmin.module.business.erp.service.SalesOrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmartAdminApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartAdminApplicationTests {
@Autowired
private SalesOrderInfoService salesOrderInfoService;
    @Test
    public void orderStatistics(){
        OrderStatisticsInputDto orderStatisticsInputDto = new OrderStatisticsInputDto();
        List<Object> objects = salesOrderInfoService.orderStatistics(orderStatisticsInputDto);
        System.out.println(JSON.toJSONString(objects));
    }
}