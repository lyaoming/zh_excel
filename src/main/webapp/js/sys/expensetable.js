$(function () {
    $("#pjqGrid").jqGrid({
        url: '../personnel/list',
        datatype: "json",
        colModel: [
            { label: 'pId', name: 'pId', width: 50, key: true,hidden:true },
            { label: '姓名', name: 'pName', width:350 },
            { label: '部门', name: 'pDepartment', width: 250 },
            { label: '状态', name: 'sName', width: 260 }	,
            { label: '身份证号', name: 'pNumber', width: 350 }
        ],
        viewrecords: true,
        height: 400,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        pager: "#pjqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#pjqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    $("#jqGrid").jqGrid({
        url: '../expensetable/list',
        datatype: "json",
        colModel: [
            { label: 'cId', name: 'cId', width: 50, key: true,hidden:true },
            { label: '名字', name: 'pName', width: 150 },
            { label: '入院日期', name: 'inDate', width: 150 },
            { label: '发票或出院日期', name: 'outDate', width: 150 },
            { label: '类型', name: 'dName', width: 150 },
            { label: '个人缴付', name: 'allSelfcost', width: 150 },
            { label: '自付', name: 'selfCost', width: 150 },
            { label: '补充医疗基数', name: 'baseNum', width: 150 },
            { label: '补充医疗系数', name: 'mFactor', width: 150 },
            { label: '补充医疗报销额', name: 'mCost', width: 150 },
            { label: '补充医疗不报销额', name: 'mNocost', width: 150 },
            { label: '重疾自付基数', name: 'maSelf', width: 150 },
            { label: '重疾报销额', name: 'maReduce', width: 150 },
            { label: '合计报销', name: 'costAll', width: 150 },
            { label: '实际自付', name: 'acCost', width: 150 },
            { label: '上次重疾累计', name: 'pevMa', width: 150 },
            { label: '本次重疾累计', name: 'nowMa', width: 120 },
            { label: '省外重疾计算', name: 'outMacount', width: 120 },
            { label: '年份', name: 'year', width: 150 }
        ],
        viewrecords: true,
        shrinkToFit:false,
        height: 500,
        autoScroll:true,
        autowidth:true,
        rowNum: 200,
        rowList : [20,50,100],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });
        }
    });
});
var vm = new Vue({
    el:'#rrapp',
    data:{
        modaltitle:'',
        pName:null,
        pId:null,
        personnelStatus:{},
        pName:null,
        pStatus:null

    },
    created:function () {
        $.get("../personnelstatus/list",function (r) {
            vm.personnelStatus=r.personnelStatusList;
        });
    },
    methods:{
        saveSelect:function(event){
            vm.pId=getSelectedRowId();
            vm.pName=getSelectedRowName();
            $("#Modal").modal("hide");
        },
        selectPersonnel:function(event){
            vm.pName=null;
            $("#pjqGrid").trigger("reloadGrid");
          $("#Modal").modal("show");
        },
        query:function(){
            $("#jqGrid").jqGrid('setGridParam',{
                postData: {
                    'pId':vm.pId,
                    'year':$("#year").val(),
                },
                page:1
            }).trigger("reloadGrid");
        },
        search:function(){
            $("#pjqGrid").jqGrid('setGridParam', {
                postData: {
                    'pName': vm.pName,
                    'pStatus': vm.pStatus,
                },
                page: 1
            }).trigger("reloadGrid");
        },
        //导出选中
        selectexport:function () {
            var cIds = getSelectedRows();
            if(cIds == null){
                return ;
            }
            layer.confirm('确定要导出选中的记录？',{offset:'100px'}, function(){
                $.ajax({
                    type: "post",
                    url: "../expensetable/exportSelect",
                    data: JSON.stringify(cIds),
                    success: function(r){
                        if(r.code == 0){
                            layer.msg('导出中......',{offset:'100px'}, function(index){
                                location.href="../expensetable/downloadExcel";
                            });
                        }else{
                            layer.msg(r.msg,{offset:'100px'});
                        }
                    }
                });
            });
        },
        //分类导出（导出查询）
        classexport:function () {
            location.href="../expensetable/downloadExcel";
        }
    }
});
function getSelectedRowName() {

    var grid = $("#pjqGrid").getGridParam("selrow");
    var rowName = $("#pjqGrid").getCell(grid,"pName");
//限制多选
    var selectedStatus = $("#pjqGrid").getGridParam("selarrrow");
    if(selectedStatus.length > 1){
        return ;
    }
    return rowName;
}

function getSelectedRowId() {
    var grid = $("#pjqGrid").getGridParam("selrow");
    var rowId = $("#pjqGrid").getCell(grid,"pId");
//限制多选
    var selectedStatus = $("#pjqGrid").getGridParam("selarrrow");
    if(selectedStatus.length > 1){
        return ;
    }
    return rowId;
}