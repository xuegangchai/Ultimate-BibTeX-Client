javascript:(function()
{
    var serviceUrl = "http://localhost:8080/vihaiset/read-bibtex";
    var handleError = function(msg)
    {
        window.alert("I'm sorry but this didn't work out.\n" + msg);
    };

    try
    {
        var id = null;

        var url = window.location.href;
        var query = url.substring(1 + url.indexOf('?'));
        var parts = query.split("&");
        for(var i = 0; i < parts.length; i++)
        {
            if(0 == parts[i].indexOf("id="))
            {
                id = parts[i].substring(3);
                break;
            }
        }

        if(id)
        {
            var el = document.getElementById(id);
            alert(el);
            var content = el.innerHTML;
            alert (content);
            
            var xhr = new XMLHttpRequest();
            xhr.open("POST", serviceUrl, true);
            xhr.withCredentials = "true";
            xhr.onreadystatechange = function() {
                if(4 == xhr.readyState)
                {
                    if(200 == xhr.status)
                        window.alert("Lisätty!");
                    else
                    {
                        handleError(xhr.responseText);
                    }
                }
            };
            xhr.send(content);
        }
    }
    catch(exc)
    {
        handleError("" + exc);
    }
})()