var page = require('webpage').create();

var library_url = 'http://library.kuet.ac.bd'

var fillLoginInfo = function(){
    var frm = document.getElementById("auth");
    frm.elements['userid'].value = '051203043';
    frm.elements['password'].value = 'xxxxxxx';
    frm.submit();
}


page.onLoadFinished = function(){
    if (page.title === "Central Library | KUET") {
        page.evaluate(fillLoginInfo);
        return;
    } else {
        page.render("LOGINMADAFAKA.png");
    }

    console.log("Logged in");

    if (page.title.search("Central Library, KUET catalog") > 0){
        page.evalu
    }

    phamtom.exit();
}

page.open(library_url)






