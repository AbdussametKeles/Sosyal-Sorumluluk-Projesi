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
      $this->validate($request, [
        'token_string' => '',
      ]);

      if(empty($request->input('token_string'))){
        $urunler = DB::table('urunler')->where('deleted','=','0')->get();

        foreach ($urunler as $urun_id => $urun) {

          $urunler[$urun_id]->gorseller = DB::table('resimler')->where('urun_id', $urun->urun_id)->get();
          $urunler[$urun_id]->yorumlar = DB::table('yorum')->where('urun_id', $urun->urun_id)->get();

          foreach($urunler[$urun_id]->gorseller as $resim_id => $resim) {
            $urunler[$urun_id]->gorseller[$resim_id]->resim = url('resim/'.$urunler[$urun_id]->gorseller[$resim_id]->resim);
          }
          foreach($urunler[$urun_id]->yorumlar as $yorum_id => $yorum){
            $urunler[$urun_id]->yorumlar[$yorum_id]->yorum_icerigi = $urunler[$urun_id]->yorumlar[$yorum_id]->yorum_icerigi;
          }
        }

      return response()->json([
        'status' => 200,
        'message' => 'Urunler Basarili Bir Sekilde Listelendi.',
        'urunler' => $urunler,
      ]);
    }else{

      $kullanici = DB::table('oturum')->where([
        ['token_string', '=', $request->input('token_string')],
      ])->first();

      $urunler = DB::table('urunler')->where([
        ['kullanici_id', '=', $kullanici->kullanici_id],
        ['deleted','=','0'],
      ])->get();

      foreach ($urunler as $urun_id => $urun){
        $urunler[$urun_id]->gorseller = DB::table('resimler')->where('urun_id', $urun->urun_id)->get();
        $urunler[$urun_id]->yorumlar = DB::table('yorum')->where('urun_id', $urun->urun_id)->get();

        foreach($urunler[$urun_id]->gorseller as $resim_id => $resim) {
          $urunler[$urun_id]->gorseller[$resim_id]->resim = url('resim/'.$urunler[$urun_id]->gorseller[$resim_id]->resim);
        }
        foreach($urunler[$urun_id]->yorumlar as $yorum_id => $yorum){
          $urunler[$urun_id]->yorumlar[$yorum_id]->yorum_icerigi = $urunler[$urun_id]->yorumlar[$yorum_id]->yorum_icerigi;
        }
      }

    return response()->json([
      'status' => 200,
      'message' => 'Urunler Basarili Bir Sekilde Listelendi.',
      'urunler' => $urunler,
    ]);
  }
}

    //ürünleri görüntüleme fonksiyonu
    public function urun_goruntule(Request $request)
    {
      $this->validate($request, [
        'urun_id' => 'required',
      ]);

      $urun = DB::table('urunler')->where([
        ['urun_id','=', $request->input('urun_id')],
      ])->first();

      if(!empty($urun)){

        $urun->gorseller = DB::table('resimler')->where('urun_id', $urun->urun_id)->get();
        $urun->yorumlar = DB::table('yorum')->where('urun_id', $urun->urun_id)->get();

        foreach($urun->gorseller as $resim_id => $resim) {
          $urun->gorseller[$resim_id]->resim = url('resim/'.$urun->gorseller[$resim_id]->resim);
        }

        foreach($urun->yorumlar as $yorum_id => $yorum){
          $urun->yorumlar[$yorum_id]->yorum_icerigi = $urun->yorumlar[$yorum_id]->yorum_icerigi;
        }

        return response()->json([
          'status' => 200,
          'message' => 'Urun Basarili Bir Sekilde Goruntulendi.',
          'urun' => $urun,
        ]);

      }else{
        return reponse()->json([
          'status' => 400,
          'message' => 'urun id Yanlis, Lutfen Tekrar Deneyiniz.',
        ]);
      }
    }

    //ürünleri görüntüleme fonksiyonu
    public function urun_silme(Request $request)
    {
      $this->validate($request, [
        'token_string' => 'required',
        'urun_id' => 'required',
      ]);

      $kullanici = DB::table('oturum')->where([
        ['token_string', '=', $request->input('token_string')],
      ])->first();

      $urun = DB::table('urunler')->where([
        ['urun_id', '=', $request->input('urun_id')],
      ])->first();

      if(($kullanici->kullanici_id) == ($urun->kullanici_id)){
        DB::table('yorum')->where([
          ['urun_id', '=', $request->input('urun_id')],
        ])->update(['deleted' => '1']);

        DB::table('resimler')->where([
          ['urun_id', '=', $request->input('urun_id')],
        ])->update(['deleted' => '1']);

        DB::table('urunler')->where([
          ['urun_id', '=', $request->input('urun_id')],
        ])->update(['deleted' => '1']);

        return response()->json([
          'status' => 200,
          'message' => 'urun basarili bir sekilde silindi.',
        ]);
      }else{
        return response()->json([
          'status' => 400,
          'message' => 'Kullaniciya ait olmayan bir urunu silmeye calistiniz. Lutfen tekrar deneyiniz.',
        ]);
      }
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

    //Urunler için guncelleme fonksiyonu
    public function urun_guncelle(Request $request){
      $this->validate($request, [
        'token_string' => 'required',
        'urun_id' => 'required',
        'kategori_id' => '',
        'bagis_tipi' => '',
        'urun_adi' => '',
        'urun_konumu' => 'numeric|min:1|max:81',
        'urun_aciklamasi' => '',
      ]);

      $kullanici = DB::table('oturum')->where([
        ['token_string', '=', $request->input('token_string')],
      ])->first();

      $urun = DB::table('urunler')->where([
        ['urun_id', '=', $request->input('urun_id')],
      ])->first();

      if(($kullanici->kullanici_id) == ($urun->kullanici_id)){
        if($request->has('kategori_id')){
          DB::table('urunler')
                    ->where('urun_id', $urun->urun_id)
                    ->update(['kategori_id' => $request->input('kategori_id')]);
        }

        if($request->has('bagis_tipi')){
          DB::table('urunler')
                    ->where('urun_id', $urun->urun_id)
                    ->update(['bagis_tipi' => $request->input('bagis_tipi')]);
        }

        if($request->has('urun_adi')){
          DB::table('urunler')
                    ->where('urun_id', $urun->urun_id)
                    ->update(['urun_adi' => $request->input('urun_adi')]);
        }

        if($request->has('urun_konumu')){
          DB::table('urunler')
                    ->where('urun_id', $urun->urun_id)
                    ->update(['urun_konumu' => $request->input('urun_konumu')]);
        }

        if($request->has('urun_aciklamasi')){
          DB::table('urunler')
                    ->where('urun_id', $urun->urun_id)
                    ->update(['urun_aciklamasi' => $request->input('urun_aciklamasi')]);
        }

        $urun = DB::table('urunler')->where([
          ['urun_id', '=', $request->input('urun_id')],
        ])->first();

        return response()->json([
          'status' => 200,
          'message' => 'urun guncelleme basarili bir sekilde yapildi.',
          'urun' => $urun,
        ]);
      }else{
        return response()->json([
          'status' => 400,
          'message' => 'kullaniciya ait olmayan bir urunu guncellemeye calistiniz. Sadece kendinize ait olan urunlerin bilgisini guncelleyebilirsiniz.'
        ]);
      }
    }

    public function resim_guncelleme(Request $request){
      $this->validate($request, [
        'resim_id' => 'required',
        'resim.*' => 'required',
      ]);
    }

    //ürünler için yorum fonksiyonu
    public function urun_yorumlama(Request $request)
    {
      $this->validate($request, [
        'token_string' => 'required',
        'urun_id' => 'required',
        'yorum_icerigi' => 'required',
      ]);

      $kullanici = DB::table('oturum')->where([
        ['token_string', '=', $request->input('token_string')],
      ])->first();

      $urun = DB::table('urunler')->where([
        ['urun_id', '=', $request->input('urun_id')],
      ])->first();

      $yorum = DB::table('yorum')->insertGetId([
        'yorum_icerigi' => $request->input('yorum_icerigi'),
        'urun_id' => $request->input('urun_id'),
        'kullanici_id' => $kullanici->kullanici_id,
      ]);

      $yayinlanan = DB::table('yorum')->where([
        ['yorum_id', '=', $yorum],
      ])->first();

      return response()->json([
        'status' => 200,
        'message' => 'yorum basarili bir sekild eklendi.',
        'yorum' => $yayinlanan,
      ]);
    }

    //Ürünler için yorum silme fonksiyonu
    public function urun_yorum_silme(Request $request){
      $this->validate($request, [
        'token_string' => 'required',
        'yorum_id' => 'required',
      ]);

      $kullanici = DB::table('oturum')->where([
        ['token_string', '=', $request->input('token_string')],
      ])->first();

      $yorum = DB::table('yorum')->where([
        ['yorum_id', '=', $request->input('yorum_id')],
      ])->first();

      $urun = DB::table('urunler')->where([
        ['urun_id', '=', $yorum->urun_id],
      ])->first();

      if(($kullanici->kullanici_id) == ($urun->kullanici_id) || ($kullanici->kullanici_id) == ($yorum->kullanici_id)){
        DB::table('yorum')->where([
          ['yorum_id', '=', $yorum->yorum_id],
        ])->update(['deleted' => '1']);

        return response()->json([
          'status' => 200,
          'message' => 'yorum basarili bir sekilde silindi.',
        ]);
      }else{
        return response()->json([
          'status' => 400,
          'message' => 'yetkiniz disindaki bir yorumu silmeye calistiniz. Lutfen tekrar deneyiniz.',
        ]);
      }
    }

    //Ürünler için yorum düzenleme fonksiyonu
    public function urun_yorum_duzenleme(Request $request){
      $this->validate($request, [
        'token_string' => 'required',
        'yorum_id' => 'required',
        'yorum_icerigi' => 'required',
      ]);

      $kullanici = DB::table('oturum')->where([
        ['token_string', '=', $request->input('token_string')],
      ])->first();

      $yorum = DB::table('yorum')->where([
        ['yorum_id', '=', $request->input('yorum_id')],
      ])->first();

      if(($kullanici->kullanici_id) == ($yorum->kullanici_id)){
        DB::table('yorum')
                  ->where('yorum_id', $request->input('yorum_id'))
                  ->update(['yorum_icerigi' => $request->input('yorum_icerigi')]);

        $guncellenen = DB::table('yorum')->where([
          ['yorum_id', '=', $yorum->yorum_id],
        ])->first();

        return response()->json([
          'status' => 200,
          'message' => 'yorum guncellendi.',
          'yorum' => $guncellenen,
        ]);
      }else{
        return response()->json([
          'status' => 200,
          'message' => 'yetkiniz disindaki yorumlari guncelleyemezsiniz.',
        ]);
      }
    }

    //urunler için filtreleme işlemi
    public function filtreleme(Request $request){
      $this->validate($request, [
        'urun_konumu' => '',
        'kategori_id' => '',
        'urun_adi' => '',
        'page' => 'numeric',
       ]);

      $page = ($request->get('page')) ? $request->get('page') : 1;

      $urunler = DB::table('urunler');
      if($request->get('urun_konumu'))
        $urunler = $urunler->where('urun_konumu', $request->get('urun_konumu'));
      if($request->get('urun_adi'))
        $urunler = $urunler->where('urun_adi', 'LIKE', "%{$request->get('urun_adi')}%");
      $urunler = $urunler->offset(20 * ($page-1))->limit(20)->get();

      foreach ($urunler as $urun_id => $urun) {

        $urunler[$urun_id]->gorseller = DB::table('resimler')->where('urun_id', $urun->urun_id)->get();
        $urunler[$urun_id]->yorumlar = DB::table('yorum')->where('urun_id', $urun->urun_id)->get();

        foreach($urunler[$urun_id]->gorseller as $resim_id => $resim) {
          $urunler[$urun_id]->gorseller[$resim_id]->resim = url('resim/'.$urunler[$urun_id]->gorseller[$resim_id]->resim);
        }
        foreach($urunler[$urun_id]->yorumlar as $yorum_id => $yorum){
          $urunler[$urun_id]->yorumlar[$yorum_id]->yorum_icerigi = $urunler[$urun_id]->yorumlar[$yorum_id]->yorum_icerigi;
        }
      }

      return response()->json([
        'status' => 200,
        'message' => 'filtreleme basarili.',
        'urunler' => $urunler,
      ]);
    }
}
