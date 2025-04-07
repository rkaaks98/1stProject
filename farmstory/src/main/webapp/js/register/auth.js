const AUTH_CODE_REGEX = /^[0-9]{6}$/;

function disable(button) {
  button.style.filter = "grayscale(100%)";
  button.style.cursur = "default";
}

document.addEventListener("DOMContentLoaded", () => {
  const emailInput = document.querySelector(
    "main .container form input[name='email']"
  );
  const emailCodeInput = document.getElementById("emailCodeInput");
  const emailBtn = document.getElementsByClassName("emailAuthBtn")[0];
  const emailCodeBtn = document.getElementById("emailCodeBtn");
  const emailCodeArea = document.getElementById("emailCodeArea");
  const emailResult = document.getElementsByClassName("emailResult")[0];

  emailCodeArea.style.display = "none";
  disable(emailCodeBtn);

  async function requestCode(address) {
    fetch(`/farmstory/auth?address=${address}`)
      .then((response) => {
        console.log(response);
        if (response.status === 200) {
          emailCodeArea.style.display = "initial";
          emailInput.readonly = true;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  }

  emailBtn.addEventListener("click", () => {
    const emailAddr = emailInput.value;
    requestCode(emailAddr);
    emailBtn.style.filter = "grayscale(100%)";
    emailBtn.style.cursor = "default";
  });

  emailCodeBtn.addEventListener("click", () => {
    disable(emailCodeBtn);
    const body = {
      code: emailCodeInput.value,
    };
    const jsonString = JSON.stringify(body);

    fetch("/farmstory/auth", {
      method: "POST",
      body: jsonString,
      header: {
        "Content-type": "application/json;charset=UTF8",
      },
    })
      .then((res) => {
        if (res.status === 200)
          printValid(emailResult, "인증이 완료되었습니다!");
      })
      .catch((err) => {
        //FIXME handle error properly
        console.log(err);
      });
  });

  emailCodeInput.addEventListener("keyup", () => {
    validateDisable(
      emailCodeInput,
      AUTH_CODE_REGEX,
      emailResult,
      "유효한 인증번호 입니다.",
      "유효하지 않은 인증번호 입니다.",
      emailCodeBtn
    );
  });
});
