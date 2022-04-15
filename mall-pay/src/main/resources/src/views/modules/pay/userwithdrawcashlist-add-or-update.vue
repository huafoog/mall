<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="申请用户uuid" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="申请用户uuid"></el-input>
    </el-form-item>
    <el-form-item label="提现（渠道）方式 1银行转账" prop="withdrawWay">
      <el-input v-model="dataForm.withdrawWay" placeholder="提现（渠道）方式 1银行转账"></el-input>
    </el-form-item>
    <el-form-item label="处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过" prop="withdrawStatus">
      <el-input v-model="dataForm.withdrawStatus" placeholder="处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过"></el-input>
    </el-form-item>
    <el-form-item label="提现单号" prop="number">
      <el-input v-model="dataForm.number" placeholder="提现单号"></el-input>
    </el-form-item>
    <el-form-item label="收款账户" prop="receivableAccount">
      <el-input v-model="dataForm.receivableAccount" placeholder="收款账户"></el-input>
    </el-form-item>
    <el-form-item label="收款人姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="收款人姓名"></el-input>
    </el-form-item>
    <el-form-item label="开户行地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="开户行地址"></el-input>
    </el-form-item>
    <el-form-item label="提现金额" prop="withdrawFee">
      <el-input v-model="dataForm.withdrawFee" placeholder="提现金额"></el-input>
    </el-form-item>
    <el-form-item label="审核不通过原因" prop="content">
      <el-input v-model="dataForm.content" placeholder="审核不通过原因"></el-input>
    </el-form-item>
    <el-form-item label="审核人" prop="verifyUser">
      <el-input v-model="dataForm.verifyUser" placeholder="审核人"></el-input>
    </el-form-item>
    <el-form-item label="操作人" prop="actionUser">
      <el-input v-model="dataForm.actionUser" placeholder="操作人"></el-input>
    </el-form-item>
    <el-form-item label="审核时间" prop="actionAt">
      <el-input v-model="dataForm.actionAt" placeholder="审核时间"></el-input>
    </el-form-item>
    <el-form-item label="发送通知时间" prop="sentNoticeAt">
      <el-input v-model="dataForm.sentNoticeAt" placeholder="发送通知时间"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createAt">
      <el-input v-model="dataForm.createAt" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateAt">
      <el-input v-model="dataForm.updateAt" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="是否删除：0未删除，1已删除" prop="deleted">
      <el-input v-model="dataForm.deleted" placeholder="是否删除：0未删除，1已删除"></el-input>
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
          withdrawWay: '',
          withdrawStatus: '',
          number: '',
          receivableAccount: '',
          name: '',
          address: '',
          withdrawFee: '',
          content: '',
          verifyUser: '',
          actionUser: '',
          actionAt: '',
          sentNoticeAt: '',
          createAt: '',
          updateAt: '',
          deleted: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '申请用户uuid不能为空', trigger: 'blur' }
          ],
          withdrawWay: [
            { required: true, message: '提现（渠道）方式 1银行转账不能为空', trigger: 'blur' }
          ],
          withdrawStatus: [
            { required: true, message: '处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '提现单号不能为空', trigger: 'blur' }
          ],
          receivableAccount: [
            { required: true, message: '收款账户不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '收款人姓名不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '开户行地址不能为空', trigger: 'blur' }
          ],
          withdrawFee: [
            { required: true, message: '提现金额不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '审核不通过原因不能为空', trigger: 'blur' }
          ],
          verifyUser: [
            { required: true, message: '审核人不能为空', trigger: 'blur' }
          ],
          actionUser: [
            { required: true, message: '操作人不能为空', trigger: 'blur' }
          ],
          actionAt: [
            { required: true, message: '审核时间不能为空', trigger: 'blur' }
          ],
          sentNoticeAt: [
            { required: true, message: '发送通知时间不能为空', trigger: 'blur' }
          ],
          createAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateAt: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          deleted: [
            { required: true, message: '是否删除：0未删除，1已删除不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/pay/userwithdrawcashlist/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.userWithdrawCashList.userId
                this.dataForm.withdrawWay = data.userWithdrawCashList.withdrawWay
                this.dataForm.withdrawStatus = data.userWithdrawCashList.withdrawStatus
                this.dataForm.number = data.userWithdrawCashList.number
                this.dataForm.receivableAccount = data.userWithdrawCashList.receivableAccount
                this.dataForm.name = data.userWithdrawCashList.name
                this.dataForm.address = data.userWithdrawCashList.address
                this.dataForm.withdrawFee = data.userWithdrawCashList.withdrawFee
                this.dataForm.content = data.userWithdrawCashList.content
                this.dataForm.verifyUser = data.userWithdrawCashList.verifyUser
                this.dataForm.actionUser = data.userWithdrawCashList.actionUser
                this.dataForm.actionAt = data.userWithdrawCashList.actionAt
                this.dataForm.sentNoticeAt = data.userWithdrawCashList.sentNoticeAt
                this.dataForm.createAt = data.userWithdrawCashList.createAt
                this.dataForm.updateAt = data.userWithdrawCashList.updateAt
                this.dataForm.deleted = data.userWithdrawCashList.deleted
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
              url: this.$http.adornUrl(`/pay/userwithdrawcashlist/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'withdrawWay': this.dataForm.withdrawWay,
                'withdrawStatus': this.dataForm.withdrawStatus,
                'number': this.dataForm.number,
                'receivableAccount': this.dataForm.receivableAccount,
                'name': this.dataForm.name,
                'address': this.dataForm.address,
                'withdrawFee': this.dataForm.withdrawFee,
                'content': this.dataForm.content,
                'verifyUser': this.dataForm.verifyUser,
                'actionUser': this.dataForm.actionUser,
                'actionAt': this.dataForm.actionAt,
                'sentNoticeAt': this.dataForm.sentNoticeAt,
                'createAt': this.dataForm.createAt,
                'updateAt': this.dataForm.updateAt,
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
