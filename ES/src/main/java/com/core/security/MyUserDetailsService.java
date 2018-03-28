package com.core.security;

import com.alibaba.fastjson.JSON;
import com.core.common.utills.EmptyUtils;
import com.sys.entity.sys.SysRole;
import com.sys.entity.sys.SysUser;
import com.sys.repository.sys.SysRoleRepositoryImp;
import com.sys.repository.sys.SysUserRepositoryImp;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MyUserDetailsService implements UserDetailsService {
    Logger log = Logger.getLogger(MyUserDetailsService.class);

    @Autowired
    private SysUserRepositoryImp sysUserRepositoryImp;
    @Autowired
    private SysRoleRepositoryImp sysRoleRepositoryImp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user;
        SysUserSecurity sysyUserSecurity = new SysUserSecurity();
        user = sysUserRepositoryImp.findOne(username);
        List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
        if (EmptyUtils.isNotEmpty(user)) {
            BeanUtils.copyProperties(sysyUserSecurity, user);
            List<SysRole> findRoleCode = sysRoleRepositoryImp.findRoleCode(user.getId().toString());
            if (EmptyUtils.isNotEmpty(findRoleCode)) {
                for (SysRole temp : findRoleCode) {
                    log.debug("User is : " + user.getUsername() + "Role is : " + temp.getRoleCode());
                    gList.add(new SimpleGrantedAuthority(temp.getRoleCode()));
                }
            }
        }

        if (EmptyUtils.isNotEmpty(gList)) {
            sysyUserSecurity.setGrantedAuthority(gList);
        } else {
            sysyUserSecurity.setGrantedAuthority(null);
        }

        log.info("copy : " + JSON.toJSONString(sysyUserSecurity));
        return sysyUserSecurity;

    }

}
