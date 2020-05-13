package Edificio

import java.util.Calendar
import java.text.SimpleDateFormat
import scala.io._
import scala.util._
import Salon._


object Interfaz extends App
{
    var smartEdi : Edificio = new Edificio
    var cerrarSistema : Boolean = false
    
    /*Tiempo*/
    var hoy = Calendar.getInstance().getTime()
    var fM = new SimpleDateFormat("mm") //fM = formato minuto
    var fH = new SimpleDateFormat("hh") //fH = formato hora
    var mA = fM.format(hoy) //mA = minuto actual
    var hA = fH.format(hoy) //hA = hora actual
    var horaActual = Map(hA.toInt -> mA.toInt)
    var tempIdeal = 23

    while(!cerrarSistema)
    {
        smartEdi.horaSistema = horaActual
        var tuplaHora = horaActual.head
        /**/
        println("\nBienvenido a Smart Building")
        println("Hora del Sistema: " + tuplaHora._1 + " : " + tuplaHora._2)
        println("===========================")
        println("\n1-> Agregar nuevo salon.\n2-> Mostrar salones.\n3-> Consultar Salon.\n4-> Reservar salon.")
        println("5-> Ajustes del sistema\n6-> Salir.")
        println("\nSu opcion: ")
        var opc : Int = StdIn.readInt()
        
        opc match
        {
            case 1 => {
                println("Agregar Nuevo\n==============")
                println("Ingrese nombre de Nuevo salon: ")
                var nam : String = StdIn.readLine()
                smartEdi.agregarSalon(nam)
            }

            case 2 => {
                println("\nSalones En El Edificio")
                println("===========================")
                var salones : Try[List[Salon]] = smartEdi.mostrarSalones()
                salones match
                {
                    case Success(s) => {
                        s.foreach(i => {
                            println("==================\nNombre: " + i.nomSalon + "\nDisponible: " + i.disponible + 
                            "\nReservas: " + i.listaReservas.keySet + "\n==================")
                        })
                    }
                    case Failure(f) => println(f)
                }
            }

            case 3 => {
                println("\nConsultar Salon Especifico")
                println("===========================")
                println("Ingrese nombre del salon a consultar")
                var nom : String = StdIn.readLine()
                var salon : Option[Salon] = smartEdi.consultarSalon(nom)
                salon match
                {
                    case Some(s) => {
                        //si en listaReservas hay una reserva para la siguiente hora de la actual y el salon esta disponible
                        var aux = horaActual.head
                        var horaNext = (aux._1) + 1
                        var horaAnt = (aux._1) - 1
                        /*verificar si existe una reserva a la hora inmediatamente siguiente a la actual*/
                        if(salon.get.listaReservas.contains(horaNext) && salon.get.disponible == true)
                        {
                            if(aux._2 >= 55) //requerimiento 1, parte 1
                            {
                                salon.get.luzOn = true

                            }
                            if(aux._2 >= 50) //requerimiento 4, parte 1
                            {
                                salon.get.acOn = true
                                salon.get.temperatura = tempIdeal                               
                            }
                            if(aux._2 >= 45) //requerimiento 8
                            {
                                salon.get.disponible = false
                                salon.get.seguroPuerta = false
                            }                           
                            mostarInfo(salon)
                        }
                        //si en listaReservas habia una reserva para la hora anterior a la actual y disponible = false
                        //osea, si habia una clase en curso
                        else if(salon.get.listaReservas.contains(horaAnt) && salon.get.disponible == false)
                        {
                            if(aux._2 >= 10) //requerimiento 1, parte 2
                            {
                                salon.get.luzOn = false
                                salon.get.disponible = true
                                salon.get.seguroPuerta = true

                            }
                            if(aux._2 >= 5) //requerimiento 4, parte 2
                            {
                                salon.get.acOn = false
                                salon.get.temperatura = 30
                            }
                            mostarInfo(salon)
                        }  
                        else
                        {
                            mostarInfo(salon)
                        }
                    }
                }
            }

            case 4 => {
                println("\nReservar Salon")
                println("===========================")
                println("Nombre salon a reservar: ")
                var name : String = StdIn.readLine()
                println("Hora a reservar: ")
                var hora : Int = StdIn.readInt()
                smartEdi.realizarReserva(name, hora)
                println("Salon reservado a la hora acordada.") /* verificar que no haya reserva previa a la misma hora*/
            }

            case 5 => { //requerimiento 5
                println("\nBienvenido a Smart Building")
                println("Hora del Sistema: " + tuplaHora._1 + " : " + tuplaHora._2)
                println("===========================")
                println("1-> Cambiar Hora\n2-> Cambiar clima ideal")
                var opcAjus : Int = StdIn.readInt()
                opcAjus match
                {
                    case 1 => {
                        println("\nCambiar Hora Del Sistema")
                        println("===========================")
                        println("Nueva Hora: ")
                        var a : Int = StdIn.readInt()
                        println("Nuevo Minuto: ")
                        var b : Int = StdIn.readInt()
                        var c = Map(a -> b)
                        horaActual = c
                        println("Hora actualizada con exito.\n")

                    }

                    case 2 => {
                        println("\nCambiar Temperatura A.C")
                        println("===========================")
                        println("Ingrese temperatura ideal: ")
                        var newTemp = StdIn.readInt()
                        tempIdeal = newTemp
                        ("Temperatura ideal actualizada.")
                    }

                }

            }
        }
        
    }
    def mostarInfo(salon : Option[Salon]) : Unit =
    {
        salon match
        {
            case Some(s) => {
                println("\nNombre: " + salon.get.nomSalon + "\nDisponible: " + salon.get.disponible +
                "\nTemperatura: " + salon.get.temperatura + " °C" + "\nReservas: " + salon.get.listaReservas +
                "\n¿Esta cerrado?: " + salon.get.seguroPuerta + "\n¿Luz encendida?: " + salon.get.luzOn)
            }
        }
    }
}