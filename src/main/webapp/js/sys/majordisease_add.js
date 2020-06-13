var iId = T.p("iId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		majorDisease:{}
	},
	created: function() {
		if(iId != null){
			this.title = "修改";
			this.getInfo(iId)
		}
    },
	methods: {
		getInfo: function(iId){
			$.get("../majordisease/info/"+iId, function(r){
                vm.majorDisease = r.majorDisease;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.majorDisease.iId == null ? "../majordisease/save" : "../majordisease/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.majorDisease),
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