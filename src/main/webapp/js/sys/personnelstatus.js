$(function () {
    $("#jqGrid").jqGrid({
        url: '../personnelstatus/list',
        datatype: "json",
        colModel: [			
			{ label: 'sId', name: 'sId', width: 50, key: true,hidden:true},
			{ label: '人员状态', name: 'sName', width: 80,formatter:function (value) {
					if(value=="在职"){
						return "<label class='label label-success'>"+value+"</label>";
					}else if(value=="退休"){
						return "<label class='label label-danger'>"+value+"</label>";
					}else{
						return "<label class='label label-primary'>"+value+"</label>";
					}
				} }
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
			var sId = getSelectedRow();
			if(sId == null){
				return ;
			}
			
			location.href = "personnelstatus_add.html?sId="+sId;
		},
		del: function (event) {
			var sIds = getSelectedRows();
			if(sIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../personnelstatus/delete",
				    data: JSON.stringify(sIds),
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