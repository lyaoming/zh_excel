var cId = T.p("cId");
$(function () {
	$("#jqGrid").jqGrid({
		url: '../personnel/list',
		datatype: "json",
		colModel: [
			{ label: 'pId', name: 'pId', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'pName', width: 350 },
			{ label: '部门', name: 'pDepartment', width:250 },
			{ label: '状态', name: 'sName', width: 260 }	,
			{ label: '身份证号', name: 'pNumber', width: 350 }
		],
		shrinkToFit:false,
		viewrecords: true,
		height: 400,
		autoScroll:true,
		autowidth:true,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
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
		title:"新增",
		costRecord:{
			pId:null,
			pName:null,
			classion:null,
			allSelfcost:null,
			selfCost:null,
			outMacount:null,
			inDate:null,
			outDate:null,
			year:null
		},
		personnel:{},
		personnelStatus:{},
		modaltitle:'',
		medicalFactor:{},
		pName:null,
		pStatus:null,
		inputData:null,
	},
	created: function() {
		if(cId != null){
			this.title = "修改";
			this.getInfo(cId)
		}
		var year=new Date().getFullYear();
		this.costRecord.year=year;
		$.get("../personnelstatus/list",function (r) {
			vm.personnelStatus=r.personnelStatusList;
		});
		$.get("../medicalfactor/list",function (r) {
			vm.medicalFactor=r.medicalFactorList;
		})
    },
	methods: {
		addPersonnel:function(){
			vm.modaltitle="新增";
          $("#selectModal").modal("show");
		},
		selectInput:function(){
		  $("#selectInput").modal("show");
		},
		input1:function(){
			vm,inputData=null;
			$("#selectInput").modal("hide");
			$("#inputModal1").modal("show");

		},
		input2:function(){
			vm.inputData=null;
			$("#selectInput").modal("hide");
			$("#inputModal2").modal("show");
		},
		save1:function(){
		  vm.inputData= "个人缴付："+vm.costRecord.allSelfcost+"元"+"  自付："+vm.costRecord.selfCost+"元";
		},
		save2:function(){
			vm.inputData="省外重疾计算:"+vm.costRecord.outMacount+"元";
		},
		select:function(){
			vm.modaltitle="新增";
			$("#selectModal").modal("hide");
			$("#jqGrid").trigger("reloadGrid");
			$("#Modal").modal("show");
		},
		add:function(){
			vm.modaltitle="新增";
			vm.personnel={},
			$("#selectModal").modal("hide");
			$("#addModal").modal("show");
		},
		saveSelect:function(){
			vm.costRecord.pId=getSelectedRowId();
            vm.costRecord.pName=getSelectedRowName();
			$("#Modal").modal("hide");
		},
		getInfo: function(cId){
			$.get("../costrecord/info/"+cId, function(r){
                vm.costRecord = r.costRecord;
                if(vm.costRecord.outMacount==null){
					vm.inputData= "个人缴付："+vm.costRecord.allSelfcost+"元"+"  自付："+vm.costRecord.selfCost+"元";
				}
                if(vm.costRecord.outMacount!=null){
					vm.inputData="省外重疾计算:"+vm.costRecord.outMacount+"元";
				}
                $("#bgTime").val(vm.costRecord.inDate);
                $("#endTime").val(vm.costRecord.outDate);
            });
		},
		query:function() {
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {
					'pName': vm.pName,
					'pStatus': vm.pStatus,
				},
				page: 1
			}).trigger("reloadGrid");
		},
		saveOrUpdate: function (event) {
			vm.costRecord.inDate=$("#bgTime").val();
			vm.costRecord.outDate=$("#endTime").val();
			var url = vm.costRecord.cId == null ? "../costrecord/save" : "../costrecord/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.costRecord),
			    success: function(r){
			    	if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							vm.back();
						});
					}else{
						layer.alert(r.msg,{offset:'100px'});
					}
				}
			});
		},
		saveOrUpdateP: function (event) {
			var url = vm.personnel.pId == null ? "../personnel/save" : "../personnel/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.personnel),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							vm.costRecord.pName=vm.personnel.pName;
							vm.costRecord.pId=r.pId;
							$("#addModal").modal("hide");
						});
					}else{
						layer.alert(r.msg,{offset:'100px'});
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});
function getSelectedRowName() {
	//获取审核状态
	var grid = $("#jqGrid").getGridParam("selrow");
	var rowName = $("#jqGrid").getCell(grid,"pName");
//限制多选
	var selectedStatus = $("#jqGrid").getGridParam("selarrrow");
	if(selectedStatus.length > 1){
		return ;
	}
	return rowName;
}

function getSelectedRowId() {
	//获取审核状态
	var grid = $("#jqGrid").getGridParam("selrow");
	var rowId = $("#jqGrid").getCell(grid,"pId");
//限制多选
	var selectedStatus = $("#jqGrid").getGridParam("selarrrow");
	if(selectedStatus.length > 1){
		return ;
	}
	return rowId;
}