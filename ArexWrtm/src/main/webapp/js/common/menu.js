console.info('[Loading : Main JS].......................');
	//-- 그림소프트 사용 메뉴이동.(수정금지)
	const grim_menu_open = function(url){
		let enc_url = ezgoUtils.getUrl(url)
		Main().moveMenu(enc_url);
	}
	const Main = function(){
		const model = (function(){
	    }());

		//-- 메뉴 처리 Controller
		const menuCtrl = (function(){
			//-- TabList 이벤트
			const tabListEvent = function(){
				$("button.nav-link").off();
				$("button.nav-link").on("click", function(){
					$(".nav-link").removeClass("active");
					let item = $(this).data("item");
					$(this).addClass("active");
					let li = $("#"+item.menuId);
					//-- class 활성화 처리
					setClass(li, item);
					if(item.hasOwnProperty("urlPath") && item.urlPath !== null && item.urlPath !== ""){
						moveMenu(ezgoUtils.getUrl(item.urlPath));
					}
				});
			}
			const setClass = function(li , param){
				//-- class 활성화 처리
				$(".menu-item").removeClass("active");
				$(li).addClass("active");

				//-- 부모 Class active 처리
				if($(li).parent(".menu-sub").length > 0){
					$(li).parents(".menu-item").eq(0).addClass("active");
				}else{
					$(".menu-item").removeClass("open");
				}
				if(param.hasOwnProperty("data")){
					//-- 자식 class 활성화 처리
					$(".menu-item").removeClass("open");
					$(li).addClass("open");
					$(li).find("li").eq(0).addClass("active");
				}
			}
			//-- 메뉴 클릭 시 동작
			const clickMenu = function(li, isMove){
				isMove = (isMove === undefined || isMove === null) ? true : isMove;

				let param = $(li).data("item");

				let isOpen = $("body").hasClass("open"); 	//-- true:메뉴펼침, false:메뉴닫힘
				let isChild = param.hasOwnProperty("data"); //-- 자식존재여부
				if(!isOpen && isChild){
					$(".menu-item").removeClass("open");
					$(li).addClass("open");
					return false;
				}

				//-- TabList 생성
				let makeTabList = function(data, menuId){
					$(".title_segments").html("");
					$(data).each(function(i, o){
						let $btn = $("<button class='nav-link' role='tab' aria-selected='false'>");
						$btn.data("item", o);
						$btn.text(ezgoUtils.getLang(o.interLangCd));
						if(typeof menuId !== "undefined"){
							if(menuId == o.menuId){
								$btn.addClass("active"); //-- tab 선택 상태
							}
						}else if(i == 0){
							$btn.addClass("active"); //-- tab 선택 상태 (첫번째 tab)
						}
						$(".title_segments").append($btn);
					});
					tabListEvent();	//-- TabList 생성완료 후 이벤트 ADD
					//<button type="button" class="nav-link active" role="tab" aria-selected="true">CSMS인증현황</button>
				}
				//-- class 활성화 처리
				setClass(li, param);

				//-- 자식이 있을때 자식 Tab생성
				if(param.hasOwnProperty("data")){
					makeTabList(param.data);
					param = param.data[0];  //-- 자식의 첫번째가 열리도록 설정
				}else if(param.levelNo !== 1){
					let item = $("#"+param.parentMenuId).data("item");	//-- Level 2일때 부모 item의 자식으로 TabList 생성
					makeTabList(item.data, param.menuId);
				}else{
					makeTabList([param]);	//-- 자기 자신으로 TabList 생성
				}
				//-- 기본 파라메터 설정
				let parameter = {};
				if(param.hasOwnProperty("params") && param.params !== null && param.params !== ""){
					parameter = JSON.parse(param.params);
				}


				//-- urlPaht(메뉴링크) 가 있을 경우 페이지 로드
				if(param.hasOwnProperty("urlPath") && param.urlPath !== null &&
						param.urlPath !== "" &&
						isMove){
					moveMenu(ezgoUtils.getUrl(param.urlPath), parameter);
				}
			}
			//-- Left 메뉴 생성
			const drawMenu = function(param){
				//-- a Tag 로 warping
				let makeText = function(o){
					let $a = $("<a href='#' class='menu-link'>");
					$a.css("color","#fff");
					if(o.hasOwnProperty("data") && $(o.data).length > 0){
						$a.addClass("menu-toggle");
					}
					if(o.hasOwnProperty("relImgPath") && o.relImgPath !=null && o.relImgPath != ""){
						$a.append("<i class='menu-icon "+ o.relImgPath +"'>");
					}
					$a.append("<div>"+ ezgoUtils.getLang(o.interLangCd) +"</div>"); //-- 메뉴명 다국어 처리
					return $a;
				}
				//-- Level 2 노드 생성(자식노드)
				let makeChild = function(child){
					let $ul = $("<ul class='menu-sub'>");
					$(child).each(function(i, o){
						let li = $("<li class='menu-item'>");
						li.attr("id", o.menuId);
						li.append(makeText(o));
						li.data("item",o);
						if(o.hasOwnProperty("data") && $(o.data).length > 0){
							let childUl = makeChild(o.data);	//-- 자식이 있으경우 재귀함수 호출.
							$li.append(childUl);
						}
						$ul.append(li);
					});
					return $ul;
				}
				let $ulRoot = $("<ul class='menu-inner'>"); //-- 최상위 UL
				$(param).each(function(i, o){
					let $li = $("<li class='menu-item'>");
					$li.append(makeText(o));
					$li.data("item",o);
					$li.attr("id", o.menuId);
					if(o.hasOwnProperty("data") && $(o.data).length > 0){
						let $childUl = makeChild(o.data); //-- 자식이 있으경우 재귀함수 호출.
						$li.append($childUl);
					}
					$ulRoot.append($li);
				});
				$("#menuNav").html("");
				$("#menuNav").append($ulRoot); //-- 메뉴영역에 Draw
			}

			const $content = $("#contents");	//-- 콘텐츠 영역
			//-- 링크로 콘텐츠영역 Load
			const moveMenu = function(url, paramData){
				let param = $.extend({}, paramData);
				// 페이지 이동시 기존 페이지 리소스 해제
				let excepObj = {};
				for(let key in webix.ui.views){
					let view = webix.ui.views[key].config.view;
//					excepObj[key] = view;
					if(",tree,suggest,contextmenu,neoForm,daterangepicker,daterange,window,calendar,pager,datatable,".indexOf(","+view+",") > -1){
						webix.ui.views[key].destructor();
					}else{
						excepObj[key] = view;
					}
				}
				//-- 뒤로가기 설정용
				if(!param.hasOwnProperty("isBack")){
					var arrUrl = url.split("/");
					var urlState = window.location.protocol + "//" + window.location.host + window.location.pathname;
					urlState +="#"+arrUrl[$(arrUrl).length -1];
					var tempParams = $.extend({}, param);
					tempParams.url = url;

					history.pushState(tempParams, "#"+arrUrl[$(arrUrl).length -1] ,urlState);
				}


				$content.load(url, param,function(e){
					ezgoUtils.convertLang($content); //-- 콘텐츠 영역 로드 후 다국어 처리
				});
			}
			//-- 메뉴 관련 이벤트 ADD
			const setMenuEvent = function(){
				$("#menuNav").off("click", "li");
				$("#menuNav").on("click", "li", function(){
					clickMenu($(this));
					return false;
				});
			}
			//-- 메뉴 초기화
			const init = function(param){
				drawMenu(param);
				setMenuEvent();
			}
			return {
				init: init,
				moveMenu: moveMenu,
				clickMenu: clickMenu,
			};
		}());

		const $rootNode = $("#container");
		let sitelocale = $rootNode.data("sitelocale");
		let opts = {changeLocale: ""};


		//-- 메뉴 생성 로드
		const loadMenu = function(){

			let callback = function(json){
				menuCtrl.init(ezgoUtils.getWebixTreeDataFormat(json.data, "TOP_MENU"))
			}
			model.getAuthMenuList(callback);
		}
	}