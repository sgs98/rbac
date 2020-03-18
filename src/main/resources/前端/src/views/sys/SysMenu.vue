<template>
    <div>
        <div>
            <div style="display: flex; justify-content: space-between;">
                <div>
                      <el-button type="primary" icon="el-icon-plus" @click="showAddView">
                          添加菜单
                      </el-button>
                </div>
            </div>
        </div>
        <div style="margin-top: 8px">
        <el-table
                :data="menus"
                style="width: 100%;margin-bottom: 20px;"
                row-key="id"
                border
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column
                    align="center"
                    prop="name"
                    label="菜单名">
            </el-table-column>
            <el-table-column
                    prop="id"
                    align="center"
                    label="ID"
                    width="80">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="parentId"
                    label="父级ID">
            </el-table-column>
            <el-table-column
                    prop="path"
                    align="center"
                    label="页面路径">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="component"
                    label="component">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="url"
                    label="路径匹配规则"
                    width="180">
            </el-table-column>
            <el-table-column
                    align="center"
                    label="图标">
                <template slot-scope="scope">
                    <el-tag size="medium"><i :class="scope.row.iconCls"></i></el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    align="center"
                    label="是否启用">
                <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                    <el-tag type="danger" v-else>未启用</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="showEditView(scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="deleteMenuById(scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        </div>
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="50%">
            <div>
                <el-form :rules="rules" :model="menu" ref="menuForm">
                    <el-row :gutter="20">
                        <el-col :span="10">
                            <el-form-item label="父级菜单:" prop="parentId">
                                <template>
                                    <el-select v-model="menu.parentId" placeholder="请选择">
                                        <el-option
                                                v-for="item in allMenu"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </template>

                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="菜单名称:" prop="name">
                                <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="menu.name"
                                          placeholder="请输入菜单名"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="10">
                            <el-form-item label="匹配规则:" prop="url">
                                <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="menu.url"
                                          placeholder="请输入路径匹配规则"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="页面路径:" prop="path">
                                <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="menu.path"
                                          placeholder="请输入页面路径"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="10">
                            <el-form-item label="页面路由:" prop="component">
                                <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="menu.component"
                                          placeholder="请输入路由"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="菜单图标:" prop="iconCls">
                                <el-input size="mini" style="width: 200px" prefix-icon="el-icon-edit" v-model="menu.iconCls"
                                          placeholder="请输入图标"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="10">
                            <el-form-item label="是否启用:" prop="enabled">
                                <el-switch
                                        v-model="menu.enabled"
                                        active-text="启用"
                                        inactive-text="禁用">
                                </el-switch>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="closeAddView">取 消</el-button>
                <el-button type="primary" @click="addMenuSubmit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "EmpAdv",
        data() {
            return {
                allMenu:[],
                menus:[],
                dialogVisible: false,
                rules: {
                    name: [{required: true, message: '请输入菜单名', trigger: 'blur'}],
                    url: [{required: true, message: '请输入匹配规则', trigger: 'blur'}],
                    path: [{required: true, message: '请输入页面路径', trigger: 'blur'}],
                    component:[{required: true, message: '请输入路由', trigger: 'blur'}],
                    iconCls:[{required: true, message: '请输入图标', trigger: 'blur'}],
                    parentId:[{required: true, message: '请选择父级菜单', trigger: 'blur'}],
                },
                menu:{
                    name:'',
                    url:'',
                    path:'',
                    component:'',
                    iconCls:'',
                    parentId:'',
                    enabled:true
                }
            }
        },
        methods: {
            showAddView(){
                this.dialogVisible=true
                this.loadAllOneMenu()
                this.empty()
            },
            initAllMenus(){
                this.getRequest("/system/menu/getAllMenus").then(res=>{
                    this.menus=res
                })
            },
            loadAllOneMenu(){
                this.getRequest("/system/menu/loadAllOneMenu").then(res=>{
                    this.allMenu=res
                })
            },
            addMenuSubmit(){
                if(this.menu.id){
                    this.$refs['menuForm'].validate(valid => {
                        if (valid) {
                          this.putRequest("/system/menu/",this.menu).then(res=>{
                              if(res){
                                  this.initAllMenus()
                                  this.empty()
                                  this.dialogVisible=false
                              }
                          })
                        }
                    });
                }else{
                    this.$refs['menuForm'].validate(valid => {
                        if (valid) {
                            this.postRequest("/system/menu/",this.menu).then(res=>{
                                if(res){
                                    this.initAllMenus()
                                    this.empty()
                                    this.dialogVisible=false
                                }
                            })
                        }
                    });
                }
            },
            empty(){
                this.menu. name=''
                this.menu.url=''
                this.menu.path=''
                this.menu.component=''
                this.menu.iconCls=''
                this.menu.parentId=''
                this.menu.id=''
            },
            closeAddView(){
              this.dialogVisible=false
              this.menu. name=''
              this.menu.url=''
              this.menu.path=''
              this.menu.component=''
              this.menu.iconCls=''
              this.menu.parentId=''
            },
            showEditView(data){
              this.menu=data
              this.dialogVisible=true
            },
            deleteMenuById(data){
                this.$confirm('此操作将永久删除【' + data.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.getRequest("/system/menu/checkIsChild/"+ data.id).then(res=>{
                        if(res>0){
                            this.$message({
                                type: 'warning',
                                message: '当前菜单存在子级'
                            });
                        }else{
                            this.deleteRequest("/system/menu/"+ data.id).then(res => {
                                if (res) {
                                    this.initAllMenus()
                                }
                            })
                        }
                    })

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        },
        mounted(){
            this.initAllMenus()
        }
    }
</script>

<style scoped>

</style>
