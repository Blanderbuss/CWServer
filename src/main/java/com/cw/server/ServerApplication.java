package com.cw.server;

import com.cw.exceptions.FighterException;
import com.cw.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication(scanBasePackages = {"com.cw"})
@EntityScan("com.cw")
public class ServerApplication {

    @Bean
    RmiServiceExporter sessionServiceExporter(SessionServiceI implementation) {
        Class<SessionServiceI> serviceInterface = SessionServiceI.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    public static void main(String[] args) throws FighterException, InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServerApplication.class, args);
        /*SessionServiceI testSession = applicationContext.getBean(SessionServiceI.class);
        String res = null;
        res = testSession.test();
        System.out.println(res);*/
    }

}
