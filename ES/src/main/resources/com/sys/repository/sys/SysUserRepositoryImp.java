package com.sys.repository.sys;


import com.sys.entity.sys.SysUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SysUserRepositoryImp extends Repository<SysUser,String>, CrudRepository<SysUser,String>{

    SysUser findByUsernameOrEmailOrPhone(String username, String email, String phone);


    int countByEmail(String eamil);

    int countByEmailAndId(String eamil, String id);

    int countByPhone(String phone);

    int countByPhoneAndId(String phone, String id);
}
