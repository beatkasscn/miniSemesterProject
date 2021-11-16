function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

function checkRealName() {
    var realname = $("#realname").val();
    var reg_realname = /^[\u4e00-\u9fa5]{1,9}$/;
    var flag = reg_realname.test(realname);
    if (flag) {
        $("#realname").addClass("is-valid");
        $("#realname").removeClass("is-invalid");
        $("#realnameCorrect").show();
        $("#realnameIncorrect").hide();
    } else {
        $("#realname").addClass("is-invalid");
        $("#realname").removeClass("is-valid");
        $("#realnameIncorrect").show();
        $("#realnameCorrect").hide();
    }
    return flag;
}

function checkusername1() {
    var username1 = $("#username1").val();
    var reg_username1 = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
    var flag = reg_username1.test(username1);
    if (flag) {
        $("#username1").addClass("is-valid");
        $("#username1").removeClass("is-invalid");
        $("#username1Correct").show();
        $("#username1Incorrect").hide();
    } else {
        $("#username1").addClass("is-invalid");
        $("#username1").removeClass("is-valid");
        $("#username1Incorrect").show();
        $("#username1Correct").hide();
    }
    return flag;
}

function checktel() {
    var tel = $("#tel").val();
    var reg_tel = /^\d{11}$/;
    var flag = reg_tel.test(tel);
    if (flag) {
        $("#tel").addClass("is-valid");
        $("#tel").removeClass("is-invalid");
        $("#telCorrect").show();
        $("#telIncorrect").hide();
    } else {
        $("#tel").addClass("is-invalid");
        $("#tel").removeClass("is-valid");
        $("#telIncorrect").show();
        $("#telCorrect").hide();
    }
    return flag;
}

function checkIDnumber() {
    var IDnumber = $("#IDnumber").val();
    var reg_IDnumber = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    var flag = reg_IDnumber.test(IDnumber);
    if (flag) {
        $("#IDnumber").addClass("is-valid");
        $("#IDnumber").removeClass("is-invalid");
        $("#IDnumberCorrect").show();
        $("#IDnumberIncorrect").hide();
    } else {
        $("#IDnumber").addClass("is-invalid");
        $("#IDnumber").removeClass("is-valid");
        $("#IDnumberIncorrect").show();
        $("#IDnumberCorrect").hide();
    }
    return flag;
}

function checkAddress() {
    var address = $("#detail").val();
    if (address !== null) {
        return true;
    } else return false;
}

$(function () {
    $("#display_username").html($.cookie("username"));
    $("#realname").blur(checkRealName);
    $("#username1").blur(checkusername1);
    $("#tel").blur(checktel);
    $("#IDnumber").blur(checkIDnumber);
    $(".close").click(function () {
        $("#modal_confirm").hide();
        $("#modal_confirm_error").hide();
    });
});
$(function () {
    $("#imginput").click(function () {
        $("#imginput").on("change", function () {
            var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#imgsrc").attr("src", objUrl); //将图片路径存入src中，显示出图片
            }
        });
    });
});
$(function () {
    $("#save").click(function () {
        $.post("http://localhost:8080/user/saveInfo",$("#saveform").serialize(),function (data) {
            if(checkusername1() && checkRealName() && checkIDnumber() && checktel() && checkAddress() && data.status === 0){
                $("#modal_confirm").show();
            }else {
                $("#modal_confirm_error").show();
            }
        })
    });
})