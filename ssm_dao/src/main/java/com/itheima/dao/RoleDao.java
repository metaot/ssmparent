package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {
    @Select("select * from sys_role order by id ")
    List<Role> findAll();

    @Select("insert into sys_role values(role_seq.nextval, #{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select r.* from sys_role r,sys_tuser_role ur where ur.roleId = r.id and ur.userId = #{userId}")
    @Results({
            //id列使用两次，所有即使属性名与列名相同，也必须做明确的映射
            @Result(property = "id",column = "id",id = true),
            @Result(property = "permissionList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionListByRoleId",fetchType = FetchType.LAZY))
    })
    public List<Role> findRoleListByUserId(Integer userId);

    @Select("select * from sys_role where id = #{roleId}")
    @Results({
            //id列使用两次，所有即使属性名与列名相同，也必须做明确的映射
            @Result(property = "id",column = "id",id = true),
            @Result(property = "permissionList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionListByRoleId",fetchType = FetchType.LAZY))
    })
    Role findPermissionByRoleId(Integer roleId);

    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    void removePermissionListFromRole(Integer roleId);

    @Insert("insert into sys_role_permission(permissionId,roleId) values(#{param1},#{param2})")
    void addPermissionListToRole(Integer permissionId, Integer roleId);
}
