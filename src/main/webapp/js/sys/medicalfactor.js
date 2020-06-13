$(function () {
    $("#jqGrid").jqGrid({
        url: '../medicalfactor/list',
        datatype: "json",
        colModel: [			
			{ label: 'dId', name: 'dId', width: 50, key: true,hidden:true },
			{ label: '名称', name: 'dName', width: 80 }, 			
			{ label: '系数', name: 'dFactor', width: 80 }			
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
			var dId = getSelectedRow();
			if(dId == null){
				return ;
			}
			
			location.href = "medicalfactor_add.html?dId="+dId;
		},
		del: function (event) {
			var dIds = getSelectedRows();
			if(dIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../medicalfactor/delete",
				    data: JSON.stringify(dIds),
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