<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="订单号" prop="orderNum">
      <el-input v-model="dataForm.orderNum" placeholder="订单号"></el-input>
    </el-form-item>
    <el-form-item label="订单总金额" prop="amounts">
      <el-input v-model="dataForm.amounts" placeholder="订单总金额"></el-input>
    </el-form-item>
    <el-form-item label="货币类型" prop="currency">
      <el-input v-model="dataForm.currency" placeholder="货币类型"></el-input>
    </el-form-item>
    <el-form-item label="实际支付金额" prop="payFee">
      <el-input v-model="dataForm.payFee" placeholder="实际支付金额"></el-input>
    </el-form-item>
    <el-form-item label="汇率" prop="exchange">
      <el-input v-model="dataForm.exchange" placeholder="汇率"></el-input>
    </el-form-item>
    <el-form-item label="支付状态：0待支付，1已支付，100已取消" prop="status">
      <el-input v-model="dataForm.status" placeholder="支付状态：0待支付，1已支付，100已取消"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateAt">
      <el-input v-model="dataForm.updateAt" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createAt">
      <el-input v-model="dataForm.createAt" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="0未删除1已删除" prop="deleted">
      <el-input v-model="dataForm.deleted" placeholder="0未删除1已删除"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          orderNum: '',
          amounts: '',
          currency: '',
          payFee: '',
          exchange: '',
          status: '',
          updateAt: '',
          createAt: '',
          deleted: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          orderNum: [
            { required: true, message: '订单号不能为空', trigger: 'blur' }
          ],
          amounts: [
            { required: true, message: '订单总金额不能为空', trigger: 'blur' }
          ],
          currency: [
            { required: true, message: '货币类型不能为空', trigger: 'blur' }
          ],
          payFee: [
            { required: true, message: '实际支付金额不能为空', trigger: 'blur' }
          ],
          exchange: [
            { required: true, message: '汇率不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '支付状态：0待支付，1已支付，100已取消不能为空', trigger: 'blur' }
          ],
          updateAt: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          createAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          deleted: [
            { required: true, message: '0未删除1已删除不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/pay/usertopuporder/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.userTopUpOrder.userId
                this.dataForm.orderNum = data.userTopUpOrder.orderNum
                this.dataForm.amounts = data.userTopUpOrder.amounts
                this.dataForm.currency = data.userTopUpOrder.currency
                this.dataForm.payFee = data.userTopUpOrder.payFee
                this.dataForm.exchange = data.userTopUpOrder.exchange
                this.dataForm.status = data.userTopUpOrder.status
                this.dataForm.updateAt = data.userTopUpOrder.updateAt
                this.dataForm.createAt = data.userTopUpOrder.createAt
                this.dataForm.deleted = data.userTopUpOrder.deleted
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/pay/usertopuporder/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'orderNum': this.dataForm.orderNum,
                'amounts': this.dataForm.amounts,
                'currency': this.dataForm.currency,
                'payFee': this.dataForm.payFee,
                'exchange': this.dataForm.exchange,
                'status': this.dataForm.status,
                'updateAt': this.dataForm.updateAt,
                'createAt': this.dataForm.createAt,
                'deleted': this.dataForm.deleted
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
