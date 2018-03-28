package com.sys.service.sys;

import com.core.common.exception.ParamException;
import com.core.common.utills.IpUtil;
import com.core.common.utills.MD5Util;
import com.core.common.utills.MailUtil;
import com.core.common.utills.PassWordUtil;
import com.core.common.webUtils.BeanValidator;
import com.core.common.webUtils.RequestHolder;
import com.google.common.base.Preconditions;
import com.sys.entity.param.Mail;
import com.sys.entity.param.UserParam;
import com.sys.entity.sys.SysUser;
import com.sys.repository.sys.SysUserRepositoryImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class SysUserService {

    Logger log = Logger.getLogger(SysUserService.class);

    @Autowired
    private SysUserRepositoryImp sysUserRepositoryImp;

    @Transactional
    public void save(UserParam param) {
        BeanValidator.check(param);
        if (checkEmailExist(param.getEmail(), null)) {
            throw new ParamException("电话已被占用");
        }
        if (checkTelPhoneExist(param.getPhone(), null)) {
            throw new ParamException("邮箱已被占用");
        }

        String passWord = PassWordUtil.randomPassWord(); //由于没有发邮件，暂时设置为固定值
//发送邮件，以后有需要再弄
//        Mail mail = Mail.builder()
//                .subject("万里长征最后一步测试")
//                .receiverName("我要女票")
//                .receiver("sunwenxiang@fhzz.com.cn")
//                .message(getString())
//                .host("smtp.qq.com")
//                .sender("979362142@qq.com")
//                .name("YourView")
//                .username("979362142@qq.com")
//                .password("sun1993322!!@@")
//                .build();
//        Boolean send = MailUtil.send(mail);
//        if(send){
//            log.info("发送邮件成功");
//        }
        passWord = "123456";
        String encryptedPassWord = MD5Util.encrypt(passWord);
        SysUser sysUser = SysUser.builder()
                .username(param.getUsername())
                .realname(param.getRealname())
                .nickname(param.getNickname())
                .password(encryptedPassWord)
                .phone(param.getPhone())
                .email(param.getEmail())
                .birthday(param.getBirthday())
                .geneder(param.getGeneder())
                .regTime(param.getRegTime())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .operator(RequestHolder.getCurrentUser().getUsername()) //TODO
                .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())) //TODO
                .operateTime(new Date())
                .build();

        // TODO  send Email
        sysUserRepositoryImp.save(sysUser);

    }



    @Transactional
    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkEmailExist(param.getEmail(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkTelPhoneExist(param.getPhone(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserRepositoryImp.findOne(param.getId().toString());
        Preconditions.checkNotNull(before, "待更新的用户不存在");

        SysUser after = SysUser.builder()
                .id(param.getId().toString())
                .username(param.getUsername())
                .realname(param.getRealname())
                .nickname(param.getNickname())
                .password(before.getPassword())
                .phone(param.getPhone())
                .email(param.getEmail())
                .birthday(param.getBirthday())
                .geneder(param.getGeneder())
                .regTime(param.getRegTime())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .operator(RequestHolder.getCurrentUser().getUsername()) //TODO
                .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())) //TODO
                .operateTime(new Date())
                .build();

        //更新
        sysUserRepositoryImp.save(after);
    }

    /**
     * 根据用户名，邮箱，电话登陆
     *
     * @param keyWord
     * @return
     */
    public SysUser findByKeyWord(String keyWord) {
        return sysUserRepositoryImp.findByUsernameOrEmailOrPhone(keyWord, keyWord, keyWord);
    }

    /**
     * 检测邮箱是否被占用
     *
     * @param mail
     * @param userId userID为空时只检测email，不为空，同时检测
     * @return
     */
    public boolean checkEmailExist(String mail, Integer userId) {
        if (StringUtils.isEmpty(userId)) {
            return sysUserRepositoryImp.countByEmail(mail) > 0;
        } else {
            return sysUserRepositoryImp.countByEmailAndId(mail, userId.toString()) > 0;
        }
    }

    /**
     * 检测电话是否被占用
     *
     * @param phone
     * @param userId userID为空时只检测phone，不为空，同时检测
     * @return
     */
    public boolean checkTelPhoneExist(String phone, Integer userId) {
        if (StringUtils.isEmpty(userId)) {
            return sysUserRepositoryImp.countByPhone(phone) > 0;
        } else {
            return sysUserRepositoryImp.countByPhoneAndId(phone, userId.toString()) > 0;
        }

    }

    /**
     * 邮件发送格式
     * @return
     */
    private String getString() {
        String html = "<!DOCTYPE html>";
        html += "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
        html += "<title>Insert title here</title>";
        html += "</head><body>";
        html += "<div style=\"width:600px;height:400px;margin:50px auto;\">";
        html += "<h1>我来看看邮件是否发送成功呢</h1><br/><br/>";
        html += "<p>下面是通过该协议可以创建一个指向电子邮件地址的超级链接，通过该链接可以在Internet中发送电子邮件</p><br/>";
        html += "<a href=\"mailto:huntereagle@foxmail.com?subject=test&cc=huntertochen@163.com&body=use mailto sample\">send mail</a>";
        html += "</div>";
        html += "</body></html>";
        return html;
    }
}
