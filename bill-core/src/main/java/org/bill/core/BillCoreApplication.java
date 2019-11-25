package org.bill.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 */
@SpringBootApplication
@MapperScan("org.bill.core.repository")
@ComponentScan(basePackages = {"org.bill"})
@EnableTransactionManagement
public class BillCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillCoreApplication.class, args);
    }
}
