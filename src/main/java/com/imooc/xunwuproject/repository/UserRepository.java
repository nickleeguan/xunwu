package com.imooc.xunwuproject.repository;

import com.imooc.xunwuproject.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * User的数据库操作类
 */
public interface UserRepository extends CrudRepository<User, Long>{
    User findByName(String username);

    User findUserByPhoneNumber(String telephone);

    @Modifying
    @Query("update User as user set user.name = :name where id = :id")
    void updateUsername(@Param(value = "id") long id, @Param(value = "name") String name);

    @Modifying
    @Query("update User as user set user.email = :email where id = :id")
    void updateEmail(@Param(value = "id") long id, @Param(value = "email") String email);

    @Modifying
    @Query("update User as user set user.password = :password where id = :id")
    void updatePassword(@Param(value = "id") long id, @Param(value = "password") String password);
}
