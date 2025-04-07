document.addEventListener("DOMContentLoaded", function () {
    // 회원 정보 수정 버튼 클릭 이벤트
    document.getElementById("updateInfoBtn").addEventListener("click", function () {
        const name = document.getElementById("name").value.trim();
        const nickname = document.getElementById("nickname").value.trim();
        const email = document.getElementById("email").value.trim();
        const phone = document.getElementById("phone").value.trim();
        const address = document.getElementById("address").value.trim();
        const detailAddress = document.getElementById("detailAddress").value.trim();

        if (!name || !nickname || !email || !phone || !address || !detailAddress) {
            alert("모든 필드를 입력해주세요.");
            return;
        }

        fetch("/farmstory/my/modifyInfo.do", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({
                action: "update",
                name, nickname, email, phone, address, detailAddress
            }).toString()
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                alert("회원 정보가 수정되었습니다.");
                window.location.reload();
            } else {
                alert("회원 정보 수정에 실패했습니다.");
            }
        })
        .catch(error => console.error("수정 오류:", error));
    });

    // 비밀번호 변경 버튼 클릭 이벤트
    document.getElementById("updatePasswordBtn").addEventListener("click", function () {
        const password = document.getElementById("password").value.trim();
        const passwordConfirm = document.getElementById("passwordConfirm").value.trim();

        if (!password || !passwordConfirm) {
            alert("새 비밀번호를 입력해주세요.");
            return;
        }
        if (password !== passwordConfirm) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        fetch("/farmstory/my/modifyInfo.do", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({
                action: "updatePassword",
                password
            }).toString()
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                alert("비밀번호가 변경되었습니다.");
                window.location.reload();
            } else {
                alert("비밀번호 변경에 실패했습니다.");
            }
        })
        .catch(error => console.error("비밀번호 변경 오류:", error));
    });
	// 우편번호 찾기 버튼 클릭 이벤트
	    document.getElementById("findZipcodeBtn").addEventListener("click", function () {
	        new daum.Postcode({
	            oncomplete: function (data) {
	                document.getElementById("zipcode").value = data.zonecode; // 우편번호 입력
	                document.getElementById("address").value = data.address; // 주소 입력
	                document.getElementById("detailAddress").focus(); // 상세주소 입력란으로 이동
	            }
	        }).open();
	    });

    // 회원 탈퇴 버튼 클릭 이벤트
    document.getElementById("deleteAccountBtn").addEventListener("click", function () {
        if (!confirm("정말 탈퇴하시겠습니까?")) {
            return;
        }

        fetch("/farmstory/my/modifyInfo.do", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({ action: "delete" }).toString()
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                alert("회원 탈퇴가 완료되었습니다.");
                window.location.href = "/farmstory/index.do";
            } else {
                alert("회원 탈퇴에 실패했습니다.");
            }
        })
        .catch(error => console.error("탈퇴 오류:", error));
    });
});
