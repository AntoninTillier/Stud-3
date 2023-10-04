<?php require_once "users.php" ?>

<?php print_users("users.txt") ?>

<h3>Test 1</h3>
<p>add_user("hello", "World", "Hello World")</p>
<?php add_user("hello", "World", "Hello World") ?>
<?php print_users("users.txt") ?>

<h3>Test 2</h3>
<p>change_password("jc", "123")</p>
<?php change_password("jc", "123") ?>
<?php print_users("users.txt") ?>

<h3>Test 3</h3>
<p>delete_user("jc")</p>
<?php delete_user("jc") ?>
<?php print_users("users.txt") ?>

<h3>Test 4</h3>
<p>check_user("hello", "World")</p>
<?php echo "<pre>";
echo check_user("hello", "world") === FALSE ? " world FAUX" : " world OK";
echo "<pre>";
echo check_user("hello", "World") === FALSE ? " World FAUX" : " World OK";
echo "<pre>";
print_users("users.txt");
?>

<h3>Test 5</h3>
<p>change_group("hello", 888)</p>
<?php change_group("hello", 888) ?>
<?php print_users("users.txt") ?>


<h3>Test 6</h3>
<p>disable_invalid_users()</p>
<?php disable_invalid_users() ?>
<?php print_users("users.txt") ?>
