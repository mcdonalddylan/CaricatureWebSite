//setting up the ajax get request function to get
// .json data back from the server

//this sucks 
function ajaxGetRequest(url, expression)
{
    //step1
    const xhr = new XMLHttpRequest();

    //step2 (when this event is triggered, this fucntion is called)
    xhe=onreadystatechange = () => {
        if(xhr.readyState === 4 && xhr.status === 200) //only when the server is ready
        {
            const jsonResponse = JSON.parse(xhr.responseText); //gets json data from server

            expression(jsonResponse, user); //places it into the given function
        }
    }

    //step 3
    xhr.open("get", url);

    //step 4
    xhr.send();
}

function fetchGetRequest(url) {
    return fetch(url)
      .then((response) => response.json())
      .then((result) => result);
}

async function asyncFetch(url, expression) {
    const response = await fetch(url);
    const json = await response.json();
    expression(json);
}
