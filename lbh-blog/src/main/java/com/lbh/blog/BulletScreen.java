package com.lbh.blog;

import com.lbh.blog.bullet_screen_server.BulletScreenServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BulletScreen implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null){
            BulletScreenServer.getBulletScreenServer().SERVERStart();
        }
    }
}
