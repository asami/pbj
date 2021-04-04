package org.goldenport.pbj.web.jetty

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import org.goldenport.pbj.Pbj

/*
 * @since   Mar.  2, 2021
 * @version Mar.  2, 2021
 * @author  ASAMI, Tomoharu
 */
object JettyServer {
  def main(args: Array[String]): Unit = {
    val pbj = Pbj.build(args)
    run(pbj)
  }

  def run(pbj: Pbj): Unit = {
    val server = new Server(8080)
    server.setHandler(new PbjHandler(pbj))
    server.start()
    server.join()
  }
}
