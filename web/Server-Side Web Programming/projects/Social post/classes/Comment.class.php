<?php
require_once "autoload.php";

class Comment
{
    private $ID = "";
    private $postID = "";
    private $userID = "";
    private $timestamp = 0;
    private $content = "";
    private $username = "";

    public function __construct($data = null)
    {
        if (is_array($data)) {
            $this->timestamp = $data['timestamp'];
            if (isset($data['id'])) {
                $this->ID = $data['id'];
            }
            $this->postID = $data['post_id'];
            $this->userID = $data['user_id'];
            $this->content = $data['content'];
            $this->username;
        } else {
            $this->ID = $data;
        }
    }

    public function getID()
    {
        return $this->ID;
    }

    public function getPostID()
    {
        return $this->postID;
    }

    public function getUserID()
    {
        return $this->userID;
    }

    public function getDateTime()
    {
        return $this->timestamp;
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

    public function insert($dbh)
    {
        // TODO Insert a new record to the database handled by $dbh. $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare('INSERT INTO comment (post_id,user_id,timestamp,content) VALUES(?, ?, ?, ?)');
        $req->execute(array($this->postID, $this->userID, $this->timestamp, $this->content));
        $req->closeCursor();
    }

    public function update($dbh)
    {
        // TODO  Update a record in the database handled by $dbh.  $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare("UPDATE comment set timestamp=?,content=? where id=?");
        $req->execute(array($this->timestamp, $this->content, $this->ID));
        $req->closeCursor();
    }

    public function delete($dbh)
    {
        // TODO  Delete a record in the database handled by $dbh.  $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare("delete from comment where id='$this->ID'");
        $req->execute();
    }
}
