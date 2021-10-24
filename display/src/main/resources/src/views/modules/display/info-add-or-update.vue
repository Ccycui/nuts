<template>
  <el-dialog
    :title="!dataForm.carId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="carBrand">
      <el-input v-model="dataForm.carBrand" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carModel">
      <el-input v-model="dataForm.carModel" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carType">
      <el-input v-model="dataForm.carType" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carYear">
      <el-input v-model="dataForm.carYear" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carMile">
      <el-input v-model="dataForm.carMile" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carColor">
      <el-input v-model="dataForm.carColor" placeholder=""></el-input>
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
          carBrand: '',
          carModel: '',
          carType: '',
          carYear: '',
          carMile: '',
          carColor: ''
        },
        dataRule: {
          carBrand: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carModel: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carType: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carYear: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carMile: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carColor: [
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
              url: this.$http.adornUrl(`/display/info/info/${this.dataForm.carId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.carBrand = data.info.carBrand
                this.dataForm.carModel = data.info.carModel
                this.dataForm.carType = data.info.carType
                this.dataForm.carYear = data.info.carYear
                this.dataForm.carMile = data.info.carMile
                this.dataForm.carColor = data.info.carColor
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
              url: this.$http.adornUrl(`/display/info/${!this.dataForm.carId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'carId': this.dataForm.carId || undefined,
                'carBrand': this.dataForm.carBrand,
                'carModel': this.dataForm.carModel,
                'carType': this.dataForm.carType,
                'carYear': this.dataForm.carYear,
                'carMile': this.dataForm.carMile,
                'carColor': this.dataForm.carColor
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
