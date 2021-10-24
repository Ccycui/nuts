<template>
  <el-dialog
    :title="!dataForm.favourId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="usrId">
      <el-input v-model="dataForm.usrId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="carId">
      <el-input v-model="dataForm.carId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="time">
      <el-input v-model="dataForm.time" placeholder=""></el-input>
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
          favourId: 0,
          usrId: '',
          carId: '',
          time: ''
        },
        dataRule: {
          usrId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          carId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.favourId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.favourId) {
            this.$http({
              url: this.$http.adornUrl(`/shopping/favour/info/${this.dataForm.favourId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.usrId = data.favour.usrId
                this.dataForm.carId = data.favour.carId
                this.dataForm.time = data.favour.time
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
              url: this.$http.adornUrl(`/shopping/favour/${!this.dataForm.favourId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'favourId': this.dataForm.favourId || undefined,
                'usrId': this.dataForm.usrId,
                'carId': this.dataForm.carId,
                'time': this.dataForm.time
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
