package com.sys.repository.sys;

import com.sys.entity.sys.SysAclModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysAclModuleRepositoryImp extends Repository<SysAclModule, String>, CrudRepository<SysAclModule, String> {

    List<SysAclModule> findByLevelIsLike(@Param("level") String level);

    @Query(value = "SELECT COUNT(*) FROM sys_acl_module WHERE name = :aclModuleName AND parent_id = :parentId AND id != :aclModuleId", nativeQuery = true)
    int checkAclModuleNameIsExist(@Param("parentId") String parentId, @Param("aclModuleName") String aclModuleName, @Param("aclModuleId") String aclModuleId);

    @Query(value = "SELECT COUNT(*) FROM sys_acl_module WHERE name = :aclModuleName AND parent_id = :parentId", nativeQuery = true)
    int checkAclModuleNameIsExistWithBlankId(@Param("parentId") String parentId, @Param("aclModuleName") String aclModuleName);

}
