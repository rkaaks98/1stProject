function send() {
  fetch("/farmstory/terms?agreed=true", {
    method: "POST",
    redirect: "follow",
  })
    .then((response) => {
      if (response.redirected) {
        //FIXME if Response.redirect doesn't work
        window.location.replace(response.url);
      }
    })
    .catch((err) => {
      //FIXME handle error properly
      console.log(err);
    });
}

function init(targetElement) {
  targetElement.disabled = true;
  targetElement.style.cursor = "default";
  targetElement.style.filter = "grayscale(100%)";
}

function onBothChecked(isTermChecked, isPrivacyChecked, target) {
  if (isTermChecked && isPrivacyChecked) {
    target.disabled = false;
    target.style.cursor = "pointer";
    target.style.filter = "none";
  } else {
    init(target);
  }
  return isTermChecked && isPrivacyChecked;
}

document.addEventListener("DOMContentLoaded", () => {
  const checkboxes = document.querySelectorAll(
    ".terms-area table tr td label input"
  );
  const sendButton = document.getElementById("sendBtn");
  const termCheckbox = checkboxes[0];
  const privacyCheckbox = checkboxes[1];

  init(sendButton);

  let isTermChecked = false;
  let isPrivacyChecked = false;
  let isBothChecked = false;
  termCheckbox.addEventListener("change", (event) => {
    isTermChecked = event.target.checked;
    isBothChecked = onBothChecked(isTermChecked, isPrivacyChecked, sendButton);
  });

  privacyCheckbox.addEventListener("change", (event) => {
    isPrivacyChecked = event.target.checked;
    isBothChecked = onBothChecked(isTermChecked, isPrivacyChecked, sendButton);
  });

  sendButton.addEventListener("click", () => {
    if (isBothChecked) {
      console.log("Both checked");
      send();
    }
  });
});
