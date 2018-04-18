package com.cw.server;

import com.cw.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication(scanBasePackages = {"com.cw"})
@EntityScan("com.cw")
public class ServerApplication {

    /*@Bean
    FightServiceI serverServiceIF() {
        return new FightService();
    }*/

    @Bean
    RmiServiceExporter battleServiceExporter(FightServiceI implementation) {
        // Expose a service via RMI. Remote object URL is:
        // rmi://<HOST>:<PORT>/<SERVICE_NAME>
        // 1099 is the default port
        Class<FightServiceI> serviceInterface = FightServiceI.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    @Bean
    RmiServiceExporter userServiceExporter(UserServiceI implementation) {
        Class<UserServiceI> serviceInterface = UserServiceI.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    @Bean
    RmiServiceExporter setServiceExporter(SetServiceI implementation) {
        Class<SetServiceI> serviceInterface = SetServiceI.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    @Bean
    RmiServiceExporter artefactServiceExporter(ArtefactServiceI implementation) {
        Class<ArtefactServiceI> serviceInterface = ArtefactServiceI.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

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

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
