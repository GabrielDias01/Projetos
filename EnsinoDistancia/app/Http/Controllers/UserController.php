<?php


namespace App\Http\Controllers;


use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;


class UserController extends Controller
{
    // Exibir o formulário de login
    public function showLoginForm()
    {
        return view('alunos.login');
    }


    // Processar o login do usuário
    public function login(Request $request)
    {
        // Validação dos dados de entrada
        $credentials = $request->validate([
            'email' => ['required', 'email'],
            'password' => ['required'],
        ]);
    
        // Tentativa de autenticação
        if (Auth::guard('web')->attempt($credentials)) {
            // Regenera a sessão após a autenticação bem-sucedida
            $request->session()->regenerate();
            return redirect()->intended('/dashboard');
        }
    
        // Redireciona de volta com mensagem de erro e mantém o email digitado
        return back()->withErrors([
            'email' => 'As credenciais não correspondem aos nossos registros.',
        ])->onlyInput('email');
    }


    // Exibir o formulário de registro
    public function showRegistroForm()
    {
        return view('alunos.registro');
    }


    // Processar o registro de um novo usuário
    public function registro(Request $request)
    {
        $request->validate([
            'name' => 'required|string|max:255',
            'email' => 'required|string|email|max:255|unique:users',
            'password' => 'required|string|min:8|confirmed',
        ]);


       
            $alunos = User::create([
                'name' => $request->name,
                'email' => $request->email,
                'password' => Hash::make($request->password),
            ]);


            return redirect('/');
    }


    // Realizar o logout do usuário
    public function logout(Request $request)
    {
        Auth::logout();


        $request->session()->regenerateToken();
        $request->session()->invalidate();


        return redirect('/');
    }
}
