package service;

import model.ResultadoLogin;
import model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public void registrarUsuario(String rut, String password, String rol, boolean activo) {
        usuarios.put(rut, new Usuario(rut, password, rol, activo));
    }

    public ResultadoLogin login(String rut, String password) {
        if (rut == null || rut.isEmpty() ||
                password == null || password.isEmpty()) {
            return new ResultadoLogin(false, "CAMPOS_VACIOS", null);
        }
        if (!validarFormatoRut(rut)) {
            return new ResultadoLogin(false, "RUT_INVALIDO", null);
        }
        Usuario u = usuarios.get(rut);
        if (u == null || !u.getPassword().equals(password)) {
            return new ResultadoLogin(false, "CREDENCIALES_INVALIDAS", null);
        }
        if (!u.isActivo()) {
            return new ResultadoLogin(false, "CUENTA_DESHABILITADA", null);
        }
        return new ResultadoLogin(true, "OK", u.getRol());
    }

    private boolean validarFormatoRut(String rut) {
        return rut.matches("\\d{7,8}-[\\dkK]");
    }
}
