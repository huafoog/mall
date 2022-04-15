<template>
  <el-dialog
    :title="!dataForm.userId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="钱包总收入额" prop="walletIncome">
      <el-input v-model="dataForm.walletIncome" placeholder="钱包总收入额"></el-input>
    </el-form-item>
    <el-form-item label="钱包总支出额" prop="walletOutcome">
      <el-input v-model="dataForm.walletOutcome" placeholder="钱包总支出额"></el-input>
    </el-form-item>
    <el-form-item label="钱包总可用余额" prop="balanceFee">
      <el-input v-model="dataForm.balanceFee" placeholder="钱包总可用余额"></el-input>
    </el-form-item>
    <el-form-item label="用于安全检查，检查不通过为异常。" prop="checkSign">
      <el-input v-model="dataForm.checkSign" placeholder="用于安全检查，检查不通过为异常。"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateAt">
      <el-input v-model="dataForm.updateAt" placeholder="更新时间"></el-input>
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
          userId: 0,
          walletIncome: '',
          walletOutcome: '',
          balanceFee: '',
          checkSign: '',
          updateAt: ''
        },
        dataRule: {
          walletIncome: [
            { required: true, message: '钱包总收入额不能为空', trigger: 'blur' }
          ],
          walletOutcome: [
            { required: true, message: '钱包总支出额不能为空', trigger: 'blur' }
          ],
          balanceFee: [
            { required: true, message: '钱包总可用余额不能为空', trigger: 'blur' }
          ],
          checkSign: [
            { required: true, message: '用于安全检查，检查不通过为异常。不能为空', trigger: 'blur' }
          ],
          updateAt: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/pay/wallet/info/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.walletIncome = data.wallet.walletIncome
                this.dataForm.walletOutcome = data.wallet.walletOutcome
                this.dataForm.balanceFee = data.wallet.balanceFee
                this.dataForm.checkSign = data.wallet.checkSign
                this.dataForm.updateAt = data.wallet.updateAt
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
              url: this.$http.adornUrl(`/pay/wallet/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'walletIncome': this.dataForm.walletIncome,
                'walletOutcome': this.dataForm.walletOutcome,
                'balanceFee': this.dataForm.balanceFee,
                'checkSign': this.dataForm.checkSign,
                'updateAt': this.dataForm.updateAt
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
