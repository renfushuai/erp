package net.rfs.smartadmin.module.business.erp.controller;


import net.rfs.smartadmin.common.constant.ResponseCodeConst;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.controller.BaseController;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.common.domain.ValidateList;
import net.rfs.smartadmin.module.business.erp.domain.dto.ImportSalesOrderExcelDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.SalesOrderQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.SalesOrderExcelVO;
import net.rfs.smartadmin.module.business.erp.service.SalesOrderService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import net.rfs.smartadmin.util.SmartEasyPoiExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * [  ]
 *
 * @author 任富帅
 * @version 1.0
 * @company rfs
 * @copyright (c)  rfsInc. All rights reserved.
 * @date 2021-05-12 14:59:01
 * @since JDK1.8
 */
@RestController
@Api(tags = {""})
public class SalesOrderController extends BaseController {

    @Autowired
    private SalesOrderService salesOrderService;
    @ApiOperation(value = "分页查询",notes = "@author 任富帅")
    @PostMapping("/salesOrder/page/query")
    public ResponseDTO<PageResultDTO<SalesOrderVO>> queryByPage(@RequestBody SalesOrderQueryDTO queryDTO) {
        return salesOrderService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加",notes = "@author 任富帅")
    @PostMapping("/salesOrder/add")
    public ResponseDTO<String> add(@RequestBody @Validated SalesOrderAddDTO addTO){
        return salesOrderService.add(addTO);
    }

    @ApiOperation(value="修改",notes = "@author 任富帅")
    @PostMapping("/salesOrder/update")
    public ResponseDTO<String> update(@RequestBody @Validated SalesOrderUpdateDTO updateDTO){
        return salesOrderService.update(updateDTO);
    }

    @ApiOperation(value="批量删除",notes = "@author 任富帅")
    @PostMapping("/salesOrder/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return salesOrderService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author 任富帅")
    @PostMapping("/salesOrder/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<SalesOrderExcelVO> salesOrderList = salesOrderService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SalesOrderExcelVO.class, salesOrderList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author 任富帅")
    @PostMapping("/salesOrder/export/all")
    public void exportAll(@RequestBody @Validated SalesOrderQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<SalesOrderExcelVO> salesOrderList = salesOrderService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, SalesOrderExcelVO.class, salesOrderList);
        downloadExcel("", workbook, response);
    }
    @ApiOperation("上传销售单")
    @PostMapping(value = "/salesOrder/upload")
    public ResponseDTO<String> upload(@RequestBody MultipartFile file,HttpServletRequest request)throws IOException {
        String companyId = request.getParameter("companyId");
        if (StringUtils.isBlank(companyId)){
            return  ResponseDTO.wrap(ResponseCodeConst.ERROR_PARAM,"companyId不能为空");
        }
        Workbook hssfWorkbook = SmartEasyPoiExcelUtil.getWorkBook(file);
        for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
            List<ImportSalesOrderExcelDto> list = SmartEasyPoiExcelUtil.importExcel(file,i,0, 1, ImportSalesOrderExcelDto.class);
            Integer orderType=i+1;
            salesOrderService.uploadSalesOrder(list,orderType,Integer.valueOf(companyId),hssfWorkbook.getSheetAt(i).getSheetName());
        }
        return ResponseDTO.succData("ok");
    }
   }
