<?php

use Illuminate\Support\Facades\Route;
use app\Http\Controllers\VagaContoller;

Route::get('/', function () {
    return view('welcome');

    
});
Route::resource('vagas',[VagaContoller::class]);
