    window.addEventListener("load", addRequirements);
    
    function addRequirements()
    {

        document.getElementById("articleButton").onclick=function(){
            var all=document.getElementsByTagName("input");   
            for (var i = 0; i < all.length; i++) { 
                all[i].removeAttribute("required"); 
                all[i].removeAttribute("placeholder");
            }
            document.getElementById("refkey").required="required";
            document.getElementById("author").required="required";
            document.getElementById("title").required="required";
            document.getElementById("journal").required="required";
            document.getElementById("year").required="required";
            
            setPlaceholders();
            
        };

        document.getElementById("bookButton").onclick=function(){
            var all=document.getElementsByTagName("input");   
            for (var i = 0; i < all.length; i++) { 
                all[i].removeAttribute("required"); 
                all[i].removeAttribute("placeholder");
            }
            document.getElementById("refkey").required="required";
            document.getElementById("author").required="required";
            document.getElementById("editor").required="required";
            document.getElementById("title").required="required";
            document.getElementById("publisher").required="required";
            document.getElementById("year").required="required";
            
            setPlaceholders();
    
        };

        document.getElementById("inproceedingsButton").onclick=function(){
            var all=document.getElementsByTagName("input");   
            for (var i = 0; i < all.length; i++) { 
                all[i].removeAttribute("required"); 
                all[i].removeAttribute("placeholder");
            }
            document.getElementById("refkey").required="required";
            document.getElementById("author").required="required";
            document.getElementById("title").required="required";
            document.getElementById("booktitle").required="required";
            document.getElementById("year").required="required";
            
            setPlaceholders();
        };
        
        //jos tyyppi on book joko author TAI editor ovat pakollisia kenttia
        
         function OnInputAuthor (event) {
           if (document.getElementById("bookButton").checked) {

            if(event.target.value == "") {
             
             document.getElementById("editor").required= "required";
             setPlaceholders();
            }
            else { 
             document.getElementById("editor").required= "";
             setPlaceholders();
            }
           }


         }
            
         //jos tyyppi on book joko author TAI editor ovat pakollisia kenttia    
          function OnInputEditor (event) {
           if (document.getElementById("bookButton").checked) {

            if(event.target.value == "") {
             document.getElementById("author").required= "required";
             setPlaceholders();
             
            }
            else {             
             document.getElementById("author").required= "";
             setPlaceholders();
            }

           }

         }
           
        function setPlaceholders(){
            
            var elements = document.querySelectorAll("input[required]");
            for(var i=0; i< elements.length; i++){
                elements[i].setAttribute('placeholder', 'required');
            }
        }


    }
    

