package com.sys.repository.sys;

import com.sys.entity.sys.SysRoleAcl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysRoleAclRepositoryImp extends Repository<SysRoleAcl, String>, CrudRepository<SysRoleAcl, String> {

    /**
     * 根据角色列表得到权限列表
     *
     * @param roleIdList
     * @return
     */
    @Query(value = "select acl_id from sys_role_acl where role_id in :roleIdList", nativeQuery = true)
    List<String> getAclIdListByRoleIdList(@Param("roleIdList") List roleIdList);

    /**
     * 根据角色得到权限列表
     * @param roleId
     * @return
     */
    @Query(value = "select acl_id from sys_role_acl where role_id = :roleId", nativeQuery = true)
    List<String> getAclIdListByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色id删除权限点
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);

}
