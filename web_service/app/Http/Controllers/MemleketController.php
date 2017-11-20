<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class MemleketController extends Controller
{
    public function __construct()
    {
    }

    //Memleket listeleme fonksiyonu
    public function memleket_listele(Request $request)
    {
      $memleketler = DB::table('memleket')->get();

        return response()->json([
          'status' => 200,
          'message' => 'Memleketler Basarili Bir Sekilde Listelendi.',
          'memleketler' => $memleketler,
        ]);
    }
}
