var pId = T.p("pId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		personnel:{
			pName:null,
			pStatus:null,
			pDepartment:null,
			pNumber:null
		},
		personnelStatus:{},
	},
	created: function() {
		if(pId != null){
			this.title = "修改";
			this.getInfo(pId)
		}
		$.get("../personnelstatus/list",function (r) {
			vm.personnelStatus=r.personnelStatusList;
		})
    },
	methods: {
		getInfo: function(pId){
			$.get("../personnel/info/"+pId, function(r){
                vm.personnel = r.personnel;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.personnel.pId == null ? "../personnel/save" : "../personnel/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.personnel),
			    success: function(r){
			    	if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							vm.back();
						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});