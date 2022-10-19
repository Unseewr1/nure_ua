import {BatteryStorage} from "../dom/batteryStorage.js"
import {ValidationError} from "../exception/validationError.js"
import {Convert} from "../util/convert.js"


const TASK1_BUTTON_ID = "task1-run"

const ENTER_BATTERY_COUNT_MESSAGE = "Введіть кількість батарей:"
const ENTER_BATTERY_DEFECT_PERCENT_MESSAGE = "Введіть рівень шлюбу:"
const VALIDATION_ERROR_MESSAGE = "Недійсні вхідні дані"


function getValidDataMessage(batteryStorage) {
    return (
`Кількість батарей: ${batteryStorage.batteryCount}
Рівень шлюбу: ${batteryStorage.batteryDefectPercent}%
Кількість несправних акумуляторів: ${batteryStorage.getBadBatteryCount()}
Кількість робочих батарей: ${batteryStorage.getGoodBatteryCount()}`
    )
}


function readInputWithPrompt() {
    try {
        let batteryStorage = BatteryStorage.builder()
            .setBatteryCount(
                Number(prompt(ENTER_BATTERY_COUNT_MESSAGE)))
            .setBatteryDefectPercent(
                Number(Convert.toNumberFormat(prompt(ENTER_BATTERY_DEFECT_PERCENT_MESSAGE))))
            .build()
        alert(getValidDataMessage(batteryStorage))
    } catch (e) {
        if (e instanceof ValidationError) {
            alert(VALIDATION_ERROR_MESSAGE)
        }
    }
}


function setTask1OnClick() {
    document.getElementById(TASK1_BUTTON_ID)
        .addEventListener("click", readInputWithPrompt)
}


setTask1OnClick()
