**Exercise 1:**

For each user (identified by their name `$_POST["nom"]`), save their inputs in a text file identified by the string `md5(strtoupper($_POST["nom"]))` and stored in the `data` directory. It is necessary to consider the reloading of this data to display a user's choices.

**Exercise 2:**

Implement the PHP script `afficher.php`, which allows displaying the data entered by a user by identifying themselves with their name and password.