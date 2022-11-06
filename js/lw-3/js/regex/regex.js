const PASSWORD_REGEX = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&'*+\-/=?^_`{|}~])(?=.+\..+)(?!.*\.$)(?!^\.)(?!.*\.\..*)/
const VOWEL_COUNT_REGEX = /[aeiou]/ig
const BRITAIN_POSTCODE_REGEX = /^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$/

const isPasswordValid = (str) => str.match(PASSWORD_REGEX) != null

const countVowels = (str) => {
    const matches = str.match(VOWEL_COUNT_REGEX)
    return matches ? matches.length : 0
}

const isPostCodeValid = (str) => str.match(BRITAIN_POSTCODE_REGEX) != null

export {
    isPasswordValid,
    countVowels,
    isPostCodeValid,
}