document.getElementById("searchBar").onkeypress = function(e) {
    if (!e) {
    e = window.event;
    }   
    
    var keyCode = e.keyCode;
    if (e.keyCode == '13'){
        // Enter pressed
        bookSearch();
    }
}

function bookSearch() {
    var search = document.getElementById("searchBar").value;
    document.getElementById("results").innerHTML = "";



    $.ajax({
        url: "https://www.googleapis.com/books/v1/volumes?q=" + search,
        dataType: "json",

        success: function(data) {
            for (i = 0; i < data.items.length; i++ ) {
                if (data.items[i].volumeInfo.description) {
                    var parag = data.items[i].volumeInfo.description;
                    parag = truncate(parag);
                    results.innerHTML += "<div class=\"box\"> <img src=\"" + data.items[i].volumeInfo.imageLinks.smallThumbnail
                    + "\" id=\"resultImg\"> <h3>" + data.items[i].volumeInfo.title
                    + "</h3> <h5>" + data.items[i].volumeInfo.authors
                    + "</h5> <p>" + parag
                    + "</p> <div> <button id=\"b" + i + "\">Read More</button> </div> </div>";
                } else {
                    results.innerHTML += "<div class=\"box\"> <img src=\"" + data.items[i].volumeInfo.imageLinks.smallThumbnail
                    + "\" id=\"resultImg\"> <h3>" + data.items[i].volumeInfo.title
                    + "</h3> <h5>" + data.items[i].volumeInfo.authors
                    + "</h5> <div> <button id=\"b" + i + "\">Read More</button> </div> </div>";
                }
            }
            if (search) {
                document.getElementById("notaMember").style.display = "none";
            }
            console.log(search);
        },

        type: 'GET'
    })
}

document.getElementById("button").addEventListener("click", bookSearch, false); 

function truncate(parag) {
    var maxlength = 200;
    if (parag.length < maxlength){
        return parag;
    } else {
        return parag.substr(0,maxlength) + "..."
    }
}