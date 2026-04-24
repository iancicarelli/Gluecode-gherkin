package model;

public class Usuario {
    private String rut;
    private String password;
    private String rol;
    private boolean activo;

    public Usuario(String rut, String password, String rol, boolean activo) {
        this.rut = rut;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    public String getRut() { return rut; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }
    public boolean isActivo() { return activo; }
}
