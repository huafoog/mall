<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户uuid" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户uuid"></el-input>
    </el-form-item>
    <el-form-item label="支付号" prop="payNum">
      <el-input v-model="dataForm.payNum" placeholder="支付号"></el-input>
    </el-form-item>
    <el-form-item label="第三方付款成功交易号" prop="tradeNo">
      <el-input v-model="dataForm.tradeNo" placeholder="第三方付款成功交易号"></el-input>
    </el-form-item>
    <el-form-item label="订单类型 1充值 " prop="orderType">
      <el-input v-model="dataForm.orderType" placeholder="订单类型 1充值 "></el-input>
    </el-form-item>
    <el-form-item label="充值订单表uuid" prop="orderUuid">
      <el-input v-model="dataForm.orderUuid" placeholder="充值订单表uuid"></el-input>
    </el-form-item>
    <el-form-item label="付款方式 1微信 2支付宝 " prop="payWay">
      <el-input v-model="dataForm.payWay" placeholder="付款方式 1微信 2支付宝 "></el-input>
    </el-form-item>
    <el-form-item label="支付状态 1 支付成功，2 支付失败 " prop="payStatus">
      <el-input v-model="dataForm.payStatus" placeholder="支付状态 1 支付成功，2 支付失败 "></el-input>
    </el-form-item>
    <el-form-item label="步骤 1 创建支付，2 支付回调通知" prop="step">
      <el-input v-model="dataForm.step" placeholder="步骤 1 创建支付，2 支付回调通知"></el-input>
    </el-form-item>
    <el-form-item label="请求支付状态，1：成功，2：失败" prop="postStatus">
      <el-input v-model="dataForm.postStatus" placeholder="请求支付状态，1：成功，2：失败"></el-input>
    </el-form-item>
    <el-form-item label="回调状态，1：成功，2：失败" prop="returnStatus">
      <el-input v-model="dataForm.returnStatus" placeholder="回调状态，1：成功，2：失败"></el-input>
    </el-form-item>
    <el-form-item label="支付价格" prop="totalFee">
      <el-input v-model="dataForm.totalFee" placeholder="支付价格"></el-input>
    </el-form-item>
    <el-form-item label="第三方返回错误码" prop="resultCode">
      <el-input v-model="dataForm.resultCode" placeholder="第三方返回错误码"></el-input>
    </el-form-item>
    <el-form-item label="第三方返回的错误记录" prop="errCodeStr">
      <el-input v-model="dataForm.errCodeStr" placeholder="第三方返回的错误记录"></el-input>
    </el-form-item>
    <el-form-item label="货币类型" prop="currency">
      <el-input v-model="dataForm.currency" placeholder="货币类型"></el-input>
    </el-form-item>
    <el-form-item label="ip2long" prop="ip2long">
      <el-input v-model="dataForm.ip2long" placeholder="ip2long"></el-input>
    </el-form-item>
    <el-form-item label="提交post json 数据" prop="postJson">
      <el-input v-model="dataForm.postJson" placeholder="提交post json 数据"></el-input>
    </el-form-item>
    <el-form-item label="回调post return data json" prop="returnJson">
      <el-input v-model="dataForm.returnJson" placeholder="回调post return data json"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createAt">
      <el-input v-model="dataForm.createAt" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateAt">
      <el-input v-model="dataForm.updateAt" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="支付成功时间" prop="successAt">
      <el-input v-model="dataForm.successAt" placeholder="支付成功时间"></el-input>
    </el-form-item>
    <el-form-item label="手续费比例，千位比。6/1000" prop="procedureKb">
      <el-input v-model="dataForm.procedureKb" placeholder="手续费比例，千位比。6/1000"></el-input>
    </el-form-item>
    <el-form-item label="支付渠道收取手续费金额" prop="procedureFee">
      <el-input v-model="dataForm.procedureFee" placeholder="支付渠道收取手续费金额"></el-input>
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
          payNum: '',
          tradeNo: '',
          orderType: '',
          orderUuid: '',
          payWay: '',
          payStatus: '',
          step: '',
          postStatus: '',
          returnStatus: '',
          totalFee: '',
          resultCode: '',
          errCodeStr: '',
          currency: '',
          ip2long: '',
          postJson: '',
          returnJson: '',
          createAt: '',
          updateAt: '',
          successAt: '',
          procedureKb: '',
          procedureFee: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户uuid不能为空', trigger: 'blur' }
          ],
          payNum: [
            { required: true, message: '支付号不能为空', trigger: 'blur' }
          ],
          tradeNo: [
            { required: true, message: '第三方付款成功交易号不能为空', trigger: 'blur' }
          ],
          orderType: [
            { required: true, message: '订单类型 1充值 不能为空', trigger: 'blur' }
          ],
          orderUuid: [
            { required: true, message: '充值订单表uuid不能为空', trigger: 'blur' }
          ],
          payWay: [
            { required: true, message: '付款方式 1微信 2支付宝 不能为空', trigger: 'blur' }
          ],
          payStatus: [
            { required: true, message: '支付状态 1 支付成功，2 支付失败 不能为空', trigger: 'blur' }
          ],
          step: [
            { required: true, message: '步骤 1 创建支付，2 支付回调通知不能为空', trigger: 'blur' }
          ],
          postStatus: [
            { required: true, message: '请求支付状态，1：成功，2：失败不能为空', trigger: 'blur' }
          ],
          returnStatus: [
            { required: true, message: '回调状态，1：成功，2：失败不能为空', trigger: 'blur' }
          ],
          totalFee: [
            { required: true, message: '支付价格不能为空', trigger: 'blur' }
          ],
          resultCode: [
            { required: true, message: '第三方返回错误码不能为空', trigger: 'blur' }
          ],
          errCodeStr: [
            { required: true, message: '第三方返回的错误记录不能为空', trigger: 'blur' }
          ],
          currency: [
            { required: true, message: '货币类型不能为空', trigger: 'blur' }
          ],
          ip2long: [
            { required: true, message: 'ip2long不能为空', trigger: 'blur' }
          ],
          postJson: [
            { required: true, message: '提交post json 数据不能为空', trigger: 'blur' }
          ],
          returnJson: [
            { required: true, message: '回调post return data json不能为空', trigger: 'blur' }
          ],
          createAt: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateAt: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          successAt: [
            { required: true, message: '支付成功时间不能为空', trigger: 'blur' }
          ],
          procedureKb: [
            { required: true, message: '手续费比例，千位比。6/1000不能为空', trigger: 'blur' }
          ],
          procedureFee: [
            { required: true, message: '支付渠道收取手续费金额不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/pay/paymentrecord/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.paymentRecord.userId
                this.dataForm.payNum = data.paymentRecord.payNum
                this.dataForm.tradeNo = data.paymentRecord.tradeNo
                this.dataForm.orderType = data.paymentRecord.orderType
                this.dataForm.orderUuid = data.paymentRecord.orderUuid
                this.dataForm.payWay = data.paymentRecord.payWay
                this.dataForm.payStatus = data.paymentRecord.payStatus
                this.dataForm.step = data.paymentRecord.step
                this.dataForm.postStatus = data.paymentRecord.postStatus
                this.dataForm.returnStatus = data.paymentRecord.returnStatus
                this.dataForm.totalFee = data.paymentRecord.totalFee
                this.dataForm.resultCode = data.paymentRecord.resultCode
                this.dataForm.errCodeStr = data.paymentRecord.errCodeStr
                this.dataForm.currency = data.paymentRecord.currency
                this.dataForm.ip2long = data.paymentRecord.ip2long
                this.dataForm.postJson = data.paymentRecord.postJson
                this.dataForm.returnJson = data.paymentRecord.returnJson
                this.dataForm.createAt = data.paymentRecord.createAt
                this.dataForm.updateAt = data.paymentRecord.updateAt
                this.dataForm.successAt = data.paymentRecord.successAt
                this.dataForm.procedureKb = data.paymentRecord.procedureKb
                this.dataForm.procedureFee = data.paymentRecord.procedureFee
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
              url: this.$http.adornUrl(`/pay/paymentrecord/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'payNum': this.dataForm.payNum,
                'tradeNo': this.dataForm.tradeNo,
                'orderType': this.dataForm.orderType,
                'orderUuid': this.dataForm.orderUuid,
                'payWay': this.dataForm.payWay,
                'payStatus': this.dataForm.payStatus,
                'step': this.dataForm.step,
                'postStatus': this.dataForm.postStatus,
                'returnStatus': this.dataForm.returnStatus,
                'totalFee': this.dataForm.totalFee,
                'resultCode': this.dataForm.resultCode,
                'errCodeStr': this.dataForm.errCodeStr,
                'currency': this.dataForm.currency,
                'ip2long': this.dataForm.ip2long,
                'postJson': this.dataForm.postJson,
                'returnJson': this.dataForm.returnJson,
                'createAt': this.dataForm.createAt,
                'updateAt': this.dataForm.updateAt,
                'successAt': this.dataForm.successAt,
                'procedureKb': this.dataForm.procedureKb,
                'procedureFee': this.dataForm.procedureFee
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
