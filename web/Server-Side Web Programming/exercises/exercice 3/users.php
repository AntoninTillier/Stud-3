<?php

function load_users($filename)
{
    $users = file($filename);
    $res = array();
    $res[] = $users[0];
    $res[] = $users[1];
    foreach ($users as $line) {
        if (stripos($line, '#') === FALSE) {
            $information = explode(':', $line);
            $res[$information[1]] = [
                "ID" => $information[0],
                "USERNAME" => $information[1],
                "PASSWORD" => $information[2],
                "REALNAME" => $information[3],
                "GROUP" => $information[4]
            ];
        }
    }
    return $res;
}

function next_id($users)
{
    $last_key = array_key_last($users);
    $id = $users[$last_key]["ID"];
    return $id + 1;
}

function save_users($users, $filename)
{
    file_put_contents($filename, "");
    $file = "";
    foreach ($users as $key => $values) {
        if (is_array($values) == FALSE) {
            $file .= "$values";
        } else {
            $file .= "$values[ID]:$values[USERNAME]:$values[PASSWORD]:$values[REALNAME]:$values[GROUP]";
        }
    }
    file_put_contents($filename, $file);
}

function change_group($username, $group)
{
    $users = load_users("users.txt");
    if (isset($users[$username])) {
        $users[$username]["GROUP"] = $group;
        save_users($users, "users.txt");
        return TRUE;
    }
    return FALSE;
}

function disable_invalid_users()
{
    /*$users = load_users("users.txt");
		$new_users = array();
		$new_users[] = $users[0];
		$new_users[] = $users[1];
		foreach ($users as $key => $value) {
			if(is_array($value) && $value["REALNAME"] != "") {
				$new_users[$value["USERNAME"]] = [
					"ID" => $value["ID"],
		      "USERNAME" => $value["USERNAME"],
		      "PASSWORD" => $value["PASSWORD"],
		      "REALNAME" => $value["REALNAME"],
		      "GROUP" => $value["GROUP"]
				];
			}
		}
		save_users($new_users, "users.txt");*/
    $users = load_users("users.txt");
    foreach ($users as $u => $v) {
        if ($v["REALNAME"] == "") {
            if (strlen($v["PASSWORD"]) <= 32) {
                $users[$u]["PASSWORD"] .= "*";
            }
        }
    }
    save_users($users, "users.txt");
}

function add_user($username, $password, $realname, $group = 999)
{
    $users = load_users("users.txt");
    $id = next_id($users);
    $users[$username] = [
        "ID" => $id,
        "USERNAME" => $username,
        "PASSWORD" => md5($password),
        "REALNAME" => $realname,
        "GROUP" => $group
    ];
    save_users($users, "users.txt");
}

function change_password($username, $password)
{
    $users = load_users("users.txt");
    if (isset($users[$username])) {
        $users[$username]["PASSWORD"] = md5($password);
        save_users($users, "users.txt");
        return TRUE;
    }
    return FALSE;
}

function delete_user($username)
{
    $users = load_users("users.txt");
    if (isset($users[$username])) {
        unset($users[$username]);
        save_users($users, "users.txt");
        return TRUE;
    }
    return FALSE;
}

function check_user($username, $password)
{
    $users = load_users("users.txt");
    if (isset($users[$username])) {
        if ($users[$username]["PASSWORD"] == md5($password)) {
            return $users[$username]["ID"];
        }
    }
    return FALSE;
}

function print_users($filename)
{
    $lines = file($filename);
    echo "<pre>";
    foreach ($lines as $line) {
        echo $line;
    }
    echo "</pre>";
}
