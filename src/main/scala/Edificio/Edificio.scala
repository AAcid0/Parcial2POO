package Edificio
import Salon._
import scala.util._

class Edificio
{
    /*Atributos*/
    //var a = new Salon("salon")
    var _listaSalones : Try[List[Salon]] = Success(List(/*a*/))
    var _horaSistema : Map[Int, Int] = Map()

    /*Getters*/
    def listaSalones = _listaSalones
    def horaSistema = _horaSistema

    /*Setters*/
    def horaSistema_=(newHora : Map[Int, Int]) = _horaSistema = newHora
    
    /*Metodos*/
    def agregarSalon(nombreSalon : String) : Unit =
    {
        var aula : Salon = new Salon(nombreSalon)
        _listaSalones match
        {
            case Success(s) => {
                var je = aula :: s
                //aula :: s
            }
        }
        //_listaSalones = aula :: _listaSalones
    }

    def mostrarSalones() : Try[List[Salon]] =
    {
        Try
        {
            var nuevaLista : Try[List[Salon]] = Success(List())
            _listaSalones match
            {
                case Success(s) => {
                    if(s.nonEmpty)
                    {
                        return Try{s}
                        //nuevaLista = s
                    }
                    else
                    {
                        throw new Exception("La lista de los salones se encuentra vacia")
                    }
                    //return nuevaLista
                }
            }
            
        }
    }

    def consultarSalon(nameSalon : String) : Option[Salon] =
    {
        var aux : Option[Salon] = None
        _listaSalones match
        {
            case Success(s) => {
                if(s.nonEmpty)
                {
                    aux = s.filter(i => i.nomSalon == nameSalon).headOption
                }
                else
                {
                    println("No hay salones.")
                }
                return aux
            }
        }
        
    }

    def realizarReserva(nomSalon : String, hora : Int) : Unit =
    {
        var salonAuxi : Option[Salon] = consultarSalon(nomSalon)
        var mapAuxi = Map(hora -> 00)
        salonAuxi match
        {
            case Some(s) => salonAuxi.get._listaReservas = mapAuxi ++ salonAuxi.get._listaReservas
        } 
    }

}