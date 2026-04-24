package model;

public class ResultadoLogin {
    private boolean exito;
    private String codigo;
    private String rol;

    public ResultadoLogin(boolean exito, String codigo, String rol) {
        this.exito = exito;
        this.codigo = codigo;
        this.rol = rol;
    }

    public boolean isExito() { return exito; }
    public String getCodigo() { return codigo; }
    public String getRol() { return rol; }
}
