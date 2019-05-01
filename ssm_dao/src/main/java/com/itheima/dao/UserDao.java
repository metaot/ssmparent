package com.itheima.dao;

import com.itheima.domain.SysTUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {
    @Select("select * from sys_tuser where username=#{username} and status=1")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleList",column = "id",
                    many = @Many(select = "com.itheima.dao.RoleDao.findRoleListByUserId",fetchType = FetchType.LAZY))
    })
    SysTUser findByName(String username);

    @Select("select * from sys_tuser")
    List<SysTUser> findAll();

    @Insert("insert into sys_tuser values (tuser_seq.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysTUser user);

    @Select("select * from sys_tuser where username=#{username}")
    SysTUser checkName(String username);

    @Select("select * from sys_tuser where id = #{id}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleList",column = "id",
                    many = @Many(select = "com.itheima.dao.RoleDao.findRoleListByUserId",fetchType = FetchType.LAZY))
    })
    SysTUser findByUserId(Integer id);

    @Delete("delete from sys_tuser_role where userId=#{userId}")
    void removeAllRoleFromUser(Integer userId);

    @Insert("insert into sys_tuser_role values (#{param2},#{param1})")
    void addRoleToUser(Integer roleId,Integer userId);
}
