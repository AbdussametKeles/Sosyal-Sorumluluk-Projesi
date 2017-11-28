<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Intervention\Image\ImageManager;

class UrunlerController extends Controller
{
    public function __construct()
    {
    }

    //üye olmayan kullanıcılar için ürünleri listeleme fonksiyonu
    public function urun_listele(Request $request)
    {
    }

    //ürünleri görüntüleme fonksiyonu
    public function urun_goruntule()
    {
    }

    //ürünleri yayinlama fonksiyonu
    public function urun_yayinlama(Request $request)
    {
      $this->validate($request, [
        'token_string' => 'required',
        'kategori_id' => 'required',
        'bagis_tipi' => 'required',
        'urun_adi' => 'required',
        'urun_konumu' => 'required|numeric|min:1|max:81',
        'urun_aciklamasi' => 'required',
        'resim.*' => 'required|image',
      ]);

      $kullanicim = DB::table('oturum')->where([
        ['token_string','=', $request->input('token_string')],
      ])->first();

      //Veritabanına ürün ekleme işlemi
      $urun_id = DB::table('urunler')->insertGetId([
        'kategori_id' => $request->input('kategori_id'),
        'kullanici_id' => $kullanicim->kullanici_id,
        'bagis_tipi' => $request->input('bagis_tipi'),
        'urun_adi' => $request->input('urun_adi'),
        'urun_konumu' => $request->input('urun_konumu'),
        'urun_aciklamasi' => $request->input('urun_aciklamasi'),
      ]);

      //Resim Upload İşlemleri
      $urun_gorselleri = [];
      foreach ($request->file('resim') as $image) {
        $image_manager = new ImageManager();
        $filename  = uniqid().'.'.$image->getClientOriginalExtension();
        $path = \base_path("/public/resim/{$filename}");
        $image_manager->make($image->getRealPath())->resize(200, 200)->save($path);
        $urun_gorselleri[] = [
          'urun_id' => $urun_id,
          'resim' => $filename,
        ];
      }

      $resimler = [];
      foreach ($urun_gorselleri as $gorsel) {
        $resimler[] = [
          'id' => DB::table('resimler')->insertGetId($gorsel),
          'resim_url' => url("/resim/{$gorsel['resim']}"),
        ];
      }

      return response()->json([
        'status' => 200,
        'message' => 'Urun basarili bir sekilde eklendi.',
        'urun_bilgileri' => [
          'kategori_id' => $request->input('kategori_id'),
          'bagis_tipi' => $request->input('bagis_tipi'),
          'urun_adi' => $request->input('urun_adi'),
          'urun_konumu' => $request->input('urun_konumu'),
          'urun_aciklamasi' => $request->input('urun_aciklamasi'),
          'gorselleri' => $resimler,
        ]
      ]);
    }

    //ürünler için yorum fonksiyonu
    public function urun_yorumlama()
    {
    }
}
