<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <style>

    .main_button {
      /*width : 100%;*/
      /*padding: 0.2em;*/
      background-color: #5eb95e;
      /*font-size: 1.4em;*/
      background-image: linear-gradient(to bottom, #62c462, #57a957);
      background-repeat: repeat-x;
      color: #ffffff;
      text-align: center;
      text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
      border-radius: 0.3em;
    }

    .tablesorter thead tr th{
      text-align: center;
    }

    .tablesorter tbody tr td{
      text-align: center;
    }

    #button_div {
      margin: 10px;
    }

    .ul_button{
      list-style: none;
      padding-left: 10px;
    }

    .ul_button li {
      float: left;
      padding: 10px;
    }


  </style>

    <title>EasyIoT</title>


    <link rel="stylesheet" href="css/tablesorter/theme.blue.css">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


</head>

<body>

<div>

  <ul class="ul_button">
    <!--<li><button type="button" id="dev_batch_reg_button" class="btn btn-primary"> 批量注册设备</button></li>-->
    <li ></li>

    <li>
      <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
          设备-科7<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
          <li><a href="#">设备-科7</a></li>
        </ul>
      </div>
    </li>
    <li> <div class="input-group"><input type="text" id="input_cmd" class="form-control" placeholder="请输入16进制字符指令" > </div></li>
    <li><button type="button" id="dev_control_button" class="btn btn-primary"> 发送指令 </button></li>
  </ul>





  <div id="table-dev">

      <table id="myTable" class="table2 tablesorter tablesorter-blue" cellpadding="1" width="100%">
        <thead>
        <tr>
          <th width="15%">设备号</th>
          <th width="10%">设备创建时间</th>
          <th width="10%">最新消息时间</th>
          <th width="10%">设备电量</th>
          <th width="10%">信号强度</th>
          <th width="45%">其他信息</th>
        </tr>
        </thead>
        <tbody>
          <tr>

          </tr>
        </tbody>
      </table>

  </div>

</div>

<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery.tablesorter.js"></script>

<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script>
    var URL = {
        testUrl : 'http://kardel.ngrok.xiaomiqiu.cn/easy-iot',
        serverUrl : 'http://ketansoft.com/easy-iot'
    };

    $(document).ready(function () {
        $("#dev_batch_reg_button").click(function () {
            $.ajax({
                //     url: 'http://localhost:8080/WCOA/dev-batch-reg-result',
                url : URL.testUrl + '/dev-batch-reg-result',
                data: { d:1},
                success: function (res) {
                    alert(res.msg);
                },
                dataType: 'json'
            });
        });




        $("#dev_control_button").click(function () {

            var cmd = $("#input_cmd").val();
            if(cmd === '') {
                alert("指令不能为空");
                return ;
            }

            $.ajax({
                url : URL.serverUrl + '/cmd-response-callback',
                data :{
                    devSerial : '863703030732767',
                    method : 'QQ',
                    params : { ser1 : cmd }
                },
                success : function (res) {
                    //    if (res.code == 0) {
                    alert(res.msg);
                    //     }

                },
                dataType: 'json'
            });
        });

        $(".table2").tablesorter();


        function tableUpdate(data) {

            var html = "<tr><td>"+ data.devSerial + "</td>";
            html += "<td>" + data.createTime +"</td>";
            html += "<td>" + data.lastMessageTime + "</td>";
            html += "<td>" + data.batteryLevel + "</td>";
            html += "<td>" + data.signalStrength + "</td>";
            html += "<td>" + JSON.stringify(data.serviceData) + "</td></tr>";

            //       var table =document.getElementById("myTable");
            //       var rows = table.rows.length;
            var row_len = $(".table2")[0].rows.length;

            if ( row_len > 11) {
                $(".table2")[0].deleteRow(row_len-1)
            }

            // append new html to table body
            $(".table2 tbody").append(html);
            // let the plugin know that we made a update
            $(".table2").trigger("update");
            // set sorting column and direction, this will sort on the first and third column
            var sorting = [[4,1]];
            // sort on the first column
            $(".table2").trigger("sorton",[sorting]);
            return false;
        }

        var data = {
            devSerial : "",
            createTime : "",
            lastMessageTime : "",
            batteryLevel : "",
            signalStrength : "",
            serviceData : "",

            setData : function (res) {
                this.devSerial = res.devSerial;
                this.createTime = res.createTime;
                this.lastMessageTime = res.lastMessageTime;
                this.batteryLevel = res.batteryLevel;
                this.signalStrength = res.signalStrength;
                this.serviceData = res.serviceData;
            }
        };

        var getDataInternal = {
            url : URL.serverUrl + '/data-search',
            dataType : 'json',

            success : function (res) {
                if (res.code == 0) {
                    //         alert(JSON.stringify(res));

                    tableUpdate(res);

                }
                //          $.ajax(getDataInternal);
            },
            error : function (res) {
                //          $.ajax(getDataInternal);
                //     alert(JSON.stringify(res));
            }
        };
        window.setInterval(
            function(){$.ajax(getDataInternal)}
            , 1000);




        /*
        function devBatchReg() {
            $.ajax({
                //     url: 'http://localhost:8080/WCOA/dev-batch-reg-result',
                url : 'http://kardel.ngrok.xiaomiqiu.cn/WCOA/dev-batch-reg-result',
                data: { d:1},
                success: function (res) {
                    alert(res.msg);
                },
                dataType: 'json'
            });
        }

        function devControl() {
            $.ajax({
                url : 'http://kardel.ngrok.xiaomiqiu.cn/WCOA/cmd-response-callback',
                data :{
                    devSerial : '863703030732767',
                    method : 'QQ',
                    params : { ser1 : '333435'}
                },
                success : function (res) {
                    //    if (res.code == 0) {
                    alert(res.msg);
                    //     }

                },
                dataType: 'json'
            });
        }
        */
    })
</script>

</body>
</html>