var Author = function (args) {
    var _self = this,
        _id,
        _name,
        _firstName,
        _address,
        _books;

    var _nameEnabled = false,
        _firstNameEnabled = false,
        _addressEnabled = false;

    function initialize(args) {
        args = args || {};
        setId(parseInt(args.numero_a));
        setName(args.nom);
        setFirstName(args.prenom);
        setAddress(args.adresse);
        //setBooks(args.books);
    }

    _self.update = function () {
    	$.soap({
        	url: "biblioapp",
            method: "web:updateAuteur",
            envAttributes: {
            	"xmlns:web": "http://webservice.isima.fr/"
            },
            appendMethodToURL: false,
            data: {
            	numero_a: _id,
                nom: _name,
                prenom: _firstName,
                adresse: _address
            }
        });
    };

    _self.delete = function () {
    	$.soap({
        	url: "biblioapp",
            method: "web:deleteAuteur",
            envAttributes: {
            	"xmlns:web": "http://webservice.isima.fr/"
            },
            appendMethodToURL: false,
            data: {
            	numero_a: _id
            }
        });
    };

    _self.getId = function () {
        return _id;
    };
    function setId(id) {
        if (!TypeCheck.isInteger(id)) {
            throw new TypeError("Author id must be a not null unsigned integer.");
        }
        _id = id;
    }

    _self.getName = function () {
        return _name;
    };
    function setName(name) {
        if (!TypeCheck.isString(name)) {
            throw new TypeError("Author name must be a string.");
        }
        if (_name !== undefined) {
            _name = name;
            _self.update();
        } else {
            _name = name;
        }
    }

    _self.getFirstName = function () {
        return _firstName;
    };
    function setFirstName(firstName) {
        if (!TypeCheck.isString(firstName)) {
            throw new TypeError("Author firstName must be a string.");
        }
        if (_firstName !== undefined) {
            _firstName = firstName;
            _self.update();
        } else {
            _firstName = firstName;
        }
    }

    _self.getAddress = function () {
        return _address;
    };
    function setAddress(address) {
        if (!TypeCheck.isString(address)) {
            throw new TypeError("Author address must be a string.");
        }
        if (_address !== undefined) {
            _address = address;
            _self.update();
        } else {
            _address = address;
        }
    }

    _self.getBooks = function () {
        return _books;
    };
    function setBooks(books) {
        if (_books !== undefined) {
            _books = books;
            _self.update();
        } else {
            _books = books;
        }
    }

    _self.addBook = function (book) {
//        _books.push(book);
//        _self.update();
    };

    _self.getFormattedName = function () {
        return _self.getFirstName() + " " + _self.getName();
    };

    /***************************************************************************
     * VIEW
     **************************************************************************/
    _self.appendTo = function (list) {
        var element = $("<a>", {class: "list"});
        var listContent = $("<div>", {class: "list-content"});
        var listTitle = $("<span>", {class: "list-title"}).text(_self.getFormattedName());

        listTitle.appendTo(listContent);
        listContent.appendTo(element);

        element.click(function () {
            _self.display();
        });

        element.appendTo(list);
    };

    _self.appendSearchTo = function (list, book) {
        var element = $("<a>", {class: "list"});
        var listContent = $("<div>", {class: "list-content"});
        var listTitle = $("<span>", {class: "list-title"}).text(_self.getFormattedName());

        listTitle.appendTo(listContent);
        listContent.appendTo(element);

        element.click(function () {
            book.setAuthor(_self.getId());
            _self.addBook(book.getId());
            book.display();
        });

        element.appendTo(list);
    };

    _self.display = function () {
        $("#content-container").empty();

        _nameEnabled = false;
        _firstNameEnabled = false;
        _addressEnabled = false;

        var grid = $("<div>", {class: "grid"});
        var row = $("<div>", {class: "row"});

        $("<h1 class='span1'><i class='icon-user-2'></i></h1>")
            .appendTo(row);

        var content = $("<div>", {class: "span8"})
            .appendTo(row);
        var name = $("<h1>")
            .text(_self.getName())
            .click(function () {
                toggleName(name);
            })
            .appendTo(content);

        var firstName = $("<h2>")
            .text(_self.getFirstName())
            .click(function () {
                toggleFirstName(firstName);
            })
            .appendTo(content);

        var address = $("<p>")
            .text(_self.getAddress())
            .click(function () {
                toggleAddress(address);
            })
            .appendTo(content);

        var bookList = $("<div>", {class: "listview-outlook"})
            .appendTo(content);
        /*
        if (_books.length > 0) {
            for (var i = 0, length = _books.length; i < length; ++i) {
                Book.read(_books[i]).done(function (data) {
                	data = $.xml2json(data).Body.getAuteurResponse.return;
                	
                    if (data !== undefined) {
                        var book = new Book(data);
                        book.appendTo(bookList);
                    }
                });

            }
        } else {
            $("<p>").text("No book entry for " + _self.getFormattedName());
        }
        */

        $("<a id='delete-button' class='span1' href='#'><h2><i class='icon-remove'></i></h2></a>")
            .click(function () {
                _self.delete();
                grid.hide("slow", function() {
                    $("#search-form").submit();
                });
                $.Notify.show("Author deleted.");
            })
            .appendTo(row);

        row.appendTo(grid);
        grid.appendTo($("#content-container"));
    };

    /**
     * Switch between editable and fixed name
     * @param {$} element
     */
    function toggleName(element) {
        if (!_nameEnabled) {
            var input = $("<input />")
                .val($(element).text())
                .keydown(function (event) {
                    if (event.keyCode === 13) {
                        setName(input.val());
                        toggleName(input);
                    }
                })
                .blur(function () {
                    setName(input.val());
                    toggleName(input);
                });
            $(element).replaceWith(input);
            input.focus();
            input.select();
            _nameEnabled = true;
        } else {
            var name = $("<h1>")
                .text($(element).val())
                .click(function () {
                    toggleName(name);
                });
            $(element).replaceWith(name);
            _nameEnabled = false;
        }
    }

    /**
     * Switch between editable and fixed firstname
     * @param {$} element
     */
    function toggleFirstName(element) {
        if (!_firstNameEnabled) {
            var input = $("<input />")
                .val($(element).text())
                .keydown(function (event) {
                    if (event.keyCode === 13) {
                        setFirstName(input.val());
                        toggleFirstName(input);
                    }
                })
                .blur(function () {
                    setFirstName(input.val());
                    toggleFirstName(input);
                });
            $(element).replaceWith(input);
            input.focus();
            input.select();
            _firstNameEnabled = true;
        } else {
            var firstName = $("<h2>")
                .text($(element).val())
                .click(function () {
                    toggleFirstName(firstName);
                });
            $(element).replaceWith(firstName);
            _firstNameEnabled = false;
        }
    }

    /**
     * Switch between editable and fixed address
     * @param {$} element
     */
    function toggleAddress(element) {
        if (!_addressEnabled) {
            var input = $("<input />")
                .val($(element).text())
                .keydown(function (event) {
                    if (event.keyCode === 13) {
                        setAddress(input.val());
                        toggleAddress(input);
                    }
                })
                .blur(function () {
                    setAddress(input.val());
                    toggleAddress(input);
                });
            $(element).replaceWith(input);
            input.focus();
            input.select();
            _addressEnabled = true;
        } else {
            var address = $("<p>")
                .text($(element).val())
                .click(function () {
                    toggleAddress(address);
                });
            $(element).replaceWith(address);
            _addressEnabled = false;
        }
    }

    initialize(args);
};

Author.create = function () {
    return $.soap({
    	url: "biblioapp",
        method: "web:addAuteur",
        envAttributes: {
        	"xmlns:web": "http://webservice.isima.fr/"
        },
        appendMethodToURL: false,
        data: {
            nom: "Name",
            prenom: "First name",
            adresse: "Address"
        }
    });
};

Author.read = function (param) {
    if (TypeCheck.isString(param)) {
    	if (param != "") {
    		return $.soap({
            	url: "biblioapp",
                method: "web:searchAuteurs",
                envAttributes: {
                	"xmlns:web": "http://webservice.isima.fr/"
                },
                appendMethodToURL: false,
                data: {
                	nom: param
                }
            });
    	} else {
    		console.log(param === "");
    		return $.soap({
            	url: "biblioapp",
                method: "web:getAllAuteurs",
                envAttributes: {
                	"xmlns:web": "http://webservice.isima.fr/"
                },
                appendMethodToURL: false,
                data: {}
            });
    	}
    } else if (TypeCheck.isInteger(param)) {
        return $.soap({
        	url: "biblioapp",
            method: "web:getAuteur",
            envAttributes: {
            	"xmlns:web": "http://webservice.isima.fr/"
            },
            appendMethodToURL: false,
            data: {
            	numero_a: param
            }
        });
    } else {
        throw new Error("Author.read needs a string or an id as parameter.");
    }
};

