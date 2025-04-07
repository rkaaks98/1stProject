async function send(json) {
  await fetch("/farmstory/signin", {
    method: "POST",
    body: JSON.stringify(json),
    headers: {
      "Content-type": "application/json;charset=utf-8",
    },
    redirect: "follow",
  })
    .then((response) => {
      if (response.redirected) {
        window.location.replace(response.url);
      }
    })
    .catch((err) => {
      // TODO Handle error properly. 404 or 400 or 500 will arrive
      console.log(err);
    });
}

document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("login-form");
  const idInput = document.getElementById("idInput");
  const pwInput = document.getElementById("passwordInput");

  loginForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const id = idInput.value;
    const password = pwInput.value;

    const json = {
      id: id,
      password: password,
    };

    send(json);
  });
});
