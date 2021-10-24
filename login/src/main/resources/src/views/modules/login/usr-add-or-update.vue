<template>
  <el-dialog
    :title="!dataForm.usrId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="acc">
      <el-input v-model="dataForm.acc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="pwd">
      <el-input v-model="dataForm.pwd" placeholder=""></el-input>
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
          usrId: 0,
          acc: '',
          pwd: ''
        },
        dataRule: {
          acc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          pwd: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.usrId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.usrId) {
            this.$http({
              url: this.$http.adornUrl(`/login/usr/info/${this.dataForm.usrId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.acc = data.usr.acc
                this.dataForm.pwd = data.usr.pwd
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
              url: this.$http.adornUrl(`/login/usr/${!this.dataForm.usrId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'usrId': this.dataForm.usrId || undefined,
                'acc': this.dataForm.acc,
                'pwd': this.dataForm.pwd
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
