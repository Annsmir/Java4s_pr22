package com.example.ex22;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class EmailAspect {

    @Autowired
    SpringEmailService sender;
    
    @Before("databaseWriteAccessMethods()")
    public void logMethodAcceptionEntityAnnotatedBean(JoinPoint jp) {

        StringBuilder sb = new StringBuilder();

        for( Object o : jp.getArgs() ) {
            sb.append(o.toString());
            sb.append(' ');
        }

        sender.sendMail("Database write notificaton", jp.toLongString() + " with args " + sb.toString());
    }

    @Pointcut("execution(* com.example.*.*Service*.create*(..))")
    public void create() {}
    @Pointcut("execution(* com.example.*.*Service*.update*(..))")
    public void update() {}
    @Pointcut("execution(* com.example.*.*Service*.delete*(..))")
    public void delete() {}

    @Pointcut("update() || delete() || create()")
    public void databaseWriteAccessMethods() {}


}
