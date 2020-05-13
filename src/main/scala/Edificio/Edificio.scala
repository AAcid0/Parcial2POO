package Edificio
import Salon._
import scala.util._

class Edificio
{
    /*Atributos*/
    var _listaSalones : List[Salon] = List()
    var _horaSistema : Map[Int, Int] = Map()

    /*Getters*/
    def listaSalones = _listaSalones
    def horaSistema = _horaSistema

    /*Setters*/
    def horaSistema_=(newHora : Map[Int, Int]) = _horaSistema = newHora
    
    /*Metodos*/
    def agregarSalon(nombreSalon : String) : Try[Unit] =
    {
        Try
        {
            var aula : Salon = new Salon(nombreSalon)
            if(nombreSalon != "")
            {
                _listaSalones = aula :: _listaSalones
            }
            else
            {
                throw new Exception("No se ingreso un nombre valido para el salon")
            }
        }
    }

    def comprobarSalones() : Try[Unit] =
    {
        Try
        {
            if(_listaSalones.isEmpty)
            {
                throw new Exception("La lista de los salones se encuentra vacia")
            }
        }
    }

    def mostrarSalones() : List[Salon] =
    {
        return _listaSalones
    }

    def consultarSalon(nameSalon : String) : Option[Salon] =
    {
        var aux : Option[Salon] = None
        if(_listaSalones.nonEmpty)
        {
            aux = _listaSalones.filter(i => i.nomSalon == nameSalon).headOption
        }
        else
        {
            println("No hay salones.")
        }
        return aux
        
    }

    def realizarReserva(nomSalon : String, hora : Int) : Unit =
    {
        var salonAuxi : Option[Salon] = consultarSalon(nomSalon)
        var mapAuxi = Map(hora -> 00)
        salonAuxi match
        {
            case Some(s) => salonAuxi.get._listaReservas = mapAuxi ++ salonAuxi.get._listaReservas
            case None => println("El salon no existe.")
        } 
    }

    def verificarSalon(nombre : String) : Try[Unit] =
    {
        Try
        {
            if(nombre == "")
            {
                throw new Exception("No se ingresó un nombre de salon valido")
            }
            var prueba = _listaSalones.filter(i => i.nomSalon == nombre)
            if(prueba.isEmpty)
            {
                throw new Exception("El salon ingresado no se encuentra registrado.")
            }
        }
    }

}