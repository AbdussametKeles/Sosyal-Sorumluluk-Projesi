<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Intervention\Image\ImageManager;

class KullaniciController extends Controller
{
    public function __construct()
    {
    }

    //kullanıcı kayıt işlemleri fonksiyonu
    public function kayit(Request $request)
    {
      //Validation
      $this->validate($request, [
        'adi_soyadi' => 'required',
        'dogum_tarihi' => 'required|date_format:Y/m/d',
        'memleket_id' => 'required|numeric|min:1|max:81',
        'sifre' => 'required|min:8|max:12',
        'mail' => 'required|email|unique:kullanici',
        'telefon' => 'required|numeric',
        'resim' => 'image',
      ]);

      //Image Upload
      if($request->file('resim'))
      {
        $image_manager = new ImageManager();
        $image = $request->file('resim');
        $filename  = uniqid().'.'.$image->getClientOriginalExtension();
        $path = \base_path("/public/resim/{$filename}");
        $image_manager->make($image->getRealPath())->resize(200, 200)->save($path);
        $resim = $filename;
      }

      //Database Insert
      $kullanici_id = DB::table('kullanici')->insertGetId([
        'adi_soyadi' => $request->input('adi_soyadi'),
        'dogum_tarihi' => $request->input('dogum_tarihi'),
        'memleket_id' => $request->input('memleket_id'),
        'sifre' => $request->input('sifre'),
        'mail' => $request->input('mail'),
        'telefon' => $request->input('telefon'),
        'resim' => (isset($resim)) ? $resim : Null,
      ]);

      //Generate Session
      $token = uniqid();
      DB::table('oturum')->insert([
        'kullanici_id' => $kullanici_id,
        'token_string' => $token,
      ]);

      //Result
      return response()->json([
        'status' => 200,
        'message' => 'Kayıt başarılı!',
        'token' => $token,
        'kullanici' => [
          'adi_soyadi' => $request->input('adi_soyadi'),
          'dogum_tarihi' => $request->input('dogum_tarihi'),
          'memleket_id' => $request->input('memleket_id'),
          'sifre' => $request->input('sifre'),
          'mail' => $request->input('mail'),
          'telefon' => $request->input('telefon'),
          'resim' => (isset($resim)) ? url("/resim/{$resim}") : Null,
        ]
      ]);
    }

    //kullanıcı giriş işlemleri fonksiyonu
    public function giris()
    {
    }

    //kullanıcı profil güncelleme fonksiyonu
    public function profil_guncelle()
    {
    }

    //kullanıcı şifremi unuttum fonksiyonu
    public function sifremi_unuttum()
    {
    }
}
