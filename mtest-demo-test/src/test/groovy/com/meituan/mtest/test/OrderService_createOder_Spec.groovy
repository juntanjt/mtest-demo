package com.meituan.mtest.test

import com.meituan.mtest.MTest
import com.meituan.mtest.MTestBaseCase
import com.meituan.mtest.MockMethod
import com.meituan.mtest.demo.item.service.ItemService
import com.meituan.mtest.demo.order.service.OrderService
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.test.context.ContextConfiguration
import spock.lang.Unroll

import javax.annotation.Resource

/**
 *
 * @author Jun Tan
 */
@ContextConfiguration(classes = [OrderService_createOder_Spec.class])
@ComponentScan("com.meituan.mtest.demo.order")
@ImportResource("classpath:spring-test-context.xml")
@Configuration
@MTest(testClass = OrderService.class, method = "createOder")
class OrderService_createOder_Spec extends MTestBaseCase {

    @Resource
    OrderService orderService

    @Unroll
    def "#testcase"() {
        given: ""

        when: ""
        def response = orderService.createOrder(userId, orderReqDTO)

        then: ""
        with(response) {
            success == expected.success
            value != null
        }

        where: ""
        testcase << testCase()
        [userId, orderReqDTO] << request()
        expected << expected()

    }

    @Override
    MockMethod[] getMockMethods() {
        return [
                new MockMethod(ItemService.class.getMethod('queryItemById', Long.class), ItemService.class, 'itemService')
        ]
    }

}
