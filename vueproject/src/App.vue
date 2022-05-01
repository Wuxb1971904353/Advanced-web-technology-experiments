<template>
  <el-row>
    <el-row>
      <el-table
          :data="tableData"
          stripe
          :key="Math.random()"
          style="width: 100%">
        <el-table-column
            prop="id"
            label="id"
            width="180">
        </el-table-column>
        <el-table-column
            prop="imgUrl"
            label="图片"
            width="180">
          <template slot-scope="scope">
            <el-upload
                action="http://localhost:9000/file"
                list-type="picture-card"
                :on-success="uploadStatus"
                :on-remove="handleRemove"
                :limit="1"
                :file-list="fileList"
                v-if="scope.row.isEdit">
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-image :src="scope.row.imgUrl" v-else></el-image>
          </template>
        </el-table-column>
        <el-table-column
            prop="name"
            label="课程"
            width="180">
          <template slot-scope="scope">
            <el-input v-if="scope.row.isEdit" class="item" size="small" v-model="scope.row.name" placeholder="请输入内容"></el-input>
            <div v-else class="txt">{{scope.row.name}}</div>
          </template>
        </el-table-column>
        <el-table-column
            prop="hours"
            label="课时"
            width="180">
          <template slot-scope="scope">
            <el-input v-if="scope.row.isEdit" class="item" size="small" v-model="scope.row.hours" placeholder="请输入内容"></el-input>
            <div v-else class="txt">{{scope.row.hours}}</div>
          </template>
        </el-table-column>
        <el-table-column
            prop="school_name"
            label="开课学院"
            width="180">
          <template slot-scope="scope">
            <el-select v-if="scope.row.isEdit" v-model="scope.row.school_name" placeholder="请选择">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
            <div v-else class="txt">{{scope.row.school_name}}</div>
          </template>
        </el-table-column>
        <el-table-column
            prop="isEdit"
            label="编辑"
            width="180">
          <template slot-scope="scope">
            <el-row>
              <el-button v-if="!scope.row.isEdit" size="small" type="primary" icon="el-icon-edit" @click="Edit(scope.$index)"></el-button>
              <el-button v-if="scope.row.isEdit" size="small" type="primary" @click="isUpdate(scope.row,scope.$index)">确认修改</el-button>
              <el-button size="small" type="primary" icon="el-icon-delete" @click.native.prevent="deleteRow(scope, tableData)"></el-button>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="margin-top: 30px">
      <el-col span="11" style="height: 120px;margin-left: 36px"></el-col>
      <el-col span="8">
        <el-button @click="dialogVisible03 = true">新增课程</el-button>
      </el-col>
      <el-dialog
          title="添加课程"
          :visible.sync="dialogVisible03"
          width="30%"
          :before-close="handleClose">
        <el-row>
          <el-row style="margin-top: 5px">
            <el-col span="8" style="margin-top: 6px">课程：</el-col>
            <el-col span="16">
              <el-input size="small" v-model="name"></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 5px">
            <el-col span="8" style="margin-top: 6px">课时：</el-col>
            <el-col span="16">
              <el-input size="small" v-model="hours" ></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 5px">
            <el-col span="8" style="margin-top: 6px">开课学院：</el-col>
            <el-col span="16">
              <el-select v-model="value" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row style="margin-top: 5px">
            <el-col span="8" style="margin-top: 6px">上传图片:</el-col>
            <el-col span="16">
              <el-upload
                  action="http://localhost:9000/file"
                  list-type="picture-card"
                  :on-success="uploadStatus"
                  :on-remove="handleRemove"
                  :limit="1">
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-col>
          </el-row>
        </el-row>
        <span slot="footer" class="dialog-footer">
              <el-button @click="clear()">我再想想</el-button>
              <el-button type="primary" @click="addCourse()">确 定</el-button>
              </span>
      </el-dialog>
    </el-row>
  </el-row>
</template>

<script>
import axios from 'axios';
export default {
  name: 'App',
  data() {
    return {
      tableData: [{
        id:'1',
        name: '1',
        hours: '1',
        school_name: '1',
        imgUrl:'1',
        isEdit:false,
      }],
      course:{
        id:'',
        name:'',
        hours:'',
        school_name:''
      },
      dialogVisible01: false,
      dialogVisible02: false,
      dialogVisible03: false,
      options: [{
        value: '1',
        label: '计算机学院'
      }, {
        value: '2',
        label: '外国语学院'
      }
      ],
      value: '',
      name:'',
      hours:'',
      imgUrl: '',
      fileList:[{url:''}]
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
          // eslint-disable-next-line no-unused-vars
          .then(_ => {
            done();
          })
          // eslint-disable-next-line no-unused-vars
          .catch(_ => {
          });
    },
    deleteRow(scope, rows) {
      console.log(scope.row)
      rows.splice(scope.$index, 1);
      axios.get("http://localhost:9000/deleteCourse/",{params:{id:scope.row.id}}).then(res=>{
        console.log(res)
      })
    },
    addCourse(){
      let id=0
      for (let i=0; i<this.tableData.length; i++){
        if (this.tableData[i].id!=i+1){
          id = i+1
          break
        }
      }
      if (id==0){
        id=this.tableData.length+1
      }
      axios.get("http://localhost:9000/addCourse",
          {params:{id:id,name:this.name,hours:this.hours,schools:this.value,imgUrl:this.imgUrl}}).then(res=>{
            axios.get("http://localhost:9000/getAllCourse").then(res=>{
              this.tableData=res.data;
              console.log(this.tableData)
            })

            console.log(res)
      })
      // console.log(this.name,this.hours,this.value)
      this.dialogVisible03 = false
      this.name=''
      this.hours=''
      this.value=''
    },
    clear(){
      this.dialogVisible03 = false
      this.name=''
      this.hours=''
      this.value=''
    },
    Edit(index){
      this.fileList[0].url=this.tableData[index].imgUrl
      this.tableData[index].isEdit=true
      this.$set(this.tableData,index,this.tableData[index])
      console.log(index)
    },
    isUpdate(row,index){
      let schools
      if (row.school_name==='计算机学院'){
        schools=1
      }
      if(row.school_name==='外国语学院'){
        schools=2
      }
      axios.get("http://localhost:9000/updateCourse",{params:{id:row.id,name:row.name,hours:row.hours,schools:schools,imgUrl:this.imgUrl}}).then(res=>{
        this.tableData[index].imgUrl=this.imgUrl
        console.log(res)
      })
      row.isEdit=false
      axios.get("http://localhost:9000/getAllCourse").then(res=>{
        this.tableData=res.data;
        console.log(this.tableData)
      })
    },
    handleRemove(file, fileList) {
      this.fileList[0].url=''
      this.imgUrl=''
      console.log(file, fileList);
    },
    uploadStatus(file){
      this.imgUrl=("http://localhost:9000//images//"+file)
      console.log(file)
    }
  },
  mounted () {
    axios.get("http://localhost:9000/getAllCourse").then(res=>{
      this.tableData=res.data;
      for (let i = 0;i<this.tableData.length;i++){
        this.tableData[i].isEdit=false
      }
      console.log(this.tableData)
    })
  }
}
</script>

<style>

</style>
