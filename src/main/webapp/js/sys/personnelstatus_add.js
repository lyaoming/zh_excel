var sId = T.p("sId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		personnelStatus:{}
	},
	created: function() {
		if(sId != null){
			this.title = "修改";
			this.getInfo(sId)
		}
    },
	methods: {
		getInfo: function(sId){
			$.get("../personnelstatus/info/"+sId, function(r){
                vm.personnelStatus = r.personnelStatus;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.personnelStatus.sId == null ? "../personnelstatus/save" : "../personnelstatus/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.personnelStatus),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});