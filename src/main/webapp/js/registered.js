

!function (win ,$) {

    if (YDUI.device.isWeixin) {
        var vm = new Vue({
            el:"#mform",
            data:{
                registered:{
                    account:"",
                    name:"",
                    org:"",
                    idtype:'',
                    orgNo:''
                }
            },
            created:function(){

            },
            methods:{
                selectIdType:function (type) {
                    vm.registered.idtype = type;
                },
                submit:function () {
                    var dialog = YDUI.dialog;
                    var submitType = true;

                    vm.registered.org =  $("#org").val();
                    vm.registered.idtype =  $("#idtype").val();

                    if (vm.registered.account=="") {
                        $("#account").next().html("工号/学号不能为空！");
                        submitType = false;
                        return;
                    }$("#account").next().html("");
                    if (!/^\d{8,11}$/.test(vm.registered.account)) {
                        $("#account").next().html("工号/学号不规范！");
                        submitType = false;
                        return;
                    }$("#account").next().html("");
                    if (vm.registered.name=="") {
                        $("#name").next().html("姓名不能为空！");
                        submitType = false;
                        return;
                    }$("#name").next().html("");
                    if(vm.registered.org==""){
                        $("#org").next().html("组织机构不能为空！");
                        submitType = false;
                        return;
                    }$("#org").next().html("");
                    if(vm.registered.idtype==""){
                        $("#idtype").next().html("身份不能为空！");
                        submitType = false;
                        return;
                    }$("#idtype").next().html("");

                    var openId = YDUI.util.getQueryString("openid");

                    if(submitType){
                        if(openId==null||openId=='') {
                            dialog.toast('请在公众号进行绑定！', 'none', 2000);
                            return;
                        }else{
                            //判断工号是否已经存在
                            $.ajax({
                                url:'../smart_lab/useropenid/isExist',
                                method:'get',
                                data:{
                                    username:vm.registered.account
                                }
                            }).then(res=>{
                                if (res.code===0){
                                    $.ajax({
                                        url:"../smart_lab/useropenid/save",
                                        type:"post",
                                        dataType:"json",
                                        contentType:"application/json",
                                        data:JSON.stringify({
                                            name:vm.registered.name,
                                            username:vm.registered.account,
                                            openId:openId,
                                            role:vm.registered.idtype=="老师"?1:2,
                                            departmentNo:vm.registered.orgNo
                                        }),
                                        success:function (r) {
                                            dialog.loading.close();//移除loading
                                            if (res.code==0){
                                                dialog.notify('等待审核，审核结果将发到公众号后台', 2000, function(){
                                                    window.location = "appoint.jsp?openid="+openId;
                                                });
                                            }else{
                                                //错误提示框
                                                dialog.toast('出错了！', 'error', 2000, function () {});
                                                return;
                                            }
                                        }
                                    });
                                }else{
                                    dialog.alert('用户名已存在')
                                }
                            })
                        }
                    }
                }
            }
        });

        var UplinkData = toTree(handleWheels());
        /**
         * 实例化选择器
         */
        new MobileSelect({
            trigger: '#org',
            title: '组织机构选择',
            wheels: [{data : UplinkData}],
            transitionEnd:function(indexArr, data){},
            callback:function(indexArr, data){
                console.log(data[data.length-1])
                var value = "";
                for(var i=0;i<data.length;i++){
                    if(i==0) value =  data[i].value;
                    else {
                        if (data[i].value !== '') {
                            value = value + "-" + data[i].value;
                        }
                    }
                }
                vm.registered.org=value;
                vm.registered.orgNo = data[data.length-1].id;
            }
        })
        /**
         * 加载所有组织机构信息
         * @returns {Array}
         */
        function handleWheels() {
            var departments = null;
            //加载所有的部门信息
            $.ajax({
                type: "get",
                url: "hrdepartment/list",
                data: "",
                async: false,
                success: function (r) {
                    res = eval("(" + r + ")");
                    if (res.code == 0) {
                        departments = res.departments;
                    } else {
                        alert("加载数据失败-_-")
                    }
                }
            });

            var tmp = [];
            for(var i=0;i<departments.length;i++){
                var d = departments[i];
                tmp.push({
                    id:d.departmentNo,
                    value:d.departmentName,
                    nodeId:d.nodeId,
                    parentId:d.parentId
                });
            }
            return tmp;
        }

        /**
         * 处理树状结构数据
         * @param data
         * @returns {Array}
         */
        function toTree(data) {
            // 删除 所有 children,以防止多次调用
            // data.forEach(function (item) {
            //     delete item.childs;
            // });
            // 将数据存储为 以 id 为 KEY 的 map 索引数据列
            var map = {};
            data.forEach(function (item) {
                map[item.nodeId] = item;
            });
            var val = [];
            data.forEach(function (item) {
                // 以当前遍历项，的pid,去map对象中找到索引的id
                var parent = map[item.parentId];
                // 好绕啊，如果找到索引，那么说明此项不在顶级当中,那么需要把此项添加到，他对应的父级中
                if (parent) {
                    if(parent.childs){
                        parent.childs.push(item)
                    }else{
                        parent.childs = []
                        parent.childs.push({
                            id:'0000',
                            value:'',
                            nodeId:item.nodeId-1,
                            parentId:item.parentId
                        })
                        parent.childs.push(item)
                    }

                    // (parent.childs || ( parent.childs = [] )).push(item);
                } else {
                    //如果没有在map中找到对应的索引ID,那么直接把 当前的item添加到 val结果集中，作为顶级
                    val.push(item);
                }
            });
            return val;
        }
    }else{
        YDUI.dialog.alert('请在公众号进行操作')
    }


}(window,jQuery);
