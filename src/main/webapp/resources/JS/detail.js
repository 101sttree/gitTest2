let uno = 0;
let nowPage = 1;	
	//화면 로딩시 댓글 목록 불러오기
$(document).ready(function()
{
	commentreset();
});

//댓글 쓰기 로그인 여부 체크
function cloginCheck()
{
	$.ajax
    ({
        url		 : "/loginCheck",
        dataType : "json",
        success: function(data)
        {
            console.log(data);
			
			if(data.check == "loginno")
			{
				
				if(confirm("로그인이 필요한 서비스 입니다. 로그인 하시겠습니까?"))
				{
					location.href = "/login"
				}
				
			}
        },
        error: function(xhr, status, error) 
        {
            console.log(error);
        }
    });
}
//댓글 답글 작성 로그인 체크 및 작성창  불러오기
function anloginCheck(cno,uno)
{
	$.ajax
    ({
        url		 : "/loginCheck",
        dataType : "json",
        success: function(data)
        {
            console.log(data);
			if(data.check == "loginno")
			{
				if(confirm("로그인이 필요한 서비스 입니다. 로그인 하시겠습니까?"))
				{
					location.href = "/login"
				}
			}
			if(data.check == "loginok")
			{
				canswer(cno,uno);
			}	
        },
        error: function(xhr, status, error) 
        {
            console.log(error);
        }
    });
}
//답글 쓰기 로그인 여부 체크
function bloginCheck()
{
	
	if($("#grouplayer").val() == 3)
	{
		alert("더 이상 답글을 작성할 수 없습니다.");
		return;
	}
	
	$.ajax
    ({
        url		 : "/loginCheck",
        dataType : "json",
        success: function(data)
        {
            console.log(data);
			if(data.check == "loginno")
			{
				if(confirm("로그인이 필요한 서비스 입니다. 로그인 하시겠습니까?"))
				{
					location.href = "/login"
				}
			}
			if(data.check == "loginok")
			{
				$("#detailform").submit();
			}	
        },
        error: function(xhr, status, error) 
        {
            console.log(error);
        }
    });
}



//댓글 불러오기
function commentlist()
{
    $.ajax
    ({
        url   : "/commentlist",
        type  : "get",
        data  : 
				{
					bno : $("#bno").val(),
					nowPage : nowPage
				},
		datetype : "json",
        success: function(data)
        {
			
			console.log(data.user);
			console.log(data.list);
			console.log(data.paging);
			console.log(data.check);
			
			user = data.user	
			if(user != null)
			{
				uno = user.uno
			}
			if(data.check == "yes")
			{
				$("#nocoment").html("");
				
				$.each(data.list, function(index, item)
				{
					let str =  '';
						str += '<tbody id="cbody'+item.cno+'">';
						str += '<tr>';
						str += '<th></th>';
						if(item.grouplayer == 0)
						{
							str += '<td align="left" colspan="3" style="border-top: 1px solid black;">'+item.cwriter+'</td>';
						}
						if(item.grouplayer == 1)
						{
							str += '<th></th>';
							str += '<td align="left" colspan="3" style="border-top: 1px solid black;">'+item.cwriter+'</td>';
						}
						
						str += '</tr>';
						str += '<tr>';
						str += '<td></td>';
						if(item.grouplayer == 0)
						{
							str += '<td align="left" colspan="3" id="ctext'+item.cno+'" class="ctext">';
						}
						if(item.grouplayer == 1)
						{
							str += '<th>|_______</th>';
							str += '<td align="left" colspan="2" id="ctext'+item.cno+'" class="ctext">';
						}
						str += '<textarea name="ctext" id="ctext" class="cta" disabled="disabled">'
						str +=	item.ctext
						str +=	'</textarea>'
						str += '</td>';
						str += '</tr>';
						str += '<tr>';
						str += '<td></td>';
						str += '<td align="right" colspan="3" style="border-bottom: 1px solid black;">';
						str += item.cdate;
						str += '</td>';
						str += '</tr>';
						str += '<tr>';
						str += '<td></td>';
						str += '<td align="right" colspan="3" style="border-bottom: 1px solid black;" id="tobutton'+item.cno+'">';
						if(item.grouplayer == 0)
						{
							str += '<input type="button" value="답글" onclick="anloginCheck('+item.cno+','+item.uno+')">';
						}
						if(uno == item.uno)
						{
							str += '<input type="button" value="수정" onclick="cmody('+item.cno+','+item.uno+')">';
							str += '<input type="button" value="삭제" onclick="cdelete('+item.cno+','+item.origino+')">';
						}
						str += '</td>';
						str += '</tr>';
						str += '</tbody>';
					$("#comment").append(str);	
				});
			}
			
        },
        error: function(xhr, status, error)
        {
            console.log(error);
        }
    });
}

//댓글 작성 및 불러오기
function commentwrite()
{
 
	cloginCheck();
	$.ajax
    ({
        url   : "/commentwrite",
        type  : "post",
        data  : 
		{ 
			bno		: $("#bno").val(),
			uno		: $("#uno").val(),
			cwriter : $("#id").val(),
			ctext 	: $("#ctext").val()
			
			
		},
        success: function(data)
        {
            commentreset();
			
        },
        error: function(xhr, status, error)
        {
            console.log(error);
        }
    });
	
}

//댓글 답글창 불러오기
function canswer(cno,uno)
{
	alert(cno);
	
	let str = '';
	str += '<tr>';
	str += '<th></th>';
	str += '<td align="left" colspan="3" id="ctextan'+cno+'" class="ctextan">';
	str += '<textarea rows="7" name="ctext" id="ctextan" class="ctaan">';
	str +=	'</textarea>'
	str += '</td>';
	str += '</tr>';
	str += '<tr>';
	str += '<td></td>';
	str += '<td align="right" colspan="3" style="border-bottom: 1px solid black;" id="tobutton'+cno+'">';
	str += '<input type="button" value="등록" onclick="canswerok('+cno+','+uno+')">';
	str += '<input type="button" value="취소" onclick="canswerno('+cno+')">';
	str += '</td>';
	str += '</tr>';
	$("#cbody"+cno+"").append(str);
	$("#cno").val(cno);
	$("#origino").val(cno);
	
}

//댓글 답글 작성 및 불러오기
function canswerok()
{
	$.ajax
	    ({
	        url   : "/commentwrite",
	        type  : "post",
	        data  : 
			{ 
				bno		: $("#bno").val(),
				uno		: $("#uno").val(),
				cno		: $("#cno").val(),
				cwriter : $("#id").val(),
				ctext 	: $("#ctextan").val() 
			},
	        success: function(data)
	        {
	            commentreset();
				
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
}

//댓글 답글 취소
function canswerno()
{
	commentreset();
}

//댓글 수정
function cmody(cno,uno)
{
	let str = '<textarea rows="7" cols="50" placeholder="댓글을 입력하세요" name="ctext" id="ctextmody">';
	$("#ctext"+cno+"").html(str);
	let str2 =  '<input type="button" value="저장" onclick="cmodyok('+cno+','+uno+')">';
		str2 += '<input type="button" value="취소" onclick="cmodyno('+cno+')">';
	$("#tobutton"+cno+"").html(str2);	
}
//댓글 수정 확인
function cmodyok(cno,uno)
{
	$.ajax
	    ({
	        url   : "/commentmody",
	        type  : "post",
	        data  : 
			{ 
				cno		: cno,
				uno		: uno,
				ctext	: $("#ctextmody").val()
			},
	        success: function(data)
	        {
				cinfo = data.cinfo;
				if(data.check == "ok")
				{
					let str = '';
						str += '<textarea rows="7" cols="50" name="ctext" id="ctext" class="cta" disabled="disabled">';
						str += cinfo.ctext;
						str += '</textarea>'
					$("#ctext"+cinfo.cno+"").html(str);
					let str2 = '';
						str2 += '<input type="button" value="수정" onclick="cmody('+cinfo.cno+','+cinfo.uno+')">';
						str2 += '<input type="button" value="삭제" onclick="cdelete('+cinfo.cno+','+cinfo.uno+')">';
					$("#tobutton"+cinfo.cno+"").html(str2);
				}
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
}

//댓글 수정 취소
function cmodyno(cno)
{
	$.ajax
	    ({
	        url   : "/commentmody",
	        type  : "post",
	        data  : 
			{ 
				cno		: cno
			},
	        success: function(data)
	        {
				cinfo = data.cinfo;
				if(data.check == "no")
				{
					let str = '';
						str += '<textarea rows="7" cols="50" name="ctext" id="ctext" class="cta" disabled="disabled">';
						str += cinfo.ctext;
						str += '</textarea>'
							
					$("#ctext"+cinfo.cno+"").html(str);
						
					let str2 = '';
						str2 += '<input type="button" value="수정" onclick="cmody('+cinfo.cno+','+cinfo.uno+')">';
						str2 += '<input type="button" value="삭제" onclick="cdelete('+cinfo.cno+','+cinfo.uno+')">';
					
					$("#tobutton"+cinfo.cno+"").html(str2);
						
				}
	        },
	        error: function(xhr, status, error)
	        {
	            console.log(error);
	        }
	    });
}

//댓글 삭제
function cdelete(cno,origino)
{
	if(confirm("댓글을 삭제하시겠습니까?"))
	{
		$.ajax
		    ({
		        url   : "/commentdelete",
		        type  : "post",
		        data  : 
				{ 
					cno		: cno
				},
		        success: function(data)
		        {
		            
					if(data.check == "no")
					{
						alert("댓글 삭제에 실패하였습니다.")
					}
					if(data.check == "ok")
					{
						commentreset();
					}
		        },
		        error: function(xhr, status, error)
		        {
		            console.log(error);
		        }
		    });
	}
}



//댓글 페이징 숫자 목록 불러오기
function commentpaginglist()
{
    $.ajax
    ({
        url   : "/commentlist",
        type  : "get",
        data  : 
				{
					bno : $("#bno").val(),
					nowPage : nowPage
				},
		datetype : "json",
        success: function(data)
        {
			console.log(data);
			paging 		= data.paging;
			startPage 	= paging.startPage;
			endPage 	= paging.endPage;
			
			if(startPage != 1)
			{            
		       $("#commentpaginglist").append("<a href='#' id='backPage'><</a>"); 
		    }
			for(var i = startPage ; i <= endPage ; i++)
			{
			 	if(nowPage == i)
				{
					$("#commentpaginglist").append("<a>"+i+"</a>"); 
				}
				if(nowPage != i)
				{
					$("#commentpaginglist").append("<a href='#' id='goPage' page='"+i+"'>"+i+"</a>"); 
				}
			}
			if(endPage != paging.lastPage)
			{            
		        $("#commentpaginglist").append("<a href='#' id='nextPage'>></a>"); 
		    }
        },
        error: function(xhr, status, error)
        {
            console.log(error);
        }
    });
}	 

function commentreset()
{
	$("#comment").html("");
	$("#commentpaginglist").html("");
	commentlist();
	commentpaginglist();
}

//댓글 페이징 (< ,> 숫자) 클릭시 이벤트
$(document).on("click","#goPage",function()
		{
			nowPage = $(this).attr("page");
			$("#comment").html("");
			$("#commentpaginglist").html("");
			commentlist();
			commentpaginglist();
		});

$(document).on("click","#backPage",function()
		{
			nowPage = startPage - 1;
			$("#comment").html("");
			$("#commentpaginglist").html("");
			commentlist();
			commentpaginglist();
		});
$(document).on("click","#nextPage",function()
		{
			nowPage = endPage + 1;
			$("#comment").html("");
			$("#commentpaginglist").html("");
			commentlist();
			commentpaginglist();
		});

//행동 확인 메세지
$(document).on("click","#file",function()
{
	if(confirm("파일을 다운 받으시겠습니까?"))
	{
		location.href="/common/download?fileName="+$("#fname").val()+"";
	}
});

$(document).on("click","#delete",function()
{
	if(confirm("글을 삭제하시겠습니까"))
	{
		location.href="/board/delete?bno="+$("#bno").val()+"";
	}
});

