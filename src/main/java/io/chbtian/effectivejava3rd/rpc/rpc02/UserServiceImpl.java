package io.chbtian.effectivejava3rd.rpc.rpc02;

import io.chbtian.effectivejava3rd.rpc.IUserService;
import io.chbtian.effectivejava3rd.rpc.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User getUserById(Integer id) {
        return new User(id,"tom2");
    }
}
