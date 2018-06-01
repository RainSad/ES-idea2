package com.sys.service.sys;

import com.core.common.exception.ParamException;
import com.core.common.utills.IpUtil;
import com.core.common.utills.LevelUtil;
import com.core.common.webUtils.BeanValidator;
import com.core.common.webUtils.RequestHolder;
import com.google.common.base.Preconditions;
import com.sys.entity.param.DeptParam;
import com.sys.entity.sys.SysDepartment;
import com.sys.repository.sys.SysDepartmentRepositoryImp;
import com.sys.repository.sys.SysUserRepositoryImp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysDepartmentService {
    Logger log = Logger.getLogger(SysDepartmentService.class);

    @Resource
    private SysDepartmentRepositoryImp sysDepartmentRepositoryImp;

    @Resource
    private SysUserRepositoryImp sysUserRepositoryImp;

    /**
     * 添加部门
     *
     * @param param
     */
    public void save(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同的部门");
        }
        SysDepartment dept = SysDepartment.builder()
                .deptName(param.getName())
                .seq(param.getSeq())
                .parentId(param.getParentId())
                .remark(param.getRemark())
                .deptAddress(param.getDeptAddress())
                .deptPhone(param.getDeptPhone())
                .build();

        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        dept.setOperator(RequestHolder.getCurrentUser().getUsername());
        dept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        dept.setOperateTime(new Date());
        dept.setCreateTime(new Date());
        //不使用spring data是因为他使用hibernate方法，主键为查询最大值后自动生成,因此没有指定主键的情况下，用save会报异常。
        sysDepartmentRepositoryImp.save(dept);
    }

    /**
     * 更新部门
     * @param param
     */
    public void update(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同的部门");
        }
        SysDepartment before = sysDepartmentRepositoryImp.findOne(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");
        SysDepartment after = SysDepartment.builder()
                .id(param.getId())
                .deptName(param.getName())
                .seq(param.getSeq())
                .parentId(param.getParentId())
                .remark(param.getRemark())
                .deptPhone(param.getDeptPhone())
                .deptAddress(param.getDeptAddress())
                .build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());

        updateWithChild(before, after);
    }

    @Transactional
    public void updateWithChild(SysDepartment before, SysDepartment after) {

        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel() + "." + before.getId();
        if (!newLevelPrefix.equals(oldLevelPrefix)) {
            List<SysDepartment> deptList = sysDepartmentRepositoryImp.findByLevelIsLike(oldLevelPrefix + ".%");
            if (!CollectionUtils.isEmpty(deptList)) {
                for (SysDepartment dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDepartmentRepositoryImp.save(deptList);
            }
        }
        sysDepartmentRepositoryImp.save(after);
    }

    /**
     * 删除部门
     *
     * @param deptId
     */
    public void delete(String deptId) {
        SysDepartment dept = sysDepartmentRepositoryImp.findOne(deptId);
        Preconditions.checkNotNull(dept, "待删除的部门不存在！无法删除！");
        if (sysDepartmentRepositoryImp.countByParentId(dept.getId()) > 0) {
            throw new ParamException("当前部门下面存在子部门，无法删除！");
        }
        if (sysUserRepositoryImp.countByDeptId(dept.getId()) > 0) {
            throw new ParamException("当前部门下面有用户，无法删除！");
        }
        sysDepartmentRepositoryImp.delete(deptId);
    }

    public boolean checkExist(String parentId, String deptName, String deptId) {

        return StringUtils.isEmpty(deptId) ? sysDepartmentRepositoryImp.checkDeptIsExistWithBlankId(parentId, deptName) > 0 : sysDepartmentRepositoryImp.checkDeptIsExist(parentId, deptName, deptId) > 0;
    }


    private String getLevel(String deptId) {
        SysDepartment sysDepartment = sysDepartmentRepositoryImp.findOne(deptId);
        if (sysDepartment == null) {
            return null;
        }
        return sysDepartment.getLevel();
    }
}
