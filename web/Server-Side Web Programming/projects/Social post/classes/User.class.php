<?php
require_once "autoload.php";

class User
{
    private $ID = "";
    private $username = "";
    private $password = "";
    private $firstname = "";
    private $lastname = "";
    private $email = "";

    public function __construct($row = null)
    {
        if (is_array($row)) {
            // TODO Create a user from an array.
            $this->ID = $row["id"];
            $this->username = $row["username"];
            $this->password = md5($row["password"]);
            $this->firstname = $row["firstname"];
            $this->lastname = $row["lastname"];
            $this->email = $row["email"];
        }
    }

    public function getID()
    {
        return $this->ID;
    }

    public function getUsername()
    {
        return $this->username;
    }

    public function getPassword()
    {
        return $this->password;
    }

    public function getName()
    {
        return $this->firstname . " " . $this->lastname;
    }

    public function getEmail()
    {
        return $this->email;
    }

    public function insert($dbh)
    {
        // TODO Insert a new record to the database handled by $dbh. $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare('INSERT INTO user (username, password, firstname, lastname, email) VALUES(?, ?, ?, ?, ?)');
        $req->execute(array($this->username, $this->password, $this->firstname, $this->lastname, $this->email));
        $req->closeCursor();
    }

    public function update($dbh)
    {
        // TODO Update a record in the database handled by $dbh.  $dbh can be an instance of mysqli or of PDO.
        $req = $dbh->prepare('UPDATE user SET password=?,firstname=?,lastname=?,username=?,email=? WHERE id=?');
        $req->execute(array($this->password, $this->firstname, $this->lastname, $this->username, $this->email, $this->ID));
        $req->closeCursor();
    }
}
