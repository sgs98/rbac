<template>
    <div>
        <div class="permissManaTool">
            <el-input size="small" placeholder="请输入角色英文名" v-model="role.name">
                <template slot="prepend">ROLE_</template>
            </el-input>
            <el-input size="small" placeholder="请输入角色中文名" v-model="role.nameZh"
                      @keydown.enter.native="doAddRole"></el-input>
            <el-button type="primary" size="small" icon="el-icon-plus" @click="doAddRole">添加角色</el-button>
        </div>
        <div class="permissManaMain">
            <el-collapse v-model="activeName"
                         accordion
                         @change="change">
                <el-collapse-item :title="item.nameZh" :key="index"  v-for="(item,index) in roles" :name="item.id">
                    <div>
                      <el-card class="box-card">
                          <div slot="header" class="clearfix">
                              <span>可访问的资源</span>
                              <el-button style="float: right; padding: 3px 0;color: #ff0000;" icon="el-icon-delete"
                                         type="text" @click="deleteRole(item)"></el-button>
                          </div>
                        <div>
                            <el-tree
                                    :data="menus"
                                    show-checkbox
                                    node-key="id"
                                    ref="tree"
                                    :key="index"
                                    :default-checked-keys="selectedMenus"
                                    :props="defaultProps">
                            </el-tree>
                            <div style="display: flex;justify-content: flex-end">
                                <el-button @click="cancelUpdate">取消修改</el-button>
                                <el-button type="primary" @click="doUpdate(item.id,index)">确认修改</el-button>
                            </div>
                        </div>
                     </el-card>
                    </div>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SysLog",
        data() {
            return {
                activeName: '-1',
                roles:[],
                role:{
                    name:'',
                    nameZh:''
                },
                menus:[],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                selectedMenus:[]
            };
        },
        methods:{
            getAllRoles(){
                this.getRequest("/system/role/").then(res=>{
                    if(res){
                       this.roles=res
                    }
                })
            },
            initAllMenus(){
                this.getRequest("/system/menu/getAllMenus").then(res=>{
                    this.menus=res
                })
            },
            doAddRole(){
                if (this.role.name && this.role.nameZh) {
                    this.postRequest("/system/role/",this.role).then(resp => {
                        if (resp) {
                            this.role.name = '';
                            this.role.nameZh = '';
                            this.getAllRoles();
                        }
                    })
                } else {
                    this.$message.error('数据不可以为空');
                }
            },
            deleteRole(role){
                this.$confirm('此操作将永久删除【' + role.nameZh + '】角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/role/" + role.id).then(resp => {
                        if (resp) {
                            this.getAllRoles();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            change(rid) {
                if (rid) {
                    this.initSelectedMenus(rid);
                    this.initAllMenus();
                }
            },
            initSelectedMenus(rid) {
                this.getRequest("/system/role/getMidsByRid/"+rid).then(res=>{
                    this.selectedMenus=res
                })
            },
            doUpdate(rid, index){
                let tree = this.$refs.tree[index];
                let selectedKeys = tree.getCheckedKeys(true);
                let url = '/system/role/updateMenuRole?rid=' + rid;
                selectedKeys.forEach(key => {
                    url += '&mids=' + key;
                })
                alert(url)
                this.putRequest(url).then(resp => {
                    if (resp) {
                        this.activeName = -1;
                    }
                })

            },
            cancelUpdate() {
                this.activeName = -1;
            },
        },
        mounted(){
            this.getAllRoles()
            this.initAllMenus()
        }
    }
</script>

<style scoped>
    .permissManaTool {
        display: flex;
        justify-content: flex-start;
    }

    .permissManaTool .el-input {
        width: 300px;
        margin-right: 6px;
    }
    .permissManaMain {
        margin-top: 10px;
        width: 700px;
    }

</style>
