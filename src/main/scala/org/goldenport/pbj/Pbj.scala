package org.goldenport.pbj

import org.goldenport.RAISE
import org.goldenport.i18n.I18NString
import org.goldenport.cli.{Config => CliConfig, _}
import org.goldenport.value._
import org.goldenport.kaleidox.Kaleidox
import org.goldenport.kaleidox.http.HttpHandle
import org.smartdox.service.operations.HtmlOperationClass

/*
 * @since   Jan. 11, 2021
 *  version Feb. 14, 2021
 * @version Mar. 19, 2021
 * @author  ASAMI, Tomoharu
 */
class Pbj(
  config: Config,
  environment: Environment,
  services: Services,
  operations: Operations
) {
  private val _engine = Engine.standard(services, operations)

  def execute(args: Array[String]) = _engine.apply(environment, args)

  def run(args: Array[String]) {
    execute(args)
  }

  def createKaleidox(): HttpHandle = {
    val args = Array[String]()
    val kconfig = org.goldenport.kaleidox.Config.create(environment)
    val kal = new Kaleidox(kconfig, environment)
    val req = spec.Request.empty
    val res = spec.Response()
    val op = spec.Operation("kaleidox", req, res)
    val call = OperationCall.create(op, args)
    kal.http(call)
  }
}

object Pbj {
  case object PbjServiceClass extends ServiceClass {
    val name = "pbj"
    val defaultOperation = None
    val operations = Operations(
      HtmlOperationClass
    )
  }

  def build(args: Array[String]): Pbj = {
    val env0 = Environment.create(args)
    val config = Config.create(env0)
    val context = new Context(env0, config)
    val env = env0.withAppEnvironment(context)
    val services = Services(
      PbjServiceClass
    )
    new Pbj(config, env, services, PbjServiceClass.operations)
  }

  def main(args: Array[String]) {
    val pbj = build(args)
    pbj.run(args)
  }
}
