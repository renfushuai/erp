<template>
  <div>
    <Card class="smart-query-card">
      <!------ 查询条件第一行 begin------->
      <Row class="smart-query-form-row">
        <span>
          结算单号 :
          <Input
            placeholder="结算单号"
            style="width: 180px"
            v-model="queryForm.orderCode"
          />
        </span>
        <span>
          规格 :
          <Input
            placeholder="请输入规格"
            style="width: 180px"
            v-model="queryForm.specifications"
          />
        </span>
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

        <Button
          @click="showMoreQueryConditionFlag = !showMoreQueryConditionFlag"
          icon="md-more"
          style="margin-left: 20px"
          type="primary"
          v-privilege="'sales-order-info-list-query'"
          >{{ showMoreQueryConditionFlag ? "隐藏" : "展开" }}</Button
        >
      </Row>
      <!------ 查询条件第一行 begin------->

      <!------ 查询条件第二行 begin------->
      <Row class="smart-query-form-row" v-show="showMoreQueryConditionFlag">
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
        <span>
          更新时间:
          <DatePicker
            placeholder="选择更新日期范围"
            split-panels
            style="width: 200px"
            type="daterange"
            v-model="queryForm.updateTimeRange"
          ></DatePicker>
        </span>
      </Row>
      <!------ 查询条件第二行 end------->
    </Card>

    <Card class="warp-card">
      <!-------操作按钮行 begin------->
      <Row class="marginBottom10">
        <Button
          @click="showUpload"
          icon="md-add"
          size="small"
          type="success"
          v-privilege="'sales-order-info-list-add'"
          >上传</Button
        >
        <Button
          @click="showAddSalesOrderInfoForm"
          class="marginLeft10"
          icon="md-add"
          size="small"
          type="primary"
          v-privilege="'sales-order-info-list-add'"
          >新建数据</Button
        >
        <Button
          @click="showBatchDeleteModal"
          class="marginLeft10"
          icon="ios-trash-outline"
          size="small"
          type="error"
          v-privilege="'sales-order-info-list-batch-delete'"
          >批量删除</Button
        >

        <Button
          :loading="allExportBtnLoading"
          @click="exportAll"
          class="marginLeft10 float-right"
          icon="ios-cloud-download-outline"
          size="small"
          type="warning"
          v-privilege="'sales-order-info-list-export-all'"
          >导出全部</Button
        >

        <Button
          :loading="batchExportBtnLoading"
          @click="batchExport"
          class="marginLeft10 float-right"
          icon="ios-download-outline"
          size="small"
          type="warning"
          v-privilege="'sales-order-info-list-batch-export'"
          >批量导出</Button
        >
      </Row>
      <!-------操作按钮行 end------->

      <!-------表格列表 begin------->
      <Table
        :columns="mainTable.columnArray"
        :data="mainTable.data"
        :loading="mainTable.loading"
        @on-sort-change="handleSortChange"
        border
        highlight-row
        ref="mainTable"
        height="600"
        show-summary
      ></Table>

      <Page
        :current="queryForm.pageNum"
        :page-size="queryForm.pageSize"
        :page-size-opts="mainTablePage.pageSizeOps"
        :total="mainTablePage.total"
        @on-change="changeMainTablePagePageNum"
        @on-page-size-change="changeMainTablePagePageSize"
        class="smart-query-table-page"
        show-elevator
        show-sizer
        show-total
      />
    </Card>
    <!-------表格列表 end------->

    <!-------批量删除Modal begin------->
    <Modal title="批量删除" v-model="batchDeleteModal.show" width="450">
      <Form :label-width="80">
        <FormItem>
          <h3 class="error-color">确定要删除以下数据吗？</h3>
        </FormItem>
        <FormItem label="删除数据">
          <Card style="width: 350px; height: 250px; overflow-y: scroll">
            <ul>
              <li v-for="item in mainTableSelectArray">
                <a href="#">{{ item.id }}</a>
              </li>
            </ul>
          </Card>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button
          @click="batchDeleteModal.show = false"
          size="small"
          type="default"
          >取消</Button
        >
        <Button @click="batchDelete" size="small" type="primary"
          >确定删除</Button
        >
      </div>
    </Modal>
    <!-------批量删除Modal end------->

    <!-------添加、更新 Form表单 begin------->
    <Modal
      :footer-hide="true"
      :title="saveModal.isUpdate ? '更新' : '新建'"
      v-model="saveModal.show"
      @on-cancel="saveFormClose"
      width="500"
    >
      <SalesOrderInfoListForm
        :isUpdate="saveModal.isUpdate"
        :updateData="saveModal.updateData"
        @on-form-close="saveFormClose"
      />
    </Modal>
    <!-------添加、更新 Form表单 end------->

    <Modal v-model="uploadModalShow" title="上传">
      <Form :label-width="80">
        <FormItem label="选择公司">
          <Select v-model="companyId">
            <Option
              v-for="item in companyData"
              :value="item.id"
              :key="item.name"
              >{{ item.name }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="选择文件">
          <Upload
            :action="uploadUrl"
            :disabled="companyId == 0"
            :before-upload="handleUpload"
            :on-success="handleSuccess"
            :headers="{ 'x-access-token': tokenData }"
            :data="{ companyId: companyId }"
          >
            <Button
              type="success"
              @click="handleUpload"
              size="small"
              icon="ios-cloud-upload-outline"
              >上传数据</Button
            >
          </Upload>
        </FormItem>
      </Form>

      <div slot="footer">
        <Button @click="queryList">关闭</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
import { dateTimeRangeConvert } from "@/lib/util";
import { PAGE_SIZE_OPTIONS } from "@/constants/table-page";
import { salesOrderInfoApi } from "@/api/sales-order-info";
import SalesOrderInfoListForm from "./components/sales-order-info-list-form";
import config from "@/config";
import cookie from "@/lib/cookie";
import { companyApi } from "@/api/company";
const PAGE_SIZE_INIT = 20;
export default {
  name: "SalesOrderInfoList",
  components: {
    SalesOrderInfoListForm,
  },
  props: {},
  data() {
    return {
      uploadUrl: config.baseUrl.apiUrl + "/salesOrder/upload",
      tokenData: cookie.getToken(),
      uploadModalShow: false,
      companyId: 0,
      companyData: [],
      /* -------------------------添加、更新表单 ------------------------- */
      saveModal: {
        show: false,
        isUpdate: false,
        updateData: null,
      },
      /* -------------------------批量操作------------------------- */
      //批量刪除彈出框
      batchDeleteModal: {
        show: false,
      },
      //表格多选选中的元素数组
      mainTableSelectArray: [],
      /* -------------------------导出操作------------------------- */
      //批量导出loading按钮
      batchExportBtnLoading: false,
      //导出全部loading按钮
      allExportBtnLoading: false,
      /* -------------------------查询条件相关数据-------------------- */
      //搜索表单
      queryForm: {
        //id
        id: null,
        //规格
        specifications: null,
        //商品名称
        productName: null,
        //销售单号
        orderCode: null,
        createTimeRange: ["", ""],
        updateTimeRange: ["", ""],
        pageNum: 1,
        pageSize: PAGE_SIZE_INIT,
        orders: [],
      },
      //是否展示更多搜索条件
      showMoreQueryConditionFlag: false,
      /*  -------------------------表格相关数据------------------------- */
      //表格分页
      mainTablePage: {
        total: 0,
        pageSizeOps: PAGE_SIZE_OPTIONS,
      },
      //表格
      mainTable: {
        //加载中
        loading: false,
        //表格数据
        data: [],
        //表格列
        columnArray: [
          {
            type: "selection",
            width: 40,
            align: "center",
          },
          {
            title: "销售公司",
            key: "orderSourceName",
            width: 100,
          },
          {
            title: "结算单号",
            key: "orderCode",
            tableColumn: "erp_sales_order_info.order_code",
            sortable: "custom",
            width: 100,
            resizable: true,
          },
          {
            title: "商品名称",
            key: "productName",
            width: 200,
            resizable: true,
          },
          {
            title: "货号",
            key: "productNumber",
            width: 100,
          },
          {
            title: "规格",
            key: "specifications",
            width: 100,
          },
          {
            title: "数量",
            key: "salesQuantity",
            tableColumn: "erp_sales_order_info.sales_quantity",
            sortable: "custom",
            width: 70,
          },
          {
            title: "出货金额",
            key: "salesPrice",
            width: 90,
          },
          {
            title: "出货单价",
            key: "standardPrice",
            width: 90,
          },
          {
            title: "医院",
            key: "hospitalName",
            width: 200,
            resizable: true,
          },
          {
            title: "销售单类型",
            key: "orderTypeName",
            width: 100,
          },
          {
            title: "经办人",
            key: "manager",
            width: 100,
          },
          {
            title: "渠道名称",
            key: "channelName",
            tableColumn: "erp_sales_order.channel_name",
            width: 100,
          },
          {
            title: "地区编号",
            key: "areaCode",
            width: 100,
          },
          {
            title: "创建时间",
            key: "createTime",
            tableColumn: "erp_sales_order.create_time",
            sortable: "custom",
            width: 180,
            resizable: true,
          },
          {
            title: "备注",
            key: "memo",
            width: 200,
          },
          {
            title: "操作",
            key: "action",
            align: "right",
            width: 130,
            className: "action-hide",
            render: (h, params) => {
              let actions = [
                {
                  title: "编辑",
                  directives: [
                    {
                      name: "privilege",
                      value: "sales-order-info-list-update",
                    },
                  ],
                  action: () => {
                    this.showEditSalesOrderInfoForm(params.row);
                  },
                },
              ];
              return this.$tableAction(h, actions);
            },
          },
        ],
      },
    };
  },
  computed: {},
  watch: {},
  filters: {},
  created() {},
  mounted() {
    this.queryList();
  },
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
  methods: {
    /* -------------------------查询相关 begin------------------------- */
    convertQueryParam() {
      let createTimeArray = dateTimeRangeConvert(
        this.queryForm.createTimeRange
      );
      let updateTimeArray = dateTimeRangeConvert(
        this.queryForm.updateTimeRange
      );
      return {
        ...this.queryForm,
        createTimeBegin: createTimeArray[0],
        createTimeEnd: createTimeArray[1],
        updateTimeBegin: updateTimeArray[0],
        updateTimeEnd: updateTimeArray[1],
      };
    },
    //查询
    async queryList() {
      this.uploadModalShow = false;
      this.mainTable.loading = true;
      try {
        let params = this.convertQueryParam();
        let result = await salesOrderInfoApi.querySalesOrderInfo(params);
        this.mainTable.data = result.data.list;
        this.mainTablePage.total = result.data.total;
      } finally {
        this.mainTable.loading = false;
      }
    },
    //重置查询
    resetQueryList() {
      let pageSize = this.queryForm.pageSize;
      this.queryForm = {
        id: null,
        specifications: null,
        productName: null,
        orderCode: null,
        createTimeRange: ["", ""],
        updateTimeRange: ["", ""],
        pageNum: 1,
        pageSize: pageSize,
        orders: [],
      };
      this.queryList();
    },
    //修改页码
    changeMainTablePagePageNum(pageNum) {
      this.queryForm.pageNum = pageNum;
      this.queryList();
    },
    //修改页大小
    changeMainTablePagePageSize(pageSize) {
      this.queryForm.pageNum = 1;
      this.queryForm.pageSize = pageSize;
      this.queryList();
    },
    //处理排序
    handleSortChange(column) {
      if (column.order === "normal") {
        this.queryForm.orders = [];
      } else {
        this.queryForm.orders = [
          {
            column: column.column.tableColumn,
            asc: "asc" === column.order,
          },
        ];
      }
      this.queryList();
    },
    /*-------------------------查询相关 end------------------------- */

    /*-------------------------批量操作 begin------------------------- */
    //显示批量删除弹窗
    showBatchDeleteModal() {
      if (!this.validateMainTableSelection()) {
        return;
      }
      this.batchDeleteModal.show = true;
    },
    //批量删除
    async batchDelete() {
      this.$Spin.show();
      await salesOrderInfoApi.batchDeleteSalesOrderInfo(
        this.mainTableSelectArray.map((e) => e.id)
      );
      this.batchDeleteModal.show = false;
      this.$Message.success("刪除成功");
      this.$Spin.hide();
      this.queryList();
    },
    //清空选中
    clearMainTableSelection() {
      this.mainTableSelectArray = [];
    },
    //校验是否有选中
    validateMainTableSelection() {
      this.mainTableSelectArray = this.$refs.mainTable.getSelection();
      if (this.mainTableSelectArray.length < 1) {
        this.$Message.error("请选择至少一条数据");
        return false;
      }
      return true;
    },
    /*-------------------------批量操作 end------------------------- */

    /*-------------------------导入导出 begin------------------------- */
    //导出全部
    async exportAll() {
      try {
        this.allExportBtnLoading = true;
        let params = this.convertQueryParam();
        await salesOrderInfoApi.exportAll(params);
      } catch (e) {
        console.log(e);
      } finally {
        this.allExportBtnLoading = false;
      }
    },
    //批量导出
    async batchExport() {
      if (!this.validateMainTableSelection()) {
        return;
      }
      try {
        this.batchExportBtnLoading = true;
        await salesOrderInfoApi.batchExport(
          this.mainTableSelectArray.map((e) => e.id)
        );
      } catch (e) {
        console.log(e);
      } finally {
        this.batchExportBtnLoading = false;
      }
    },
    /*-------------------------导入导出 end------------------------- */

    /*-------------------------添加，修改 表单 begin------------------------- */
    //显示添加表单
    showAddSalesOrderInfoForm() {
      this.saveModal.isUpdate = false;
      this.saveModal.show = true;
    },
    showEditSalesOrderInfoForm(updateObject) {
      this.saveModal.isUpdate = true;
      this.saveModal.updateData = updateObject;
      this.saveModal.show = true;
    },
    saveFormClose() {
      this.saveModal.show = false;
      this.queryList();
    },
    /*-------------------------添加，修改 表单 end------------------------- */
    async showUpload() {
      if (this.companyData.length == 0) {
        let result = await companyApi.getAll();
        this.companyData = result.data;
      }
      this.uploadModalShow = true;
    },
    handleUpload(file) {
      if (this.companyId == 0) {
        this.$Message.warning("请选择公司");
        return false;
      }
      return true;
    },
    handleSuccess(res, file) {
        this.$Message.success("导入成功");
    },
  },
};
</script>
