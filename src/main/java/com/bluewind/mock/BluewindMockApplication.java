package com.bluewind.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@EnableTransactionManagement // 简单开启事务管理
@MapperScan(basePackages = "com.bluewind.mock.**.mapper")
public class BluewindMockApplication {
    final static Logger logger = LoggerFactory.getLogger(BluewindMockApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(BluewindMockApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");

        logger.info("bluewind-mock 启动成功！\n" +
                "┌─┐┬ ┬┌─┐┌─┐┌─┐┌─┐┌─┐  ┌─┐┌┬┐┌─┐┬─┐┌┬┐┌─┐┌┬┐   ┬\n" +
                "└─┐│ ││  │  ├┤ └─┐└─┐  └─┐ │ ├─┤├┬┘ │ ├┤  ││   │\n" +
                "└─┘└─┘└─┘└─┘└─┘└─┘└─┘  └─┘ ┴ ┴ ┴┴└─ ┴ └─┘─┴┘   o\n" +
                "-------------------------------------------------------------------------"
        );

        logger.info("\n----------------------------------------------------------\n\t" +
                "Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + "/\n\t" +
                "----------------------------------------------------------");
    }

}
