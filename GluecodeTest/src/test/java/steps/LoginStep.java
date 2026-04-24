package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import model.ResultadoLogin;
import service.AuthService;
import static org.junit.Assert.*;

public class LoginStep {
    private AuthService authService;
    private String rutIngresado;
    private String passwordIngresado;
    private ResultadoLogin resultado;

    @Before
    public void setUp() {
        authService = new AuthService();
    }


    @Given("el usuario con RUT {string} está registrado en el sistema con rol {string}")
    public void registrarUsuario(String rut, String rol) {
        authService.registrarUsuario(rut, "contrasena_valida", rol, true);
    }

    @Given("el usuario con RUT {string} tiene su cuenta deshabilitada en EasyCheck")
    public void registrarUsuarioDeshabilitado(String rut) {
        authService.registrarUsuario(rut, "contrasena_valida", "estudiante", false);
    }

    @Given("el usuario se encuentra en el formulario de inicio de sesión")
    public void formularioAbierto() {
        rutIngresado = null;
        passwordIngresado = null;
    }

    @When("el usuario ingresa el RUT {string}")
    public void ingresarRut(String rut) {
        rutIngresado = rut;
    }

    @When("el usuario ingresa la contraseña {string}")
    public void ingresarPassword(String pwd) {
        passwordIngresado = pwd;
    }

    @When("el usuario ingresa el RUT {string} sin dígito verificador")
    public void ingresarRutSinDv(String rut) {
        rutIngresado = rut;
    }

    @When("el usuario deja el campo RUT vacío")
    public void dejaCampoRutVacio() {
        rutIngresado = "";
    }

    @When("el usuario deja el campo contraseña vacío")
    public void dejaCampoPasswordVacio() {
        passwordIngresado = "";
    }

    @When("el usuario ingresa cualquier contraseña")
    public void ingresarCualquierPassword() {
        passwordIngresado = "cualquiera";
    }

    @When("el usuario selecciona {string}")
    public void presionaEntrar(String accion) {
        resultado = authService.login(rutIngresado, passwordIngresado);
    }

    @Then("el sistema autentica al usuario correctamente")
    public void verificarAutenticacion() {
        assertTrue(resultado.isExito());
        assertEquals("OK", resultado.getCodigo());
    }

    @Then("el usuario es redirigido a la pantalla principal de {word}")
    public void verificarRedireccion(String rol) {
        assertEquals(rol, resultado.getRol());
    }

    @Then("el sistema no envía el formulario")
    public void verificarNoEnvio() {
        assertFalse(resultado.isExito());
        assertEquals("CAMPOS_VACIOS", resultado.getCodigo());
    }

    @Then("se muestra el mensaje {string}")
    public void verificarMensaje(String msg) {
        String expected = switch (resultado.getCodigo()) {
            case "CAMPOS_VACIOS"        -> "Debe completar los campos obligatorios";
            case "CUENTA_DESHABILITADA" -> "Su cuenta se encuentra deshabilitada, contacte al administrador";
            case "RUT_INVALIDO"         -> "El formato del RUT ingresado no es válido";
            default                     -> "";
        };
        assertEquals(msg, expected);
    }

    @Then("se marcan en rojo los campos vacíos")
    public void verificarCamposMarcados() {
        assertEquals("CAMPOS_VACIOS", resultado.getCodigo());
    }

    @Then("el sistema rechaza el acceso")
    public void verificarRechazo() {
        assertFalse(resultado.isExito());
    }

    @Then("el usuario permanece en el formulario de inicio de sesión")
    public void verificarPermanencia() {
        assertFalse(resultado.isExito());
    }

    @Then("el sistema detecta el formato inválido")
    public void verificarFormatoInvalido() {
        assertEquals("RUT_INVALIDO", resultado.getCodigo());
    }
}
