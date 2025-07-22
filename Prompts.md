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


segun este codigo de kotlin:

operator fun suma(otra: Fraccion): Fraccion {

val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador

val denominador1 = this._denominador * otra._denominador

return Fraccion(numerador1, denominador1)

}

Dime como funcion el this y otra para saber cual es cual ya que tengo que cumplir la formula (a/b) + (c/d) = (a*d + b*c)/(b*d) 


 segun este codigo de kotlin:

operator fun suma(otra: Fraccion): Fraccion {

val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador

val denominador1 = this._denominador * otra._denominador

return Fraccion(numerador1, denominador1)

}

Dime como funcion el this y otra para saber cual es cual ya que tengo que cumplir la formula (a/b) + (c/d) = (a*d + b*c)/(b*d)

En el código Kotlin que proporcionaste, la función suma es una función de extensión o un método de una clase Fraccion. Para entender cómo funcionan this y otra, es fundamental comprender el contexto en el que se llama a esta función.

Asumamos que la función suma está definida dentro de una clase Fraccion de la siguiente manera:
Kotlin

class Fraccion(private val _numerador: Int, private val _denominador: Int) {

    operator fun suma(otra: Fraccion): Fraccion {
        val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador
        val denominador1 = this._denominador * otra._denominador
        return Fraccion(numerador1, denominador1)
    }

    // Otras funciones o propiedades de la clase Fraccion
}

Ahora, veamos cómo se utilizan this y otra en la operación de suma de fracciones (a/b)+(c/d)=(acdotd+bcdotc)/(bcdotd):

    this:

        Representa la instancia actual de la clase Fraccion sobre la cual se está llamando al método suma.

        En términos de la fórmula (a/b)+(c/d), this se refiere a la primera fracción (a/b).

        Por lo tanto:

            this._numerador es el a de la primera fracción.

            this._denominador es el b de la primera fracción.

    otra:

        Es el parámetro que se pasa a la función suma. Representa la otra instancia de la clase Fraccion que se va a sumar a la fracción actual.

        En términos de la fórmula (a/b)+(c/d), otra se refiere a la segunda fracción (c/d).

        Por lo tanto:

            otra._numerador es el c de la segunda fracción.

            otra._denominador es el d de la segunda fracción.

Ejemplo Práctico:

Imagina que tienes dos objetos Fraccion:
Kotlin

val fraccion1 = Fraccion(1, 2) // Representa 1/2
val fraccion2 = Fraccion(3, 4) // Representa 3/4

Cuando llamas a la función suma de la siguiente manera:
Kotlin

val resultado = fraccion1.suma(fraccion2)

Dentro de la ejecución de fraccion1.suma(fraccion2):

    this será fraccion1 (es decir, el objeto Fraccion(1, 2)).

    otra será fraccion2 (es decir, el objeto Fraccion(3, 4)).

Entonces, la línea:
Kotlin

val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador

Se traduciría como:

val numerador1 = (1 * 4) + (2 * 3)
val numerador1 = 4 + 6
val numerador1 = 10

Y la línea:
Kotlin

val denominador1 = this._denominador * otra._denominador

Se traduciría como:

val denominador1 = 2 * 4
val denominador1 = 8

Finalmente, la función devolverá Fraccion(10, 8), que es el resultado de sumar 1/2+3/4.

En resumen, this se refiere al objeto sobre el que se invoca el método, mientras que otra es el objeto que se pasa como argumento al método.


Mi fraccion.kt es esto:
package org.example
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
if (_denominador < 0) {
 numerador = -numerador
 denominador = -denominador
 }
override fun toString(): String {
return "$_numerador/$_denominador"
 }
fun mostrar() {
println(this.toString())
 }
operator fun plus(otra: Fraccion): Fraccion {
val numerador1 = this._numerador * otra._denominador + this._denominador * otra._numerador
val denominador1 = this._denominador * otra._denominador
return Fraccion(numerador1, denominador1)
 }
operator fun minus(otra: Fraccion): Fraccion {
val numerador1 = this._numerador * otra._denominador - this._denominador * otra._numerador
val denominador1 = this._denominador * otra._denominador
return Fraccion(numerador1, denominador1)
 }
}
Mi App.kt es esto:
/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example
fun main() {
val fraccion1 = Fraccion(3, 4)
val fraccion2 = Fraccion(1, 3)
print("Fracción 1: ")
 fraccion1.mostrar()
val resultado = fraccion1 + fraccion2
print("La suma de (3/4 + 1/3): ")
 resultado.mostrar()
val resultadoResta = fraccion1 - fraccion2
print("La resta de (3/4 - 1/3): ")
 resultadoResta.mostrar()
}
Porque no anda


El problema está en tu código de Fraccion.kt. Hay varios errores de sintaxis:
Errores encontrados:

Sintaxis incorrecta para normalizar signos: El código está fuera de cualquier función/bloque
Uso incorrecto de asteriscos: *numerador y *denominador no es sintaxis válida en Kotlin

Código corregido:


Los cambios principales:

Agregué el bloque init: Para las validaciones y normalización de signos
Corregí la sintaxis:

*numerador = -*numerador → _numerador = -_numerador
*denominador = -*denominador → _denominador = -_denominador


Moví la lógica al lugar correcto: Dentro del bloque init en lugar de estar suelta en la clase

Tu App.kt está bien, la salida será:



El problema era que tenías código de inicialización fuera de cualquier función o bloque, y la sintaxis con asteriscos no es válida en Kotlin. Con estos cambios debería funcionar correctamente.


Pregunta:
Tengo un problema con el init de mi código dentro del fraccion.kt, 

Rta:
Hay varias formas, pero la más clara es evitar crear otra instancia dentro de simplificar(). En vez de eso, simplemente modificá los valores actuales:
🔧 SOLUCIÓN: Cambiá el init así:

init {
    if (_denominador == 0) {
        throw IllegalArgumentException("El denominador no puede ser cero")
    }

    if (_denominador < 0) {
        _numerador = -_numerador
        _denominador = -_denominador
    }

    val divisorComun = mcd(_numerador, _denominador)
    _numerador /= divisorComun
    _denominador /= divisorComun
}

Y eliminá completamente el método simplificar() si ya no lo vas a usar en otro lado, o dejalo privado si lo necesitas más adelante.
