<template>
    <div >
        <el-form :rules="rules"
                 ref="loginForm"
                 :model="loginForm"
                 class="loginContainer"
                 v-loading="loading"
                 element-loading-text="正在登陆中..."
                 element-loading-spinner="el-icon-loading"
                 element-loading-background="rgba(0, 0, 0, 0.8)">
            <h3 class="loginTitle">系统登陆</h3>
            <el-form-item prop="username">
                <el-input size="normal" v-model="loginForm.username" auto-complete="off" aria-placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input size="normal" v-model="loginForm.password" @keydown.enter.native="submitLogin" auto-complete="off" aria-placeholder="请输入用户密码"></el-input>
            </el-form-item>
            <el-button size="normal" type="primary" style="width: 100%" @click="submitLogin">登陆</el-button>
        </el-form>

    </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return{
                loginForm:{
                    username:'libai',
                    password:'123'
                },
                loading:false,
                checked:true,
                rules:{
                    username:[{required:true,message:'请输入用户名',trigger:'blur'}],
                    password:[{required:true,message:'请输入密码',trigger:'blur'}],
                }
            }
        },
        methods:{
            submitLogin(){

                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.loading=true
                        this.postKeyValueRequest('/doLogin',this.loginForm).then(resp=>{
                            this.loading=false
                            if(resp){
                                window.sessionStorage.setItem("user",JSON.stringify(resp.obj))
                                let path=this.$route.query.redirect;
                                this.$router.replace((path=='/'||path==undefined)?'/home':path);
                            }
                        })
                    } else {
                        this.$message.error("请输入所有字段")
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .loginContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        height: 250px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .loginTitle{
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }
</style>
