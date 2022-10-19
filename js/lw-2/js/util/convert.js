import {MAXIMUM_PERCENT_VALUE} from "../const/percent.js"


class Convert {

    static toPercent(value) {
        return (value / MAXIMUM_PERCENT_VALUE)
    }

    static toNumberFormat(value) {
        return value.replace(/,/, ".")
    }
}


export {
    Convert
}
