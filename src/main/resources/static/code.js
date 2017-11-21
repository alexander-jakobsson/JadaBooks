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
    console.log(search);

    $.ajax({
        url: "https://www.googleapis.com/books/v1/volumes?q=" + search,
        dataType: "json",

        success: function(data) {
            for (i = 0; i < data.items.length; i++ ) {
                results.innerHTML += "<div class=\"box\"> <img src=\"" + data.items[i].volumeInfo.imageLinks.smallThumbnail
                + "\" id=\"resultImg\"> <h3>" + data.items[i].volumeInfo.title
                + "</h3> <h5>" + data.items[i].volumeInfo.authors
                + "</h5> <p>" + data.items[i].volumeInfo.description
                + "</p> <div> <button id=\"b" + i + "\">Read More</button> </div> </div>";
            }
            console.log(data);
        },

        type: 'GET'
    })
}

document.getElementById("button").addEventListener("click", bookSearch, false); 