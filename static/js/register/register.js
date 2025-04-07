const ID_REGEX = /^[a-z]+[a-z0-9]{4,19}$/g;
const PASSWORD_REGEX =
  /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)-_=+]).{5,16}$/;
const NAME_REGEX = /^[가-힣]{2,10}$/;
const NICKNAME_REGEX = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
const EMAIL_REGEX =
  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
const PHONE_NUM_REGEX = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
const DETAIL_ADDRESS_REGEX = /[가-힣 1-9\-]+/g;
const jsonObject = {
  id: "",
  password: "",
  passwordConfirm: "",
  name: "",
  nickname: "",
  email: "",
  phoneNum: "",
  zip: "",
  address: "",
  addressDetail: "",
};

function findPostCode(zipInput, addressInput) {
  // Put those information into proper input html tags
  new daum.Postcode({
    oncomplete: function (data) {
      jsonObject.zip = data.zonecode;
      zipInput.value = jsonObject.zip;
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택한 경우
        jsonObject.address = data.roadAddress;
        addressInput.value = jsonObject.address;
      } else if (data.userSelectedType === "J") {
        // 사용자가 지번 주소를 선택한 경우
        jsonObject.address = data.jibunAddress;
        addressInput.value = jsonObject.address;
      }
    },
  }).open();
}

// '회원가입' 버튼을 클릭할 경우 실행되는 이벤트 핸들러 함수
function onSubmit(event) {
  event.preventDefault();
  const inputs = document.getElementsByClassName("register-input");
  // 입력하지 않은 input이 있는지 확인
  for (input of inputs) {
    if (input.value === "") {
      //값이 입력되지 않은 input이 있는 경우
      alert(`${input.placeholder}(을)를 입력하지 않았습니다.`);
      break;
    }
  }
  // jsonObject를 Stringify하고 서버로 HTTP POST 요청 전송
  jsonString = JSON.stringify(jsonObject);
  fetch("/farmstory/signup", {
    method: "POST",
    body: jsonString,
    header: {
      "Content-type": "application/json;charset=UTF8",
    },
  });
}

// 사용자의 입력값이 유효한 경우 사용되는 함수.
// input 태그 옆의 span에 전달받은 메시지를 출력.
function printValid(resultElement, validMessage) {
  resultElement.innerText = "✔ " + validMessage;
  resultElement.style.color = "green";
}

// 사용자의 입력값이 유효하지 않은 경우 사용되는 함수.
// input 태그 옆의 span에 전달받은 메시지를 출력.
function printInvalid(resultElement, invalidMessage) {
  resultElement.innerText = "❌ " + invalidMessage;
  resultElement.style.color = "red";
}

// 사용자가 입력한 값의 유효성을 검사
function validate(
  inputElement,
  regex,
  resultElement,
  validMessage,
  invalidMessage
) {
  let currentValue = "";
  currentValue += inputElement.value;
  if (currentValue.match(regex)) {
    printValid(resultElement, validMessage);
  } else {
    printInvalid(resultElement, invalidMessage);
  }
}

// Document 로딩이 끝난 후에 이벤트 핸들러 함수를 실행
document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("main .container form");
  const idInput = document.querySelector(
    "main .container form input[name='id']"
  );

  const passwordInput = document.querySelector(
    "main .container form input[name='password']"
  );

  const passwordConfirmInput = document.querySelector(
    "main .container form input[name='password_confirm']"
  );

  const nameInput = document.querySelector(
    "main .container form input[name='name']"
  );

  const nicknameInput = document.querySelector(
    "main .container form input[name='nickname']"
  );

  const emailInput = document.querySelector(
    "main .container form input[name='email']"
  );

  const phoneNumInput = document.querySelector(
    "main .container form input[name='phone_num']"
  );

  const zipInput = document.querySelector(
    "main .container form input[name='zip']"
  );

  const addressInput = document.querySelector(
    "main .container form input[name='address']"
  );

  const addressDetailInput = document.querySelector(
    "main .container form input[name='address_detail']"
  );
  const idResult = document.getElementsByClassName("idResult")[0];
  const passwordResult = document.getElementsByClassName("passwordResult")[0];
  const pwConfirmResult = document.getElementsByClassName(
    "passwordConfirmResult"
  )[0];
  const nameResult = document.getElementsByClassName("nameResult")[0];
  const nicknameResult = document.getElementsByClassName("nicknameResult")[0];
  const emailResult = document.getElementsByClassName("emailResult")[0];
  const phoneNumResult = document.getElementsByClassName("phoneNumResult")[0];
  const postCodeBtn = document.getElementById("findPostCode");
  const addressDetailResult = document.getElementsByClassName(
    "addressDetailResult"
  )[0];

  // 사용자가 input 태그에 값을 입력할때마다 이벤트 핸들러 함수들이 실행됨.
  idInput.addEventListener("keyup", () => {
    // 매 키보드 입력마다 validate()함수를 호출
    validate(
      idInput,
      ID_REGEX,
      idResult,
      "사용가능한 아이디입니다.",
      "사용불가능한 아이디 입니다."
    );
  });

  // input 태그의 focusout 이벤트가 발생하면 태그의 value값이 jsonObject의 적절한 필드에 저장됨.
  idInput.addEventListener("focusout", (event) => {
    jsonObject.id = event.target.value;
  });

  passwordInput.addEventListener("keyup", () => {
    validate(
      passwordInput,
      PASSWORD_REGEX,
      passwordResult,
      "사용가능한 비밀번호 입니다.",
      "사용불가능한 비밀번호 입니다."
    );
  });

  passwordInput.addEventListener("focusout", (event) => {
    jsonObject.password = event.target.value;
  });

  passwordConfirmInput.addEventListener("keyup", () => {
    let currentValue = "";
    const password = passwordInput.value;
    currentValue += passwordConfirmInput.value;
    if (currentValue === password) {
      printValid(pwConfirmResult, "비밀번호가 일치합니다.");
    } else {
      printInvalid(pwConfirmResult, "비밀번호가 일치하지 않습니다..");
    }
  });

  passwordConfirmInput.addEventListener("focusout", (event) => {
    jsonObject.passwordConfirm = event.target.value;
  });

  nameInput.addEventListener("keyup", () => {
    validate(
      nameInput,
      NAME_REGEX,
      nameResult,
      "유효한 이름입니다.",
      "유효하지 않은 이름입니다."
    );
  });

  nameInput.addEventListener("focusout", (event) => {
    jsonObject.name = event.target.value;
  });

  nicknameInput.addEventListener("keyup", () => {
    validate(
      nicknameInput,
      NICKNAME_REGEX,
      nicknameResult,
      "유효한 별명입니다.",
      "유효하지 않은 별명입니다."
    );
  });

  nicknameInput.addEventListener("focusout", (event) => {
    jsonObject.nickname = event.target.value;
  });

  emailInput.addEventListener("keyup", () => {
    validate(
      emailInput,
      EMAIL_REGEX,
      emailResult,
      "유효한 이메일입니다.",
      "유효하지 않은 이메일입니다."
    );
  });

  emailInput.addEventListener("focusout", (event) => {
    jsonObject.email = event.target.value;
  });

  phoneNumInput.addEventListener("keyup", () => {
    validate(
      phoneNumInput,
      PHONE_NUM_REGEX,
      phoneNumResult,
      "유효한 전화번호입니다.",
      "유효하지 않은 전화번호입니다"
    );
  });

  phoneNumInput.addEventListener("focusout", (event) => {
    jsonObject.phoneNum = event.target.value;
  });

  addressDetailInput.addEventListener("keyup", () => {
    validate(
      addressDetailInput,
      DETAIL_ADDRESS_REGEX,
      addressDetailResult,
      "유효한 주소입니다.",
      "유효하지 않은 주소입니다."
    );
  });

  addressDetailInput.addEventListener("focusout", (event) => {
    jsonObject.addressDetail = event.target.value;
  });

  //TODO 1. Use Zip-finding API, to get zip, address, detail address information
  postCodeBtn.addEventListener("click", () => {
    findPostCode(zipInput, addressInput);
  });

  form.addEventListener("submit", (event) => {
    onSubmit(event);
  });
});
