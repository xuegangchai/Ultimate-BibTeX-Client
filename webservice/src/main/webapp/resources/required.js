window.addEventListener("load", sivuLadattu);

function sivuLadattu() {
    document.getElementById("articleButton").onclick = function () {
        var all = document.getElementsByTagName("input");
        for (var i = 0; i < all.length; i++) {
            all[i].required = false;
            all[i].placeholder = ""
        }
        document.getElementById("author").required = "required";
        document.getElementById("author").placeholder = "required";
        document.getElementById("title").required = "required";
        document.getElementById("title").placeholder = "required";
        document.getElementById("journal").required = "required";
        document.getElementById("journal").placeholder = "required";

        document.getElementById("year").required = "required";
        document.getElementById("year").placeholder = "required";

    }
    document.getElementById("bookButton").onclick = function () {
        var all = document.getElementsByTagName("input");
        for (var i = 0; i < all.length; i++) {
            all[i].required = false;
            all[i].placeholder = ""
        }
        document.getElementById("author").required = "required";
        document.getElementById("author").placeholder = "required";
        document.getElementById("title").required = "required";
        document.getElementById("title").placeholder = "required";
        document.getElementById("publisher").required = "required";
        document.getElementById("publisher").placeholder = "required";

        document.getElementById("year").required = "required";
        document.getElementById("year").placeholder = "required";
    }
    document.getElementById("inproceedingsButton").onclick = function () {
        var all = document.getElementsByTagName("input");
        for (var i = 0; i < all.length; i++) {
            all[i].required = false;
            all[i].placeholder = ""
        }
        document.getElementById("author").required = "required";
        document.getElementById("author").placeholder = "required";
        document.getElementById("title").required = "required";
        document.getElementById("title").placeholder = "required";
        document.getElementById("booktitle").required = "required";
        document.getElementById("booktitle").placeholder = "required";

        document.getElementById("year").required = "required";
        document.getElementById("year").placeholder = "required";
    }
}