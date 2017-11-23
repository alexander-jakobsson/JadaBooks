<<<<<<< HEAD
document.getElementById("button").addEventListener("click", bookSearch, true);


=======
>>>>>>> 440d7d7207bf21d52d26d2682b39fec123b7332e
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

document.getElementById("button").addEventListener("click", bookSearch, false);

document.getElementsByClassName("aboutHide").addEventListener("click", hideAbout);

function bookSearch() {
    var search = document.getElementById("searchBar").value;
    document.getElementById("results").innerHTML = "";

    $.ajax({
        url: "https://www.googleapis.com/books/v1/volumes?q=" + search + "&maxResults=39&startIndex=0",
        dataType: "json",

        success: function(data) {

            for (i = 0; i < data.items.length; i++ ) {
                var image = data.items[i].volumeInfo.imageLinks.smallThumbnail;
                var title = data.items[i].volumeInfo.title;
                var author = data.items[i].volumeInfo.authors;
                var idType = data.items[i].volumeInfo.industryIdentifiers[0].type;

                if (idType === "ISBN_10") {
                    var bookID = data.items[i].volumeInfo.industryIdentifiers[0].identifier;
                } else if (idType === "ISBN_13") {
                    var bookID = data.items[i].volumeInfo.industryIdentifiers[1].identifier;
                }

                if (data.items[i].volumeInfo.description) {
                    var parag = data.items[i].volumeInfo.description;
                    parag = truncate(parag);
                } else {
                    var parag = "";
                }
                results.innerHTML += "<div class=\"box\"> <img src=\"" + image
                + "\" id=\"resultImg\"> <h3>" + title
                + "</h3> <h5>" + author
                + "</h5> <p>" + parag
                + "</p> <div> <button class=\"readMoreBtn\" id=" + bookID + " onClick=\"reply_click(this.id)\">Read More</button> </div> </div>";
            }
            if (search) {
                document.getElementById("notaMember").style.display = "none";
            }
        },

        type: 'GET'
    })
}

function reply_click(clicked_id) {
    document.getElementById("readMore").style.display = "block";
    document.getElementById("readMore").innerHTML = "";

    $.ajax({
        url: "https://www.googleapis.com/books/v1/volumes?q=isbn:" + clicked_id,
        dataType: "json",

        success: function(data) {

            console.log(data);
            var image = data.items[0].volumeInfo.imageLinks.smallThumbnail || "";
            var title = data.items[0].volumeInfo.title || "";
            var author = data.items[0].volumeInfo.authors || "";
            var parag = data.items[0].volumeInfo.description || "";
            var bookID = clicked_id;

            readMore.innerHTML += "<div class=\"aboutBox\"> <img src=\"" + image
            + "\" id=\"resultImg\"> <h3>" + title
            + "</h3> <h5>" + author
            + "</h5> <p>" + parag
            + "</p> <input type=\"submit\" value=\"Buy the book\" id=\"regMemBtn\" onclick=\"location.href='/BuyTheBook?id="
            + bookID + "'\"/> </div>";

        },

        type: 'GET'
    })
}

document.getElementsByClassName("aboutHide").addEventListener("click", hideAbout);

function hideAbout() {
    document.getElementsByClassName("aboutHide").style.display = "add";
}

function truncate(parag) {
    var maxlength = 200;
    if (parag.length < maxlength){
        return parag;
    } else {
        return parag.substr(0,maxlength) + "..."
    }
}