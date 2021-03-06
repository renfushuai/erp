package net.rfs.smartadmin.module.system.login;

import net.rfs.smartadmin.common.constant.JudgeEnum;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.constant.CommonConst;
import net.rfs.smartadmin.module.system.department.DepartmentDao;
import net.rfs.smartadmin.module.system.department.domain.entity.DepartmentEntity;
import net.rfs.smartadmin.module.system.employee.EmployeeDao;
import net.rfs.smartadmin.module.system.employee.constant.EmployeeResponseCodeConst;
import net.rfs.smartadmin.module.system.employee.constant.EmployeeStatusEnum;
import net.rfs.smartadmin.module.system.employee.domain.dto.EmployeeDTO;
import net.rfs.smartadmin.module.system.employee.domain.dto.EmployeeLoginFormDTO;
import net.rfs.smartadmin.module.business.log.LogService;
import net.rfs.smartadmin.module.business.log.userloginlog.domain.UserLoginLogEntity;
import net.rfs.smartadmin.module.system.login.domain.KaptchaVO;
import net.rfs.smartadmin.module.system.login.domain.LoginDetailVO;
import net.rfs.smartadmin.module.system.login.domain.LoginPrivilegeDTO;
import net.rfs.smartadmin.module.system.login.domain.RequestTokenBO;
import net.rfs.smartadmin.module.system.privilege.domain.entity.PrivilegeEntity;
import net.rfs.smartadmin.module.system.privilege.service.PrivilegeEmployeeService;
import net.rfs.smartadmin.util.SmartBeanUtil;
import net.rfs.smartadmin.util.SmartDigestUtil;
import net.rfs.smartadmin.util.SmartIPUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/3/27 0027 ?????? 18:10
 * @since JDK1.8
 */
@Slf4j
@Service
public class LoginService {

    private static final String VERIFICATION_CODE_REDIS_PREFIX = "vc_%s";

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private PrivilegeEmployeeService privilegeEmployeeService;

    @Autowired
    private LoginTokenService loginTokenService;

    @Autowired
    private LogService logService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private ValueOperations<String, String> redisValueOperations;

    /**
     * ??????
     *
     * @param loginForm ????????? ??????
     * @return ????????????????????????
     */
    public ResponseDTO<LoginDetailVO> login(@Valid EmployeeLoginFormDTO loginForm, HttpServletRequest request) {
//        String redisVerificationCode = redisValueOperations.get(loginForm.getCodeUuid());
//        //??????????????????????????????????????? ????????????
//        redisValueOperations.getOperations().delete(loginForm.getCodeUuid());
//        if (StringUtils.isEmpty(redisVerificationCode)) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
//        if (!redisVerificationCode.equalsIgnoreCase(loginForm.getCode())) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
        String loginPwd = SmartDigestUtil.encryptPassword(CommonConst.Password.SALT_FORMAT, loginForm.getLoginPwd());
        EmployeeDTO employeeDTO = employeeDao.login(loginForm.getLoginName(), loginPwd);
        if (null == employeeDTO) {
            return ResponseDTO.wrap(EmployeeResponseCodeConst.LOGIN_FAILED);
        }
        if (EmployeeStatusEnum.DISABLED.equalsValue(employeeDTO.getIsDisabled())) {
            return ResponseDTO.wrap(EmployeeResponseCodeConst.IS_DISABLED);
        }
        //jwt token??????
        String compactJws = loginTokenService.generateToken(employeeDTO);

        LoginDetailVO loginDTO = SmartBeanUtil.copy(employeeDTO, LoginDetailVO.class);

        //????????????????????????
        loginDTO.setPrivilegeList(initEmployeePrivilege(employeeDTO.getId()));

        loginDTO.setXAccessToken(compactJws);
        DepartmentEntity departmentEntity = departmentDao.selectById(employeeDTO.getDepartmentId());
        loginDTO.setDepartmentName(departmentEntity.getName());

        //?????????????????????
        Boolean isSuperman = privilegeEmployeeService.isSuperman(loginDTO.getId());
        loginDTO.setIsSuperMan(isSuperman);
        //??????????????????
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        UserLoginLogEntity logEntity =
                UserLoginLogEntity.builder()
                        .userId(employeeDTO.getId())
                        .userName(employeeDTO.getActualName())
                        .remoteIp(SmartIPUtil.getRemoteIp(request))
                        .remotePort(request.getRemotePort())
                        .remoteBrowser(userAgent.getBrowser().getName())
                        .remoteOs(userAgent.getOperatingSystem().getName())
                        .loginStatus(JudgeEnum.YES.getValue()).build();
        logService.addLog(logEntity);
        return ResponseDTO.succData(loginDTO);
    }

    /**
     * ??????????????????????????????token??????
     *
     * @param requestToken
     * @return ???????????????????????????bool
     */
    public ResponseDTO<Boolean> logoutByToken(RequestTokenBO requestToken) {
        privilegeEmployeeService.removeCache(requestToken.getRequestUserId());
        return ResponseDTO.succ();
    }

    /**
     * ???????????????
     *
     * @return
     */
    public ResponseDTO<KaptchaVO> verificationCode() {
        KaptchaVO kaptchaVO = new KaptchaVO();
        String uuid = buildVerificationCodeRedisKey(UUID.randomUUID().toString());
        String kaptchaText = defaultKaptcha.createText();

        String base64Code = "";

        BufferedImage image = defaultKaptcha.createImage(kaptchaText);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            base64Code = Base64.encodeBase64String(outputStream.toByteArray());
        } catch (Exception e) {
            log.error("verificationCode exception .{}", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    log.error("verificationCode outputStream close exception .{}", e);
                }
            }
        }
        kaptchaVO.setUuid(uuid);
        kaptchaVO.setCode("data:image/png;base64," + base64Code);
        redisValueOperations.set(uuid, kaptchaText, 60L, TimeUnit.SECONDS);
        return ResponseDTO.succData(kaptchaVO);
    }

    private String buildVerificationCodeRedisKey(String uuid) {
        return String.format(VERIFICATION_CODE_REDIS_PREFIX, uuid);
    }

    /**
     * ?????????????????????
     *
     * @param employeeId
     * @return
     */
    public List<LoginPrivilegeDTO> initEmployeePrivilege(Long employeeId) {
        List<PrivilegeEntity> privilegeList = privilegeEmployeeService.getPrivilegesByEmployeeId(employeeId);
        privilegeEmployeeService.updateCachePrivilege(employeeId, privilegeList);
        return SmartBeanUtil.copyList(privilegeList, LoginPrivilegeDTO.class);
    }

    public LoginDetailVO getSession(RequestTokenBO requestUser) {
        LoginDetailVO loginDTO = SmartBeanUtil.copy(requestUser.getEmployeeBO(), LoginDetailVO.class);
        List<PrivilegeEntity> privilegeEntityList = privilegeEmployeeService.getEmployeeAllPrivilege(requestUser.getRequestUserId());
        //======  ????????????   ======
        if (privilegeEntityList == null) {
            List<LoginPrivilegeDTO> loginPrivilegeDTOS = initEmployeePrivilege(requestUser.getRequestUserId());
            loginDTO.setPrivilegeList(loginPrivilegeDTOS);
        } else {
            loginDTO.setPrivilegeList(SmartBeanUtil.copyList(privilegeEntityList, LoginPrivilegeDTO.class));
        }

        //======  ???????????????   ======
//        List<LoginPrivilegeDTO> loginPrivilegeDTOS = initEmployeePrivilege(requestUser.getRequestUserId());
//        loginDTO.setPrivilegeList(loginPrivilegeDTOS);

        //?????????????????????
        Boolean isSuperman = privilegeEmployeeService.isSuperman(loginDTO.getId());
        loginDTO.setIsSuperMan(isSuperman);
        return loginDTO;
    }
}
