package io.chbtian.effectivejava3rd.rpc.rpc01;

import io.chbtian.effectivejava3rd.rpc.IUserService;
import io.chbtian.effectivejava3rd.rpc.User;

public class UserServiceImpl implements IUserService {
    @Override
    public User getUserById(Integer id) {
        return new User(id,"tom");
    }

    @Override
    public User getUserByIdAndName(Integer id, String name) {
        return new User(id,name);
    }
}
