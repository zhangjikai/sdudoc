function checkLogin() {
	var username = document.getElementById("username").value.replace(
			/(^\s*)|(\s*$)/g, "");
	var password = document.getElementById("password").value.replace(
			/(^\s*)|(\s*$)/g, "");
	var random = document.getElementById("random").value.replace(
			/(^\s*)|(\s*$)/g, "");
	if (username == null || username == "") {
		alert("用户名不能为空！！！")
		return false;
	}
	if (password == null || password == "") {
		alert("密码不能为空！！！")
		return false;
	}
	if (random == null || random == "") {
		alert("验证码不能为空！！！");
		return false;
	}
	return true;
}

function checkUserName() {
	// console.log(11111111111);
	var username = $("#username").val();
	if (username == null || username == "") {
		$("#uTip").html("用户名不能为空!!!");
		$("#submitRegister").attr("disabled", "disabled");
		$("#submitRegister").css("color", "#A0C974");
		return;
	}
	
	if(username.length<5 || username.length>18) {
		$("#uTip").html("用户名长度应在5到18个字符间!!!");
		$("#submitRegister").attr("disabled", "disabled");
		$("#submitRegister").css("color", "#A0C974");
		return;
	}
	$.post("checkUserExists.action?username=" + username, null, function(
			response) {
		if (response == "exists") {
			$("#uTip").html("用户名已存在!!!");
			$("#submitRegister").attr("disabled", "disabled");
			$("#submitRegister").css("color", "#A0C974");
		} else {
			$("#uTip").html("用户名可以使用!!!");
			$("#submitRegister").removeAttr("disabled");
			$("#submitRegister").css("color", "#4A682D");
		}
	});
	f
}

function checkEmail() {
	var email = $("#email").val();
	if (email == null || email == "") {
		$("#eTip").html("邮箱不能为空!!!");
		$("#submitRegister").attr("disabled", "disabled");
		$("#submitRegister").css("color", "#A0C974");
		return;
	}

	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!emailReg.test(email)) {
		$("#eTip").html("邮箱格式不正确!!!");
		$("#submitRegister").attr("disabled", "disabled");
		$("#submitRegister").css("color", "#A0C974");
		return;
	}

	$.post("checkEmailExists.action?email=" + email, null, function(response) {
		if (response == "exists") {
			$("#eTip").html("邮箱已注册!!!");
			$("#submitRegister").attr("disabled", "disabled");
			$("#submitRegister").css("color", "#A0C974");
		} else {
			$("#eTip").html("邮箱可以使用!!!");
			$("#submitRegister").removeAttr("disabled");
			$("#submitRegister").css("color", "#4A682D");
		}
	});
}

function checkRegister() {
	var username = document.getElementById("username").value.replace(
			/(^\s*)|(\s*$)/g, "");
	if (username == null || username == "") {
		alert("用户名不能为空！！！")
		return false;
	}

	var password = document.getElementById("password").value.replace(
			/(^\s*)|(\s*$)/g, "");
	if (password == null || password == "") {
		alert("登录密码不能为空！！！")
		return false;
	}
	
	if(password.length<5 || password.length>18) {
		alert("密码长度应在5到18个字符之间！！！")
		return false;
	}

	var repassword = document.getElementById("repassword").value.replace(
			/(^\s*)|(\s*$)/g, "");
	if (repassword == null || repassword == "") {
		alert("确认密码不能为空！！！")
		return false;
	}

	if (password != repassword) {
		alert("两次密码不一致！！！")
		return false;
	}

	var email = document.getElementById("email").value.replace(
			/(^\s*)|(\s*$)/g, "");
	if (email == null || email == "") {
		alert("邮箱不能为空！！！");
		return false;
	}

	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!emailReg.test(email)) {
		alert("邮箱格式不正确");
		return false;
	}

	var random = document.getElementById("random").value.replace(
			/(^\s*)|(\s*$)/g, "");
	if (random == null || random == "") {
		alert("验证码不能为空！！！");
		return false;
	}
	return true;
}
