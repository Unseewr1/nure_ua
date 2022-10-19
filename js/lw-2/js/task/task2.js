import {ValidationError} from "../exception/validationError.js"
import {MiddleGetter} from "../util/middleGetter.js"

const TASK2_BUTTON_ID = "task2-run"

const ENTER_WORD_MESSAGE = "Введіть слово:"
const MIDDLE_SYMBOLS_EQUAL_MESSAGE = "Середні символи збігаються"
const VALIDATION_ERROR_MESSAGE = "Неприпустиме значення"


function runTask2() {
    try {
        let middlePart = MiddleGetter.getMiddle(prompt(ENTER_WORD_MESSAGE))
        alert(getValidDataMessage(middlePart))
    } catch (e) {
        if (e instanceof ValidationError) {
            alert(VALIDATION_ERROR_MESSAGE)
        }
    }
}

function getValidDataMessage(middlePart) {
    if (middlePart.length === 1) {
        return middlePart
    }
    if (middlePart[0] === middlePart[1]) {
        return MIDDLE_SYMBOLS_EQUAL_MESSAGE
    }
    return middlePart
}


function setTask2OnClick() {
    document.getElementById(TASK2_BUTTON_ID)
        .addEventListener("click", runTask2)
}


setTask2OnClick()
