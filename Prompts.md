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
