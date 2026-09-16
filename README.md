# Taller 1 - Motor de Puntuación de Bowling con TDD

## 1. Identificación
* **Nombre completo:** Daniel Camilo Mosquera Martinez
* **Código estudiantil:** 1000099251
* **Correo institucional:** Daniel.mmartinez@mail.escuelaing.edu.co

---

## 2. Descripción del Proyecto
El proyecto digitaliza el sistema de puntuación para **BowlTech S.A.S.**, automatizando el registro de tiros y el cálculo
de bonos de *Spare*, *Strike* y las reglas especiales del *Frame 10*.

### Responsabilidades de las clases
* **`BowlingGame`:** Motor principal del juego. Administra el estado de los 10 frames, valida la entrada de pinos y 
controla el flujo de tiros.
* **`Frame`:** Entidad que almacena los tiros individuales y clasifica el tipo de tiro realizado en ese turno.
* **`FrameType`:** Enumeración con los tipos posibles de frame (`NORMAL`, `SPARE`, `STRIKE`, `TENTH`).
* **`BowlingScorer`:** Servicio sin estado encargado del cálculo matemático del puntaje acumulado y la suma de bonos.

---

## 3. Evidencia del Ciclo TDD
Se aplicó la metodología **RED → GREEN → REFACTOR** para cada uno de los casos requeridos.

### Ciclo de Ejemplo: Caso A2 (`roll(-1)`)
1. **RED:** Se escribió la prueba `rollNegativePins_shouldThrowException` esperando `IllegalArgumentException`. La prueba
falló porque `roll()` aún no tenía validación.
   ![Consola en ROJO](bowling-tdd/docs/evidence/RedTDDA2.png)

2. **GREEN:** Se incluyó el bloque `if (pins < 0)` en `BowlingGame.java` para que la prueba pasara en verde exitosamente.
   ![Consola en VERDE](bowling-tdd/docs/evidence/GreenTDDA2.png)

3. **REFACTOR:** Se extrajo la comprobación a un método auxiliar `validatePins(pins)` para eliminar código repetido sin
romper las pruebas pasadas.
