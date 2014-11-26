$(document).ready(function () {
    var query;

    function initializeLists() {
        $("#content-container").empty();
        $("<h2>", {}).text("Books").appendTo($("#content-container"));
        $("<div>", {class: "listview-outlook", id: "book-list"}).appendTo($("#content-container"));
        $("<h2>", {}).text("Authors").appendTo($("#content-container"));
        $("<div>", {class: "listview-outlook", id: "author-list"}).appendTo($("#content-container"));
    }

    // Brand reset page content
    $("#brand").click(function () {
        $("#search-form").submit();
    });

    // Search authors & books
    $("#search-form").submit(function (event) {
        event.preventDefault();
        initializeLists();

        query = $("#search-query").val();
    	Book.read(query).done(function (data) {
    		if (query != "") {
    			data = $.xml2json(data).Body.searchLivresResponse.return;
    		} else {
    			data = $.xml2json(data).Body.getAllLivresResponse.return;
    		}
        	
            if (data !== undefined) {
            	if (data.length !== undefined) {
            		// Array
            		for (var index = 0, length = data.length; index < length; ++index) {
                        var book = new Book(data[index]);
                        book.appendTo($("#book-list"));
                    }
            	} else {
            		// Single
            		var book = new Book(data);
                    book.appendTo($("#book-list"));
            	}
                
            } else {
                $("<p>").text("No book matching request \"" + query + "\".").appendTo($("#book-list"));
            }
        });

        Author.read(query).done(function (data) {
        	if (query != "") {
        		data = $.xml2json(data).Body.searchAuteursResponse.return;
    		} else {
    			data = $.xml2json(data).Body.getAllAuteursResponse.return;
    		}
        	
            if (data !== undefined) {
            	if (data.length !== undefined) {
            		// Array
	                for (var index = 0, length = data.length; index < length; ++index) {
	                    var author = new Author(data[index]);
	                    author.appendTo($("#author-list"));
	                }
            	} else {
            		// Single
            		var author = new Author(data);
                    author.appendTo($("#author-list"));
            	}
            } else {
                $("<p>").text("No author matching request \"" + query + "\".").appendTo($("#author-list"));
            }
        });
    });

    // Add book and display it
    $("#add-book").click(function () {
        Book.create().done(function (data) {
        	data = $.xml2json(data).Body.addLivreResponse.return;
        	if (data !== undefined) {
        		var book = new Book(data);
                book.display();        		
        	}
        });
    });

    $("#add-author").click(function () {
        Author.create().done(function (data) {
        	data = $.xml2json(data).Body.addAuteurResponse.return;
        	if (data !== undefined) {
        		var author = new Author(data);
                author.display();
        	}
        });
    });
});