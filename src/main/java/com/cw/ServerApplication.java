package com.cw;

import com.cw.appif.ServerServiceIF;
import com.cw.models.db.services.ArtefactServiceI;
import com.cw.models.db.services.SessionServiceI;
import com.cw.models.db.services.SetServiceI;
import com.cw.models.db.services.UserServiceI;
import com.cw.server.ServerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication(scanBasePackages = {"com.cw"})
@EntityScan("com.cw")
public class ServerApplication {

    /*@Bean
    ServerServiceIF serverServiceIF() {
        return new ServerService();
    }*/

    @Bean
    RmiServiceExporter battleServiceExporter(ServerServiceIF implementation) {
        // Expose a service via RMI. Remote object URL is:
        // rmi://<HOST>:<PORT>/<SERVICE_NAME>
        // 1099 is the default port
        Class<ServerServiceIF> serviceInterface = ServerServiceIF.class;
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
