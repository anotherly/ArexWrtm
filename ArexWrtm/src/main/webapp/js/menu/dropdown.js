
		$(document).on("click", ".nav-list li.magz-dropdown > a", function() {
			var $this = $(this),
					$parent = $this.parent(),
					$titleBefore = $this.text(),
					$back = '<div class="back"><i class="ion-ios-arrow-left"></i></div>';

			if($(".nav-title .back").length) {
				var titleNow = $(".nav-title .back").attr('data-title');
				titleNow += ("," + $this.text());
				$(".nav-title .back").attr('data-title', titleNow);
			}else{
				$(".nav-title").prepend($($back).attr('data-title', $(".nav-title a").text() + "," + $this.text()));
			}
			$(".nav-title a").html($this.text());
			$parent.find("> .dropdown-menu").fadeIn(100).addClass("active");
			return false;
		});

		var titleLen = 0;
		$(document).on("click", ".nav-title .back", function() {
			var $dd = $(".dropdown-menu.active"),
					$len = $dd.length,
					title;

			$dd.eq($len-1).removeClass("active");
			setTimeout(function() {
				$dd.eq($len-1).hide();
			},500);
			title = $(this).attr('data-title').split(",");
			titleLen = title.length-1;
			title = title.splice(0, titleLen);
			$(".nav-title a").text(title[title.length-1]);
			$(".nav-title .back").attr('data-title', title);
			if((title.length-1) == 0) {
				$(".nav-title .back").remove();
			}
			return false;
		});