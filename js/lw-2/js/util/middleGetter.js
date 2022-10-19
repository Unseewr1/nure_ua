import {ValidationError} from "../exception/validationError.js";

class MiddleGetter {

    static getMiddle(text) {
        let trimmedText = text.trim()
        validateText(trimmedText)
        let leftIndex = getLeftMiddleIndex(trimmedText)
        let rightIndex = getRightMiddleIndex(trimmedText)
        return trimmedText.slice(leftIndex, rightIndex)
    }
}


function validateText(text) {
    if (text === "") {
        throw new ValidationError()
    }
}


function getLeftMiddleIndex(text) {
    return Math.floor((text.length - 1) / 2)
}

function getRightMiddleIndex(text) {
    return Math.floor(text.length / 2 + 1)
}


export {
    MiddleGetter
}
