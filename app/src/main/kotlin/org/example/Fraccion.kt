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

    override fun toString(): String {
        return "$_numerador/$_denominador"
    }

    fun mostrar() {
        println(this.toString())
    }
}