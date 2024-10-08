
/**************** 제보 유형 관리 기능 *****************/

var typeCode0 = '100';
var typeCode1;
var typeCode2;

var selType0 = '';
var selType1 = '';

var dbObj = '';
var dbObjVal = '';
var dbObjFlag = '';

var addObjId = '';

var modiFlag ='0';

//대분류 코드의 하위 중분류 코드를 가져온다.
function getTypeCode(code, type, obj,pcode){		
	console.log("겟타입코드 진입");
	if(!changeStatus(code, type, obj)){
		return false;
	}
	
	if (code != ""){
		var url;
		if(mUrl=='reportType'){//제보유형 의 경우
			
			if(type == '2'){
				typeCode1 = code;
				url = "/option/"+mUrl+"/code2.do"	// 대분류 코드의 하위 중분류 코드를 가져온다.
				$('#divTypeCode2').empty();
				$('#divTypeCode3').empty();
			} else {
				typeCode2 = code;
				url = "/option/"+mUrl+"/code3.do"	// 중분류 코드의 하위 소분류 코드를 가져온다.
				$('#divTypeCode3').empty();
			}
			 
		}else if(mUrl=='inTel'){ //내선번호
			//$('#dbInTelDiv').empty();
			$('#divTypeCode2').empty();
			$('#divTypeCode3').empty();
			typeCode0 = code;
			var options = {
				url : "/option/"+mUrl+"/code2.do",
		        data:{typeNum:"2",tCode:code},
		        success: function(res){
		        	$('#dbInTelDiv').html(res);
		        },
		        error: function(res,error){
		            alert("에러가 발생했습니다."+error);
		        }
		    };
			 
		}else{//제보유형,내선번호 제외 나머지
			
			 if(type == '2'){
				typeCode0 = code;
				url = "/option/"+mUrl+"/code2.do"//대클릭->중	// 대분류 코드의 하위 중분류 코드를 가져온다.
				$('#divTypeCode2').empty();
				$('#divTypeCode3').empty();
			 }else if (type == '3'){
				 typeCode1 = code;
				url = "/option/"+mUrl+"/code3.do"//중클릭->소		// 중분류 코드의 하위 소분류 코드를 가져온다.
				$('#divTypeCode3').empty();
			} else {
				typeCode2 = code;
				url = "/option/"+mUrl+"/code3.do"//중클릭->소		// 중분류 코드의 하위 소분류 코드를 가져온다.
				$('#divTypeCode3').empty();
			}
		
		}
		
		if(mUrl=='inTel'){
			$.ajax(options); 
		}else{
			var data = {"tCode" : code, "typeNum" : type, "pCode" : pcode, "selArea" : $("#areaOptSel option:selected").val()};
			sendAjaxRequest(url, data, '#divTypeCode'+type);
		}
	}
}

function addTypeCode(cdFlag){
	
	console.log("제보유형 추가");
	
	removeInputBox();
	if ($('#newTypeCode'+cdFlag).length > 0){
		alert("추가는 1개씩 가능합니다.");
	} else {
		var ul = $("#ulTypeCode"+cdFlag);
		var lis = ul.children();
		addObjId = 'newLi'+cdFlag;
		
		ul.children().remove();
		
		var li = $(document.createElement("li")).attr({id:'newLi'+cdFlag}).addClass("");
		li.append($(document.createElement("input"))
					.attr({type:'text', id:'newTypeCode'+cdFlag, name:'newTypeCode'+cdFlag })
					.css('width','80px')
					.focus());
		li.append($(document.createElement("img"))
					.attr({src:'../images/code_add.gif', title:'추가'})
					.css('vertical-align','middle').css('margin-top', '-4px')
					.click(function(){
						//alert(cdFlag+", "+typeCode0+", "+typeCode1+", "+typeCode2+", "+$('#newTypeCode'+cdFlag).val());
						console.log("추가 승인 요청중");
						var parent_code;
						var tcode;
						var target;
						if(mUrl != 'reportType'){//통신원유형
							if (cdFlag == '1'){
								target = '#tabMenu';
							} else if (cdFlag == '2'){
								parent_code = typeCode0;
								target = '#divTypeCode2';
							} else {
								parent_code = typeCode0;
								tcode = typeCode1;
								target = '#divTypeCode3';
							}
						}else{//제보유형
							if (cdFlag == '1'){
								parent_code = typeCode0;
								target = '#tabMenu';
							} else if (cdFlag == '2'){
								parent_code = typeCode1;
								target = '#divTypeCode2';
							} else {
								parent_code = typeCode1+typeCode2;
								target = '#divTypeCode3';
							}
						}
						
						var url = '/option/'+mUrl+'/save.do';
						var data;

						if(mUrl=='infrm'){
							data= {"cdFlag" : cdFlag, "pCode" : parent_code, "tCode" : tcode, "pageFlag" : pageFlag, "value" : $('#newTypeCode'+cdFlag).val(),"selArea" : $("#areaOptSel option:selected").val()}; 
						}else{
							data= {"cdFlag" : cdFlag, "pCode" : parent_code, "tCode" : tcode, "pageFlag" : pageFlag, "value" : $('#newTypeCode'+cdFlag).val(),};
						}
						
						sendAjaxRequest(url, data, target, saveMent);
					}));
		li.append($(document.createElement("img")).attr({src:'../images/code_del.gif', title:'취소'})
				.css('vertical-align','middle').css('margin-top', '-4px')
				.click(function(){
					console.log($("#"+addObjId).children());
					$("#"+addObjId).remove();
					modiFlag=0;
			}));
		ul.append(li);
		ul.append(lis);
	}
}

function modTypeCode(cd, cdName, cdFlag,obj){
	console.log(cdFlag+", "+cd+", "+cdName);
	var nwFlag = true;
	$($("#tabMenu ul li").each(function(i){
		if($("#tabMenu ul li")[i].childElementCount>2){
			nwFlag =false;
		}
	}));
	if(nwFlag){
		var li = $(obj).parent();
		dbObj = li;
		dbObjVal = li.html();
		dbObjFlag = cdFlag;
		li.children().hide();
		li.append($(document.createElement("input")).attr({type:'text', id:'chgTypeCode'+cd, value:cdName}).css('width','70px').focus());
		li.append($(document.createElement("img")).attr({src:'../images/code_y.gif', title:'추가'})
				.css('vertical-align','middle').css('margin-top', '-4px')
				.click(function(){
					//alert($("#chgTypeCode"+cd).val());
					var target;
					var parent_code='';
					var pCode2='';
					if(mUrl != 'reportType'){
						if (cdFlag == '1'){
							target = '#tabMenu';
							$('#divTypeCode2').empty();
							$('#divTypeCode3').empty();
						} else if (cdFlag == '2'){
							target = '#divTypeCode'+cdFlag;
							parent_code = typeCode0;
							$('#divTypeCode3').empty();
						} else {
							target = '#divTypeCode'+cdFlag;
							parent_code = typeCode0;
							pCode2 = typeCode1;
						}
					}else{
						if (cdFlag == '1'){
							target = '#tabMenu';
							parent_code = typeCode0;
							$('#divTypeCode2').empty();
							$('#divTypeCode3').empty();
						} else if (cdFlag == '2'){
							target = '#divTypeCode'+cdFlag;
							parent_code = typeCode1;
							$('#divTypeCode3').empty();
						} else {
							target = '#divTypeCode'+cdFlag;
							parent_code = typeCode1+typeCode2;
						}
					}
					if(mUrl=='inTel'){
						if(cdFlag == '1'){
							target = '#tabMenu';
						}else{
							target = '#dbInTelDiv'
						}
					}
					var url = '/option/'+mUrl+'/modify.do';
					var data;
					if(mUrl=='infrm'){
						data= {"pageFlag" : pageFlag, "cdFlag" : cdFlag,"pCode" : parent_code, 
								"pCode2" : pCode2, "code" : cd, "codeName" : $('#chgTypeCode'+cd).val(),
								"selArea" : $("#areaOptSel option:selected").val()}; 
					}else{
						data= {"pageFlag" : pageFlag, "cdFlag" : cdFlag,"pCode" : parent_code, 
								"pCode2" : pCode2, "code" : cd, "codeName" : $('#chgTypeCode'+cd).val()};
					}
					sendAjaxRequest(url, data, target, modifyMent);
				}));
		li.append($(document.createElement("img")).attr({src:'../images/code_x.gif', title:'취소'})
			.css('vertical-align','middle').css('margin-top', '-4px')
			.click(function(){
				console.log(dbObj.children());
				console.log(dbObj.children().length);
				console.log(dbObj.children()[2]);
				//이미지 2개 input 1개 제거해야하므로 뒤에서부터 3개를 제거
				for (var i = 0; i < 3; i++) {
					if(typeof dbObj.children()[dbObj.children().length-1] !=="undefined"){
						dbObj.children()[dbObj.children().length-1].remove();
					}
				}
				dbObj.children().show();
				modiFlag=0;
		}));
	}else{
		alert("기존 항목을 먼저 수정하거나 선택 취소해 주세요");
	}
}

function delTypeCode(cdFlag){
	removeInputBox();
	console.log("제보유형 삭제항목");
	var errorMent = '항목이 없습니다.';
	if ($("input:checkbox[id='chkTypeCode"+cdFlag+"']").length != 0){
		var strArr = new Array();
		var idx = 0;
		var parent_code='';
		var pCode='';
		var pCode2='';
		
		$("input:checkbox[id='chkTypeCode"+cdFlag+"']").each(function(){
			if ($(this).is(":checked")){
				strArr[idx++] = $(this).val();	// 체크된 코드를 리스트에 저장
			}
		});
		
		if (strArr.length == 0){
			alert("체크된 항목이 없습니다.");
			return false;
		}
		
		if (confirm("하위 항목도 같이 삭제됩니다.\n    삭제하시겠습니까?")){
			var url = '/option/'+mUrl+'/delete.do';
			var data;
			var target;
			
			if(mUrl != 'reportType'){
				if (cdFlag == '1'){
					target = '#tabMenu';
					$('#divTypeCode2').empty();
					$('#divTypeCode3').empty();
				} else if (cdFlag == '2'){
					target = '#divTypeCode'+cdFlag;
					parent_code = typeCode0;
					$('#divTypeCode3').empty();
				} else {
					target = '#divTypeCode'+cdFlag;
					parent_code = typeCode0;
					pCode2 = typeCode1;
				}
			}else{
				if (cdFlag == '1'){
					target = '#tabMenu';
					parent_code = typeCode0;
					$('#divTypeCode2').empty();
					$('#divTypeCode3').empty();
				} else if (cdFlag == '2'){
					target = '#divTypeCode'+cdFlag;
					parent_code = typeCode1;
					$('#divTypeCode3').empty();
				} else {
					target = '#divTypeCode'+cdFlag;
					
					parent_code = typeCode1+typeCode2;
				}
			}
			
			if(mUrl=='infrm'){
				data = {
						"cdFlag" : cdFlag, 
						"pCode" : parent_code, 
						"pCode2" : pCode2, 
						"chkValList" : strArr, 
						"pageFlag" : pageFlag,
						"selArea" : $("#areaOptSel option:selected").val()
					};
			}else{
				data = {
						"cdFlag" : cdFlag, 
						"pCode" : parent_code, 
						"pCode2" : pCode2, 
						"chkValList" : strArr, 
						"pageFlag" : pageFlag
					};
			}
			
			sendAjaxRequest(url, data, target, delMent);
		} else {
			return false;
		}
	} else {
		alert(errorMent);
		return false;
	}
}

