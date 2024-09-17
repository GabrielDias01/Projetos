import { NextResponse } from 'next/server'; // ou use o import adequado conforme sua configuração
import jwt from 'jsonwebtoken';
import connectMongo from '@/utils/dbConnect'; // ou o caminho correto para sua função de conexão
import User from '@/models/User';

export async function GET(request) {
  try {
    await connectMongo();
    const token = request.headers.get('Authorization')?.split(' ')[1];

    if (!token) {
      return NextResponse.json({ success: false, message: 'Token de autenticação ausente' }, { status: 401 });
    }

    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    const userId = decoded.userId;

    const user = await User.findById(userId);
    if (!user) {
      return NextResponse.json({ success: false, message: 'Usuário não encontrado' }, { status: 404 });
    }

    return NextResponse.json({ username: user.username, isAdmin: user.isAdmin });
  } catch (error) {
    console.error("Erro ao obter informações do usuário:", error);
    return NextResponse.json({ success: false, message: "Erro ao obter informações do usuário" }, { status: 500 });
  }
}
