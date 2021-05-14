package net.rfs.smartadmin.module.support.codegenerator;

import net.rfs.smartadmin.BaseTest;
import net.rfs.smartadmin.module.support.codegenerator.constant.SqlOperateTypeEnum;
import net.rfs.smartadmin.module.support.codegenerator.domain.CodeGeneratorDTO;
import net.rfs.smartadmin.module.support.codegenerator.domain.CodeGeneratorQueryColumnDTO;
import net.rfs.smartadmin.module.support.codegenerator.service.CodeGeneratorService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * IdGeneratorService Tester.
 *
 * @author zhuoda
 * @version 1.0
 */
public class CodeGeneratorServiceTest extends BaseTest {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Test
    public void testGenerate() throws Exception {
        /**
         * !!! 所有查询条件均包含了createTime和updateTime的 范围查询 !!!
         */

        // t_peony 牡丹花表

        //搜索字段 kind， 使用like搜索
/*        CodeGeneratorQueryColumnDTO kind = CodeGeneratorQueryColumnDTO.builder()
                .columnName("kind")
                .sqlOperate(SqlOperateTypeEnum.LIKE).build();*/
        //搜索字段 kind， 使用 == 搜索
        //搜索字段 name， 使用like搜索
        CodeGeneratorQueryColumnDTO code = CodeGeneratorQueryColumnDTO.builder()
                .columnName("product_number")
                .sqlOperate(SqlOperateTypeEnum.LIKE).build();
        CodeGeneratorQueryColumnDTO areCode = CodeGeneratorQueryColumnDTO.builder()
                .columnName("product_name")
                .sqlOperate(SqlOperateTypeEnum.LIKE).build();
        //搜索字段 color， 使用like搜索
        CodeGeneratorQueryColumnDTO manager = CodeGeneratorQueryColumnDTO.builder()
                .columnName("specifications")
                .sqlOperate(SqlOperateTypeEnum.LIKE).build();
        List<CodeGeneratorQueryColumnDTO> queryColumnList = Lists.newArrayList(manager,areCode,code);

        CodeGeneratorDTO codeGenerator = CodeGeneratorDTO.builder()
                .author("任富帅")//class 注释作者
                .company("rfs")//class注释公司名字
                .tableName("erp_product")//表名
                .tablePrefix("erp_")//表名前缀
                .basePackage("net.rfs.smartadmin")//包名
                .modulePackage("business.erp")//业务子包名
                .queryColumnList(queryColumnList)//加入搜搜字段
                .build();//构建

        //当需要代码生成的时候，请将注释去掉
        codeGeneratorService.codeGenerator(codeGenerator);
    }

}
