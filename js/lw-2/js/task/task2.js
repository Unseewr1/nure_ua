import {ValidationError} from "../exception/validationError.js"
import {MiddleGetter} from "../util/middleGetter.js"

const TASK2_BUTTON_ID = "task2-run"

const ENTER_WORD_MESSAGE = "Введіть слово:"
const MIDDLE_SYMBOLS_EQUAL_MESSAGE = "Середні символи збігаються"
const VALIDATION_ERROR_MESSAGE = "Неприпустиме значення"


function runTask2() {
    try {
        let middlePart = MiddleGetter.getMiddle(prompt(ENTER_WORD_MESSAGE))
        if (middlePart.length === 1) {
            alert(middlePart)
            return
        }
        if (middlePart[0] === middlePart[1]) {
            alert(MIDDLE_SYMBOLS_EQUAL_MESSAGE)
            return
        }
        alert(middlePart)
    } catch (e) {
        if (e instanceof ValidationError) {
            alert(VALIDATION_ERROR_MESSAGE)
        }
    }
}


function setTask2OnClick() {
    document.getElementById(TASK2_BUTTON_ID)
        .addEventListener("click", runTask2)
}


setTask2OnClick()