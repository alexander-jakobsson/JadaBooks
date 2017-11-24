console.log("Hej");
var books = document.getElementsByClassName("books").id;
for (i = 0; i < books.length; i++) {
    getTitleForLib(books[i]);
}


function getTitleForLib(books) {
    document.getElementById("entireLib").innerHTML;

    $.ajax ({
        url: "https://www.googleapis.com/books/v1/volumes?q=isbn:" + books,
        dataType: "json",

        success: function(data) {

            var title = data.items[0].volumeInfo.title || "";

            entireLib.innerHTML += "<div> <h3>" + title
            + "</h3> </div>";

        },

        type: 'GET'
    })
}