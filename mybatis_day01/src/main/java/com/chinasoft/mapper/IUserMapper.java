package com.chinasoft.mapper;

import com.chinasoft.domain.User;

import java.util.List;

public interface IUserMapper {
   /*
   *
   * 查询所有用户
   * @return
   * */
    List<User> findAll();
    /*
    * 添加用户信息
    * @param user
    *
    * */
    void addUser(User user);
    /*
    *
    * 删除用户信息
    *
    * */
    void deleteUserById(Integer id);

    /*
    * 修改用户
    *
    * */
    void updateUser(User user);

    /*
    * 查询用户
    *
    * */
    User findUserById(Integer id);

    /*
    * 根据条件查询用户信息
    * */
 List<User> findUserByCondition(User user);
}
