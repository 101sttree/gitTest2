function loginCheck()
	{
		$.ajax
	    ({
	        url		 : "/loginCheck",
	        dataType : "json",
	        success: function(data)
	        {
	            console.log(data);
				if(data.check == "loginok")
				{
					location.href = "/writego"
				}
				if(data.check == "loginno")
				{
					alert("로그인이 필요한 서비스 입니다.");
					location.href = "/login"
				}
	        },
	        error: function(xhr, status, error) 
	        {
	            console.log(error);
	        }
	    });
	}
	
$(document).on("click","#logout",function()
{
	if(confirm("로그아웃 하시겠습니까?"))
	{
		location.href="/logout";
	}
});