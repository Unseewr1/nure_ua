import {MAXIMUM_PERCENT_VALUE} from "../const/percent.js"

const MINIMUM_NON_NEGATIVE_VALUE = 0


class Validation {

    static isNonNegative(value) {
        return (value >= MINIMUM_NON_NEGATIVE_VALUE)
    }

    static isNumber(value) {
        return ((typeof value === "number")
            && (isFinite(value)))
    }

    static isInteger(value) {
        return Number.isInteger(value)
    }

    static isNonNegativeInteger(value) {
        return (this.isInteger(value)
            && this.isNonNegative(value))
    }

    static isNonNegativeNumber(value) {
        return this.isNumber(value)
            && this.isNonNegative(value)
    }

    static isPercent(value) {
        return (this.isNonNegativeNumber(value)
            && (value <= MAXIMUM_PERCENT_VALUE))
    }
}


export {
    Validation
}
