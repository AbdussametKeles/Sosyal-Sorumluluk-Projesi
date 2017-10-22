<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

// KullanıcıController için Routes Oluşturuldu.
$router->group(['prefix' => 'kullanici'], function () use ($router) {
  $router->post('kayit', ['uses' => 'KullaniciController@kayit']);
  $router->get('giris', ['uses' => 'KullaniciController@giris']);
  $router->get('sifremi_unuttum', ['uses' => 'KullaniciController@sifremi_unuttum']);
  $router->post('profil_guncelle', ['uses' => 'KullaniciController@profil_guncelle']);
});

// MemleketController için Routes Oluşturuldu.
$router->group(['prefix' => 'memleket'], function () use ($router) {
  $router->get('listele', ['uses' => 'MemleketController@memleket_listele']);
});

// KategoriController için Routes Oluşturuldu.
$router->group(['prefix' => 'kategori'], function () use ($router) {
  $router->get('listele', ['uses' => 'KategoriController@kategori_listele']);
});

// UrunlerController için Routes Oluşturuldu.
$router->group(['prefix' => 'urunler'], function () use ($router) {
  $router->get('listele', ['uses' => 'UrunlerController@urun_listele']);
  $router->get('goruntule', ['uses' => 'UrunlerController@urun_goruntule']);
  $router->post('yayinlama', ['uses' => 'UrunlerController@urun_yayinlama']);
  $router->post('yorumlama', ['uses' => 'UrunlerController@urun_yorumlama']);
});

// ContactController için Routes Oluşturuldu.
$router->group(['prefix' => 'contact'], function () use ($router) {
  $router->post('iletisimformu', ['uses' => 'ContactController@iletisim_formu']);
});
