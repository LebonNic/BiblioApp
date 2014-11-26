var Book = function (args) {
    var _self = this,
        _id,
        _title,
        _description,
        _price,
        _author;

    var _titleEnabled = false,
        _descriptionEnabled = false,
        _priceEnabled = false;

    function initialize(args) {
        args = args || {};
        setId(parseInt(args.numero_l));
        setTitle(args.titre);
        setDescription(args.resume);
        setPrice(parseFloat(args.prix));
        _self.setAuthor(parseInt(args.numero_a));
    }

    _self.update = function () {
        $.soap({
        	url: "biblioapp",
            method: "web:updateLivre",
            envAttributes: {
            	"xmlns:web": "http://webservice.isima.fr/"
            },
            appendMethodToURL: false,
            data: {
            	numero_l: _id,
                titre: _title,
                prix: _price,
                resume: _description,
                numero_a: _author
            }
        });
    };

    _self.delete = function () {
    	$.soap({
        	url: "biblioapp",
            method: "web:deleteLivre",
            envAttributes: {
            	"xmlns:web": "http://webservice.isima.fr/"
            },
            appendMethodToURL: false,
            data: {
            	numero_l: _id
            }
        });
    };

    _self.getId = function () {
        return _id;
    };
    function setId(id) {
        if (!TypeCheck.isInteger(id)) {
            throw new TypeError("Book id must be a not null unsigned integer.");
        }
        _id = id;
    }

    /**
     * Get book title
     * @returns {String} Book title
     */
    _self.getTitle = function () {
        return _title;
    };
    /**
     * Set book title
     * @param {String} title Book title
     */
    function setTitle(title) {
        if (!TypeCheck.isString(title)) {
            throw new TypeError("Book title must be a string.");
        }
        if (_title !== undefined) {
            _title = title;
            _self.update();
        } else {
            _title = title;
        }
    }

    /**
     * Get book description
     * @returns {String} Book description
     */
    _self.getDescription = function () {
        return _description;
    };
    /**
     * Set book description
     * @param {String} description Book description
     */
    function setDescription(description) {
        if (!TypeCheck.isString(description)) {
            throw new TypeError("Book description must be a string.");
        }
        if (_description !== undefined) {
            _description = description;
            _self.update();
        } else {
            _description = description;
        }
    }

    /**
     * Get book price in cents
     * @returns {Number} Book price in cents
     */
    _self.getPrice = function () {
        return _price;
    };
    _self.getFormattedPrice = function () {
        return "$" + (_self.getPrice()).toFixed(2);
    };
    /**
     * Set book price
     * @param {Number} price Book price in cents
     */
    function setPrice(price) {
        if (!TypeCheck.isNumber(price) && price >= 0.0) {
            throw new TypeError("Book price must be a number.");
        }
        if (_price !== undefined) {
            _price = price;
            _self.update();
        } else {
            _price = price;
        }
    }

    _self.setAuthor = function (author) {
        if (_author !== undefined) {
            _author = author;
            _self.update();
        } else {
            _author = author;
        }
    };

    /***************************************************************************
     * VIEW
     **************************************************************************/
    /**
     * Append book's view to given list
     * @param {type} list
     */
    _self.appendTo = function (list) {
        var element = $("<a>", {class: "list"});
        var listContent = $("<div>", {class: "list-content"});
        var listTitle = $("<span>", {class: "list-title"}).text(_self.getTitle());
        var listSubtitle = $("<span>", {class: "list-subtitle"}).text(_self.getDescription());
        var listRemark = $("<span>", {class: "list-remark"}).text(_self.getFormattedPrice());

        listTitle.appendTo(listContent);
        listSubtitle.appendTo(listContent);
        listRemark.appendTo(listContent);
        listContent.appendTo(element);

        element.click(function () {
            _self.display();
        });

        element.appendTo(list);
    };

    _self.display = function () {
        $("#content-container").empty();

        _titleEnabled = false;
        _descriptionEnabled = false;
        _priceEnabled = false;

        var grid = $("<div>", {class: "grid"});
        var row = $("<div>", {class: "row"});

        // Book icon
        $("<h1 class='span1'><i class='icon-book'></i></h1>")
            .appendTo(row);

        // Book content
        var content = $("<div>", {class: "span8"})
            .appendTo(row);
        // Book title
        var title = $("<h1>")
            .text(_self.getTitle())
            .click(function () {
                toggleTitle(title);
            })
            .appendTo(content);

        // Book description
        var description = $("<p>")
            .text(_self.getDescription())
            .click(function () {
                toggleDescription(description);
            })
            .appendTo(content);
        // Book price
        var price = $("<h3>")
            .text(_self.getFormattedPrice())
            .click(function () {
                togglePrice(price);
            })
            .appendTo(content);

        var form = $("<form>", {class: "search-from"})
            .appendTo(content);
        var list = $("<div>", {class: "listview-outlook"}).appendTo(content);
        var inputControl = $("<div>", {class: "input-control text"})
            .appendTo(form);
        var input = $("<input/>")
            .attr("placeholder", "Author")
            .attr("type", "text")
            .appendTo(inputControl);
        $("<button>", {class: "btn-search"})
            .attr("type", "submit")
            .appendTo(inputControl);

        form.submit(function (event) {
            list.empty();
            event.preventDefault();
            Author.read(input.val()).done(function (data) {
            	data = $.xml2json(data).Body.searchAuteursResponse.return;
            	
                if (data !== undefined) {
                	if (data.length !== undefined) {
                		// Array
    	                for (var index = 0, length = data.length; index < length; ++index) {
    	                    var author = new Author(data[index]);
    	                    author.appendSearchTo(list, _self);
    	                }
                	} else {
                		// Single
                		var author = new Author(data);
                		author.appendSearchTo(list, _self);
                	}
                } else {
                    $("<p>").text("No author matching request \"" + query + "\".").appendTo(list);
                }
            });
        });
        if (_author !== 0) {
            Author.read(_author).done(function (data) {
            	data = $.xml2json(data).Body.getAuteurResponse.return;
            	
                if (data !== undefined) {
                    var author = new Author(data);
                    var line = $("<div>").appendTo(content);
                    $("<a>")
                        .text("By " + author.getFormattedName())
                        .click(function () {
                            author.display();
                        })
                        .appendTo(line);
                    $("<i>", {class: "icon-pencil on-right"}).click(function () {
                        line.hide();
                        form.show("slow");
                    }).appendTo(line);
                }
            });
            form.hide();
        }

        // Book delete button
        $("<a id='delete-button' class='span1' href='#'><h2><i class='icon-remove'></i></h2></a>")
            .click(function () {
                _self.delete();
                grid.hide("slow", function () {
                    $("#search-form").submit();
                });
                $.Notify.show("Book deleted.");
            })
            .appendTo(row);

        row.appendTo(grid);
        grid.appendTo($("#content-container"));
    };

    /**
     * Switch between editable and fixed title
     * @param {$} element
     */
    function toggleTitle(element) {
        if (!_titleEnabled) {
            var input = $("<input />")
                .val($(element).text())
                .keydown(function (event) {
                    if (event.keyCode === 13) {
                        setTitle(input.val());
                        toggleTitle(input);
                    }
                })
                .blur(function () {
                    setTitle(input.val());
                    toggleTitle(input);
                });
            $(element).replaceWith(input);
            input.focus();
            input.select();
            _titleEnabled = true;
        } else {
            var title = $("<h1>")
                .text($(element).val())
                .click(function () {
                    toggleTitle(title);
                });
            $(element).replaceWith(title);
            _titleEnabled = false;
        }
    }

    /**
     * Switch between editable and fixed description
     * @param {$} element
     */
    function toggleDescription(element) {
        if (!_descriptionEnabled) {
            var textarea = $("<textarea>")
                .val($(element).text())
                .keydown(function (event) {
                    if (event.keyCode === 13) {
                        setDescription(textarea.val());
                        toggleDescription(textarea);
                    }
                })
                .blur(function () {
                    setDescription(textarea.val());
                    toggleDescription(textarea);
                });
            $(element).replaceWith(textarea);
            textarea.focus();
            textarea.select();
            _descriptionEnabled = true;
        } else {
            var description = $("<p>")
                .text($(element).val())
                .click(function () {
                    toggleDescription(description);
                });
            $(element).replaceWith(description);
            _descriptionEnabled = false;
        }
    }

    /**
     * Switch between editable and fixed price
     * @param {$} element
     */
    function togglePrice(element) {
        if (!_priceEnabled) {
            var input = $("<input />")
                .val(_self.getPrice())
                .keydown(function (event) {
                    if (event.keyCode === 13) {
                        setPrice(parseFloat(input.val()));
                        togglePrice(input);
                    }
                })
                .blur(function () {
                    setPrice(parseFloat(input.val()));
                    togglePrice(input);
                });
            $(element).replaceWith(input);
            input.focus();
            input.select();
            _priceEnabled = true;
        } else {
            var price = $("<h3>")
                .text(_self.getFormattedPrice())
                .click(function () {
                    togglePrice(price);
                });
            $(element).replaceWith(price);
            _priceEnabled = false;
        }
    }

    initialize(args);
};

Book.create = function () {
    return $.soap({
    	url: "biblioapp",
        method: "web:addLivre",
        envAttributes: {
        	"xmlns:web": "http://webservice.isima.fr/"
        },
        appendMethodToURL: false,
        data: {
        	titre: "Undefined",
        	prix: 0.0,
        	resume: "No description",
        	numero_a: 0
        }
    });
};

Book.read = function (param) {
    if (TypeCheck.isString(param)) {
    	if (param != "") {
    		return $.soap({
                url: "biblioapp",
                method: "web:searchLivres",
                envAttributes: {
                	"xmlns:web": "http://webservice.isima.fr/"
                },
                appendMethodToURL: false,
                data: {
                    titre: param
                }
            });
    	} else {
    		return $.soap({
            	url: "biblioapp",
                method: "web:getAllLivres",
                envAttributes: {
                	"xmlns:web": "http://webservice.isima.fr/"
                },
                appendMethodToURL: false,
                data:{}
            });
    	}
    } else if (TypeCheck.isId(param)) {
        return $.soap({
        	url: "biblioapp",
            method: "web:getLivre",
            envAttributes: {
            	"xmlns:web": "http://webservice.isima.fr/"
            },
            appendMethodToURL: false,
            data: {
            	numero_l: param
            }
        });
    } else {
        throw new Error("Book.read needs a string or an id as parameter.");
    }
};

