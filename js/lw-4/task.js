const transform = (text) => {
    const rolePhrasesMap = new Map()
    text.split("\r\n")
        .forEach((line, index) => {
                const [role, phrase] = line.match(/^(.+?): (.+)$/).slice(1)
                if (rolePhrasesMap.get(role) === undefined) {
                    rolePhrasesMap.set(role, [])
                }
                rolePhrasesMap.set(role, rolePhrasesMap.get(role).concat([(index + 1) + ") " + phrase]))
            })
    let result = ""
    for (let [role, phrases] of rolePhrasesMap) {
        result += role + ":\n" + phrases.join("\n") + "\n"
    }
    return result
}

const logFileText = async file => {
    const response = await fetch(file)
    const text = await response.text()
    return [text, transform(text)]
}

logFileText('input.txt').then(r => {
    const [text, result] = r
    console.log(text)
    console.log(result)
})