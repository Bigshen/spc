$(document).ready(function () {
    initMeasureDataCTPRequire();
    getDateYear();

    function initMeasureDataCTPResult(factory,link,partVerion,datetime,proName,testType) {
            $.ajax({
                url: "../MeasureDataCTP/ShowMeasureDataCTPResult",
                type: 'post',
                async: false,
                data: {
                    Factory: factory,
                    LINE_NUMBER: link,
                    PART_NUMBER_V: partVerion,
                    DATETIME:datetime,
                    PROJECT_NAME:proName,
                    TestType:testType
                },
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert("查無數據!");
                        $('#linkManageTable tbody').html('');
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $('#linkManageTable tbody').html('');
                        $('#linkManageTable thead').html('');
                        var thead="\t\t<tr>\n" +
                            "\t\t\t<th>日期時間</th>\n" +
                            "\t\t\t<th>工站號</th>\n" +
                            "\t\t\t<th>節次</th>\n" +
                            "\t\t\t<th>工站名稱</th>\n" +
                            "\t\t\t<th>檢驗項目</th>\n" +
                            "\t\t\t<th>機台名稱</th>\n" +
                            "\t\t\t<th>機台類型</th>\n" +
                            "\t\t\t<th>備注</th>\n" +
                            "\t\t\t<th>上限</th>\n" +
                            "\t\t\t<th>下限</th>\n" +
                            "\t\t\t<th>量測值</th>\n" +
                            "\t\t\t<th>量測結果</th>\n" +
                            "\t\t\t<th>檢驗備注</th>\n" +
                            "\t\t\t<th>測量人員</th>\n" +
                            "\t\t</tr>";
                        for (var i = 0; i < obj.length; i++) {
//                            var testTypeResult="";
                            var tableContents = "";
                            // if(parseInt(obj[i].YIELD_RATE.toString())>=90){
                            //     testTypeResult="Pass"
                            // }else {
                            //     testTypeResult="Fail"
                            // }
                            if(obj[i].MEASURE_VALUE==null){
                                obj[i].MEASURE_VALUE="NA"
                            }
                            if(obj[i].MEASURE_RESULT==null){
                                obj[i].MEASURE_RESULT="NA"
                            }
                            tableContents += '<tr>' +
                                '<td>' + obj[i].DATETIME + '</td>'
                                + '<td>' + obj[i].WORKSHOP + '</td>'
                                + '<td>' + obj[i].PERIOD + '</td>'
                                + '<td>' + obj[i].WORKSHOP_NAME + '</td>'
                                + '<td>' + obj[i].INSPECTION_ITEM + '</td>'
                                + '<td>' + obj[i].MACHINE_NAME+ '</td>'
                            +'<td>' + obj[i].MACHINE_TYPE + '</td>'
                            + '<td>' + obj[i].REMARK1 + '</td>'
                            + '<td>' + obj[i].UPPER_DIM + '</td>'
                            + '<td>' + obj[i].LOWER_DIM + '</td>'
                            + '<td>' + obj[i].MEASURE_VALUE + '</td>'
                            + '<td>' + obj[i].MEASURE_RESULT+ '</td>'
                            + '<td>' + obj[i].BATCH_REMARK+ '</td>'
                                + '<td>' + obj[i].PERSONNEL_ID + '</td>';
                            $('#MeasureDataCTPTable tbody').append(tableContents);
                        }
                        $('#MeasureDataCTPTable thead').append(thead);
                    }
                },
                error: function (err) {
                    alert("日期下拉框廠區查詢異常(js)：" + err.status);
                }
            })
    }

    function AllClear() {
        $("#fl-Factory").find("option:selected").text("");
        $("#fl-Factory").empty();
        $("#fl-Link").find("option:selected").text("");
        $("#fl-Link").empty();
        $("#pro-Name").find("option:selected").text("");
        $("#pro-Name").empty();
        $("#part-Verion").find("option:selected").text("");
        $("#part-Verion").empty();
        $("#measure-Status").find("option:selected").text("");
        $("#measure-Status").empty();
        $("#dpick1").find("option:selected").text("");
        $("#dpick1").empty();
    }

    function initMeasureDataCTPRequire() {
        AllClear();
        $.ajax({
            url: "../MeasureDataCTP/ShowDropdownBoxFactory",
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
            url: "../MeasureDataCTP/ShowDropdownBoxLine",
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
            url: "../MeasureDataCTP/ShowDropdownBoxProjectName",
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
                        $("#pro-Name").append("<option>" + obj[i].PROJECT_NAME + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框專案名稱值失敗，失敗原因：" + err);
            }
        })//加載專案名稱下拉框值
        $.ajax({
            url: "../MeasureDataCTP/ShowDropdownBoxPartNumber",
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
        var ButDate = false;//判斷日期按鈕是否點擊
        var year = "";
        var month = "";
        $("#fl-Factory").change(function () {
            var Factory = $("#fl-Factory").find("option:selected").text();
            $("#fl-Link").find("option:selected").text("");
            $("#fl-Link").empty();
            $("#pro-Name").find("option:selected").text("");
            $("#pro-Name").empty();
            $("#part-Verion").find("option:selected").text("");
            $("#part-Verion").empty();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();
            if (Factory == '') {
                alert("請選擇廠區！");
                return;
            }
            $.ajax({
                url: "../MeasureDataCTP/FactoryDropdownBoxIf",
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
        })//廠區下拉框點擊事件
        $("#fl-Link").change(function () {
            var Link = $("#fl-Link").find("option:selected").text();
            var Factory = $("#fl-Factory").find("option:selected").text();
            if (Factory == '') {
                alert("請先選擇廠區！");
                return;
            }
            $("#pro-Name").find("option:selected").text("");
            $("#pro-Name").empty();
            $("#part-Verion").find("option:selected").text("");
            $("#part-Verion").empty();
            $("#measure-Status").find("option:selected").text("");
            $("#measure-Status").empty();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();
            if (Link == '') {
                alert("請選擇綫別！");
                return;
            }
            $.ajax({
                url: "../MeasureDataCTP/ShowDropdownBoxProjectNames",
                type: 'post',
                async: false,
                data: {Factory: Factory, LINE_NUMBER: Link},
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $("#pro-Name").append("<option></option>");
                        for (var i = 0; i < obj.length; i++) {
                            $("#pro-Name").append("<option>" + obj[i].PROJECT_NAME + "</option>");
                        }
                    }
                },
                error: function (err) {
                    alert("獲取專案名稱下拉框值查詢異常：" + err.status);
                }
            })//獲取專案名稱下拉框值
            $.ajax({
                url: "../MeasureDataCTP/ShowDropdownBoxPartNumbers",
                type: 'post',
                async: false,
                data: {Factory: Factory, LINE_NUMBER: Link},
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
        $("#DateOK").click(function () {
            year = $("#year").find("option:selected").text();
            month = $("#month").find("option:selected").text();
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var partVerion = $("#part-Verion").find("option:selected").text();
            var proName = $("#pro-Name").find("option:selected").text();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();//清除日期下拉框裏面的值
            if (factory != '' && link != '' && partVerion != '' && proName!='') {
                ButDate = true;
                $.ajax({
                    url: "../MeasureDataCTP/ShowDropdownBoxDay",
                    type: 'post',
                    async: false,
                    data: {
                        Factory: factory,
                        LINE_NUMBER: link,
                        PART_NUMBER_V: partVerion,
                        DATETIME: year + "-" + month,
                        PROJECT_NAME:proName
                    },
                    success: function (result) {
                        var StatusCode = result.StatusCode;
                        var m = result.message;
                        if (StatusCode == "500") {

                        } else if (StatusCode == "200") {
                            var obj = eval(m);
                            // $("#dpick1").append("<option></option>");
                            for (var i = 0; i < obj.length; i++) {
                                $("#dpick1").append("<option>" + obj[i].DATETIME + "</option>");
                            }
                        }
                    },
                    error: function (err) {
                        alert("日期下拉框廠區查詢異常(js)：" + err.status);
                    }
                })//加載天數下拉框值

                return;
            }
            alert("(*)處為必填項！");
        })//年份月份按鈕點擊事件
        $("#dpick1").click(function () {
            if (ButDate == false) {
                alert("請選擇上方的日期！");
                return;
            }
        })//日期下拉框點擊事件
        $("#QueryOK").click(function () {
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var partVerion = $("#part-Verion").find("option:selected").text();
            var datetime = $("#dpick1").find("option:selected").text();
            var proName = $("#pro-Name").find("option:selected").text();
            var testType = $("#testType").find("option:selected").text();
            if (factory != '' && link != '' && partVerion != '' && datetime!='' && proName!='' && testType!='') {
                initMeasureDataCTPResult(factory,link,partVerion,datetime,proName,testType);
                return;
            }

            alert("(*)處不能爲空！");

        })//查詢按鈕點擊事件

        // $("#exportData").click(function () {
        //
        //     alert("導出");
        //
        // })

        // var faultList=[];
        // $("#MeasureDataCTPTable").find('tbody').each(function (index, item) {
        //     $(this).find('tr').each(function () {
        //         var tdArr = $(this).children();
        //         var MeasureSize = 0;
        //         var Measure = new Object();
        //         Measure.WorkShop = tdArr.eq(1).text();
        //         Measure.WORKSHOP_NAME = tdArr.eq(3).text();
        //         Measure.INSPECTION_ITEM = tdArr.eq(4).text();
        //         Measure.MACHINE_NAME = tdArr.eq(5).text();
        //         Measure.MACHINE_TYPE = tdArr.eq(6).text();
        //         Measure.REMARK1 = tdArr.eq(7).text();
        //         Measure.PERIOD = tdArr.eq(2).text();
        //         Measure.MEASURE_VALUE=tdArr.eq(10).text();
        //         Measure.MEASURE_RESULT=tdArr.eq(11).text();
        //         Measure.BATCH_REMARK=tdArr.eq(12).text();
        //         faultList.push(Measure);
        //     });
        // });

    }

    function getDateYear() {
        var data = new Date();
        var year = data.getFullYear();
        $("#year").get(0).options[0].text = year - 3;
        $("#year").get(0).options[1].text = year - 2;
        $("#year").get(0).options[2].text = year - 1;
        $("#year").get(0).options[3].text = year;
        $("#year").get(0).options[4].text = year + 1;
        $("#year").get(0).options[5].text = year + 2;
        $("#year").get(0).options[6].text = year + 3;
        var month = data.getMonth() + 1;
        var option = $("#month").children();
        for (var i = 0; i < option.length; i++) {
            var time = "0" + month;
            var timeC = option.eq(i).text();
            if (time == timeC) {
                option[i].selected = true;
            }
        }
    }// 獲取下拉框日期年份，當前時間前3年和后3年








})
