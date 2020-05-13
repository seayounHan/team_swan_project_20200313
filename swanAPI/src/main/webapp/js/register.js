/**
 * 
 */
$(function() {
	$('.submit').click(
			function() {
				var storeName = $('#store_name').val();
				var category = $('#category').val();
				var sectionId = $('#sectionId').val();
				var address1 = $('#address1').val();
				var address2 = $('#address2').val();
				var zipcode = $('#zipcode').val();
				var coordinates = $('#coordinates').val();
				var tel_main_1 = $('#tel_main_1').val();
				var tel_main_2 = $('#tel_main_2').val();
				var tel_main_3 = $('#tel_main_3').val();
				var tel_sub_1 = $('#tel_sub_1').val();
				var tel_sub_2 = $('#tel_sub_2').val();
				var tel_sub_3 = $('#tel_sub_3').val();
				var img1=$('#img1').val();
				var img2=$('#img2').val();
				var storeUrl =$('#store_url').val();
				var tagKeyword = $('#tag_keyword').val();
				var tagMenu = $('#tag_menu').val();
				var tagService = $('#tag_service').val();
				var open = $('#open').val();
				var nickname = $('#nickname').val();

				var telMain='';
				if(tel_main_2 != '' && tel_main_3 !='') {
					telMain = tel_main_1+"-"+tel_main_2+"-"+tel_main_3;
				}
				
				var telSub='';
				if(tel_sub_2 !='' && tel_sub_3 != '') {
					telSub = tel_sub_1+"-"+tel_sub_2+"-"+tel_sub_3;
				}
				
				if (storeName == '') {
					alert("업체명을 입력해주세요.");
				} else if(category == '' ) {
					alert("분류를 입력해주세요.");
				} else if(address1 == '' ) {
					alert("주소1을 입력해주세요.");
				} else if(telMain == '' ) {
					alert("전화번호를 입력해주세요.");
				} else if(img1 == '' ) {
					alert("이미지를 입력해주세요.");
				} else if(tagKeyword == '' ) {
					alert("태그 키워드를 입력해주세요.");
				} else if(tagMenu == '' ) {
					alert("메뉴 태그를 입력해주세요.");
				} else {
					$.ajax({
						type : 'POST',
						data : "storeName="+storeName+ "&category="+category 
								+ "&sectionId=" + sectionId + "&address1=" + address1
								+ "&address2="+address2 + "&zipcode="+zipcode
								+ "&coordinates="+coordinates + "&telMain="+telMain
								+ "&telSub="+telSub
								+ "&img1=" +img1 + "&img2="+img2 + "&storeUrl="+storeUrl
								+ "&tagKeyword="+tagKeyword + "&tagMenu="+tagMenu
								+ "&tagService="+tagService + "&open="+open + "&nickname="+nickname,
								
						dataType : 'text',
						url : '/home/store/register/done',
						success : function(data) {
							if (data == "true") {
								alert("등록되었습니다.");
								document.location.href = "/home";
							} else {
								alert("등록에 실패하였습니다.");
							}
						},
						error : function(e) {
							alert("error: " + e);
						}
					})
				}
			});

});

$(function() {
	$('.search_address').click(function() {
		new daum.Postcode({
            oncomplete: function(data) {
            	var address1=data.address;
            	var zipcode=data.postcode;
            	
            	$('#address1').val(address1);
            	$('#zipcode').val(zipcode);
            	
            }
        }).open();
	});
})