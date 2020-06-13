$(function () {
    $("#jqGrid").jqGrid({
        url: '../personnel/list',
        datatype: "json",
        colModel: [			
			{ label: 'pId', name: 'pId', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'pName', width: 80 }, 			
			{ label: '部门', name: 'pDepartment', width: 80 },
			{ label: '状态', name: 'sName', width: 150 ,formatter:function (value) {
					if(value=="在职"){
						return "<span style='color:green;'>"+value+"</span>";
					}else if(value=="退休"){
						return "<span style='color:red;'>"+value+"</span>";
					}else{
						return "<span style='color:blue;'>"+value+"</span>";
					}

				}},
			{ label: '身份证号', name: 'pNumber', width: 80 }
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		pName:null,
		personnelStatus:null,
		pStatus:null,
	},
	created:function(){
		$.get("../personnelstatus/list",function (r) {
			vm.personnelStatus=r.personnelStatusList;
		});
	},
	methods: {
		update: function (event) {
			var pId = getSelectedRow();
			if(pId == null){
				return ;
			}
			
			location.href = "personnel_add.html?pId="+pId;
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
		del: function (event) {
			var pIds = getSelectedRows();
			if(pIds == null){
				return ;
			}
			
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../personnel/delete",
				    data: JSON.stringify(pIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.msg(r.msg,{offset:'100px'});
						}
					}
				});
			});
		}
	}
});