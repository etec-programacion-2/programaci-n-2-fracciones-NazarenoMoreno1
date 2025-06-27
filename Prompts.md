Pregunta:
Segun este codigo:
class Fraccion(
 private var _numerador: Int,
private var _denominador: Int
)
Necesariamente tiene que haber variables privadas para definir las clases?

Respuesta:
Respuesta directa: NO es necesario usar variables privadas
¿Cuándo usar cada opción?
Opción Cuándo usarlaVentajasDesventajasCampos privadosCuando necesitas control total sobre accesoMáxima encapsulación, flexibilidad futuraMás código, más complejoPropiedades públicasPara casos simples sin validación complejaCódigo más limpio y directoMenos control sobre accesoSetters personalizadosCuando necesitas validación específicaBalance entre simplicidad y controlCódigo intermedioInmutable (val)Cuando los valores no deben cambiarThread-safe, más seguroNo se puede modificar después
¿Por qué alguien usaría campos privados entonces?
Los campos privados se usan cuando necesitas:

Control granular: Decidir exactamente cómo y cuándo se accede a los datos
Validación compleja: Lógica avanzada en getters/setters
Logging o debugging: Registrar cuándo se accede o modifica
Compatibilidad futura: Cambiar implementación sin afectar código cliente
Cálculos derivados: El getter puede calcular valores en tiempo real

Pregunta:
Explicame porque no esta bien escrito mi setter y getter, lo hice de esta forma:
var real: Double
        get() = _real
        set(value) {
            _real = value
        }
Me inspire en este código

Respuesta:
Perfecto! Ahora la clase Fraccion está estructurada exactamente como el ejemplo que mostraste:
Estructura final:
class Fraccion(
    private var _numerador: Int,
    private var _denominador: Int
) {
    
    var numerador: Int
        get() = _numerador
        set(value) {
            _numerador = value
        }
    
    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            _denominador = value
        }
}

Características:

Parámetros privados: _numerador y _denominador son campos privados en el constructor
Propiedades públicas: numerador y denominador son las interfaces públicas
Getters: Devuelven los valores de los campos privados (_numerador, _denominador)
Setters: Asignan valores a los campos privados con validación en el denominador
Encapsulación: Los campos reales están ocultos, solo se accede a través de las propiedades públicas

¡Ahora sigue exactamente el mismo patrón que tu ejemplo de la clase Complejo!


Estoy trabajando en kotlin y tenía que hacer esto:
```kotlin
 var numerador: Int = 0
get() = field
set(value) { field = value }
var denominador: Int = 1
get() = field
set(value) {
if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
field = value
 }
Pero hice esto:
var numerador: Int
 get() = _numerador
set(value) {
 _numerador = value
 }
var denominador: Int
get() = _denominador
set(value) {
if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
 _denominador = value
 }
Está bien lo que hice? Que es el field?

Tu implementación con las propiedades backing (_numerador y _denominador) funciona perfectamente y es una alternativa válida. De hecho, ambos enfoques son correctos, pero tienen diferencias importantes:
¿Qué es field?
field es una palabra clave especial en Kotlin que se refiere al backing field (campo de respaldo) de una propiedad. Es una referencia al almacenamiento interno real de la propiedad.
Diferencias entre los dos enfoques:
Enfoque original (con field):
var numerador: Int = 0
    get() = field
    set(value) { field = value }
Kotlin automáticamente crea un backing field interno
field se refiere a ese almacenamiento interno
Es más conciso
El backing field es privado y no es accesible desde fuera
Tu enfoque (con backing properties):
private var _numerador: Int = 0
var numerador: Int
    get() = _numerador
    set(value) { _numerador = value }
Tú creates explícitamente las propiedades backing (_numerador, _denominador)
Tienes control total sobre estas propiedades
Es más explícito sobre lo que está pasando
Puedes acceder a las backing properties si las haces públicas (aunque no es recomendable)
¿Cuál es mejor?
Para tu caso específico, el enfoque original con field es más apropiado porque:

Es más simple y directo
Menos código
Es la forma idiomática en Kotlin para custom getters/setters simples
Kotlin maneja automáticamente el backing field

Tu enfoque es útil cuando necesitas:

Lógica más compleja en los accessors
Múltiples backing properties para una sola propiedad pública
Casos donde necesitas acceso directo a la backing property desde otros métodos de la clase

En resumen: tu código funciona correctamente, pero para este caso el enfoque con field es más elegante y conciso.