/**
 * 
 */
$(function() {
	$('.submit').click(function() {
				var sectionId = $('#sectionId').val();
				var nickname = $('#nickname').val();
				var password = $('#password').val();
				var passConfirm = $('#password_confirm').val();
				var emailId = $('#email').val();
				var eamilDomain =$('#emailDomain').val();
				var eamil='';
				if(emailId != '') {
					email = emailId +"@"+eamilDomain;
				}
				alert(email);
				var regNicknameLengthCheck = /^[a-z0-9]{3,10}$/i;
				var regPasswordLengthCheck = /^[a-z0-9]{6,20}$/i;                            // 패스워드 길이가 6-20 사이인지 확인
				var regConditionCheck = /([a-z]+[0-9]+|[0-9]+[a-z]+)[a-z0-9]*/i;        // 최소 숫자 하나, 문자 하나 이상 입력했는지 확인
				
				if (nickname == '') {
					alert("아이디를 입력해주세요.");
				} else if(password == '' || passConfirm =='') {
					alert("비밀번호를 입력해주세요.");
				} else if(password != passConfirm) {
					alert("비밀번호가 일치하지 않습니다.");
				} else if(!(regNicknameLengthCheck.test(nickname) && regConditionCheck.test(nickname))) {
					alert("1자 이상의 영문자, 1자 이상의 숫자로 최소 3에서 최대 10자의 아이디로 만들어주세요.");
				} else if(!(regPasswordLengthCheck.test(password) && regConditionCheck.test(password))) {
					alert("1자 이상의 영문자, 1자 이상의 숫자로 최소 6에서 최대 20자의 비밀번호로 만들어주세요.");
				} else {
					$.ajax({
						type : 'POST',
						data : "sectionId=" + sectionId + "&nickname="
								+ nickname + "&password=" + password + "&email="+email, 
						dataType : 'text',
						url : '/join/done',
						success : function(data) {
							if (data == 1) {
								alert("가입에 성공하였습니다.");
								document.location.href = "/home";
							} else if(data == -1) {
								alert("같은 닉네임이 존재합니다.");
							} else {
								alert("가입에 실패하였습니다.");
							}
						},
						error : function(e) {
							alert("error: " + e);
						}
					})
				}
			});

});