<?php

  include('config.php');

  if(!empty($_GET)) {
    if(!empty($_GET['report'])) {
      $report = $_GET['report'];
    }
  }

  $options = array(
    PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
  );
  
  try {
    $pdo = new PDO('mysql:dbname=' . $mysql_database . ';host=' . $mysql_host . ';port=' . $mysql_port, $mysql_user, $mysql_password, $options);
    $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
  } catch (PDOException $e) {
    echo 'Couldn\'t connect to MySQL database! Make sure to edit config.php. <br>';
    echo $e->getMessage();
  }
  if(!empty($report)):

    $users_query = $pdo->prepare("SELECT DISTINCT(`name`) AS `name` FROM `test_reportmessages` WHERE `reportid` = ?");
    $users_query->execute(array($report));

    $users = array();
    $names = array();
    while ($row = $users_query->fetch(PDO::FETCH_ASSOC)) {
      $users[]= $row;
      if(strlen($row['name']) > 20) {
        $data = json_decode(file_get_contents('http://api.minetools.eu/uuid/' . $row['name'] . ''), true);
        $names[$row['name']] = $data['name'];
      } else {
        $names[$row['name']] = $row['name'];
      }
    }
    $usercount = count($users);

    $msgcount_query = $pdo->prepare("SELECT COUNT(*) FROM `test_reportmessages` WHERE `reportid` = ?");
    $msgcount_query->execute(array($report));
    $msgcount_query = $msgcount_query->fetch();
    $msgcount = $msgcount_query['0'];

  endif;

?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><?php echo $network_name; ?> ChatLog</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    </style>
  </head>
  <body>
    <div class="container" style="padding-top:40px; padding-bottom:20px; max-width:600px;">
      <?php
        if(!file_exists("config.php")) {
          $errors = true;
          $file = 'config.php';
          $content = '<?php

    $mysql_host = \'\';
    $mysql_port = \'3306\';
    $mysql_user = \'\';
    $mysql_password = \'\';
    $mysql_database = \'chatlog\';

    $network_name = \'\';

?>';

            $fp = fopen($file, "w") or die("<div class=\"alert alert-danger\" role=\"alert\">Couldn't write config file! Make sure you have writing permission in this directory.</div>");
            echo "<div class=\"alert alert-success\" role=\"success\">I just wrote a config file for you! Make sure to add your MySQL-Details now.</div>";
            fwrite($fp, $content);

            fclose($fp);
        }
        
        if(isset($errors) && $errors != false) {
          exit();
       }
    ?>
      <?php if(!empty($report)): ?>
      <div class="panel panel-default" style="margin:auto;">
        <div class="panel-heading">
          <h3 class="panel-title">
            <?php
              $query = $pdo->prepare("SELECT * FROM `test_reportmessages` WHERE `reportid` = ?");
              $query->execute(array($report));
              $row = $query->fetch();
              if(!empty($row)): 
                if($usercount > 1):
                  echo $usercount . ' users - ' . $msgcount . ' messages - ' . date("d.m.Y", $row['timestamp']) . " - " . $row['server'];
                else:
                  echo '<img src="https://cravatar.eu/helmhead/'. htmlspecialchars($names[$row['name']]) . '/32.png"> ' . htmlspecialchars($names[$row['name']]) . " - " . date("d.m.Y", $row['timestamp']) . " - " . $row['server'];
                endif;
              else:
                echo "404 - Report not found"; 
              endif; 
            ?>
          </h3>
        </div>
        <div class="panel-body" style="padding:5px;">
          <?php
            if(!empty($row)) {
              $query = $pdo->prepare("SELECT * FROM `test_reportmessages` WHERE `reportid` = ? ORDER BY `timestamp` ASC");
              $query->execute(array($report));
              while($row = $query->fetch()) {
                echo "<pre style='padding:6px; margin:6px;'>["  . date("H:i:s", $row['timestamp'])   . "] ";
                if($usercount > 1):
                  echo '<img src="https://cravatar.eu/helmavatar/'. htmlspecialchars($names[$row['name']]) . '/14.png"> ' . htmlspecialchars($names[$row['name']]) . " > ";
                endif;
                echo htmlspecialchars($row['message']) . "</pre>";
              }
            } else {
              echo "There is no Report with the ID <strong>" . htmlspecialchars($report) . "</strong>";
            }
          ?>
        </div>
    </div>
    <?php else: ?>
      <div class="panel panel-default" style="margin:auto; max-width:600px;">
        <div class="panel-heading">
          <h3 class="panel-title">
            ChatLog
          </h3>
        </div>
        <div class="panel-body">
          To get a ChatLog from a player use this command:
          <pre>/chatreport &ltplayername&gt</pre>
        </div>
    </div>
    <?php endif; ?>
    </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.js"></script>
  </body>
</html>