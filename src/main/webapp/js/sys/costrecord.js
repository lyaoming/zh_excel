$(function () {
    $("#jqGrid").jqGrid({
        url: '../costrecord/list',
        datatype: "json",
        colModel: [			
			{ label: 'cId', name: 'cId', width: 50, key: true,hidden:true },
			{ label: '名字', name: 'pName', width: 150 },
			{ label: '状态', name: 'sName', width: 150 ,formatter:function (value) {
					if(value=="在职"){
						return "<span style='color:green;'>"+value+"</span>";
					}else if(value=="退休"){
						return "<span style='color:red;'>"+value+"</span>";
					}else{
						return "<span style='color:blue;'>"+value+"</span>";
					}

				}},
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
			{ label: '年份', name: 'year', width: 150 },
			{ label: '备注', name: 'remark', width: 150 }
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
		medicalFactor:{},
		pName:null,
		classion:null,
		year:null,
	},
	created:function(){
		$.get("../medicalfactor/list",function (r) {
			vm.medicalFactor=r.medicalFactorList;
		})
	},
	methods: {
		query:function(event){
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {
					'pName': vm.pName,
					'classion': vm.classion,
					"year":$("#year").val()
				},
				page: 1
			}).trigger("reloadGrid");
		},
		update: function (event) {
			var cId = getSelectedRow();
			if(cId == null){
				return ;
			}
			$.get("../costrecord/querylast/"+cId,function (r) {
				if(r.code==0){
					location.href = "costrecord_add.html?cId="+cId;
				}else{
					layer.msg(r.msg,{offset:'100px'})
				}
			});

		},
		del: function (event) {
			var cIds = getSelectedRows();
			if(cIds == null){
				return ;
			}
			
			layer.confirm('确定要删除选中的记录？', {offset:'100px'},function(){
				$.ajax({
					type: "POST",
				    url: "../costrecord/delete",
				    data: JSON.stringify(cIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.alert(r.msg,{offset:'100px'});
						}
					}
				});
			});
		}
	}
});