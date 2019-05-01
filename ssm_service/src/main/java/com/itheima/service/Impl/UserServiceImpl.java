package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysTUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysTUser sysTUser= userDao.findByName(username);

        if(sysTUser!=null){
            Collection<GrantedAuthority> grantedAuthorities=new ArrayList<>();

            for (Role role : sysTUser.getRoleList()) {
                SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+role.getRoleName());

                grantedAuthorities.add(authority);
            }
//下面语句中sysTUser.getPassword()前面的"{noop}"+要在用不加密的密码登录创建有加密密码的用户后再删除
            UserDetails userDetails = new User(sysTUser.getUsername(),sysTUser.getPassword(),grantedAuthorities);

            return userDetails;
        }
        return null;
    }

    @Override
    public PageInfo<SysTUser> findByPageHelper(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<SysTUser> userList=userDao.findAll();

        PageInfo<SysTUser> pageInfo = new PageInfo<>(userList, 4);

        return pageInfo;
    }

    @Override
    public void save(SysTUser user) {
        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);

        userDao.save(user);
    }

    @Override
    public boolean checkName(String username) {
        SysTUser user=userDao.checkName(username);

        if(user==null){
            return true;
        }

        return false;
    }

    @Override
    public SysTUser findByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public void addRoleListToUser(Integer[] ids, Integer userId) {
        userDao.removeAllRoleFromUser(userId);

        for (Integer roleId : ids) {
            userDao.addRoleToUser(roleId,userId);
        }
    }

}
