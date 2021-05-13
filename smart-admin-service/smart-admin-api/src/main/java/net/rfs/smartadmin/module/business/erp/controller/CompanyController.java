package net.rfs.smartadmin.module.business.erp.controller;

import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.controller.BaseController;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.common.domain.ValidateList;
import net.rfs.smartadmin.constant.SwaggerTagConst;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.CompanyQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.CompanyExcelVO;
import net.rfs.smartadmin.module.business.erp.service.CompanyService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [  ]
 *
 * @author 卓大
 * @version 1.0
 * @company 任富帅
 * @copyright (c)  任富帅Inc. All rights reserved.
 * @date 2021-05-12 05:37:59
 * @since JDK1.8
 */
@RestController
@Api(tags = {SwaggerTagConst.Admin.ERP})
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "分页查询",notes = "@author 卓大")
    @PostMapping("/company/page/query")
    public ResponseDTO<PageResultDTO<CompanyVO>> queryByPage(@RequestBody CompanyQueryDTO queryDTO) {
        return companyService.queryByPage(queryDTO);
    }
    @ApiOperation(value = "获取所有公司")
    @GetMapping("/company/getAll")
    public ResponseDTO<List<CompanyVO>> getAll() {
        return companyService.getAll();
    }
    @ApiOperation(value = "添加",notes = "@author 卓大")
    @PostMapping("/company/add")
    public ResponseDTO<String> add(@RequestBody @Validated CompanyAddDTO addTO){
        return companyService.add(addTO);
    }

    @ApiOperation(value="修改",notes = "@author 卓大")
    @PostMapping("/company/update")
    public ResponseDTO<String> update(@RequestBody @Validated CompanyUpdateDTO updateDTO){
        return companyService.update(updateDTO);
    }

    @ApiOperation(value="批量删除",notes = "@author 卓大")
    @PostMapping("/company/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return companyService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author 卓大")
    @PostMapping("/company/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<CompanyExcelVO> companyList = companyService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, CompanyExcelVO.class, companyList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author 卓大")
    @PostMapping("/company/export/all")
    public void exportAll(@RequestBody @Validated CompanyQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<CompanyExcelVO> companyList = companyService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, CompanyExcelVO.class, companyList);
        downloadExcel("", workbook, response);
    }

}
