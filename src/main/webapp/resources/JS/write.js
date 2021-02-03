function writerok()
	{
		
		if($("#title").val() == "")
		{
			alert("제목을 입력하세요");
			return;
		}
		if($("#btext").val() == "")
		{
			alert("내용을 입력하세요");
			return;
		}
		
		$("#writefrm").submit();
	}



var sel_file;

//file id 태그 값이 변경되면 해당 함수를 실행한다.
$(document).ready(function()
{
	$("#file").on("change", handleImgFileSelect);
});

function handleImgFileSelect(e)
{
	//file라는 id를 가진 태그의 이벤트를 객체로 가져와 담는다.
	var files = e.target.files;
	//files이라는 객체를 배열로 바꾼다.
	var filesArr = Array.prototype.slice.call(files);
	
	//filesArr배열에 하나씩 접근을 한다.
	filesArr.forEach(function(f)
	{
		if(!f.type.match("image.*"))
		{
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}
		
		
		var reader = new FileReader();
		reader.onload = function(e)
		{
			$("#img").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
		
	});
	
}





