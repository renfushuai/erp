package net.rfs.smartadmin.module.business.erp.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rfs.smartadmin.common.controller.BaseController;
import net.rfs.smartadmin.common.domain.PageResultDTO;
import net.rfs.smartadmin.common.domain.ResponseDTO;
import net.rfs.smartadmin.common.domain.ValidateList;
import net.rfs.smartadmin.module.business.erp.domain.dto.GroupProductOutputDto;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductAddDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductQueryDTO;
import net.rfs.smartadmin.module.business.erp.domain.dto.ProductUpdateDTO;
import net.rfs.smartadmin.module.business.erp.domain.vo.ProductExcelVO;
import net.rfs.smartadmin.module.business.erp.domain.vo.ProductVO;
import net.rfs.smartadmin.module.business.erp.service.ProductService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @date 2021-05-14 15:53:42
 * @since JDK1.8
 */
@RestController
@Api(tags = {""})
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "聚合产品ID", notes = "@author 任富帅")
    @GetMapping("/product/group")
    public ResponseDTO<List<GroupProductOutputDto>> groupProduct() {
        return productService.groupProduct();
    }

    @ApiOperation(value = "分页查询", notes = "@author 任富帅")
    @PostMapping("/product/page/query")
    public ResponseDTO<PageResultDTO<ProductVO>> queryByPage(@RequestBody ProductQueryDTO queryDTO) {
        return productService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加", notes = "@author 任富帅")
    @PostMapping("/product/add")
    public ResponseDTO<String> add(@RequestBody @Validated ProductAddDTO addTO) {
        return productService.add(addTO);
    }

    @ApiOperation(value = "修改", notes = "@author 任富帅")
    @PostMapping("/product/update")
    public ResponseDTO<String> update(@RequestBody @Validated ProductUpdateDTO updateDTO) {
        return productService.update(updateDTO);
    }

    @ApiOperation(value = "批量删除", notes = "@author 任富帅")
    @PostMapping("/product/deleteByIds")
    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
        return productService.deleteByIds(idList);
    }

    @ApiOperation(value = "批量导出", notes = "@author 任富帅")
    @PostMapping("/product/export/batch")
    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
        //查询数据
        List<ProductExcelVO> productList = productService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ProductExcelVO.class, productList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author 任富帅")
    @PostMapping("/product/export/all")
    public void exportAll(@RequestBody @Validated ProductQueryDTO queryDTO, HttpServletResponse response) {
        //查询数据
        List<ProductExcelVO> productList = productService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ProductExcelVO.class, productList);
        downloadExcel("", workbook, response);
    }

}
