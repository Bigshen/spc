$(document).ready(function () {

    var reg=/^[0-9]+.?[0-9]*$/;
    var  isok=1;
    initEleScaleMeasureRequire();
    function AllClear() {
        $("#fl-Factory").find("option:selected").text("");
        $("#fl-Factory").empty();
        $("#fl-Link").find("option:selected").text("");
        $("#fl-Link").empty();
        $("#part-Verion").find("option:selected").text("");
        $("#part-Verion").empty();
        $("#measure-Status").find("option:selected").text("");
        $("#measure-Status").empty();
        $("#measure-Frequency").find("option:selected").text("");
        $("#measure-Frequency").empty();
        $("#pro-Name").find("option:selected").text("");
        $("#pro-Name").empty();
    }

    function initEleScaleMeasureRequire() {
        AllClear();
        $.ajax({
            url: "../EleScaleMeasure/ShowDropdownBoxFactory",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#fl-Factory").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#fl-Factory").append("<option>" + obj[i].FACTORY + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框廠區值失敗，失敗原因：" + err);
            }
        })//加載廠區下拉框值
        $.ajax({
            url: "../EleScaleMeasure/ShowDropdownBoxLine",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#fl-Link").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#fl-Link").append("<option>" + obj[i].LINE_NUMBER + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框綫別值失敗，失敗原因：" + err);
            }
        })//加載綫別下拉框值
        $.ajax({

            url: "../EleScaleMeasure/ShowDropdownBoxProjectName",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#pro-Name").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].PROJECT_NAME!=null && obj[i].PROJECT_NAME!="") {
                            $("#pro-Name").append("<option>" + obj[i].PROJECT_NAME + "</option>");
                        }
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框專案名稱值失敗，失敗原因：" + err);
            }
        })//加載專案名稱下拉框值
        $.ajax({
            url: "../EleScaleMeasure/ShowDropdownBoxPartNumber",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#part-Verion").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#part-Verion").append("<option>" + obj[i].PART_NUMBER_V + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框料號版本值失敗，失敗原因：" + err);
            }
        })//加載料號版本下拉框值
        $.ajax({
            url: "../EleScaleMeasure/ShowDropdownBoxStatus",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#measure-Status").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#measure-Status").append("<option>" + obj[i].STATUS + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框量測階段值失敗，失敗原因：" + err);
            }
        })//加載測量階段下拉框值


        $("#fl-Factory").change(function () {
            var Factory = $("#fl-Factory").find("option:selected").text();
            $("#fl-Link").find("option:selected").text("");
            $("#fl-Link").empty();
            if (Factory == '') {
                alert("請選擇廠區！");
                return;
            }
            $.ajax({
                url: "../EleScaleMeasure/FactoryDropdownBoxIf",
                type: 'post',
                async: false,
                data: {Factory: Factory},
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $("#fl-Link").append("<option></option>");
                        for (var i = 0; i < obj.length; i++) {
                            $("#fl-Link").append("<option>" + obj[i].LINE_NUMBER + "</option>");
                        }
                    }
                },
                error: function (err) {
                    alert("下拉框廠區查詢異常：" + err.status);
                }
            })
        });//廠區下拉框點擊事件
        $("#pro-Name").change(function () {
            var ProName = $("#pro-Name").find("option:selected").text();
            var Factory = $("#fl-Factory").find("option:selected").text();
            if (Factory == '') {
                alert("請先選擇廠區！");
                return;
            }
            $("#part-Verion").find("option:selected").text("");
            $("#part-Verion").empty();
            $("#measure-Status").find("option:selected").text("");
            $("#measure-Status").empty();
            $("#measure-Frequency").find("option:selected").text("");
            $("#measure-Frequency").empty();
            $.ajax({
                url: "../EleScaleMeasure/ShowDropdownBoxPartNumbers",
                type: 'post',
                async: false,
                data: {ProName:ProName},
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $("#part-Verion").append("<option></option>");
                        for (var i = 0; i < obj.length; i++) {
                            $("#part-Verion").append("<option>" + obj[i].PART_NUMBER_V + "</option>");
                        }
                    }
                },
                error: function (err) {
                    alert("獲取料號版本下拉框值查詢異常：" + err.status);
                }
            })//獲取料號版本下拉框值

        })//綫別點擊事件
        $("#measure-Status").change(function () {
            var ProName = $("#pro-Name").find("option:selected").text();
            var PartVerion = $("#part-Verion").find("option:selected").text();
            var MeasureStatus=$("#measure-Status").find("option:selected").text();
                if (MeasureStatus==''){
                    $("#measure-Frequency").find("option:selected").text("");
                    $("#measure-Frequency").empty();
                    return;
                }
                $.ajax({
                    url: "../EleScaleMeasure/MeasureStatusDropdownBoxIf",
                    type: 'post',
                    async: false,
                    data: {ProName: ProName,PartVerion:PartVerion,MeasureStatus:MeasureStatus},
                    success: function (result) {
                        var StatusCode = result.StatusCode;
                        var m = result.message;
                        if (StatusCode == "500") {
                            alert(m);
                        } else if (StatusCode == "200") {
                            $("#measure-Frequency").find("option:selected").text("");
                            $("#measure-Frequency").empty();
                            var obj = eval(m);
                            $("#measure-Frequency").append("<option></option>");
                            for (var i = 0; i < obj.length; i++) {
                                $("#measure-Frequency").append("<option>" + obj[i].Frequency + "</option>");
                            }
                        }
                    },
                    error: function (err) {
                        alert("獲取量測頻率下拉框值查詢異常：" + err.status);
                    }
                })//獲取量測頻率下拉框值
                return;


        })//量測階段下拉框點擊事件
        $("#QueryOK").click(function () {
            var Link = $("#fl-Link").find("option:selected").text();
            var Factory = $("#fl-Factory").find("option:selected").text();
            var PartVerion = $("#part-Verion").find("option:selected").text();
            var MeasureStatus=$("#measure-Status").find("option:selected").text();
            var MeasureFrequency=$("#measure-Frequency").find("option:selected").text();
            var JieCi=$("#JieCi").find("option:selected").text();
            var MeasureEmpID=$("#MeasureEmpID").val();
            var TicketNumber=$("#EnterStorageNum").val();//入庫單號

            if(Link=='' || Factory=='' || PartVerion=='' || MeasureStatus=='' || MeasureFrequency=='' || JieCi=='' || MeasureEmpID==''){
                alert("*為必填欄位,請確實填寫");
            }else if (MeasureStatus=='初件檢驗' && TicketNumber!=''){
                alert('初件檢驗量測,請不要填寫"入庫單號"');
            }else if (MeasureStatus=='巡迴檢驗' && TicketNumber!=''){
                alert('巡迴檢驗量測,請不要填寫"入庫單號"');
            }else {
                isok=0;
                $(".a").css("display","block");
                initEleScaleMeasureResult();
                return;
            }


        })//查詢按鈕點擊事件
        $("#part-Verion").change(function () {
            var partVerion = $("#part-Verion").find("option:selected").text();
            if (partVerion==''){
                return;
            }

            $("#measure-Frequency").find("option:selected").text("");
             $("#measure-Frequency").empty();
                $("#measure-Status").find("option:selected").text("");
                $("#measure-Status").empty();
                $.ajax({
                    url: "../EleScaleMeasure/ShowDropdownBoxStatuss",
                    type: 'post',
                    async: false,
                    data: {PartVerion:partVerion},
                    success: function (result) {
                        var StatusCode = result.StatusCode;
                        var m = result.message;
                        if (StatusCode == "500") {
                            alert(m);
                        } else if (StatusCode == "200") {
                            var obj = eval(m);
                            $("#measure-Status").append("<option></option>");
                            for (var i = 0; i < obj.length; i++) {
                                $("#measure-Status").append("<option>" + obj[i].STATUS + "</option>");
                            }
                        }
                    },
                    error: function (err) {
                        alert("獲取量測階段下拉框值查詢異常：" + err.status);
                    }
                })//獲取量測階段下拉框值
                return;

        })//料號版本下拉框稱事件
    }

    function initEleScaleMeasureResult() {
        var Factory = $("#fl-Factory").find("option:selected").text();//廠區
        var Link29 = $("#fl-Link").find("option:selected").text();//綫別
        var ProName = $("#pro-Name").find("option:selected").text();//專案名稱
        var PartVerion = $("#part-Verion").find("option:selected").text();//版本料號
        var MeasureStatus=$("#measure-Status").find("option:selected").text();//量測階段
        var MeasureFrequency=$("#measure-Frequency").find("option:selected").text();//量測頻率
        var JieCi=$("#JieCi").find("option:selected").text();//節次
        var TicketNumber=$("#EnterStorageNum").val();//入庫單號
        var MeasureMachineNum=$("#MeasureMachineNum").val();//量测幾台號
        var MachineNum=$("#MachineNum").val();//幾台號
        var MeasureEmpID=$("#MeasureEmpID").val();//員工工號
            $.ajax({
                url: "../EleScaleMeasure/ShowEleScaleMeasureResult",
                type: 'post',
                async: false,
                data: {
                    Factory: Factory,
                    Link: Link29,
                    ProjectName:ProName,
                    PartVersion:PartVerion,
                    Status:MeasureStatus,
                    Frequency:MeasureFrequency,
                    Period:JieCi,
                    TicketNumber:TicketNumber,
                    MachineNumber:MachineNum,
                    Personnel_ID:MeasureEmpID,
                    MeasurementNumber:MeasureMachineNum
                },
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {

                        var obj = eval(m);
                        $('#EleScaleManageTable tbody').html('');
                        $('#EleScaleManageTable thead').html('');
                        var thead="\t\t<tr><th style='text-align: center' colspan='13'>天平重量數據輸入</th></tr><tr>\n" +
                            "\t\t\t<th>尺寸/工站號</th>\n" +
                            "\t\t\t<th>檢驗項目</th>\n" +
                            "\t\t\t<th>尺寸</th>\n" +
                            "\t\t\t<th>上限</th>\n" +
                            "\t\t\t<th>下限</th>\n" +
                            "\t\t\t<th>頻率</th>\n" +
                            "\t\t\t<th>樣品數</th>\n" +
                            "\t\t\t<th>測量-1</th>\n" +
                            "\t\t\t<th>測量-2</th>\n" +
                            "\t\t\t<th>測量-3</th>\n" +
                            "\t\t\t<th>測量-4</th>\n" +
                            "\t\t\t<th>測量-5</th>\n" +
                            "\t\t\t<th>備注</th>\n" +
                            "\t\t</tr>";
                        var A6=1;

                        for (var i = 0; i < obj.length; i++) {


                            var tableContents = "";
                            tableContents += '<tr>' +
                                '<td>' + obj[i].WORKSHOP + '</td>'
                                + '<td>' + obj[i].INSPECTION_ITEM + '</td>'
                                + '<td>' + obj[i].NOMINAL_DIM + '</td>'
                                + '<td><input type="hidden" name="Upper_'+A6+'"  id="Upper_'+A6+'"  value="'+ obj[i].UPPER_DIM +'">' + obj[i].UPPER_DIM + '</td>'
                                + '<td><input type="hidden" name="Lower_'+A6+'"  id="Lower_'+A6+'"  value="'+ obj[i].LOWER_DIM +'">' + obj[i].LOWER_DIM + '</td>'
                                + '<td>' + obj[i].FREQUENCY + '</td>'

                                + '<td align=center width="60px" style="font-size:14px;"> ' +
                                '<select name="MeasureNums_'+A6+'" id="MeasureNums_'+A6+'" class="cy"> ' +
                                '<option value="1">1</option>' +
                                ' <option value="2">2</option>' +
                                ' <option value="4">4</option> ' +
                                '<option value="5">5</option> ' +
                                '<option value="6">6</option> ' +
                                '<option value="8">8</option> ' +
                                '<option value="10">10</option>' +
                                ' </select></td>'

                                + '<td>點膠前(g):<input name="ADim1_'+A6+'" id="ADim1_'+A6+'" class="xqw" type="text" style="width: 50px"/> <br>'+
                                '點膠後(g):<input name="BDim1_'+A6+'" id="BDim1_'+A6+'" type="text" class="xqw" style="width: 50px"/><br> ' +
                                '量測值(mg):<span name="ABDim1_'+A6+'" id="ABDim1_'+A6+'"></span>' +
                                '<input type="hidden" name="H_ADim1_'+A6+'" id="H_ADim1_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="H_BDim1_'+A6+'" id="H_BDim1_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="Dim1_'+A6+'" id="Dim1_'+A6+'" class="xqwcy" style="width: 60px"/></td>'

                                + '<td>點膠前(g):<input name="ADim2_'+A6+'" id="ADim2_'+A6+'" class="xqw" type="text" style="width: 50px"/> <br>'+
                                '點膠後(g):<input name="BDim2_'+A6+'" id="BDim2_'+A6+'" type="text" class="xqw" style="width: 50px"/><br> ' +
                                '量測值(mg):<span name="ABDim2_'+A6+'" id="ABDim2_'+A6+'"></span>' +
                                '<input type="hidden" name="H_ADim2_'+A6+'" id="H_ADim2_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="H_BDim2_'+A6+'" id="H_BDim2_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="Dim2_'+A6+'" id="Dim2_'+A6+'" style="width: 60px"/></td>'

                                + '<td>點膠前(g):<input name="ADim3_'+A6+'" id="ADim3_'+A6+'" class="xqw" type="text" style="width: 50px"/> <br>'+
                                '點膠後(g):<input name="BDim3_'+A6+'" id="BDim3_'+A6+'" type="text" class="xqw" style="width: 50px"/><br> ' +
                                '量測值(mg):<span name="ABDim3_'+A6+'" id="ABDim3_'+A6+'"></span>' +
                                '<input type="hidden" name="H_ADim3_'+A6+'" id="H_ADim3_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="H_BDim3_'+A6+'" id="H_BDim3_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="Dim3_'+A6+'" id="Dim3_'+A6+'" style="width: 60px"/></td>'

                                + '<td>點膠前(g):<input name="ADim4_'+A6+'" id="ADim4_'+A6+'" class="xqw" type="text" style="width: 50px"/> <br>'+
                                '點膠後(g):<input name="BDim4_'+A6+'" id="BDim4_'+A6+'" type="text" class="xqw" style="width: 50px"/><br> ' +
                                '量測值(mg):<span name="ABDim4_'+A6+'" id="ABDim4_'+A6+'"></span>' +
                                '<input type="hidden" name="H_ADim4_'+A6+'" id="H_ADim4_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="H_BDim4_'+A6+'" id="H_BDim4_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="Dim4_'+A6+'" id="Dim4_'+A6+'" style="width: 60px"/></td>'

                                + '<td>點膠前(g):<input name="ADim5_'+A6+'" id="ADim5_'+A6+'" class="xqw" type="text" style="width: 50px"/> <br>'+
                                '點膠後(g):<input name="BDim5_'+A6+'" id="BDim5_'+A6+'" type="text" class="xqw" style="width: 50px"/><br> ' +
                                '量測值(mg):<span name="ABDim5_'+A6+'" id="ABDim5_'+A6+'"></span>' +
                                '<input type="hidden" name="H_ADim5_'+A6+'" id="H_ADim5_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="H_BDim5_'+A6+'" id="H_BDim5_'+A6+'" style="width: 60px"/>' +
                                '<input type="hidden" name="Dim5_'+A6+'" id="Dim5_'+A6+'" style="width: 60px"/></td>'

                                +'<td style="font-size:12px;width:70px;text-align:left">'
                                +'<textarea name="Inspection_Remark_"  id="Inspection_Remark_" style="width:100px;height:50px;"></textarea></td></tr>';

                            $('#EleScaleManageTable tbody').append(tableContents);
                            A6++;
                        }

                       $('#EleScaleManageTable tbody').append("");
                        $('#EleScaleManageTable thead').append(thead);

                        $("#submit_Upload").click(function () {
                            if (isok!=0){
                                return;
                            }
                            var faultList=[];
                            var istrue =0;
                            var ok_data_count=0;
                            $("#EleScaleManageTable").find('tbody').each(function (index, item) {
                                $(this).find('tr').each(function () {
                                    var tdArr = $(this).children();
                                    var MeasureSize = 0;
                                    var Measure = new Object();

                                    Measure.WorkShop = tdArr.eq(0).text();
                                    Measure.Inspection_Item = tdArr.eq(1).text();
                                    Measure.Nominal_Dim = tdArr.eq(2).text();
                                    Measure.Upper_Dim = tdArr.eq(3).text();
                                    Measure.Lower_Dim = tdArr.eq(4).text();
                                    Measure.Frequency = tdArr.eq(5).text();
                                    Measure.REMARK1 = tdArr.find("#Inspection_Remark_").val();

                                    Measure.ADim1_array=new Array();
                                    Measure.BDim1_array=new Array();
                                    Measure.Dim1_array=new Array();


                                    for(var i = 7;i<12;i++) {
                                        if (tdArr.eq(i).find('input').eq(0).val()=='' || tdArr.eq(i).find('input').eq(1).val()==''){
                                            MeasureSize = MeasureSize + 1;
                                            Measure.ADim1_array.push('');
                                            Measure.BDim1_array.push('');
                                            Measure.Dim1_array.push('');


                                        }else if (parseInt(tdArr.eq(i).find('input').eq(4).val()) <= parseInt(tdArr.eq(3).text())  && parseInt(tdArr.eq(i).find('input').eq(4).val()) >=parseInt(tdArr.eq(4).text())) {
                                            ok_data_count++;
                                            Measure.ADim1_array.push(tdArr.eq(i).find('input').eq(0).val());
                                            Measure.BDim1_array.push(tdArr.eq(i).find('input').eq(1).val());
                                            Measure.Dim1_array.push(tdArr.eq(i).find('input').eq(4).val());

                                        }else  if (tdArr.eq(i).find('input').eq(4).val()==''){
                                            istrue = istrue + 1;
                                        }
                                        else {
                                            MeasureSize = MeasureSize + 1;
                                            Measure.ADim1_array.push(tdArr.eq(i).find('input').eq(0).val());
                                            Measure.BDim1_array.push(tdArr.eq(i).find('input').eq(1).val());
                                            Measure.Dim1_array.push(tdArr.eq(i).find('input').eq(4).val());
                                        }

                                    }

                                    if(MeasureSize==0){
                                        Measure.Result="OK";
                                    }else{
                                        Measure.Result="NG";
                                    }
                                    faultList.push(Measure);
                                });
                            });

                            if (ok_data_count>0){
                                $.ajax({
                                    url:"../EleScaleMeasure/insertMeaTable",
                                    type:'post',
                                    contentType:"application/json",
                                    data:JSON.stringify(faultList),
                                    async:false,
                                    success:function(result){
                                    },
                                    error:function(err){
                                        alert("量測數據1上傳失敗");
                                    }
                                })
                            }

                            if(istrue==0){
                                $.ajax({
                                    url:"../EleScaleMeasure/btnMeasureTable",
                                    type:'post',
                                    contentType:"application/json",
                                    data:JSON.stringify(faultList),
                                    async:false,
                                    success:function(result){
                                        if(result.SPECStatus=='200'){
                                            $('.bottom').html('<h1>'+result.Message+'</h1>');
                                            setTimeout(function(){location.reload()},2000);
                                        }else{
                                            alert('請確認測量資料是否填寫正確');
                                            location.reload();
                                        }
                                    },
                                    error:function(err){
                                        alert("量測數據上傳失敗，請檢查所填寫信息是否正確");
                                    }
                                })
                            }else{
                                alert('請確認測量資料是否填寫正確');
                                location.reload();

                            }


                        });

                        $(".xqw").keyup(function () {
                            getdata(this.id);
                            //this.value=this.value.toUpperCase();
                        });

                        $(".cy").change(function () {
                            getdata(this.id);

                        });
             }
                },
                error: function (err) {
                    alert("查詢所有線別失敗，失敗原因：" + err);

                }
            })

        function getdata(id){
                //alert(id);
            var color;
            var key1=id.indexOf("_")+1;
            var key2=Number(key1);
            var key=id.substring(key2);
            var Upper_key="Upper_"+key;
            var Lower_key="Lower_"+key;
            var Upper_Dim=document.getElementById(Upper_key).value;
            var Lower_Dim=document.getElementById(Lower_key).value;
            var UpperDim = Number(Upper_Dim);
            var LowerDim = Number(Lower_Dim);


            //  讀取select的值
            var MeasureNums_key="MeasureNums_"+key;;
            var Measure_Nums =document.getElementById(MeasureNums_key).value

            var data =document.getElementById(id).value;
            var position=data.search("w");
            if(position==-1){position=data.search("W");}

            var new_data=data.substring(position+1);
            document.getElementById(id).value = new_data;

            var ok_id=id.substring(1);
            var A_id="A"+ok_id;
            var B_id="B"+ok_id;
            var AB_id="AB"+ok_id;
            var HA_id="H_"+A_id;
            var HB_id="H_"+B_id;

            var data_A=Number(document.getElementById(A_id).value);
            var data_B=Number(document.getElementById(B_id).value);

            if(data_B && data_A && data_B!=0 && data_A!=0){
                var ok_data0=1000*(data_B-data_A);
                var ok_data1=(parseFloat(Math.round(ok_data0*10000))/10000)/Measure_Nums;
                var ok_data=ok_data1.toFixed(2);

                if(ok_data>=LowerDim && ok_data<=UpperDim){color="t1"}
                else{color="t2"}

                document.getElementById(AB_id).innerHTML = ok_data;
                document.getElementById(AB_id).className = color;
                document.getElementById(ok_id).value = ok_data;
            }
            else{
                document.getElementById(AB_id).innerHTML = null;
                document.getElementById(ok_id).value = null;
            }

            if(data_A && data_A!=0){document.getElementById(HA_id).value = data_A}
            else{document.getElementById(HA_id).value =null}
            if(data_B && data_B!=0){document.getElementById(HB_id).value = data_B}
            else{document.getElementById(HB_id).value =null}

        }

        function insertData() {
                // var faultList=[];
                // var istrue =0;
                // var ok_data_count=0;
                // $("#EleScaleManageTable").find('tbody').each(function (index, item) {
                //     $(this).find('tr').each(function () {
                //         var tdArr = $(this).children();
                //         var MeasureSize = 0;
                //         var Measure = new Object();
                //
                //         Measure.WorkShop = tdArr.eq(0).text();
                //         Measure.Inspection_Item = tdArr.eq(1).text();
                //         Measure.Nominal_Dim = tdArr.eq(2).text();
                //         Measure.Upper_Dim = tdArr.eq(3).text();
                //         Measure.Lower_Dim = tdArr.eq(4).text();
                //         Measure.Frequency = tdArr.eq(5).text();
                //         Measure.REMARK1 = tdArr.find("#Inspection_Remark_").val();
                //
                //         Measure.ADim1_array=new Array();
                //         Measure.BDim1_array=new Array();
                //         Measure.Dim1_array=new Array();
                //
                //
                //         for(var i = 7;i<12;i++) {
                //             if (tdArr.eq(i).find('input').eq(0).val()=='' || tdArr.eq(i).find('input').eq(1).val()==''){
                //                 MeasureSize = MeasureSize + 1;
                //                 Measure.ADim1_array.push('');
                //                 Measure.BDim1_array.push('');
                //                 Measure.Dim1_array.push('');
                //
                //
                //             }else if (parseInt(tdArr.eq(i).find('input').eq(4).val()) <= parseInt(tdArr.eq(3).text())  && parseInt(tdArr.eq(i).find('input').eq(4).val()) >=parseInt(tdArr.eq(4).text())) {
                //                 ok_data_count++;
                //                 Measure.ADim1_array.push(tdArr.eq(i).find('input').eq(0).val());
                //                 Measure.BDim1_array.push(tdArr.eq(i).find('input').eq(1).val());
                //                 Measure.Dim1_array.push(tdArr.eq(i).find('input').eq(4).val());
                //
                //             }else  if (tdArr.eq(i).find('input').eq(4).val()==''){
                //                 istrue = istrue + 1;
                //             }
                //             else {
                //                 MeasureSize = MeasureSize + 1;
                //                 Measure.ADim1_array.push(tdArr.eq(i).find('input').eq(0).val());
                //                 Measure.BDim1_array.push(tdArr.eq(i).find('input').eq(1).val());
                //                 Measure.Dim1_array.push(tdArr.eq(i).find('input').eq(4).val());
                //             }
                //
                //         }
                //
                //         if(MeasureSize==0){
                //             Measure.Result="OK";
                //         }else{
                //             Measure.Result="NG";
                //         }
                //         faultList.push(Measure);
                //     });
                // });
                //
                // if (ok_data_count>0){
                //     $.ajax({
                //         url:"../EleScaleMeasure/insertMeaTable",
                //         type:'post',
                //         contentType:"application/json",
                //         data:JSON.stringify(faultList),
                //         async:false,
                //         success:function(result){
                //             if(result.SPECStatus=='200'){
                //            //  $('#EleScaleManageTable tbody').html('');
                //             // alert(result.Message);
                //                $('.bottom').html('<h1>'+result.Message+'</h1>');
                //                 setTimeout(function(){location.reload()},2000);
                //
                //             }else{
                //                 alert(result.Message);
                //             }
                //         },
                //         error:function(err){
                //             alert("量測數據上傳失敗，請檢查所填寫信息是否正確");
                //         }
                //     })
                // }
                //
                // if(istrue==0){
                //     $.ajax({
                //         url:"../EleScaleMeasure/btnMeasureTable",
                //         type:'post',
                //         contentType:"application/json",
                //         data:JSON.stringify(faultList),
                //         async:false,
                //         success:function(result){
                //             if(result.SPECStatus=='200'){
                //                 alert(result.Message);
                //
                //              //   $('.bottom').html('<h1>'+result.Message+'</h1>');
                //             }else{
                //                 alert(result.Message);
                //             }
                //         },
                //         error:function(err){
                //             alert("量測數據上傳失敗，請檢查所填寫信息是否正確");
                //         }
                //     })
                // }else{
                //     alert('請確認測量資料是否填寫正確111');
                // }
        }

    }

})

