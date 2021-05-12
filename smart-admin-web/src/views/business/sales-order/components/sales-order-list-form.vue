<template>
  <div>
    <Form ref="form" :rules="formValidate" :label-width="90" :model="form">
          <FormItem label="订单号" prop="code">
            <Input v-model="form.code" />
          </FormItem>
          <FormItem label="实付金额" prop="actuallyAmount">
            <Input type="number" v-model.number="form.actuallyAmount" />
          </FormItem>
          <FormItem label="订单金额" prop="orderAmount">
            <Input type="number" v-model.number="form.orderAmount" />
          </FormItem>
          <FormItem label="医院" prop="hospitalName">
            <Input v-model="form.hospitalName" />
          </FormItem>
          <FormItem label="医院id" prop="hospitalId">
            <Input type="number" v-model.number="form.hospitalId" />
          </FormItem>
          <FormItem label="1=外科2=内科3=器械" prop="orderType">
            <Input type="number" v-model.number="form.orderType" />
          </FormItem>
          <FormItem label="销售单类型" prop="orderTypeName">
            <Input v-model="form.orderTypeName" />
          </FormItem>
          <FormItem label="经办人" prop="manager">
            <Input v-model="form.manager" />
          </FormItem>
          <FormItem label="渠道1=医院 2=代理商" prop="channel">
            <Input type="number" v-model.number="form.channel" />
          </FormItem>
          <FormItem label="渠道名称" prop="channelName">
            <Input v-model="form.channelName" />
          </FormItem>
          <FormItem label="地区编号" prop="areaCode">
            <Input v-model="form.areaCode" />
          </FormItem>
          <FormItem label="地区名称" prop="areaName">
            <Input v-model="form.areaName" />
          </FormItem>
          <FormItem label="备注" prop="memo">
            <Input v-model="form.memo" />
          </FormItem>
          <FormItem label="版本" prop="version">
            <Input type="number" v-model.number="form.version" />
          </FormItem>
    </Form>
    <Row class="code-row-bg" justify="end" type="flex">
      <Button @click="cancel" style="margin-right:10px">取消</Button>
      <Button @click="save" type="primary">保存</Button>
    </Row>
  </div>
</template>
<script>
  import { salesOrderApi } from '@/api/sales-order';
  export default {
    name: 'SalesOrderListForm',
    components: {
    },
    props: {
      //是否为更新表单
      isUpdate: {
        type: Boolean,
        default: true
      },
      //更新的表单数据对象
      updateData: {
        type: Object
      }
    },
    data() {
      return {
        //表单数据
        form: {
         //订单号
         code:null,
         //实付金额
         actuallyAmount:null,
         //订单金额
         orderAmount:null,
         //医院
         hospitalName:null,
         //医院id
         hospitalId:null,
         //1=外科2=内科3=器械
         orderType:null,
         //销售单类型
         orderTypeName:null,
         //经办人
         manager:null,
         //渠道1=医院 2=代理商
         channel:null,
         //渠道名称
         channelName:null,
         //地区编号
         areaCode:null,
         //地区名称
         areaName:null,
         //备注
         memo:null,
         //版本
         version:null,
        },
        //表单验证
        formValidate: {
        //订单号
        code:[{ required: true, message: '请输入订单号', trigger: 'blur' }],
        //实付金额
        actuallyAmount:[{ type:'number',required: true, message: '请输入实付金额', trigger: 'blur' }],
        //订单金额
        orderAmount:[{ type:'number',required: true, message: '请输入订单金额', trigger: 'blur' }],
        //医院
        hospitalName:[{ required: true, message: '请输入医院', trigger: 'blur' }],
        //医院id
        hospitalId:[{ type:'number',required: true, message: '请输入医院id', trigger: 'blur' }],
        //1=外科2=内科3=器械
        orderType:[{ type:'number',required: true, message: '请输入1=外科2=内科3=器械', trigger: 'blur' }],
        //销售单类型
        orderTypeName:[{ required: true, message: '请输入销售单类型', trigger: 'blur' }],
        //经办人
        manager:[{ required: true, message: '请输入经办人', trigger: 'blur' }],
        //渠道1=医院 2=代理商
        channel:[{ type:'number',required: true, message: '请输入渠道1=医院 2=代理商', trigger: 'blur' }],
        //渠道名称
        channelName:[{ required: true, message: '请输入渠道名称', trigger: 'blur' }],
        //地区编号
        areaCode:[{ required: true, message: '请输入地区编号', trigger: 'blur' }],
        //地区名称
        areaName:[{ required: true, message: '请输入地区名称', trigger: 'blur' }],
        //备注
        memo:[{ required: true, message: '请输入备注', trigger: 'blur' }],
        //版本
        version:[{ type:'number',required: true, message: '请输入版本', trigger: 'blur' }],
        }
      };
    },
  watch: {
      updateData: function(newValue, oldValue) {
          this.$refs['form'].resetFields();
          if (this.isUpdate) {
              for (let k in this.form) {
                  this.$set(this.form, k, newValue[k]);
              }
              this.$set(this.form, 'id', newValue['id']);
          }
      },
      isUpdate: function(newValue, oldValue) {
          if (!newValue) {
              this.resetForm();
              this.$refs['form'].resetFields();
          }
      }
  },
    created() {},
    mounted() {},
    methods: {
      cancel() {
        this.$emit('on-form-close');
      },
      save() {
       this.$refs['form'].validate(valid => {
         if (valid) {
           if (this.isUpdate) {
            this.update();
           } else {
             this.add();
           }
         } else {
          this.$Message.error('参数验证错误，请仔细填写表单数据!');
         }
       });
      },
    resetForm() {
        this.form = {
          //id
          id:null,
          //订单号
          code:null,
          //实付金额
          actuallyAmount:null,
          //订单金额
          orderAmount:null,
          //医院
          hospitalName:null,
          //医院id
          hospitalId:null,
          //1=外科2=内科3=器械
          orderType:null,
          //销售单类型
          orderTypeName:null,
          //经办人
          manager:null,
          //渠道1=医院 2=代理商
          channel:null,
          //渠道名称
          channelName:null,
          //地区编号
          areaCode:null,
          //地区名称
          areaName:null,
          //备注
          memo:null,
          //版本
          version:null,
        };
        this.$refs['form'].resetFields();
      },
      async add() {
        this.$Spin.show();
        let res = await salesOrderApi.addSalesOrder(this.form);
        this.$Message.success(res.msg);
        this.$Spin.hide();
        this.resetForm();
        this.$emit('on-form-close');
      },
      async update() {
        this.$Spin.show();
        let res = await salesOrderApi.updateSalesOrder(this.form);
        this.$Message.success(res.msg);
        this.$Spin.hide();
        this.resetForm();
        this.$emit('on-form-close');
      }
    }
  };
</script>