package net.rfs.smartadmin.module.support.file.domain.dto;

import net.rfs.smartadmin.common.anno.ApiModelPropertyEnum;
import net.rfs.smartadmin.common.domain.PageParamDTO;
import net.rfs.smartadmin.common.validator.en.CheckEnum;
import net.rfs.smartadmin.module.support.file.constant.FileModuleTypeEnum;
import net.rfs.smartadmin.module.support.file.constant.FileServiceTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 文件信息查询dto
 * @Author: sbq
 * @CreateDate: 2019/7/3 17:38
 * @Version: 1.0
 */
@Data
public class FileQueryDTO extends PageParamDTO {

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "业务类型")
    @ApiModelPropertyEnum(FileModuleTypeEnum.class)
    @CheckEnum(enumClazz = FileModuleTypeEnum.class, message = "文件业务类型错误")
    private Integer moduleType;

    @ApiModelProperty(value = "文件位置")
    @ApiModelPropertyEnum(FileServiceTypeEnum.class)
    @CheckEnum(enumClazz = FileServiceTypeEnum.class, message = "文件位置类型错误")
    private Integer fileLocationType;

}
