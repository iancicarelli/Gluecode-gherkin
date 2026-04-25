## 1. Descripción general
Este proyecto implementa un ciclo BDD (Behavior-Driven Development) completo para el caso de uso CU-01 del sistema EasyCheck. Los escenarios Gherkin ya definidos por el equipo se ejecutan contra la lógica de dominio en Java, sin necesidad de backend, siguiendo el ciclo: escenario escrito → falla → glue code → implementación → verde.
## 2. Ciclo BDD — fases recorridas
El desarrollo siguió el ciclo completo de BDD en este orden:
- Escenario escrito: se redactó LoginStep.feature con los 5 escenarios en Gherkin.
- Paso no implementado (ROJO): al ejecutar TestRunner sin glue code ni dominio, Cucumber lanzó UndefinedStepException en todos los pasos.
- Glue code desarrollado: se implementó LoginStep.java con todos los @Given, @When y @Then vinculados a los pasos del .feature.
- Implementación de la funcionalidad (mínima): se crearon Usuario.java, ResultadoLogin.java y AuthService.java con la lógica suficiente para que los escenarios pasen.
- Escenario ejecutándose correctamente (VERDE): 5 scenarios passed, 28 steps passed.
