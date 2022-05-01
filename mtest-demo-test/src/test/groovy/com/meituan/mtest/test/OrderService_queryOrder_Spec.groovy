package com.meituan.mtest.test

import com.meituan.mtest.MTest
import com.meituan.mtest.MTestBaseCase
import com.meituan.mtest.demo.order.service.OrderService
import org.assertj.core.api.Assertions
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
@ContextConfiguration(classes = [OrderService_queryOrder_Spec.class])
@ComponentScan("com.meituan.mtest.demo.order")
@ImportResource("classpath:spring-test-context.xml")
@Configuration
@MTest(testClass = OrderService.class, method = "queryOrder")
class OrderService_queryOrder_Spec extends MTestBaseCase {

    @Resource
    OrderService orderService

    @Unroll
    def "#testcase"() {
        given: ""

        when: ""
        def response = orderService.queryOrder(userId, orderId)

        then: ""
        with(response) {
            success == expected.success
        }

        Assertions.assertThat(response).usingRecursiveComparison()
                .ignoringFields("value.createTime").isEqualTo(expected)

        where: ""
        testcase << testCase()
        [userId, orderId] << request()
        expected << expected()

    }

}
