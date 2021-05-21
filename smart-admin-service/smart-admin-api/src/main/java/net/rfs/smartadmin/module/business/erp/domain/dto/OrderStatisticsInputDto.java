package net.rfs.smartadmin.module.business.erp.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderStatisticsInputDto {
    private List<String> productIdList;
}
