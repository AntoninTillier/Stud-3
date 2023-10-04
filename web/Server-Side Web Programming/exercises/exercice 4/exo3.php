<?php

function wordlist($t)
{
    $genericRegex = "/(([A-Za-z]+(\.[A-Za-z]+)+)((\/[A-Za-z0-9]+)|\/)*)|([A-Za-z]+(-[A-Za-z]+)*)|([0-9]+(\.[0-9]+)*)/";
    $allWords = [];
    preg_match_all($genericRegex, $t, $allWords, PREG_OFFSET_CAPTURE);
    $allWords = $allWords[0];
    $allWordsUnsorted = [];
    foreach ($allWords as $word) {
        if (array_key_exists($word[0], $allWordsUnsorted)) {
            $currentPosList = $allWordsUnsorted[$word[0]][1];
            array_push($currentPosList, $word[1]);
            $allWordsUnsorted[$word[0]] = [$allWordsUnsorted[$word[0]][0] + 1, $currentPosList];
        } else {
            $allWordsUnsorted[$word[0]] = [1, [$word[1]]];
        }
    }
    $temp = [];
    foreach ($allWordsUnsorted as $word => $wordOccurenceList) {
        $temp[] = [$word, $wordOccurenceList[0], implode(",", $wordOccurenceList[1])];
    }
    usort($temp, function ($a, $b) {
        if ($a[1] == $b[1]) return 0;
        return $a[1] < $b[1] ? 1 : -1;
    });

    return $temp;
}

$text = "PHP is a popular general-purpose scripting language that
 is especially suited to web development. Fast, flexible and pragmatic,
 PHP powers everything from your blog to the most popular websites
 in the world. The PHP development team announces the immediate
 availability of PHP 7.2.23. This is a bugfix release. For source
 downloads of PHP 7.2.23 please visit our downloads page, Windows
 source and binaries can be found on windows.php.net/download/.
 The list of changes is recorded in the ChangeLog.";

echo "<p>";
$a = wordlist($text);

echo "<pre>";
foreach ($a as $k => $v) {
    echo "$k :\n";
    foreach ($v as $e) {
        echo " $e";
    }
    echo "\n";
}
echo "</pre>";
