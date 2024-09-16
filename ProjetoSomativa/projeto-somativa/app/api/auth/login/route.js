import User from "@/models/User";
import connectMongo from "@/utils/dbConnect";
import { NextResponse } from "next/server";
import jwt from "jsonwebtoken";

export async function POST(request) {
    const { username, password } = await request.json(); // Obtém os dados de login do usuário
    await connectMongo(); // Conecta ao banco de dados

    try {
        // Verifica se o usuário existe no banco de dados
        const user = await User.findOne({ username });

        // Se o usuário não existir ou a senha estiver incorreta, retorna um erro
        if (!user || !(await user.comparePassword(password))) {
            return NextResponse.json({ success: false, message: "Usuário ou senha incorretos" }, { status: 400 });
        }

        // Se o usuário existir e a senha estiver correta, gera o token JWT
        const token = jwt.sign({ userId: user._id }, process.env.JWT_SECRET, { expiresIn: '1h' }); // Token expira em 1 hora

        // Retorna o token para o frontend
        return NextResponse.json({ token });

    } catch (error) {
        return NextResponse.json({ success: false }, { status: 400 });
    }

}
