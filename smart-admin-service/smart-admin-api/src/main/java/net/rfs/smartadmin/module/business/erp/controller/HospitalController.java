package net.rfs.smartadmin.module.business.erp.controller;

import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.controller.BaseController;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.common.domain.ValidateList;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.HospitalQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.vo.HospitalVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.HospitalExcelVO;
import net.rfs.smartadmin.module.business.erp.service.HospitalService;
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
 * [ 医院 ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-12 14:41:23
 * @since JDK1.8
 */
@RestController
@Api(tags = {"医院"})
public class HospitalController extends BaseController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "分页查询医院",notes = "@author 任富帅")
    @PostMapping("/hospital/page/query")
    public ResponseDTO<PageResultDTO<HospitalVO>> queryByPage(@RequestBody HospitalQueryDTO queryDTO) {
        return hospitalService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加医院",notes = "@author 任富帅")
    @PostMapping("/hospital/add")
    public ResponseDTO<String> add(@RequestBody @Validated HospitalAddDTO addTO){
        return hospitalService.add(addTO);
    }

    @ApiOperation(value="修改医院",notes = "@author 任富帅")
    @PostMapping("/hospital/update")
    public ResponseDTO<String> update(@RequestBody @Validated HospitalUpdateDTO updateDTO){
        return hospitalService.update(updateDTO);
    }

    @ApiOperation(value="批量删除医院",notes = "@author 任富帅")
    @PostMapping("/hospital/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return hospitalService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author 任富帅")
    @PostMapping("/hospital/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<HospitalExcelVO> hospitalList = hospitalService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("医院", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, HospitalExcelVO.class, hospitalList);
        downloadExcel("医院", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author 任富帅")
    @PostMapping("/hospital/export/all")
    public void exportAll(@RequestBody @Validated HospitalQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<HospitalExcelVO> hospitalList = hospitalService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("医院", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, HospitalExcelVO.class, hospitalList);
        downloadExcel("医院", workbook, response);
    }

}
