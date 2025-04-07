document.addEventListener("DOMContentLoaded", function () {
        const userInfo = JSON.parse(sessionStorage.getItem("userInfo"));

        if (userInfo) {
            document.getElementById("userInfo").innerHTML = `
			<tr>
                <td>이름</td>
                <td>${userInfo.name || "정보 없음"}</td>
            </tr>
            <tr>
                <td>아이디</td>
                <td>${userInfo.id || "정보 없음"}</td>
            </tr>
            <tr>
                <td>이메일</td>
                <td>${userInfo.email || "정보 없음"}</td>
            </tr>
            <tr>
                <td>가입일</td>
                <td>${userInfo.registerDate || "정보 없음"}</td>
            </tr>
        `;
        } else {
            alert("유저 정보를 불러올 수 없습니다.");
        }
    });