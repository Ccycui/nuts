<template>
  <el-dialog
    :title="!dataForm.carId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="carName">
      <el-input v-model="dataForm.carName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="1-未售出 0-已售出" prop="carState">
      <el-input v-model="dataForm.carState" placeholder="1-未售出 0-已售出"></el-input>
    </el-form-item>
    <el-form-item label="" prop="carPrice">
      <el-input v-model="dataForm.carPrice" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carPicture">
      <el-input v-model="dataForm.carPicture" placeholder=""></el-input>
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
          carId: 0,
          carName: '',
          carState: '',
          carPrice: '',
          carPicture: ''
        },
        dataRule: {
          carName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carState: [
            { required: true, message: '1-未售出 0-已售出不能为空', trigger: 'blur' }
          ],
          carPrice: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carPicture: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.carId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.carId) {
            this.$http({
              url: this.$http.adornUrl(`/display/car/info/${this.dataForm.carId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.carName = data.car.carName
                this.dataForm.carState = data.car.carState
                this.dataForm.carPrice = data.car.carPrice
                this.dataForm.carPicture = data.car.carPicture
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
              url: this.$http.adornUrl(`/display/car/${!this.dataForm.carId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'carId': this.dataForm.carId || undefined,
                'carName': this.dataForm.carName,
                'carState': this.dataForm.carState,
                'carPrice': this.dataForm.carPrice,
                'carPicture': this.dataForm.carPicture
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
