<template>
  <div>
    <Form ref="form" :rules="formValidate" :label-width="90" :model="form">
          <FormItem label="货号" prop="productNumber">
            <Input v-model="form.productNumber" />
          </FormItem>
          <FormItem label="商品名称" prop="productName">
            <Input v-model="form.productName" />
          </FormItem>
          <FormItem label="规格" prop="specifications">
            <Input v-model="form.specifications" />
          </FormItem>
          <FormItem label="库存" prop="stock">
            <Input type="number" v-model.number="form.stock" />
          </FormItem>
          <FormItem label="单价" prop="standardPrice">
            <Input type="number" v-model.number="form.standardPrice" />
          </FormItem>
          <FormItem label="商品类型1=外科，2=内科 3=器械" prop="productType">
            <Input type="number" v-model.number="form.productType" />
          </FormItem>
          <FormItem label="状态" prop="status">
            <Input type="number" v-model.number="form.status" />
          </FormItem>
    </Form>
    <Row class="code-row-bg" justify="end" type="flex">
      <Button @click="cancel" style="margin-right:10px">取消</Button>
      <Button @click="save" type="primary">保存</Button>
    </Row>
  </div>
</template>
<script>
  import { productApi } from '@/api/product';
  export default {
    name: 'ProductListForm',
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
         //货号
         productNumber:null,
         //商品名称
         productName:null,
         //规格
         specifications:null,
         //库存
         stock:null,
         //单价
         standardPrice:null,
         //商品类型1=外科，2=内科 3=器械
         productType:null,
         //状态
         status:null,
        },
        //表单验证
        formValidate: {
        //货号
        productNumber:[{ required: true, message: '请输入货号', trigger: 'blur' }],
        //商品名称
        productName:[{ required: true, message: '请输入商品名称', trigger: 'blur' }],
        //规格
        specifications:[{ required: true, message: '请输入规格', trigger: 'blur' }],
        //库存
        stock:[{ type:'number',required: true, message: '请输入库存', trigger: 'blur' }],
        //单价
        standardPrice:[{ type:'number',required: true, message: '请输入单价', trigger: 'blur' }],
        //商品类型1=外科，2=内科 3=器械
        productType:[{ type:'number',required: true, message: '请输入商品类型1=外科，2=内科 3=器械', trigger: 'blur' }],
        //状态
        status:[{ type:'number',required: true, message: '请输入状态', trigger: 'blur' }],
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
          //货号
          productNumber:null,
          //商品名称
          productName:null,
          //规格
          specifications:null,
          //库存
          stock:null,
          //单价
          standardPrice:null,
          //商品类型1=外科，2=内科 3=器械
          productType:null,
          //状态
          status:null,
        };
        this.$refs['form'].resetFields();
      },
      async add() {
        this.$Spin.show();
        let res = await productApi.addProduct(this.form);
        this.$Message.success(res.msg);
        this.$Spin.hide();
        this.resetForm();
        this.$emit('on-form-close');
      },
      async update() {
        this.$Spin.show();
        let res = await productApi.updateProduct(this.form);
        this.$Message.success(res.msg);
        this.$Spin.hide();
        this.resetForm();
        this.$emit('on-form-close');
      }
    }
  };
</script>