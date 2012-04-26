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
            var parts = id.split(".");
            id = parts[parts.length - 1];

            var el = document.getElementById(id);
            var content = el.innerHTML;

            var xhr = new XMLHttpRequest();
            xhr.open("POST", serviceUrl, true);
            xhr.setRequestHeader("Content-Type", "text/x-bibtex");
            xhr.onreadystatechange = function(evt) {
                if(this.DONE == this.readyState)
                {
                    if(200 == this.status || (0 == this.status && (!this.statusText) && (!this.responseText)))
                        window.alert("Lisätty!");
                    else
                        handleError(this.statusText + "\n" + this.responseText);
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