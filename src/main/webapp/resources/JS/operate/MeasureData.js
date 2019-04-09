$(document).ready(function () {
    initMeasureDataRequire();
    getDateYear();

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

    function initMeasureDataRequire() {
        AllClear();
        $.ajax({
            url: "../MeasureData/ShowDropdownBoxFactory",
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
            url: "../MeasureData/ShowDropdownBoxLine",
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
            url: "../MeasureData/ShowDropdownBoxProjectName",
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
                 if (obj[i].PROJECT_NAME!=null && obj[i].PROJECT_NAME!=""){
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
            url: "../MeasureData/ShowDropdownBoxPartNumber",
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
            url: "../MeasureData/ShowDropdownBoxStatus",
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
            $("#measure-Status").find("option:selected").text("");
            $("#measure-Status").empty();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();
            if (Factory == '') {
                alert("請選擇廠區！");
                return;
            }
            $.ajax({
                url: "../MeasureData/FactoryDropdownBoxIf",
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
                url: "../MeasureData/ShowDropdownBoxProjectNames",
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
                            if (obj[i].PROJECT_NAME!=null && obj[i].PROJECT_NAME!="") {
                                $("#pro-Name").append("<option>" + obj[i].PROJECT_NAME + "</option>");
                            }
                        }
                    }
                },
                error: function (err) {
                    alert("獲取專案名稱下拉框值查詢異常：" + err.status);
                }
            })//獲取專案名稱下拉框值
            $.ajax({
                url: "../MeasureData/ShowDropdownBoxPartNumbers",
                type: 'post',
                async: false,
                data: {Factory: Factory, LINE_NUMBER: Link,ProName:""},
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


        })//綫別點擊時間
        $("#DateOK").click(function () {
            year = $("#year").find("option:selected").text();
            month = $("#month").find("option:selected").text();
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var partVerion = $("#part-Verion").find("option:selected").text();
            var status = $("#measure-Status").find("option:selected").text();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();//清楚日期下拉框裏面的值
            if (factory != '' && link != '' && partVerion != '' && status != '') {
                ButDate = true;
                $.ajax({
                    url: "../MeasureData/ShowDropdownBoxDay",
                    type: 'post',
                    async: false,
                    data: {
                        Factory: factory,
                        LINE_NUMBER: link,
                        PART_NUMBER_V: partVerion,
                        STATUS: status,
                        DATETIME: year + "-" + month
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
            var status29 = $("#measure-Status").find("option:selected").text();
            var datetime = $("#dpick1").find("option:selected").text();
            if (factory != '' && link != '' && partVerion != '' && status29!= '' && datetime!='') {
                $.ajax({
                    url: "../MeasureData/ShowMeasureDataResult",
                    type: 'post',
                    async: false,
                    data: {
                        Factory: factory,
                        LINE_NUMBER: link,
                        PART_NUMBER_V: partVerion,
                        STATUS: status29,
                        DATETIME:datetime
                    },
                    success: function (result) {
                        var StatusCode = result.StatusCode;
                        var m = result.message;
                        if (StatusCode == "500") {
                            alert("查無數據！");
                            $('#linkManageTable tbody').html('');

                        } else if (StatusCode == "200") {
                            var obj = eval(m);
                            $('#linkManageTable tbody').html('');
                            $('#linkManageTable thead').html('');
                            var thead="\t\t<tr>\n" +
                                "\t\t\t<th>日期時間</th>\n" +
                                "\t\t\t<th>尺寸/工站號</th>\n" +
                                "\t\t\t<th>檢驗項目</th>\n" +
                                "\t\t\t<th>檢驗内容</th>\n" +
                                "\t\t\t<th>尺寸</th>\n" +
                                "\t\t\t<th>上限</th>\n" +
                                "\t\t\t<th>下限</th>\n" +
                                "\t\t\t<th>測量階段</th>\n" +
                                "\t\t\t<th>節次</th>\n" +
                                "\t\t\t<th>頻率</th>\n" +
                                "\t\t\t<th>檢驗方式</th>\n" +
                                "\t\t\t<th>機台號</th>\n" +
                                "\t\t\t<th>測量-1</th>\n" +
                                "\t\t\t<th>測量-2</th>\n" +
                                "\t\t\t<th>測量-3</th>\n" +
                                "\t\t\t<th>測量-4</th>\n" +
                                "\t\t\t<th>測量-5</th>\n" +
                                "\t\t\t<th>測量-6</th>\n" +
                                "\t\t\t<th>測量-7</th>\n" +
                                "\t\t\t<th>測量-8</th>\n" +
                                "\t\t\t<th>測量-9</th>\n" +
                                "\t\t\t<th>測量-10</th>\n" +
                                "\t\t\t<th>測量-11</th>\n" +
                                "\t\t\t<th>測量-12</th>\n" +
                                "\t\t\t<th>測量-13</th>\n" +
                                "\t\t\t<th>測量-14</th>\n" +
                                "\t\t\t<th>測量-15</th>\n" +
                                "\t\t\t<th>測量-16</th>\n" +
                                "\t\t\t<th>測量-17</th>\n" +
                                "\t\t\t<th>測量-18</th>\n" +
                                "\t\t\t<th>測量-19</th>\n" +
                                "\t\t\t<th>測量-20</th>\n" +
                                "\t\t\t<th>測量-21</th>\n" +
                                "\t\t\t<th>測量-22</th>\n" +
                                "\t\t\t<th>測量-23</th>\n" +
                                "\t\t\t<th>測量-24</th>\n" +
                                "\t\t\t<th>測量-25</th>\n" +
                                "\t\t\t<th>測量-26</th>\n" +
                                "\t\t\t<th>測量-27</th>\n" +
                                "\t\t\t<th>測量-28</th>\n" +
                                "\t\t\t<th>測量-29</th>\n" +
                                "\t\t\t<th>測量-30</th>\n" +
                                "\t\t\t<th>測量-31</th>\n" +
                                "\t\t\t<th>測量-32</th>\n" +
                                "\t\t\t<th>測量結果</th>\n" +
                                "\t\t\t<th>人員</th>\n" +
                                "\t\t</tr>";
                            for (var i = 0; i < obj.length; i++) {
                                var tableContents = "";
                                tableContents += '<tr><td>' + obj[i].DATETIME + '</td>'
                                    + '<td>' + obj[i].WORKSHOP + '</td>'
                                    + '<td>' + obj[i].INSPECTION_ITEM + '</td>'
                                    + '<td>' + obj[i].INSPECTION_CONTENT + '</td>'
                                    + '<td>' + obj[i].NOMINAL_DIM + '</td>'
                                    + '<td>' + obj[i].UPPER_DIM + '</td>'
                                    + '<td>' + obj[i].LOWER_DIM + '</td>'
                                    + '<td>' + obj[i].STATUS + '</td>'
                                    + '<td>' + obj[i].PERIOD + '</td>'
                                    + '<td>' + obj[i].FREQUENCY + '</td>'
                                    + '<td>' + obj[i].INSPECTION_METHOD + '</td>'
                                    + '<td>' + obj[i].SPC_NUM + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE1 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE2 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE3 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE4 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE5 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE6 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE7 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE8 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE9 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE10 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE11 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE12 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE13 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE14 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE15 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE16 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE17 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE18 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE19 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE20 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE21 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE22 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE23 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE24 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE25 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE26 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE27 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE28 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE29 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE30 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE31 + '</td>'
                                    + '<td>' + obj[i].MEASURE_VALUE32 + '</td>'
                                    + '<td>' + obj[i].MEASURE_RESULT + '</td>'
                                    + '<td>' + obj[i].PERSONNEL_ID + '</td>';
                                $('#linkManageTable tbody').append(tableContents);
                            }
                            $('#linkManageTable thead').append(thead);
                        }
                    },
                    error: function (err) {
                        alert("日期下拉框廠區查詢異常(js)：" + err.status);
                    }
                })//按條件獲取表單裏的值
                return;
            }
            alert("(*)處不能爲空！");
        })//查詢按鈕點擊事件
        $("#pro-Name").change(function () {
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var proName = $("#pro-Name").find("option:selected").text();
            if (factory != '' && link != '' ) {
                $("#part-Verion").find("option:selected").text("");
                $("#part-Verion").empty();
                $("#measure-Status").find("option:selected").text("");
                $("#measure-Status").empty();
                $("#dpick1").find("option:selected").text("");
                $("#dpick1").empty();
                $.ajax({
                    url: "../MeasureData/ShowDropdownBoxPartNumbers",
                    type: 'post',
                    async: false,
                    data: {Factory: factory, LINE_NUMBER: link,ProName:proName},
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
                return;
            }
            alert("(*)處不能爲空！");
        })//專案名称下拉框稱事件
        $("#part-Verion").change(function () {
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var proName = $("#pro-Name").find("option:selected").text();
            var partVerion = $("#part-Verion").find("option:selected").text();
            if (partVerion==''){
                return;
            }

            if (factory != '' && link != '') {
                $("#measure-Status").find("option:selected").text("");
                $("#measure-Status").empty();
                $("#dpick1").find("option:selected").text("");
                $("#dpick1").empty();
                $.ajax({
                    url: "../MeasureData/ShowDropdownBoxStatuss",
                    type: 'post',
                    async: false,
                    data: {Factory: factory, LINE_NUMBER: link,ProName:proName,PartVerion:partVerion},
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
            }
        })//料號版本下拉框稱事件

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
