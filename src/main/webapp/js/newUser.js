function newPassBox()
{
    const passTest = document.getElementById("pass-entry2");
    if(passTest == null)
    {
        const passDiv = document.getElementById("pass-div2");
        const passLabel = document.createElement("label");
        passLabel.for = "pass2";
        passLabel.innerText = "Please enter that password ooone more time:";
        passLabel.className = "col-3";
        const passBtn = document.createElement("input");
        passBtn.className = "col-4 entry";
        passBtn.type = "password";
        passBtn.name = "pass2";
        passBtn.id = "pass-entry2"
        passBtn.placeholder = "****";
        passDiv.style="margin-top: 15px;";

        passDiv.append(passLabel, passBtn);
    }
};

const createForm = document.getElementById("create");
console.log(createForm);
createForm.addEventListener("submit", () => {

    console.log("YOU SHOULD SEE THIS WHEN YOU CLICK THE CREATE BUTTON");

    const passText1 = document.getElementById("pass-entry");
    const passText2 = document.getElementById("pass-entry2");
    const userText = document.getElementById("user-entry");
    const emailText = document.getElementById("email-entry");

    console.log("text length of pass1 box: " + passText1.textContent.length);
    console.log("is passText value a thing?: " + passText1.value);
    if(passText2 == null)
    {
        alert("Please enter a password.");
        event.preventDefault();
        stopPropagation();
    }
    else if(passText1.value !== passText2.value)
    {
        alert("Passwords did not match.");
        event.preventDefault();
        stopPropagation();
    }
    else if(passText1.value.length < 3)
    {
        alert("Please enter a password with 4 or more characters.");
        event.preventDefault();
        stopPropagation();
    }
    if(userText.value == "")
    {
        alert("Please enter a unique username.");
        event.preventDefault();
        stopPropagation();
    }
    if(emailText.value == "")
    {
        alert("Please enter an email.");
        event.preventDefault();
        stopPropagation();
    }
    else if(emailText.value.includes("@") == false)
    {
        alert("Please enter a valid email address.");
        event.preventDefault();
        stopPropagation();
    }
    else if(emailText.value.includes(".") == false)
    {
        alert("Please enter a valid email address.");
        event.preventDefault();
        stopPropagation();
    }

    alert("**User created! :D**");
}, true);