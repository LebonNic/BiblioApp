var TypeCheck = {
    isString: function (object) {
        return typeof object === "string";
    },
    isNumber: function(object) {
        return typeof object === "number";
    },
    isInteger: function(object) {
        return TypeCheck.isNumber(object) && object === object | 0;
    },
    isUnsignedInteger: function(object) {
        return TypeCheck.isInteger(object) && object === object >>> 0;
    },
    isId: function(object) {
        return TypeCheck.isUnsignedInteger(object) && object !== 0;
    }
};