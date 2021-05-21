package net.rfs.smartadmin.module.business.erp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rfs.smartadmin.common.controller.BaseController;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.common.domain.ValidateList;
import net.rfs.smartadmin.module.business.erp.domain.dto.OrderStatisticsInputDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderInfoUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderInfoExcelVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderInfoVO;
import net.rfs.smartadmin.module.business.erp.service.SalesOrderInfoService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-12 15:00:50
 * @since JDK1.8
 */
@RestController
@Api(tags = {""})
public class SalesOrderInfoController extends BaseController {

    @Autowired
    private SalesOrderInfoService salesOrderInfoService;

    @ApiOperation(value = "分页查询", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/page/query")
    public ResponseDTO<PageResultDTO<SalesOrderInfoVO>> queryByPage(@RequestBody SalesOrderInfoQueryDTO queryDTO) {
        return salesOrderInfoService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/add")
    public ResponseDTO<String> add(@RequestBody @Validated SalesOrderInfoAddDTO addTO) {
        return salesOrderInfoService.add(addTO);
    }

    @ApiOperation(value = "修改", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/update")
    public ResponseDTO<String> update(@RequestBody @Validated SalesOrderInfoUpdateDTO updateDTO) {
        return salesOrderInfoService.update(updateDTO);
    }

    @ApiOperation(value = "批量删除", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return salesOrderInfoService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<SalesOrderInfoExcelVO> salesOrderInfoList = salesOrderInfoService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SalesOrderInfoExcelVO.class, salesOrderInfoList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/export/all")
    public void exportAll(@RequestBody @Validated SalesOrderInfoQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<SalesOrderInfoExcelVO> salesOrderInfoList = salesOrderInfoService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SalesOrderInfoExcelVO.class, salesOrderInfoList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "数据统计", notes = "@author 任富帅")
    @PostMapping("/salesOrderInfo/orderStatistics")
    public ResponseDTO<List<Object>> orderStatistics(@RequestBody OrderStatisticsInputDto dto) {
        return ResponseDTO.succData(salesOrderInfoService.orderStatistics(dto));
    }
}
