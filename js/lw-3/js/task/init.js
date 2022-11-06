import {countVowels, isPasswordValid, isPostCodeValid} from "../regex/regex.js"

const CLASS_FUNCTION_MAP = [
    {
        buttonClass: "button-task--1",
        inputId: "input-task--1",
        _function: isPasswordValid,
    },
    {
        buttonClass: "button-task--2",
        inputId: "input-task--2",
        _function: countVowels,
    },
    {
        buttonClass: "button-task--3",
        inputId: "input-task--3",
        _function: isPostCodeValid,
    },
]

CLASS_FUNCTION_MAP.forEach((buttonClassToFunction) => {
    const [buttonClass, inputId, _function] = [buttonClassToFunction.buttonClass, buttonClassToFunction.inputId, buttonClassToFunction._function]
    Array.from(document.getElementsByClassName(buttonClass))
        .forEach((button) => {
        button.addEventListener('click', () => {
            alert(_function(document.getElementById(inputId).value))
        })
    })
})