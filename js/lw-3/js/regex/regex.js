const PASSWORD_REGEX = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&'*+\-/=?^_`{|}~])(?=.+\..+)(?!.*\.$)(?!^\.)(?!.*\.\..*)/
const VOWEL_COUNT_REGEX = /[aeiou]/ig
//https://postal-code.co.uk/
const BRITAIN_POSTCODE_REGEX = /^[A-Z]{1,2}\d{1,2} \d[A-Z]{2}$/

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