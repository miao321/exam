var XMLHttpReq;
//创建XMLHttpRequest对象
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		XMLHttpReq = new XMLHttpRequest();
	}else if (window.ActiveXOnject) {
		try{
			XMLHttpReq = new ActiveXObject("Msxm12.XMLHTTP");
		}catch (e) {
			// TODO: handle exception
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				// TODO: handle exception
			}
		}
	}
}

//发送请求函数
function sendRequest(url) {
	createXMLHttpRequest();
	//url是请求的路径，例如/exam/login.do
	//get是请求方法
	//true是确定为异步提交，如果是false即不采用异步提交
	XMLHttpReq.open("GET", url, true);
	//指定响应函数，响应函数是执行了异步提交后，程序的返回信息，有响应函数返回
	XMLHttpReq.onreadystatechange = processResponse;
	//发送请求
	XMLHttpReq.send(null);
}

//处理返回信息函数
function processResponse() {
	if (XMLHttpReq.readyState == 4) {
		//判断对象状态
		if(XMLHttpReq.status == 200){
			//信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
			if (res == 1) {
				ajaxVal1();
			}
			if (res == 0) {
				ajaxVal();
			}
		}else {
			//页面不正常
			window.alert("您所请求的页面有异常");
		}
	}
}

//此方法是用来验证文本框是数据是否合法
function signUp() {
	var accounts = document.examManager.accounts.value;
	if (accounts =="") {
		window.alert("用户名不能为空");
		document.examManager.accounts.focus();
		return false;
	}else if (accounts.length < 8) {
		window.alert("用户名长度不能少于8位！");
		document.examManager.accounts.focus();
		return false;
	}else if (accounts.length > 20) {
		window.alert("用户名长度不能超过20位！");
		document.examManager.accounts.focus();
		return false;
	}else{
		sendRequest("<%=basePath %>exam/valAccount.do?accounts="+accounts);
	}
}

//此处是根据ID来插入内容
function ajaxVal() {
	document.getElementById("put").innerHTML = "<input type='submit' value='提交'>";
	document.getElementById("reg").innerHTML = "<font color='red'>此账号已经存在</font>";
}

function ajaxVal1() {
	document.getElementById("put").innerHTML = "<input type='submit' value='提交' disabled>";
	document.getElementById("reg").innerHTML = "<font color='red'>此账号已经存在</font>";
}