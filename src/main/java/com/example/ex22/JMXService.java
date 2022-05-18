package com.example.ex22;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;

import java.lang.management.ManagementFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JMXService implements JMXServiceMBean {

    @PostConstruct
    private void init(){
        try {
            ObjectName n = new ObjectName("com.example.ex22:type=basic,name=JMXAdaptor");
            MBeanServer s = ManagementFactory.getPlatformMBeanServer();
            s.registerMBean(this, n);
            log.info("* Successfully registered instance {} of {} as MBean {}", 
                this, this.getClass().getName(), "JMXService");
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e) {
            log.info("* Error registering instance {} of {} as MBean {} : {}", 
                this, this.getClass().getName(), "JMXService", e);
        }
    }

    @Autowired
    SchedulerService svc;

    @Override
    public void saveDB() {
        svc.saveDB();
    }
    
}
