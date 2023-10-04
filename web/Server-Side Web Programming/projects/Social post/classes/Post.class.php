<?php
require_once "autoload.php";

class Post
{
    private $ID = "";
    private $userID = "";
    private $timestamp = 0;
    private $title = "";
    private $photo = "";
    private $content = "";
    private $username = "";

    public function __construct($data = null)
    {
        if (is_array($data)) {
            $this->timestamp = $data['timestamp'];
            if (isset($data['id'])) {
                $this->ID = $data['id'];
            }
            if (isset($data['user_id'])) {
                $this->userID = $data['user_id'];
            }
            $this->title = $data["title"];
            if (isset($data['photo'])) {
                $this->photo = $data["photo"];
            }
            $this->content = $data["content"];
            $this->username = $this->getUsername();
        } else {
            $this->ID = $data;
        }
    }

    public function getID()
    {
        return $this->ID;
    }

    public function getUserID()
    {
        return $this->userID;
    }

    public function getDateTime()
    {
        return $this->timestamp;
    }

    public function getTitle()
    {
        return $this->title;
    }

    public function getPhoto()
    {
        return $this->photo;
    }

    public function getContent()
    {
        return $this->content;
    }

    public function getUsername()
    {
        try {
            $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
        } catch (Exception $e) {
            die("Il est impossible d'accéder aux donnés pour l'instant");
        }
        $req = $bdd->prepare('SELECT username FROM user WHERE id = ?');
        $req->execute(array($this->userID));
        $data = $req->fetch();
        $this->username = $data['username'];
        $req->closeCursor();
        return $this->username;
    }

    public function getName()
    {
        try {
            $bdd = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
        } catch (Exception $e) {
            die("Il est impossible d'accéder aux donnés pour l'instant");
        }
        $req = $bdd->prepare('SELECT firstname, lastname FROM user WHERE id = ?');
        $req->execute(array($this->userID));
        $data = $req->fetch();
        $req->closeCursor();
        return $data['firstname'] . " " . $data['lastname'];
    }

    public function getComments()
    {
        $comments = [];
        try {
            $dbh = new PDO('mysql:host=localhost;dbname=pws;charset=utf8', 'pws', 'pws');
        } catch (Exception $e) {
            die("Il est impossible d'accéder aux donnés pour l'instant");
        }
        $req = $dbh->prepare('SELECT * FROM comment WHERE post_id=?');
        $req->execute(array($this->ID));
        $n = 0;
        while ($data = $req->fetch()) {
            $comments[$n] = new Comment($data);
            $n++;
        }
        $req->closeCursor();
        return $comments;
    }

    public function insert($dbh)
    {
        // TODO  Insert a new record to the database handled by $dbh. $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare('INSERT INTO post (user_id,timestamp,title,photo,content) VALUES(?, ?, ?, ?, ?)');
        $req->execute(array($this->userID, time(), $this->title, $this->photo, $this->content));
        $req->closeCursor();
    }

    public function update($dbh)
    {
        // TODO Update a record in the database handled by $dbh. $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare('UPDATE post SET user_id=?,timestamp=?,content=?,photo=?,title=? WHERE id=?');
        $req->execute(array($this->userID, $this->timestamp, $this->content, $this->photo, $this->title, $this->ID));
        $req->closeCursor();
    }

    public function delete($dbh)
    {
        // TODO  Delete a record in the database handled by $dbh. $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare("delete from post where id='$this->ID'");
        $req->execute();
    }
}
