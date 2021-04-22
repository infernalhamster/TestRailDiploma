
function printTestRailStats() {
    var response = httpGet('https://testrail.bercut.com/index.php');
}

function httpGet(theUrl)
{
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.open( "GET", theUrl, false );
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {//Вызывает функцию при смене состояния.
        // if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            document.getElementById("output").textContent = xhr.responseText;
        // }
    };
    xhr.send( '/api/v2/get_case/71798');
    return xhr.responseText;
}