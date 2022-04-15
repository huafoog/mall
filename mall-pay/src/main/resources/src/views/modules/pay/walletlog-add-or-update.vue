<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="流水号" prop="number">
      <el-input v-model="dataForm.number" placeholder="流水号"></el-input>
    </el-form-item>
    <el-form-item label="业务类型，1：充值，2：提现  3：下单" prop="targetType">
      <el-input v-model="dataForm.targetType" placeholder="业务类型，1：充值，2：提现  3：下单"></el-input>
    </el-form-item>
    <el-form-item label="来源uuid（如提现uuid）" prop="targetUuid">
      <el-input v-model="dataForm.targetUuid" placeholder="来源uuid（如提现uuid）"></el-input>
    </el-form-item>
    <el-form-item label="操作类型，1：充值，2：提现，3：订单" prop="actionType">
      <el-input v-model="dataForm.actionType" placeholder="操作类型，1：充值，2：提现，3：订单"></el-input>
    </el-form-item>
    <el-form-item label="变动的金额，正负数。" prop="fee">
      <el-input v-model="dataForm.fee" placeholder="变动的金额，正负数。"></el-input>
    </el-form-item>
    <el-form-item label="" prop="originalAccountJson">
      <el-input v-model="dataForm.originalAccountJson" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="disposeAccountJson">
      <el-input v-model="dataForm.disposeAccountJson" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="处理状态1，处理完成，0未完成" prop="status">
      <el-input v-model="dataForm.status" placeholder="处理状态1，处理完成，0未完成"></el-input>
    </el-form-item>
    <el-form-item label="处理结果，0：没有变更，1：有变更。" prop="resultType">
      <el-input v-model="dataForm.resultType" placeholder="处理结果，0：没有变更，1：有变更。"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createAt">
      <el-input v-model="dataForm.createAt" placeholder="创建时间"></el-input>
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
          number: '',
          targetType: '',
          targetUuid: '',
          actionType: '',
          fee: '',
          originalAccountJson: '',
          disposeAccountJson: '',
          status: '',
          resultType: '',
          createAt: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '流水号不能为空', trigger: 'blur' }
          ],
          targetType: [
            { required: true, message: '业务类型，1：充值，2：提现  3：下单不能为空', trigger: 'blur' }
          ],
          targetUuid: [
            { required: true, message: '来源uuid（如提现uuid）不能为空', trigger: 'blur' }
          ],
          actionType: [
            { required: true, message: '操作类型，1：充值，2：提现，3：订单不能为空', trigger: 'blur' }
          ],
          fee: [
            { required: true, message: '变动的金额，正负数。不能为空', trigger: 'blur' }
          ],
          originalAccountJson: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          disposeAccountJson: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '处理状态1，处理完成，0未完成不能为空', trigger: 'blur' }
          ],
          resultType: [
            { required: true, message: '处理结果，0：没有变更，1：有变更。不能为空', trigger: 'blur' }
          ],
          createAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/pay/walletlog/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.walletLog.userId
                this.dataForm.number = data.walletLog.number
                this.dataForm.targetType = data.walletLog.targetType
                this.dataForm.targetUuid = data.walletLog.targetUuid
                this.dataForm.actionType = data.walletLog.actionType
                this.dataForm.fee = data.walletLog.fee
                this.dataForm.originalAccountJson = data.walletLog.originalAccountJson
                this.dataForm.disposeAccountJson = data.walletLog.disposeAccountJson
                this.dataForm.status = data.walletLog.status
                this.dataForm.resultType = data.walletLog.resultType
                this.dataForm.createAt = data.walletLog.createAt
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
              url: this.$http.adornUrl(`/pay/walletlog/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'number': this.dataForm.number,
                'targetType': this.dataForm.targetType,
                'targetUuid': this.dataForm.targetUuid,
                'actionType': this.dataForm.actionType,
                'fee': this.dataForm.fee,
                'originalAccountJson': this.dataForm.originalAccountJson,
                'disposeAccountJson': this.dataForm.disposeAccountJson,
                'status': this.dataForm.status,
                'resultType': this.dataForm.resultType,
                'createAt': this.dataForm.createAt
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
