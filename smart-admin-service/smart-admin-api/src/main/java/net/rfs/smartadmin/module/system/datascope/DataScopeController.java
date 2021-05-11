package net.rfs.smartadmin.module.system.datascope;

import net.rfs.smartadmin.common.anno.NoValidPrivilege;
import net.rfs.smartadmin.common.anno.OperateLog;
import net.rfs.smartadmin.constant.SwaggerTagConst;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.module.system.datascope.domain.dto.DataScopeAndViewTypeVO;
import net.rfs.smartadmin.module.system.datascope.domain.dto.DataScopeBatchSetRoleDTO;
import net.rfs.smartadmin.module.system.datascope.domain.dto.DataScopeSelectVO;
import net.rfs.smartadmin.module.system.datascope.service.DataScopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * [  ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/4/27 0027 下午 15:12
 * @since JDK1.8
 */
@Api(tags = {SwaggerTagConst.Admin.MANAGER_DATA_SCOPE})
@OperateLog
@RestController
public class DataScopeController {

    @Autowired
    private DataScopeService dataScopeService;

    @ApiOperation(value = "获取当前系统所配置的所有数据范围")
    @GetMapping("/dataScope/list")
    @NoValidPrivilege
    public ResponseDTO<List<DataScopeAndViewTypeVO>> dataScopeList() {
        return dataScopeService.dataScopeList();
    }

    @ApiOperation(value = "获取某角色所设置的数据范围")
    @GetMapping("/dataScope/listByRole/{roleId}")
    @NoValidPrivilege
    public ResponseDTO<List<DataScopeSelectVO>> dataScopeListByRole(@PathVariable Long roleId) {
        return dataScopeService.dataScopeListByRole(roleId);
    }

    @ApiOperation(value = "批量设置某角色数据范围")
    @PostMapping("/dataScope/batchSet")
    @NoValidPrivilege
    public ResponseDTO<String> dataScopeBatchSet(@RequestBody @Valid DataScopeBatchSetRoleDTO batchSetRoleDTO) {
        return dataScopeService.dataScopeBatchSet(batchSetRoleDTO);
    }


}
