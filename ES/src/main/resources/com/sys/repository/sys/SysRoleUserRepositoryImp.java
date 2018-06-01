package com.sys.repository.sys;

import com.sys.entity.sys.SysRoleUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysRoleUserRepositoryImp extends Repository<SysRoleUser, String>, CrudRepository<SysRoleUser, String> {

    /**
     * 根据userid查询角色对象集合
     *
     * @param userId
     * @return
     */
    List<SysRoleUser> findByUserId(String userId);

    /**
     * 根据用户id查询角色id集合
     *
     * @param userId
     * @return
     */
    @Query(value = "select role_id from sys_role_user where user_id = :userId", nativeQuery = true)
    List<String> getRoleIdListByUserId(@Param("userId") String userId);


    /**
     * 根据角色id查询用户id集合
     *
     * @param roleId
     * @return
     */
    @Query(value = "select user_id from sys_role_user where role_id = :roleId", nativeQuery = true)
    List<String> getUserIdListByRoleId(@Param("roleId") String roleId);


}


