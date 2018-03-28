package com.sys.repository.sys;

import com.sys.entity.sys.SysDepartment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysDepartmentRepositoryImp extends Repository<SysDepartment, String>, CrudRepository<SysDepartment, String> {

    List<SysDepartment> findByLevelIsLike(@Param("level") String level);

    @Query(value = "SELECT COUNT(*) FROM sys_department WHERE dept_name = :deptName AND parent_id = :parentId AND id != :deptId", nativeQuery = true)
    int checkDeptIsExist(@Param("parentId") String parentId, @Param("deptName") String deptName, @Param("deptId") String deptId);

    @Query(value = "SELECT COUNT(*) FROM sys_department WHERE dept_name = :deptName AND parent_id = :parentId", nativeQuery = true)
    int checkDeptIsExistWithBlankId(@Param("parentId") String parentId, @Param("deptName") String deptName);

}
