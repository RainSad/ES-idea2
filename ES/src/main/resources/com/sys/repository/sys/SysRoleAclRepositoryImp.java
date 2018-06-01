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

    /**
     * 根据权限点id查询拥有该权限的角色
     *
     * @param aclId
     * @return
     */
    @Query(value = "select role_id from sys_role_acl where acl_id = :aclId", nativeQuery = true)
    List<String> getRoleIdsByAclId(@Param("aclId") String aclId);

}
