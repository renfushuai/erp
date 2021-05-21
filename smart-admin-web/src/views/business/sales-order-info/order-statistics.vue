<template>
  <div>
    <Card class="smart-query-card">
      <!------ 查询条件第一行 begin------->
      <Row class="smart-query-form-row">
        <span>
          商品名称 :
          <Input
            placeholder="请输入商品名称"
            style="width: 180px"
            v-model="queryForm.productName"
          />
        </span>
        <span>
          创建时间:
          <DatePicker
            placeholder="选择创建日期范围"
            split-panels
            style="width: 200px"
            type="daterange"
            v-model="queryForm.createTimeRange"
          ></DatePicker>
        </span>
        <ButtonGroup>
          <Button
            @click="queryList"
            icon="ios-search"
            type="primary"
            v-privilege="'sales-order-info-list-query'"
            >查询</Button
          >
          <Button
            @click="resetQueryList"
            icon="md-refresh"
            type="default"
            v-privilege="'sales-order-info-list-query'"
            >重置</Button
          >
        </ButtonGroup>
      </Row>
    </Card>
     
    <Checkbox-group
      v-model="tableColumnsChecked"
      @on-change="changeTableColumns"
    >
     <Checkbox v-for="item in productData" :label="item.id">{{item.productName}}</Checkbox>
    </Checkbox-group>
    <Table :data="tableData2" :columns="tableColumns2" border> </Table>
  </div>
</template>

<script>
import { dateTimeRangeConvert } from "@/lib/util";
import { salesOrderInfoApi } from "@/api/sales-order-info";
import { productApi } from "@/api/product";
export default {
  name: "orderStatistics",
  components: {},
  props: {},
  data() {
    return {
      //搜索表单
      queryForm: {
        //商品名称
        productName: null,
        //销售单号
        orderCode: null,
        createTimeRange: ["", ""],
      },
      productData:[],
      tableData2: [],
      tableColumns2: [],
      tableColumnsChecked: ["68", "71", "57"],
    };
  },
  methods: {
    getTable2Columns() {
      const table2ColumnList = {
        areCode: {
          title: "地区",
          key: "area_code",
          width: 80,
        },
        name: {
          title: "医院",
          key: "name",
          fixed: "left",
          width: 400,
        },
        68: {
          title: "Weak",
          key: "weak",
          width: 150,
          sortable: true,
        },
        71: {
          title: "Weak",
          key: "weak",
          width: 150,
          sortable: true,
        },
        57: {
          title: "Signin",
          key: "signin",
          width: 150,
          sortable: true,
        },
      };
      let data = [table2ColumnList.areCode,table2ColumnList.name];
      this.tableColumnsChecked.forEach((col) =>
        data.push(table2ColumnList[col])
      );

      return data;
    },
    changeTableColumns() {
      this.tableColumns2 = this.getTable2Columns();
    },
     async queryProductList() {
      let result = await productApi.groupProduct();
      this.productData = result.data;
      console.log(this.productData)
    },
    //查询
    async queryList() {
      let params = this.convertQueryParam();
      let result = await salesOrderInfoApi.orderStatistics(params);
      this.tableData2 = result.data;
      console.log(result.data);
    },
    //重置查询
    resetQueryList() {
      this.queryForm = {
        productName: null,
        createTimeRange: ["", ""],
      };
      this.queryList();
    },
    convertQueryParam() {
      let createTimeArray = dateTimeRangeConvert(
        this.queryForm.createTimeRange
      );
      return {
        ...this.queryForm,
        createTimeBegin: createTimeArray[0],
        createTimeEnd: createTimeArray[1],
      };
    },
  },
  mounted() {
    this.changeTableColumns();
    this.queryList();
    this.queryProductList();
  },
};
</script>
