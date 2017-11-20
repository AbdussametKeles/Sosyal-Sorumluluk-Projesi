<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class KategoriController extends Controller
{
    public function __construct()
    {
    }

    //Kategori listeleme fonksiyonu
    public function kategori_listele(Request $request)
    {
      $kategoriler = DB::table('kategori')->get();

      return response()->json([
        'status' => 200,
        'message' => 'Kategoriler Basarili Bir Sekilde Listelendi.',
        'kategoriler' => $kategoriler,
      ]);
    }
}
