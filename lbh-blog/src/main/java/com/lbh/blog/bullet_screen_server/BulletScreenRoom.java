package com.lbh.blog.bullet_screen_server;

import java.util.Date;

public class BulletScreenRoom {
    public static String showMessage(String userName, String message) {
        return   (new Date().toString() + "[" + userName + "]:" + message);
    }
}