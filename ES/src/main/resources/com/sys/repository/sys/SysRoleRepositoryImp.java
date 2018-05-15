package com.sys.repository.sys;

import com.sys.entity.sys.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysRoleRepositoryImp extends Repository<SysRole,String>, CrudRepository<SysRole,String>{

	@Query(value = "SELECT COUNT(*) FROM sys_role WHERE role_name = :roleName AND id != :id", nativeQuery = true)
	int checkRoleIsExist(@Param("roleName") String roleName, @Param("id") String id);

	@Query(value = "SELECT COUNT(*) FROM sys_role WHERE role_name = :roleName", nativeQuery = true)
	int checkRoleIsExistWithBlankId(@Param("roleName") String roleName);

}
