<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
  <meta charset="UTF-8">
    <title>wo-link</title>
    <link rel="stylesheet" href="css/tablesorter/theme.blue.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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

	.row {
		margin-top: 10px;
		margin-bottom: 10px;
	}
	
	#myTable th{
		font-size: 25px;
	}
	
	.form-control {
		height : 58px;
		font-size: 25px;
	}
	
	.btn {
		font-size: 20px;
		padding: 15px 20px;
	}
	
	.container-fluid {
		margin-top : 300px;
	}
	
	.tablesorter-blue .header, .tablesorter-blue .tablesorter-header {
		background-image: none;
	}
  </style>
  
 
  <script>
   /*
	var useragent = navigator.userAgent;
    if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
        // 这里警告框会阻塞当前页面继续加载
        alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
        // 以下代码是用javascript强行关闭当前页面
        var opened = window.open('about:blank', '_self');
        opened.opener = null;
        opened.close();
    }
     */
  </script>

</head>

<body>

<div>
    
    <div class="container-fluid">
	    <!-- <div class="row ">
		 	<div class="col-xs-2 col-md-3">
				<input type="text" id="set_temp" class="form-control" disabled="disabled"> 
		    </div>
		    <div class="col-xs-3 col-md-2">
		  		<button type="button" id="set_temp_button" class="btn btn-default btn-lg"> 设定温度 </button>
			</div>
		</div> -->
		<div class="row">	
			<div class="col-xs-2 col-md-3 ">
				<input type="text" id="get_temp" class="form-control" disabled="disabled"> 
		    </div>
		    <div class="col-xs-3 col-md-2">
		  		<button type="button" id="get_temp_button" class="btn btn-primary btn-lg"> 显示温度 </button>
			</div>
	 	</div>
	    <div class="row">	
			<div class="col-xs-4 col-md-4">
				<input type="text" id="get_timestamp" class="form-control" disabled="disabled"> 
		    </div>
		    <div class="col-xs-3 col-md-2">
		  		<button type="button" id="get_timestamp_button" class="btn btn-primary btn-lg"> 显示时间戳 </button>
			</div>
	 	</div>
	 	
	 	<!-- <div class="row">	
			<div class="col-xs-3 col-md-3">
				<input type="text" id="heat_duration" class="form-control" disabled="disabled"> 
		    </div>
		    <div class="col-xs-3 col-md-2">
		  		<button type="button" id="get_heat_duration_button" class="btn btn-primary"> 加热时长 </button>
			</div>
	 	</div> -->

    </div>    
    

    <div id="table-dev">

      <table id="myTable" class="table2 tablesorter tablesorter-blue" cellpadding="1" width="100%" style="font-size: 35px">
        <thead>
        <tr>
          <th width="25%">执行操作</th>
          <th width="25%">响应状态</th>
          <th width="50%">时间</th>
        </tr>
        </thead>
        
        <tbody>
         <!--   <tr>
			<td>a </td>
			<td>10 </td>
			<td>1 </td>
		  <tr>
			<td>b </td>
			<td>20 </td>
			<td>2 </td>
		  </tr> -->
		    <!-- <tr>
			 <td>c </td>
			<td>30 </td>
			<td>3 </td>
		</tr>  -->
 
        </tbody>
      </table>

    </div>

</div>

<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery.tablesorter.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script>
    var URL = {
    	testUrl : 'http://vhf93w.natappfree.cc',
    	baeUrl: 'http://ktsoft.duapp.com'
    };

	var requestUrl = URL.baeUrl;

	var requestInterval = false;
/*
	var timeout = setTimeout(function(){
			requestInterval = false;
			console.log(requestInterval);
	}, 1000);
*/
    $(document).ready(function () {
		
 		$("#set_temp_button").click(function () {
 			//if (!requestInterval) {
 			//	requestInterval = true;
 				
 			//	console.log(requestInterval);
				disableButtons();
            	$.ajax({
                url : requestUrl + '/devicecontrol/operations/temperature/setting',
                data : null,
                success : function (res) {
                    tableUpdate(res.data);
                    $("#set_temp").val(res.data.tmp);
                    activateButtons()
                },
                	dataType: 'json'
           	    });
 			//} 
			
			/*
			//仍在timeout运行期内
			if (timeout) {
				console.log("timeout" + requestInterval);
			} else {
				
			}
			*/
        });
        
        $("#get_temp_button").click(function () {
        	disableButtons();
            $.ajax({
                url : requestUrl + '/devicecontrol/operations/temperature/getting',
                data : null,
                success : function (res) {
                    //    if (res.code == 0) {
                    tableUpdate(res.data);
                    $("#get_temp").val(res.data.tmp);
                    activateButtons()
                },
                dataType: 'json'
            });
        });

		$("#get_timestamp_button").click(function() {
			disableButtons();
			$.ajax({
				url : requestUrl + '/devicecontrol/operations/timestamp/getting',
				data : null,
				success : function (res) {
				//	alert(JSON.stringify(res.data));
					tableUpdate(res.data);
					$("#get_timestamp").val(res.data.date);
					activateButtons()
				},
				dataType: 'json'
			});
		});

		$("#get_heat_duration_button").click(function () {
            disableButtons();
            $.ajax({
                url : requestUrl + '/devicecontrol/operations/heatduration/getting',
                data : null,
                success : function (res) {
                    //    if (res.code == 0) {
                    tableUpdate(res.data);
                    $("heat_duration").val(res.data.duration);
                    activateButtons()
                },
                dataType: 'json'
            });
        });

		function disableButtons() {
			$("#set_temp_button").attr("disabled", "disabled");
			$("#get_temp_button").attr("disabled", "disabled");
			$("#get_timestamp_button").attr("disabled", "disabled");
		}
		
		function activateButtons() {
			$("#set_temp_button").removeAttr("disabled");
			$("#get_temp_button").removeAttr("disabled");
			$("#get_timestamp_button").removeAttr("disabled");
		}

        $(".table2").tablesorter();

        function tableUpdate(data) {

			var html = "<tr><td>"+  data.commandType + "</td>";
			html += "<td>" + data.responseStatus +"</td>";
			html += "<td>" + data.time +"</td>" + "</td></tr>";
			
            var row_len = $(".table2")[0].rows.length;

            if ( row_len > 11) {
                $(".table2")[0].deleteRow(row_len-1)
            }

            // append new html to table body
            $(".table2 tbody").append(html);
            // let the plugin know that we made a update
            $(".table2").trigger("update");
            // set sorting column and direction, this will sort on the first and third column
            var sorting = [[2,1]];
            // sort on the first column
            $(".table2").trigger("sorton",[sorting]);
            return false;
        }
/*
        var getDataInternal = {
            url : URL.testUrl + '/data-search',
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
		*/
    })
</script>

</body>
</html>