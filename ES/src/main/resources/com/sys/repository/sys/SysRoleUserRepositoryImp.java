package com.sys.repository.sys;

import com.sys.entity.sys.SysRoleUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysRoleUserRepositoryImp extends Repository<SysRoleUser, String>, CrudRepository<SysRoleUser, String> {

    /**
     * 根据userid查询角色集合
     *
     * @param userId
     * @return
     */
    List<SysRoleUser> findByUserId(String userId);

    @Query(value = "select role_id from sys_role_user where user_id = :userId", nativeQuery = true)
    List<String> getRoleIdListByUserId(@Param("userId") String userId);


}
