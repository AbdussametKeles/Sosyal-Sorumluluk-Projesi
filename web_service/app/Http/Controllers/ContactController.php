<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class ContactController extends Controller
{
    public function __construct()
    {
    }

    //iletişim formu için çalışacak fonksiyon
    public function iletisim_formu(Request $request)
    {
      $this->validate($request, [
        'adi_soyadi' => 'required',
        'eposta' => 'required|email',
        'konu' => 'required',
        'message' => 'required',
      ]);

      $contact = DB::table('iletisim')->insertGetId([
        'adi_soyadi' => $request->input('adi_soyadi'),
        'eposta' => $request->input('eposta'),
        'konu' => $request-> input('konu'),
        'message' => $request->input('message'),
      ]);

      return response()->json([
        'status' => 200,
        'message' => 'Basarili bir sekilde iletildi!',
      ]);
    }
}
