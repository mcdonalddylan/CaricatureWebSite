//should create and remove the login fields based
// on whether or not the user has logged in during a given session.

//Check for user using fetch request
let user = new User();

let count = 0;

asyncFetch("http://localhost:8080/Caricature/user.json", function(importUser)
{
    console.log(importUser);
    console.log(typeof importUser);
    console.log(importUser != null);
    user.id = 0;

    //enter the data from user.json into user object
    if(importUser != null)
    {
        user.id = importUser.id;
        user.username = importUser.username;
        user.firstN = importUser.firstName;
        user.lastN = importUser.lastName;
        user.role = importUser.role;
    }
    
    
    console.log("user object id: " + user.id);

    //check if logged in
    if(user.id > 0)
    {
        count++;
        console.log(user.firstN + " has logged in!");       
        LoggedIn();
    }
    else
    {
        count++;
        console.log("no user logged in");
        LoggedOut();
    }

//If logging in, show welcome message and logout button
function LoggedIn()
{
    console.log("this should not be entered when the user is null");
    const navBar = document.getElementById("bg");

    //adds welcome messsage and logout button
    const navForm = document.createElement("form");
    navForm.id = "logout-form";
    navForm.action = "logout";
    navForm.method = "POST";
    
    const h3 = document.createElement("h3");
    const homeLink = document.createElement("a");
    homeLink.textContent = "DreamButts.com";
    homeLink.style = "color:#000066";
    homeLink.href = "./";
    h3.append(homeLink);

    const welMsg = document.createElement("p");
    welMsg.className = "welc-msg";
    welMsg.textContent = "User currently logged in: " + user.firstN;

    const outBtn = document.createElement("input");
    outBtn.type="submit";
    outBtn.className = "col-3 login-btn";
    outBtn.value = "LOGOUT";

    const reimForm = document.createElement("form");
    reimForm.action= "reimburse";
    reimForm.method ="POST";
    reimForm.id = "reim-menu";

    const reimBtn = document.createElement("input");
    reimBtn.type = "submit";
    reimBtn.value = "Reimburse Menu";
    reimBtn.className = "reim-menu-btn";

    navBar.append(navForm);
    navForm.append(h3, welMsg, outBtn);
    navBar.append(reimForm);
    reimForm.append(reimBtn);
}

//if logging out, show username and password entry
function LoggedOut()
{
    if(user.id === 0)
    {
    console.log("this should not be entered when the user has logged in");
    const navBar = document.getElementById("bg");

    //adds the login entry elements back
    const logForm = document.createElement("form");
    logForm.id = "user-entry";
    logForm.action = "login";
    logForm.method = "POST";

    const h3 = document.createElement("h3");
    const homeLink = document.createElement("a");
    homeLink.textContent = "DreamButts.com";
    homeLink.style = "color:#000066";
    homeLink.href = "./";
    h3.append(homeLink);

    const userTxt = document.createElement("input");
    userTxt.type = "text";
    userTxt.name = "username";
    userTxt.className = "col-3 entry-nav";
    userTxt.placeholder = "username";

    const passTxt = document.createElement("input");
    passTxt.type = "password";
    passTxt.name = "password";
    passTxt.className = "col-3 entry-nav";
    passTxt.placeholder = "*******";

    const logBtn = document.createElement("input");
    logBtn.className = "col-2 login-btn";
    logBtn.type = "submit";
    logBtn.value = "LOGIN";
    
    console.log(navBar);
    console.log(logForm);
    navBar.append(logForm);
    logForm.append(h3, userTxt, passTxt, logBtn);
    }
    
}   
    console.log("count is: " + count);
});



