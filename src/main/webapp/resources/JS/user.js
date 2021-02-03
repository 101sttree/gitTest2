let idcklet = "";
let idlet = "";



var pwckr = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;





//회원가입 유효성 검사
function join()
{
	
	if($("#jid").val() == "")
	{
		alert("아이디를 입력하세요");
		return;
	}
	if($("#jpw").val() == "")
	{
		alert("비밀번호를 입력하세요");
		return;
	}
	if($("#pwck").val() == "")
	{
		alert("비밀번호 확인을 입력하십시오.");
		return;
	}
	if($("#pwck").val() != $("#pw").val())
	{
		alert("비밀번호가 일치하지 않습니다.");
		return;
	}
	if($("#name").val() == "")
	{
		alert("이름을 입력하세요");
		return;
	}
	if(idcklet == "")
	{
		alert("아이디 중복확인을 해주십시오.");
		return;
	}	
	
	$("#joinfrm").submit();
}

//아이디 중복 확인
function idck()
{	
	var idckr = /^[A-za-z0-9]{5,15}/g;
	if(!idckr.test($("#jid").val()))
	{
		var str = "아이디는 영문자와 숫자로 이루어진 5자리에서 15자리를 입력해야 합니다.";
		$("#idno").html("<tr><td></td><td id='no'>"+str+"</td></td><td></tr>");
		return;
	}
	else
	{
		$("#idno").html("");
	}
	$.ajax
    ({
        url : "/idck",
        type  : "post",
        data  : { id : $("#jid").val() },
        dataType : "json",
        success: function(data)
        {
            console.log(data);
			if($("#jid").val() == "")
			{
				alert("아이디를 입력하세요");
				return;
			}
			
			if(data.result == "no")
			{
				alert("중복된 아이디 입니다.");
				var str = "중복된 아이디입니다.";
				$("#idno").html("<tr><td></td><td id='no'>"+str+"</td></td><td></tr>");
				return;
			}
			if(data.result == "ok")
			{
				var str = "사용 가능한 아이디 입니다.";
				$("#idno").html("<tr><td></td><td id='no'>"+str+"</td></td><td></tr>");
				idcklet = "ok";
				//$('#jpw').focus();
			}
        },
        error: function(xhr, status, error)
        {
            console.log(error);
        }
    });
}

$(document).ready(function()
{
	
	//비밀번호 정규식 검사
	$('#jpw').blur(function() 
	{
		if(!pwckr.test($("#jpw").val()))
		{
			var str = "비밀번호는 영문, 숫자, 특수문자를 포함한 8자리 이상이여야 합니다.";
			$("#pwno").html("<tr><td></td><td id='no'>"+str+"</td></td><td></tr>");
		}
		if(pwckr.test($("#jpw").val()))
		{
			var str = "비밀번호는 영문, 숫자, 특수문자를 포함한 8자리 이상이여야 합니다.";
			$("#pwno").html("");
		} 
	});

	//비밀번호 확인 검사 
	$('#pwck').blur(function() 
	{
		if($("#pwck").val() != $("#jpw").val())
		{
			var str = "비밀번호가 일치하지 않습니다.";
			$("#pwckno").html("<tr><td></td><td id='no'>"+str+"</td></td><td></tr>");
		}
		if($("#pwck").val() == $("#jpw").val())
		{
			var str = "비밀번호가 일치하지 않습니다.";
			$("#pwckno").html("");
		}
	});
});

//로그인
function login()
{
	if($("#id").val()=="")
		{
			alert("아이디를 입력하십시오");
			return;
		}
	if($("#pw").val()=="")
	{
		alert("비밀번호를 입력하십시오");
		return;
	}
	
	
	$.ajax
	({
		url: "/login",
		type: "post",
		data:
			{
				id:$("#id").val(),
				Pw:$("#pw").val()
			}	
		,
		dataType: "json",
		success:function(data) 
		{
			console.log(data);
			if(data.check != "success")
			{
				alert("로그인에 실패하였습니다.");
				return;
			}
			if(data.result == "idfail")
			{
				alert("존재하지 않는 아이디 입니다.");
				return;	
			}
			if(data.result == "pwfail")
			{
				alert("비밀번호가 틀렸습니다.");
				return;
			}
			if(data.result == "loginok")
			{
				alert("환영합니다.");
				location.href = "/";
			}
		}
		
		
	});
}

//로그인 엔터 키 작동 함수
function pressEnter(){
    if(event.keyCode == 13){
    	login();
    }
}





