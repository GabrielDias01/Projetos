<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Produto;


class HomeController extends Controller
{
    public function index(){
        $produtos = Produto::take(5)->get();
        return view('home', compact('produtos'));
    }
}
