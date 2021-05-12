<template>
  <div>
    <Form ref="form" :rules="formValidate" :label-width="90" :model="form">
          <FormItem label="销售单号" prop="orderCode">
            <Input v-model="form.orderCode" />
          </FormItem>
          <FormItem label="商品Id" prop="productId">
            <Input type="number" v-model.number="form.productId" />
          </FormItem>
          <FormItem label="商品名称" prop="productName">
            <Input v-model="form.productName" />
          </FormItem>
          <FormItem label="货号" prop="productNumber">
            <Input v-model="form.productNumber" />
          </FormItem>
          <FormItem label="规格" prop="specifications">
            <Input v-model="form.specifications" />
          </FormItem>
          <FormItem label="数量" prop="salesQuantity">
            <Input type="number" v-model.number="form.salesQuantity" />
          </FormItem>
          <FormItem label="出货金额" prop="salesPrice">
            <Input type="number" v-model.number="form.salesPrice" />
          </FormItem>
          <FormItem label="出货单价" prop="standardPrice">
            <Input type="number" v-model.number="form.standardPrice" />
          </FormItem>
    </Form>
    <Row class="code-row-bg" justify="end" type="flex">
      <Button @click="cancel" style="margin-right:10px">取消</Button>
      <Button @click="save" type="primary">保存</Button>
    </Row>
  </div>
</template>
<script>
  import { salesOrderInfoApi } from '@/api/sales-order-info';
  export default {
    name: 'SalesOrderInfoListForm',
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
         //销售单号
         orderCode:null,
         //商品Id
         productId:null,
         //商品名称
         productName:null,
         //货号
         productNumber:null,
         //规格
         specifications:null,
         //数量
         salesQuantity:null,
         //出货金额
         salesPrice:null,
         //出货单价
         standardPrice:null,
        },
        //表单验证
        formValidate: {
        //销售单号
        orderCode:[{ required: true, message: '请输入销售单号', trigger: 'blur' }],
        //商品Id
        productId:[{ type:'number',required: true, message: '请输入商品Id', trigger: 'blur' }],
        //商品名称
        productName:[{ required: true, message: '请输入商品名称', trigger: 'blur' }],
        //货号
        productNumber:[{ required: true, message: '请输入货号', trigger: 'blur' }],
        //规格
        specifications:[{ required: true, message: '请输入规格', trigger: 'blur' }],
        //数量
        salesQuantity:[{ type:'number',required: true, message: '请输入数量', trigger: 'blur' }],
        //出货金额
        salesPrice:[{ type:'number',required: true, message: '请输入出货金额', trigger: 'blur' }],
        //出货单价
        standardPrice:[{ type:'number',required: true, message: '请输入出货单价', trigger: 'blur' }],
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
          //销售单号
          orderCode:null,
          //商品Id
          productId:null,
          //商品名称
          productName:null,
          //货号
          productNumber:null,
          //规格
          specifications:null,
          //数量
          salesQuantity:null,
          //出货金额
          salesPrice:null,
          //出货单价
          standardPrice:null,
        };
        this.$refs['form'].resetFields();
      },
      async add() {
        this.$Spin.show();
        let res = await salesOrderInfoApi.addSalesOrderInfo(this.form);
        this.$Message.success(res.msg);
        this.$Spin.hide();
        this.resetForm();
        this.$emit('on-form-close');
      },
      async update() {
        this.$Spin.show();
        let res = await salesOrderInfoApi.updateSalesOrderInfo(this.form);
        this.$Message.success(res.msg);
        this.$Spin.hide();
        this.resetForm();
        this.$emit('on-form-close');
      }
    }
  };
</script>