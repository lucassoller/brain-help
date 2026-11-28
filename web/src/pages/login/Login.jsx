import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext.jsx";
import api from "../../api/api.js";
import "./Login.css";

function Login() {
    const navigate = useNavigate();
    const { login: realizarLogin, logout } = useAuth();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [erro, setErro] = useState("");
    const [carregando, setCarregando] = useState(false);
    const [mostrarSenha, setMostrarSenha] = useState(false);

    useEffect(() => {
        logout();
    }, []);

    async function entrar(e) {
        e.preventDefault();
        setErro("");
        setCarregando(true);

        try {
            const response = await api.post("/public/login", {
                email,
                senha,
            });

            realizarLogin(response.data, response.data.token);
            navigate("/home/meus-pacientes");
        } catch (error) {
            const mensagem = error.response?.data?.message;
            const erroEmail = error.response?.data?.errors?.email;
            const erroSenha = error.response?.data?.errors?.senha;

            setErro(erroEmail || erroSenha || mensagem || "Erro ao realizar login");
        } finally {
            setCarregando(false);
        }
    }

    return (
        <div className="login-container">
            <div className="login-right-container">
                {erro && (
                    <div className="erro">
                        <div className="erro-alert">
                            <span>{erro}</span>
                            <span className="close-erro" onClick={() => setErro("")}>✕</span>
                        </div>
                    </div>
                )}

                <form onSubmit={entrar} className="login-content">
                    <div className="login-title">
                        <h1>Entrar</h1>
                    </div>

                    <input
                        type="email"
                        className="form form-email"
                        name="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />

                    <div className="login-form-senha">
                        <input
                            type={mostrarSenha ? "text" : "password"}
                            className="form form-senha"
                            name="senha"
                            placeholder="Senha"
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            required
                        />
                        <span
                            className="eye"
                            onClick={() => setMostrarSenha(!mostrarSenha)}
                        >
                            <img
                                src={mostrarSenha ? "/eye2.svg" : "/eye.svg"}
                                alt="mostrar senha"
                                width={20}
                            />
                        </span>
                    </div>

                    <div className="login-senha">
                        <div
                            className="login-link-senha"
                            onClick={() => navigate("/esquecer")}
                        >
                            Esqueceu sua senha?
                        </div>
                    </div>

                    <button
                        type="submit"
                        className="login-entrar"
                        disabled={carregando}
                    >
                        {carregando ? "Entrando..." : "Entrar"}
                    </button>

                    <div className="login-footer">
                        <div>Não possui uma conta?</div>
                        <div
                            className="login-cadastro"
                            onClick={() => navigate("/cadastro-inicial")}
                        >
                            Cadastre-se
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;