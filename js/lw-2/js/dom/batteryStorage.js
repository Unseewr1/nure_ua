import {Validation} from "../util/validation.js"
import {ValidationError} from "../exception/validationError.js"
import {Convert} from "../util/convert.js";

const MAX_COUNT_PRECISION = 2


class BatteryStorage {

    constructor(builder) {
        this.batteryCount = builder.batteryCount
        this.batteryDefectPercent = builder.batteryDefectPercent
    }

    static builder() {
        return new Builder()
    }


    getGoodBatteryCount() {
        this.goodBatteryCount = (this.batteryCount - this.getBadBatteryCount())
        this.getGoodBatteryCount = () => this.goodBatteryCount
        return this.goodBatteryCount
    }

    getBadBatteryCount() {
        this.badBatteryCount = Number((this.batteryCount * Convert.toPercent(this.batteryDefectPercent)).toFixed(MAX_COUNT_PRECISION))
        this.getBadBatteryCount = () => this.badBatteryCount
        return this.badBatteryCount
    }
}

class Builder {

    setBatteryCount(batteryCount) {
        validateIsPositiveInteger(batteryCount)
        this.batteryCount = batteryCount
        return this
    }

    setBatteryDefectPercent(batteryDefectPercent) {
        validateDefectPercent(batteryDefectPercent)
        this.batteryDefectPercent = batteryDefectPercent
        return this
    }


    build() {
        return new BatteryStorage(this)
    }
}


function validateIsPositiveInteger(value) {
    if (!Validation.isNonNegativeInteger(value)) {
        throw new ValidationError()
    }
}

function validateDefectPercent(defectPercent) {
    if (!Validation.isPercent(defectPercent)) {
        throw new ValidationError()
    }
}


export {
    BatteryStorage
}
