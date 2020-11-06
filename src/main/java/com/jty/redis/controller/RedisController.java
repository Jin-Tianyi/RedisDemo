package com.jty.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :jty
 * @date :20-9-27
 */
@RestController
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value="/put/")
    public String put(){
        try{
            stringRedisTemplate.opsForValue().set("key1","hello world!");
            stringRedisTemplate.opsForValue().set("key2","2020");
            String mes = stringRedisTemplate.opsForValue().get("key1");
            //设置有效期十秒
            stringRedisTemplate.opsForValue().set("key1",mes,Duration.ofSeconds(10));
            return mes;
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping(value="/get/")
    public String get(){
        try{
           String mes= stringRedisTemplate.opsForValue().get("key1");
           String number =stringRedisTemplate.opsForValue().get("key2");
           Map map = new HashMap<String,Object>(10);
           map.put("key1",mes);
           map.put("key2",number);
           //key2减1
            stringRedisTemplate.opsForValue().increment("key2");
            return map.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping(value="/put/obj")
    public String putList(){
        try{
            redisTemplate.opsForValue().set("user1",new User("张三","北京"));
            return redisTemplate.opsForValue().get("user1").toString();
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @GetMapping(value="/get/obj")
    public String getList(){
        try{
            User user= (User)redisTemplate.opsForValue().get("user1");
            return user.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
