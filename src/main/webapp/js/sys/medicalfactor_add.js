var dId = T.p("dId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		medicalFactor:{}
	},
	created: function() {
		if(dId != null){
			this.title = "修改";
			this.getInfo(dId)
		}
    },
	methods: {
		getInfo: function(dId){
			$.get("../medicalfactor/info/"+dId, function(r){
                vm.medicalFactor = r.medicalFactor;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.medicalFactor.dId == null ? "../medicalfactor/save" : "../medicalfactor/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.medicalFactor),
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