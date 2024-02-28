package com.yue.yojbackenduserservice.controller.inner;

import com.yue.yojbackendclientservice.service.UserFeignClient;
import com.yue.yojbackendmodel.model.entity.User;
import com.yue.yojbackenduserservice.controller.UserController;
import com.yue.yojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author Yuuue
 * creat by 2023-12-28
 * 用于内部服务调用的接口
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;


    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId")Long userId) {
        return userService.getById(userId);
    }

    @Override
    @GetMapping("/list")
    public List<User> listByIds(@RequestParam("userIdSet")Collection<Long> userIdSet) {
        return userService.listByIds(userIdSet);
    }
}
