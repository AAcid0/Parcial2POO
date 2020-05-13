package Salon

class Salon
{
    /*Atributos*/
    var _nomSalon : String = _
    var _luzOn : Boolean = false
    var _acOn : Boolean = false
    var _temperatura : Double = 30
    var _needRefrigerio : Boolean = false
    var _seguroPuerta : Boolean = true
    var _disponible : Boolean = true
    var _listaReservas : Map[Int, Int] = Map()

    /*Constructor auxiliar*/
    def this(n : String)
    {
        this()
        _nomSalon = n
    }

    /*Getters*/
    def nomSalon =_nomSalon
    def luzOn = _luzOn
    def acOn = _acOn
    def temperatura = _temperatura
    def needRefrigerio = _needRefrigerio
    def seguroPuerta = _seguroPuerta
    def disponible = _disponible
    def listaReservas = _listaReservas

    /*Setters*/
    def nomSalon_=(newNombre : String) = _nomSalon = newNombre
    def luzOn_=(newEstado : Boolean) = _luzOn = newEstado
    def acOn_=(newEstado1 : Boolean) = _acOn = newEstado1
    def temperatura_=(newClima : Double) = _temperatura = newClima
    def needRefrigerio_=(newSoli : Boolean) = _needRefrigerio = newSoli
    def seguroPuerta_=(newSeguro : Boolean) = _seguroPuerta = newSeguro
    def disponible_=(disponibilidad : Boolean) = _disponible = disponibilidad
    
}