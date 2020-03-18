<template>
    <div>
        <div>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                   <span>{{user.name}}</span>
                </div>
                <div>
                    <div class="img-container">
                        <el-upload
                                class="upload-demo"
                                action="/system/user/uploadFile"
                                :show-file-list=false
                                :on-success="onSuccess">
                            <img v-if="avatarUrl" :src="avatarUrl" class="userface-img" />
                        </el-upload>

                    </div>
                    <div class="userinfo-container">
                        <div>用户名称:<el-input size="normal" class="inp" disabled v-model="user.username" auto-complete="off" placeholder="请输入用户名"></el-input></div>
                        <div>手机号码:<el-input size="normal" class="inp" v-model="user.phone" auto-complete="off" placeholder="请输入手机号码"></el-input></div>
                        <div>电话号码:<el-input size="normal" class="inp" v-model="user.telephone" auto-complete="off" placeholder="请输入电话号码"></el-input></div>
                        <div>用户备注:<el-input size="normal" class="inp" v-model="user.remark" auto-complete="off" placeholder="请输入用户备注"></el-input></div>
                    </div>
                    <div class="btndiv">
                        <el-button size="normal" class="btn" type="primary" @click="updateUser">保存</el-button>
                        <el-button size="normal" class="btn" type="warning" @click="">修改密码</el-button>
                    </div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserInfo",
        data(){
            return{
                user:[],
                avatarUrl:''
            }
        },
        methods:{
            // 上传成功的函数
            onSuccess() {
                this.initUser()
            },

            initUser(){
                this.getRequest("/system/user/currentUser").then(res=>{
                    if(res){
                        this.user=res
                        this.avatarUrl=res.userface
                    }
                })
            },
            updateUser(){
                this.$confirm('确定修改【' + this.user.name + '】?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.putRequest("/system/user/",this.user).then(res=>{
                        if(res){
                            this.initUser()
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消修改'
                    });
                });

            }
        },
        mounted(){
            this.initUser()
        }
    }
</script>

<style scoped>
    .btndiv{
        margin-top: 20px;
        margin-left: 292px;
    }
    .btn{
        height: 35px;
    }
    .box-card {
        width: 600px;
        margin: 50px 200px;
        height: 500px;
    }
    .userinfo-container div{
        font-size: 12px;
        /*color: #409eff;*/
        margin-top: 8px;
        margin-left: 60px;
    }
    .userinfo-container div span{
        margin-left: 70px;
    }
    .userinfo-container{
        margin-top: 20px;
    }
    .img-container{
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .userface-img{
        width: 72px;
        height: 72px;
        border-radius: 42px;
    }
    .inp{
        width: 330px;
        height: 20px !important;
        margin-top:-70px;
    }
</style>
