package io.chbtian.effectivejava3rd.rpc;

public interface IUserService {
    User getUserById(Integer id);
    User getUserByIdAndName(Integer id,String name);
}
