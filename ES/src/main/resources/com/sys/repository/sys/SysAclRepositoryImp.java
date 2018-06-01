package com.sys.repository.sys;

import com.sys.entity.sys.SysAcl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface SysAclRepositoryImp extends Repository<SysAcl, String>, CrudRepository<SysAcl, String> {


    @Query(value = "SELECT COUNT(*) FROM sys_acl WHERE acl_module_id = :aclModuleId AND name = :name AND id != :id", nativeQuery = true)
    int checkAclNameIsExist(@Param("aclModuleId") String aclModuleId, @Param("name") String name, @Param("id") String id);

    @Query(value = "SELECT COUNT(*) FROM sys_acl WHERE acl_module_id = :aclModuleId AND name = :name", nativeQuery = true)
    int checkAclNameIsExistWithBlankId(@Param("aclModuleId") String parentId, @Param("name") String name);

    /**
     * 查询权限模块下面是否有权限点
     *
     * @param aclModuleId
     * @return
     */
    int countByAclModuleId(String aclModuleId);
}
