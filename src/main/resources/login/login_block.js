//$ = jQuery;
//window.alert("sometext");
$(document).ready(function() {
    if (typeof (Storage) != "undefined")
    {
        // Store
        localStorage.setItem("username", "#{ProgramUser.username}");
        // Retrieve
        if (localStorage.getItem("username") == null ||
                localStorage.getItem("username") == '') {
            //$("#modalDialog").show();
            PF('loginbox').show()
            //loginBox.show();
            //window.alert("sometext");

        } else {
            $('modalDialog').hide();
        }
    }
    else
    {
        document.getElementById("header").innerHTML = "<br/>Sorry, your browser does not support Web Storage...";
    }
});