async function doCheck(name, value, resultElement) {
  await fetch(`/farmstory/check?type=${name}&value=${value}`)
    .then((response) => {
      if (response.status === 200) {
        if (name === "id") {
          printValid(resultElement, "사용 가능한 아이디 입니다!");
        } else if (name === "nickname") {
          printValid(resultElement, "사용 가능한 별명 입니다!");
        }
      }
    })
    .catch((err) => {
      if (err.status === 409) {
        if (name === "id") {
          printInvalid(resultElement, "이미 사용중인 아이디 입니다");
        } else if (name === "nickname") {
          printInvalid(resultElement, "이미 사용중인 별명 입니다");
        }
      }
    });
}

document.addEventListener("DOMContentLoaded", () => {
  const idInput = document.querySelector(
    "main .container form input[name='id']"
  );
  const nicknameInput = document.querySelector(
    "main .container form input[name='nickname']"
  );

  const idBtn = document.getElementsByClassName("idCheckBtn")[0];
  const nicknameBtn = document.getElementsByClassName("nicknameCheckBtn")[0];

  const idResult = document.getElementsByClassName("idResult")[0];
  const nicknameResult = document.getElementsByClassName("nicknameResult")[0];

  idBtn.addEventListener("click", () => {
    const value = idInput.value;
    doCheck(idInput.name, value, idResult);
  });

  nicknameBtn.addEventListener("click", () => {
    const value = nicknameInput.value;
    doCheck(nicknameInput.name, value, nicknameResult);
  });
});
