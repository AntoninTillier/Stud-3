**Project**

In this project, we will create a PHP and MySQL web application that focuses on managing user accounts, announcements, and user-submitted comments on a website.

The usage of this web application is simple:
- A user must log in with their username and password to access the site.
- An authenticated user can view a list of announcements and see individual announcements.
- An authenticated user can post new announcements or comments on existing announcements.
- An authenticated user can edit or delete announcements and comments they have posted.
- When an announcement is deleted, all associated comments must also be deleted.

The instructions in the rest of this document must be strictly followed to complete this project, and the project structure must also be adhered to.

**Preparation**

Create a database called `pws` for the user `pws` with the password `pws`. Then, create the three tables defined below:
- The `user` table to store user information.
```sql
id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(32),
password VARCHAR(32),
firstname VARCHAR(32),
lastname VARCHAR(32),
email VARCHAR(64)
```
- The `post` table to store announcements.
```sql
id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT,
timestamp INT,
title VARCHAR(64),
content TEXT,
photo TEXT
```
- The `comment` table to store comments.
```sql
id INT AUTO_INCREMENT PRIMARY KEY,
post_id INT,
user_id INT,
timestamp INT,
content TEXT
```

**Account Management**

The account management functionality allows for the creation or modification of accounts (e.g., changing a password). Before creating an account, it should be checked whether the same username already exists in the database. If it does, display a warning and ask the user to enter a new username. If a user changes their username, it should also be checked for availability (not already in use).
The following scripts should be added for account management:

- `user-update.php(.phtml)`: Display the form defined in .phtml to modify an account.
- `do-user-update.php`: Modify the account (UPDATE)

The following scripts should be completed for account management:

- `classes/User.class.php`: The class for manipulating an account.
- `do-sign-in.php`: Authenticate a user.
- `do-sign-up.php`: Add an account (INSERT)

**Announcement Management**

An authenticated user can post announcements, and they can also edit or delete their announcements. An announcement has a title, content, and a photo. The photo should be stored in the database in BASE64 mode in the `photo` field. An announcement may have multiple comments, and in the case of deletion, all comments associated with it must be deleted as well.
The following scripts should be added for announcement management:

- `post-update.php(.phtml)`: Display the form defined in .phtml to modify an announcement.
- `post-delete.php(.phtml)`: Display a message to confirm deletion.

The following scripts should be completed for announcement management:

- `classes/Post.class.php`: The class for manipulating an announcement.
- `do-post-insert.php`: Add a new announcement (INSERT)
- `do-post-update.php`: Modify the announcement (UPDATE)
- `do-post-delete.php`: Delete an announcement and its comments (DELETE)
- `list.php`: Display the list of announcements (SELECT)
- `view.php`: View an announcement and its comments (SELECT)

**Comment Management**

An authenticated user can post comments on announcements, and they can also edit or delete their comments. A comment only has content.
The following scripts should be added for comment management:

- `comment-update.php(.phtml)`: Display the form defined in .phtml to modify a comment.
- `comment-delete.php(.phtml)`: Display a message to confirm deletion.

The following scripts should be completed for comment management:

- `classes/Comment.class.php`: The class for manipulating a comment.
- `do-comment-insert.php`: Add a new comment (INSERT)
- `do-comment-update.php`: Modify a comment (UPDATE)
- `do-comment-delete.php`: Delete a comment (DELETE)