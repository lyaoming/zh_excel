$(function () {
    $("#jqGrid").jqGrid({
        url: '../majordisease/list',
        datatype: "json",
        colModel: [			
			{ label: 'iId', name: 'iId', width: 50, key: true,hidden:true },
			{ label: '名称', name: 'iName', width: 80 }, 			
			{ label: '最小值(元)', name: 'min', width: 80 },
			{ label: '最大值(元)', name: 'max', width: 80 },
			{ label: '系数', name: 'iFactor', width: 80 }			
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
		
	},
	methods: {
		update: function (event) {
			var iId = getSelectedRow();
			if(iId == null){
				return ;
			}
			
			location.href = "majordisease_add.html?iId="+iId;
		},
		del: function (event) {
			var iIds = getSelectedRows();
			if(iIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../majordisease/delete",
				    data: JSON.stringify(iIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});