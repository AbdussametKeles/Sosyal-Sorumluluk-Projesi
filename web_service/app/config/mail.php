<?php

return array(

    'driver' => 'smtp',

    'host' => 'smtp.gmail.com',

    'port' => 587,

    'from' => array('address' => 'mailadresi', 'name' => 'Sosyal Sorumluluk Projesi Şifreniz'),

    'encryption' => 'tls',

    'username' => 'mailadresi',

    'password' => 'mailsifre',

    'sendmail' => '/usr/sbin/sendmail -bs',

    'pretend' => false,

);
