<template>
    <div>
        <div class="hr-container">
            <el-card class="hr-card" v-for="(u,index) in user" :key="index">
                <div slot="header" class="clearfix">
                    <span>{{u.name}}</span>
                    <el-button style="float: right; padding: 3px 0; color: red" type="text" icon="el-icon-delete" @click="deleteUser(u.id,u.name)"></el-button>
                </div>
                <div>
                    <div class="img-container">
                        <img :src="u.userface" :alt="u.name" :title="u.name" class="userface-img"/>
                    </div>
                    <div class="userinfo-container">
                        <div>用户名:{{u.name}}</div>
                        <div>手机号码:{{u.phone}}</div>
                        <div>电话号码:{{u.telephone}}</div>
                        <div>用户状态:
                            <el-switch
                                    v-model="u.enabled"
                                    active-text="启用"
                                    @change="enabledChange(u)"
                                    active-color="#13ce66"
                                    inactive-color="#ff4949"
                                    inactive-text="禁用">
                            </el-switch>
                        </div>
                        <div>用户角色:
                            <el-tag type="success" style="margin-right: 4px" v-for="(role,indexj) in u.roles" :key="indexj">{{role.nameZh}}</el-tag>
                            <el-popover
                                    placement="bottom"
                                    title="角色列表"
                                    width="200"
                                    @hide="hidePop(u)"
                                    @show="showPop(u)"
                                    trigger="click">
                                <el-select multiple v-model="selectedRoles" placeholder="请选择">
                                    <el-option :key="indexk"
                                               v-for="(r,indexk) in allRoles"
                                               :label="r.nameZh"
                                               :value="r.id">
                                    </el-option>
                                </el-select>
                                <el-button slot="reference" type="text" icon="el-icon-more"></el-button>
                            </el-popover>
                        </div>
                        <div>备注:{{u.remark}}</div>
                    </div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SysHr",
        data(){
            return{
              user:[],
              keywords:'',
              allRoles:[],
              selectedRoles:[]
            }
        },
        methods:{
            initAllUser(){
                this.getRequest("/system/user/?keywords="+this.keywords).then(res=>{
                    if(res){
                        this.user=res
                    }
                })
            },
            initRoles(){
                this.getRequest("/system/role/").then(res=>{
                    if(res){
                        this.allRoles=res
                    }
                })
            },
            deleteUser(id,name){
                this.$confirm('此操作将永久删除【' + name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/user/"+id).then(res=>{
                        if(res){
                            this.initAllUser();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            enabledChange(u){
                console.log(u)
                delete u.roles
                this.putRequest("/system/user/",u).then(res=>{
                    if(res){
                        this.initAllUser()
                    }
                })
            },
            showPop(u){
                let roles=u.roles
                this.selectedRoles=[]
                roles.forEach(r=>{
                    this.selectedRoles.push(r.id)
                })
                this. initRoles()
            },
            hidePop(u){
                let roles = [];
                Object.assign(roles, u.roles);
                let flag = false;
                if (roles.length != this.selectedRoles.length) {
                    flag = true;
                } else {
                    for (let i = 0; i < roles.length; i++) {
                        let role = roles[i];
                        for (let j = 0; j < this.selectedRoles.length; j++) {
                            let sr = this.selectedRoles[j];
                            if (role.id == sr) {
                                roles.splice(i, 1);
                                i--;
                                break;
                            }
                        }
                    }
                    if (roles.length != 0) {
                        flag = true;
                    }
                }
                if(flag){
                    let url = '/system/user/updateUserRole?uid=' + u.id;

                    this.selectedRoles.forEach(sr => {
                        url += '&rids=' + sr;
                    });
                    this.putRequest(url).then(resp => {
                        if (resp) {
                            this.initAllUser();
                        }
                    });
                }
            }
        },
        mounted(){
            this.initAllUser()
        }
    }
</script>

<style scoped>
    .userinfo-container div{
        font-size: 12px;
        color: #409eff;
    }
    .userinfo-container{
        margin-top: 20px;
    }
    .img-container{
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .hr-card{
        width: 360px;
        margin-bottom:20px ;
    }
    .hr-container{
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }
    .userface-img{
        width: 72px;
        height: 72px;
        border-radius: 42px;
    }
</style>
