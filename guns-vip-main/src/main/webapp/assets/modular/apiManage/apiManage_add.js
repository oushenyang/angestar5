/**
 * 详情对话框
 */
var ApiManageInfoDlg = {
    data: {
        appId: "",
        callCode: "",
        apiStatus: "",
        apiType: "",
        apiName: "",
        apiCode: "",
        parameterNum: "",
        parameterOne: "",
        parameterOneRemark: "",
        parameterTwo: "",
        parameterTwoRemark: "",
        parameterThree: "",
        parameterThreeRemark: "",
        parameterFour: "",
        parameterFourRemark: "",
        parameterFive: "",
        parameterFiveRemark: "",
        parameterSix: "",
        parameterSixRemark: "",
        parameterSeven: "",
        parameterSevenRemark: "",
        returnRemark: "",
        remark: "",
        revision: "",
        createUser: "",
        createTime: "",
        updateUser: "",
        updateTime: ""
    }
};

layui.use(['form', 'formX','admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/apiManage/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });
});