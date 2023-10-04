**Exercise 1:**

Complete the `highlight($s, $ss)` function that puts every sub-string `$ss` within a string `$s` in bold style using the `<b></b>` tag (for example, `<b>abc</b>` will display as "abc" instead of "abc").

**Exercise 2:**

Complete the `inverse($s)` function that converts all uppercase letters to lowercase and all lowercase letters to uppercase within a string `$s`.

**Exercise 3:**

Complete the `wordlist($t)` function that lists the occurrence positions of each word (without any punctuation marks except "-" between words, "." between numbers, and "/" in URLs) in a text `$t`, without distinguishing between uppercase and lowercase letters. This function should return an array where the keys are the words, and the values are lists (arrays) of positions. Additionally, this return array should be sorted in descending order based on the word position counts.