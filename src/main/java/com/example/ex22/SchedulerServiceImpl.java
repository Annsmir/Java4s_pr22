package com.example.ex22;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;

import java.lang.management.ManagementFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService, SchedulerServiceImplMBean {

    @PostConstruct
    private void init(){
        try {
            ObjectName n = new ObjectName("com.example.ex22:type=basic,name=SchedulerService");
            MBeanServer s = ManagementFactory.getPlatformMBeanServer();
            s.registerMBean(this, n);
            log.info("* Successfully registered instance {} of {} as MBean {}", 
                this, this.getClass().getName(), "SchedulerService");
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e) {
            log.info("* Error registering instance {} of {} as MBean {} : {}", 
                this, this.getClass().getName(), "SchedulerService", e);
        }
    }

    @Autowired
    AddressService svca;

    @Autowired
    BuildingService svcb;

    @Value("${com.example.ex22.path}")
    private String path;

    @Override
    @Scheduled(cron = "0 */3 * * * *")
    @Transactional(readOnly = true)
    public void saveDB() {
        log.info("* saveDB() called, path = {}", path);

        File dir = new File(path);

        if(dir.exists() & (!dir.isDirectory())) {
            dir.delete();
        }

        if(!dir.exists()) {
            dir.mkdirs();
        }

        if(dir.exists() & dir.isDirectory()) {

            for(File x : dir.listFiles()) x.delete();

            ObjectMapper m = new ObjectMapper();

            File fa = new File(dir, "Address.json");
            File fb = new File(dir, "Building.json");

            try {
                fa.createNewFile();
                fb.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try(PrintStream a = new PrintStream(fa)) {
                m.writeValue(fa, svca.getAll());
            } catch (IOException e) {
                log.info("* Save Address failed: {}", e);
            }

            try(PrintStream b = new PrintStream(fb)) {
                m.writeValue(fb, svcb.getAll());
            } catch (IOException e) {
                log.info("* Save Building failed: {}", e);
            }
        }

    }

    @Override
    public void helloJMX(String msg){
        log.info("* helloJMX(msg) called, with msg = {}", msg);
    }
    
}
