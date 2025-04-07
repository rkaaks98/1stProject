document.addEventListener("DOMContentLoaded", function() {
    const sendCodeBtn = document.getElementById("sendCodeBtn");
    const verifyCodeBtn = document.getElementById("verifyCodeBtn");
    const authCodeInput = document.getElementById("authCode");
	const nextBtn = document.getElementById("nextBtn");
	

    // "인증번호 받기" 버튼 클릭 이벤트
    sendCodeBtn.addEventListener("click", function(event) {
        event.preventDefault();

        const id = document.getElementById("id").value.trim();
        const email = document.getElementById("email").value.trim();

        if (!id || !email) {
            alert("아이디와 이메일을 모두 입력해주세요.");
            return;
        }

        // 서버의 인증번호 발송 엔드포인트에 AJAX 요청 (예: /farmstory/find/sendAuthCode.do)
        fetch("/farmstory/find/emailAuth.do", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "name=" + encodeURIComponent(name) + "&email=" + encodeURIComponent(email)
        })
        .then(response => response.json())
        .then(data => {
			
			console.log(data);
			
            if (data.status == 'success') {
                alert(data.message);
                // 예제용: 서버에서 발송된 인증번호를 클라이언트 변수에 저장(실제에서는 보안상 클라이언트에 전달하지 않습니다)
                window.authCode = data.authCode;
            } else {
                alert(data.message);
            }
        })
        .catch(error => {
            console.error("인증번호 발송 중 오류:", error);
            alert("인증번호 발송 중 오류가 발생했습니다.");
        });
    });
	
	verifyCodeBtn.addEventListener("click", function(event) {
	        event.preventDefault();

	        const inputCode = authCodeInput.value.trim(); // 입력 필드에서 인증번호 가져오기

	        if (!inputCode) {
	            alert("인증번호를 입력해주세요.");
	            return;
	        }

	// 서버로 인증번호 검증 요청 (AJAX)
	    fetch("/farmstory/find/check.do", {
	        method: "POST",
	        headers: {
	            "Content-Type": "application/x-www-form-urlencoded",
	        },
	        body: "code=" + encodeURIComponent(inputCode),
	    })
	    .then(response => response.json())
	    .then(data => {
			
			console.log(data);
			
	        if (data.status === "success") {
	            alert(data.message);
	            window.isVerified = true;
	        } else {
	            alert(data.message);
	            window.isVerified = false;
	        }
	    })
	    .catch(error => console.error("Error:", error));
	});
	nextBtn.addEventListener("click", function (event) {
	        event.preventDefault(); // 기본 이동 방지

	        if (!window.isVerified) {
	            alert("인증을 완료해주세요.");
	            return;
	        }

	        const email = document.getElementById("email").value.trim();

	        fetch("/farmstory/find/findIdResult.do", {
	            method: "POST",
	            headers: {
	                "Content-Type": "application/x-www-form-urlencoded"
	            },
	            body: new URLSearchParams({ email }).toString(),
	        })
	        .then(response => response.json())
	        .then(data => {
				console.log("서버 응답 데이터 : ", data);
	            if (data.status === "success") {
	                // 사용자 정보를 sessionStorage에 저장
	                sessionStorage.setItem("userInfo", JSON.stringify({
						name: data.name,
		                id: data.id,  // 여기서 id 저장 확인
		                email: data.email,
		                registerDate: data.register_date  // 여기서 날짜 저장 확인
					}));

	                // 비밀번호 변경 페이지로 이동
	                window.location.href = "/farmstory/find/changePass.do";
	            } else {
	                alert(data.message);
	            }
	        })
	        .catch(error => console.error("사용자 정보 가져오기 오류:", error));
	    });
	});