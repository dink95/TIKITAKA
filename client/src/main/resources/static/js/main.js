AOS.init({
	duration: 800,
	easing: 'slide',
	once: true
});

jQuery(document).ready(function ($) {

	"use strict";

	var slider = function () {
		$('.nonloop-block-3').owlCarousel({
			center: false,
			items: 1,
			loop: false,
			stagePadding: 15,
			margin: 20,
			nav: true,
			navText: ['<span class="icon-arrow_back">', '<span class="icon-arrow_forward">'],
			responsive: {
				600: {
					margin: 20,
					items: 2
				},
				1000: {
					margin: 20,
					items: 3
				},
				1200: {
					margin: 20,
					items: 3
				}
			}
		});
	};
	slider();


	var siteMenuClone = function () {

		$('<div class="site-mobile-menu"></div>').prependTo('.site-wrap');

		$('<div class="site-mobile-menu-header"></div>').prependTo('.site-mobile-menu');
		$('<div class="site-mobile-menu-close "></div>').prependTo('.site-mobile-menu-header');
		$('<div class="site-mobile-menu-logo"></div>').prependTo('.site-mobile-menu-header');

		$('<div class="site-mobile-menu-body"></div>').appendTo('.site-mobile-menu');


		$('.js-logo-clone').clone().appendTo('.site-mobile-menu-logo');

		$('<span class="ion-ios-close js-menu-toggle"></div>').prependTo('.site-mobile-menu-close');


		$('.js-clone-nav').each(function () {
			var $this = $(this);
			$this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
		});


		setTimeout(function () {

			var counter = 0;
			$('.site-mobile-menu .has-children').each(function () {
				var $this = $(this);

				$this.prepend('<span class="arrow-collapse collapsed">');

				$this.find('.arrow-collapse').attr({
					'data-toggle': 'collapse',
					'data-target': '#collapseItem' + counter,
				});

				$this.find('> ul').attr({
					'class': 'collapse',
					'id': 'collapseItem' + counter,
				});

				counter++;

			});

		}, 1000);

		$('body').on('click', '.arrow-collapse', function (e) {
			var $this = $(this);
			if ($this.closest('li').find('.collapse').hasClass('show')) {
				$this.removeClass('active');
			} else {
				$this.addClass('active');
			}
			e.preventDefault();

		});

		$(window).resize(function () {
			var $this = $(this),
				w = $this.width();

			if (w > 768) {
				if ($('body').hasClass('offcanvas-menu')) {
					$('body').removeClass('offcanvas-menu');
				}
			}
		})

		$('body').on('click', '.js-menu-toggle', function (e) {
			var $this = $(this);
			e.preventDefault();

			if ($('body').hasClass('offcanvas-menu')) {
				$('body').removeClass('offcanvas-menu');
				$this.removeClass('active');
			} else {
				$('body').addClass('offcanvas-menu');
				$this.addClass('active');
			}
		})

		// click outisde offcanvas
		$(document).mouseup(function (e) {
			var container = $(".site-mobile-menu");
			if (!container.is(e.target) && container.has(e.target).length === 0) {
				if ($('body').hasClass('offcanvas-menu')) {
					$('body').removeClass('offcanvas-menu');
				}
			}
		});
	};
	siteMenuClone();


	var sitePlusMinus = function () {
		$('.js-btn-minus').on('click', function (e) {
			e.preventDefault();
			if ($(this).closest('.input-group').find('.form-control').val() != 0) {
				$(this).closest('.input-group').find('.form-control').val(parseInt($(this).closest('.input-group').find('.form-control').val()) - 1);
			} else {
				$(this).closest('.input-group').find('.form-control').val(parseInt(0));
			}
		});
		$('.js-btn-plus').on('click', function (e) {
			e.preventDefault();
			$(this).closest('.input-group').find('.form-control').val(parseInt($(this).closest('.input-group').find('.form-control').val()) + 1);
		});
	};
	sitePlusMinus();


	var siteSliderRange = function () {
		$("#slider-range").slider({
			range: true,
			min: 0,
			max: 500,
			values: [75, 300],
			slide: function (event, ui) {
				$("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
			}
		});
		$("#amount").val("$" + $("#slider-range").slider("values", 0) +
			" - $" + $("#slider-range").slider("values", 1));
	};
	siteSliderRange();


	var siteMagnificPopup = function () {
		$('.image-popup').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			fixedContentPos: true,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			gallery: {
				enabled: true,
				navigateByImgClick: true,
				preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
			},
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});

		$('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
			disableOn: 700,
			type: 'iframe',
			mainClass: 'mfp-fade',
			removalDelay: 160,
			preloader: false,

			fixedContentPos: false
		});
	};
	siteMagnificPopup();


});

/**********************스크립트 추가*************************/


document.querySelectorAll("input").forEach(input => {
	input.addEventListener("invalid", () => {
		document.forms[0].classList.add("was-validated")
	})
})

function categoryChange(e) {
	var detail_passion = ["선택하세요", "남성의류", "여성의류", "아동의류", "가방", "모자",
		"신발", "엑세서리", "화장품", "기타"];
	var detail_digital = ["선택하세요", "pc/노트북", "휴대폰/태블릿", "카메라", "게임", "기타"];
	var detail_interior = ["선택하세요", "침실", "주방", "DIY", "서재/사무용", "기타"];
	var detail_leisure = ["선택하세요", "등산", "캠핑", "골프", "헬스", "공연/티켓", "여행", "기타"];
	var detail_else = ["선택하세요", "반려동물용품", "차량용품", "출산/유아용품"];
	var detail_buy = ["선택하세요", "삽니다"];
	var target = document.getElementById("detail");

	$('#selectedMain').val(e.value);
	if (e.value == "passion") var d = detail_passion;
	if (e.value == "digital") var d = detail_digital;
	if (e.value == "interior") var d = detail_interior;
	if (e.value == "leisure") var d = detail_leisure;
	if (e.value == "else") var d = detail_else;
	else if (e.value == "buy") var d = detail_buy;

	target.options.length = 0;

	for (x in d) {
		var opt = document.createElement("option");
		opt.value = d[x];
		opt.innerHTML = d[x];
		target.appendChild(opt);
	}

	if ($('#category').val() == "메인카테고리") {
		$('#detail').attr("disabled", true);
	} else {
		$('#detail').attr("disabled", false);
	}

}

var arr = [["남성의류", "여성의류", "아동의류", "가방", "신발", "모자", "액세서리", "화장품", "기타"],
	["pc/노트북", "휴대폰/태블릿", "카메라", "게임", "기타"],
	["침실", "주방","거실", "DIY", "서재/사무용", "기타"],
	["등산", "캠핑", "골프", "헬스", "공연/티켓", "여행", "기타"],
	["반려동물용품", "차량용품", "출산/유아용품"],
	["삽니다"]];


function categoryChangeDetail(e) {

	for (var i = 0; i < arr.length; i++) {
		for (var j = 0; j < arr[i].length; j++) {
			if(e.value=="선택하세요"){
				$('#selectedDetail').val('');
			}
			else if($('#selectedMain').val()=="passion"){
				if (arr[0][j] == e.value) {
					$('#selectedDetail').val(10+j);
				}
			}

			else if($('#selectedMain').val()=="digital"){
				if (arr[1][j] == e.value) {
					$('#selectedDetail').val(20 + j);
				}
			}
			else if($('#selectedMain').val()=="interior"){
				if (arr[2][j] == e.value) {
					$('#selectedDetail').val(30 + j);
				}
			}
			else if($('#selectedMain').val()=="leisure"){
				if (arr[3][j] == e.value) {
					$('#selectedDetail').val(40 + j);
				}
			}
			else if($('#selectedMain').val()=="else"){
				if (arr[4][j] == e.value) {
					$('#selectedDetail').val(50 + j);
				}
			}
			else if($('#selectedMain').val()=="buy"){
				if (arr[5][j] == e.value) {
					$('#selectedDetail').val(60 + j);
				}
			}

		}
	}

}


function CheckId(str) {


	var reg_id = /^[a-zA-Z0-9]{4,12}$/; //id 유효성 검사
	if (!reg_id.test(str)) {
		return false;
	} else {
		return true;
	}
}


/* 이메일 유효셩*/
function CheckEmail(str) {
	var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+).(\.[0-9a-zA-Z_-]+){1,2}$/;
	if (!reg_email.test(str)) {
		return false;
	} else {
		return true;
	}
}

/*이름유효성*/
function CheckName(str) {
	var reg_name = /^[가-힣]{2,4}$/;
	if (!reg_name.test(str)) {
		return false;
	} else {
		return true;
	}
}

function autoHypenPhone(str) {
	str = str.replace(/[^0-9]/g, '');
	var tmp = '';
	if (str.length < 4) {
		return str;
	} else if (str.length < 7) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	} else if (str.length < 11) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	} else {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
	return str;
}

function autoNumber(str) {
	str = str.replace(/[^0-9]/g, '');
	return str;

}

$(".dropdown img.flag").addClass("flagvisibility");

$(".dropdown dt a ").hover(function () {
	$(".dropdown dd ul").toggle();
});

$(".dropdown dd a ").hover(function () {
	$(".dropdown dd ul").toggle();
});

$(".dropdown dd ul li a").click(function () {
	var text = $(this).html();
	//$(".dropdown dt a span").html(text);
	$(".dropdown dd ul").hide();
	/* $("#result").html("Selected value is: " + getSelectedValue("sample"));*/
});

function getSelectedValue(id) {
	return $("#" + id).find("dt a span.value").html();
}

$(document).bind('click', function (e) {
	var $clicked = $(e.target);
	if (!$clicked.parents().hasClass("dropdown"))
		$(".dropdown dd ul").hide();
});

$(".dropdown img.flag").toggleClass("flagvisibility");

function enterkey() {
	if (window.event.keyCode == 13) {


	}
}

function modal(content, button, href) {
	$('#modal_overlay').css('display', 'flex');
	$('#modal_overlay').animate({opacity: '1'}, 300);
	$('#modal_content').text(content);
	$('#modal_button').text(button);
	$('#modal_button').attr('href', href);

}

function pre_modal(content, button) {
	$('#modal_overlay').css('display', 'flex');
	$('#modal_overlay').animate({opacity: '1'}, 300);
	$('#modal_content').text(content);
	$('#modal_button').text(button);
	$('#modal_button').click(function () {
		$('#modal_overlay').css('display', 'none');
	});

}

/*input 포커스*/
var len = $('input').val().length;
$('input').focus();
$('input')[0].setSelectionRange(len, len);

/*스크롤 위로 이동*/
function scroll_up() {
	$("html, body").animate({scrollTop: 0}, "slow");
};


