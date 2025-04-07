document.addEventListener("DOMContentLoaded", function () {
    const signInBtn = document.getElementById("signInBtn"); // '다음' 버튼
    const userIdElement = document.getElementById("userId");
    const newPasswordInput = document.getElementById("newPass");
    const confirmPasswordInput = document.getElementById("confirmNewPass");


    // 1️⃣ `sessionStorage`에서 사용자 아이디 가져와 자동 입력
    const userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    if (userInfo && userInfo.id) {
        userIdElement.textContent = userInfo.id;  // 아이디 표시
    } else {
        alert("사용자 정보를 불러올 수 없습니다. 다시 시도해주세요.");
        window.location.href = "/farmstory/find/findId.do"; // 정보 없으면 아이디 찾기 페이지로 이동
        return;
    }

    // 2️⃣ '비밀번호 변경' 버튼 클릭 이벤트
    signInBtn.addEventListener("click", function(event){
        event.preventDefault();

        const userId = userInfo.id;  // 가져온 사용자 ID
        const newPassword = newPasswordInput.value.trim();
        const confirmPassword = confirmPasswordInput.value.trim();
		
		console.log("전송할 userId:", userId);
        console.log("전송할 newPassword:", newPassword);

        // 3️⃣ 입력값 검증 (비밀번호 길이, 일치 여부 확인)
        if (!newPassword || !confirmPassword) {
            alert("비밀번호를 입력해주세요.");
            return;
        }

        if (newPassword.length < 8 || !/[A-Za-z]/.test(newPassword) || !/\d/.test(newPassword) || !/[!@#$%^&*]/.test(newPassword)) {
            alert("비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.");
            return;
        }

        if (newPassword !== confirmPassword) {
            alert("새 비밀번호가 일치하지 않습니다.");
            return;
        }
		

        // 4️⃣ 서버에 비밀번호 변경 요청 (AJAX)
        fetch("/farmstory/find/changePass.do", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({
                userId: userId,
                newPassword: newPassword,
            }).toString()
			
        })
        .then(response => response.json())
		.then(data => {
		            alert(data.message);
		            if (data.status === "success") {
		                sessionStorage.removeItem("userInfo"); // 세션 스토리지 초기화
		                window.location.href = "/farmstory/signin"; // 로그인 페이지로 이동
		            }
		        })
		        .catch(error => console.error("비밀번호 변경 오류:", error));
					    });
			
		});